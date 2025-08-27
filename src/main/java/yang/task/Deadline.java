package yang.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public final class Deadline extends Task {
    private final LocalDate by;
    private static final DateTimeFormatter OUT = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     *
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDate by) {
        super(description, false);
        this.by = by;
    }

    /**
     *
     * @param description
     * @param by
     * @param isDone
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " + by.format(OUT) + ")";
    }

    @Override
    public String toStorage() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
