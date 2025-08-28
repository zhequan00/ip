package yang.task;

/**
 * Base abstraction for a user task with a textual description and a completion flag.
 * <p>
 * Subclasses (e.g., {@link Todo}, {@link Deadline}, {@link Event}) provide concrete
 * storage formats and rendering. Each task supports being marked done/undone and
 * serialized/deserialized to a compact pipe-delimited format for persistence.
 * </p>
 */
public abstract class Task {
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

    /**
     * Marks this task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

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

    public static Task fromStorage(String line) {
        String[] p = line.split("\\s*\\|\\s*", 4);
        if (p.length < 3) return null;
        boolean done = "1".equals(p[1]);
        String desc = p[2];

        switch (p[0]) {
        case "T":
            return new Todo(desc, done);
        case "D":
            return (p.length >= 4) ? new Deadline(desc, java.time.LocalDate.parse(p[3]), done) : null;
        case "E":
            return (p.length >= 4) ? new Event(desc, java.time.LocalDate.parse(p[3]), done) : null;
        default:
            return null;
        }
    }
}
