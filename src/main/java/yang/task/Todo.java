// yang/task/Todo.java
package yang.task;

public final class Todo extends Task {
    public Todo(String description) { super(description, false); }
    public Todo(String description, boolean isDone) { super(description, isDone); }

    @Override public String toString() { return "[T][" + (isDone ? "X" : " ") + "] " + description; }
    @Override public String toStorage() { return "T | " + (isDone ? "1" : "0") + " | " + description; }
}
