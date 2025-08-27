// yang/task/Event.java
package yang.task;

public final class Event extends Task {
    private final String at;

    public Event(String description, String at) { super(description, false); this.at = at; }
    public Event(String description, String at, boolean isDone) { super(description, isDone); this.at = at; }

    @Override public String toString() { return "[E][" + (isDone ? "X" : " ") + "] " + description + " (at: " + at + ")"; }
    @Override public String toStorage() { return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + at; }
}
