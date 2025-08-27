package yang.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Event extends Task {
    private final LocalDate at;
    private static final DateTimeFormatter OUT = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Event(String description, LocalDate at) {
        super(description, false);
        this.at = at;
    }

    public Event(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (at: " + at.format(OUT) + ")";
    }

    @Override
    public String toStorage() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + at;
    }
}
