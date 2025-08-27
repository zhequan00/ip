// yang/task/Deadline.java
package yang.task;

public final class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) { super(description, false); this.by = by; }
    public Deadline(String description, String by, boolean isDone) { super(description, isDone); this.by = by; }

    @Override public String toString() { return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " + by + ")"; }
    @Override public String toStorage() { return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by; }
}
