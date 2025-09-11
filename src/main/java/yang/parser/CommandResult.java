package yang.parser;

import yang.task.Task;

/**
 * Immutable result of executing a user command.
 * <p>
 * Use the static factory methods (e.g., {@link #added(Task)}, {@link #list()})
 * to create specific kinds of results.
 * </p>
 */
public class CommandResult {

    /**
     * Kinds of command outcomes that the UI can render.
     */
    public enum Type {
        NONE, ADDED, DELETED, LIST, MARKED, UNMARKED, FIND
    }

    /** The outcome type. */
    public final Type type;

    /** The task relevant to this result, if any (e.g., added/marked/deleted). */
    public final Task task;

    /** The keyword used for a {@code FIND} result; {@code null} otherwise. */
    public final String keyword;

    /**
     * Constructor for {@code CommandResult}.
     *
     * @param type    the result type (e.g., {@link Type#ADDED}, {@link Type#LIST})
     * @param task    the task associated with the result, or {@code null} if not applicable
     * @param keyword the keyword for {@link Type#FIND} results, or {@code null} otherwise
     */
    private CommandResult(Type type, Task task, String keyword) {
        this.type = type;
        this.task = task;
        this.keyword = keyword;
    }

    private CommandResult(Type type, Task task) {
        this(type, task, null);
    }

    public static CommandResult list() {
        return new CommandResult(Type.LIST, null);
    }

    public static CommandResult added(Task t) {
        return new CommandResult(Type.ADDED, t);
    }

    public static CommandResult deleted(Task t) {
        return new CommandResult(Type.DELETED, t);
    }

    public static CommandResult marked(Task t) {
        return new CommandResult(Type.MARKED, t);
    }

    public static CommandResult unmarked(Task t) {
        return new CommandResult(Type.UNMARKED, t);
    }
}
