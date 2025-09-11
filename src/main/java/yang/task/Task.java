package yang.task;

import java.time.LocalDate;

/**
 * Base abstraction for a user task with a textual description and a completion flag.
 * <p>
 * Subclasses (e.g., {@link Todo}, {@link Deadline}, {@link Event}) provide concrete
 * storage formats and rendering. Each task supports being marked done/undone and
 * serialized/deserialized to a compact pipe-delimited format for persistence.
 * </p>
 */
public abstract class Task {

    private static final String TASK_TODO = "T";
    private static final String TASK_DEADLINE = "D";
    private static final String TASK_EVENT = "E";
    private static final String DONE_FLAG = "1";

    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a task.
     *
     * @param description the task description text
     * @param isDone      initial completion state
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /** Marks this task as completed. */
    public void markDone() {
        this.isDone = true;
    }

    /** Marks this task as not completed. */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns whether this task has been completed.
     *
     * @return {@code true} if completed; {@code false} otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Serializes this task to a compact pipe-delimited line suitable for storage.
     * <p>Example formats:</p>
     * <ul>
     *   <li>{@code T|0|buy milk}</li>
     *   <li>{@code D|1|submit report|2025-08-30}</li>
     *   <li>{@code E|0|conference|2025-09-12}</li>
     * </ul>
     *
     * @return a single-line string representation for persistence
     */
    public abstract String toStorage();

    /**
     * Reconstructs a {@link Task} from a storage line.
     *
     * @param line the serialized line
     * @return the corresponding {@link Task}, or {@code null} if malformed
     */
    public static Task fromStorage(String line) {
        String[] parts = line.split("\\s*\\|\\s*", 4);
        if (parts.length < 3) {
            return null;
        }

        boolean isDone = DONE_FLAG.equals(parts[1]);
        String description = parts[2];

        switch (parts[0]) {
        case TASK_TODO:
            return new Todo(description, isDone);
        case TASK_DEADLINE:
            return (parts.length >= 4)
                    ? new Deadline(description, LocalDate.parse(parts[3]), isDone)
                    : null;
        case TASK_EVENT:
            return (parts.length >= 4)
                    ? new Event(description, LocalDate.parse(parts[3]), isDone)
                    : null;
        default:
            return null;
        }
    }
}
