package yang.parser;

import yang.task.Task;

public class CommandResult {
    public enum Type {
        NONE, ADDED, DELETED, LIST, MARKED, UNMARKED, FIND
    }

    public final Type type;
    public final Task task;
    public final String keyword;

    /**
     * Contrusctor for CommandResult
     *
     * @param type
     * @param task
     * @param keyword
     */
    private CommandResult(Type type, Task task, String keyword) {
        this.type = type;
        this.task = task;
        this.keyword = keyword;
    }

    private CommandResult(Type type, Task task) {
        this(type, task, null);
    }

    public static CommandResult none() {
        return new CommandResult(Type.NONE, null);
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

    public static CommandResult find(String keyword) {
        return new CommandResult(Type.FIND, null, keyword);
    }
}
