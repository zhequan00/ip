package yang.task;

public abstract class Task {
    protected final String description;
    protected boolean isDone;

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }
    public void markUndone() {
        this.isDone = false;
    }
    public boolean isDone() {
        return isDone;
    }

    public abstract String toStorage();

    public static Task fromStorage(String line) {
        String[] p = line.split("\\s*\\|\\s*", 4);
        if (p.length < 3) return null;
        boolean done = "1".equals(p[1]);
        String desc = p[2];

        switch (p[0]) {
            case "T": return new Todo(desc, done);
            case "D": return (p.length >= 4) ? new Deadline(desc, java.time.LocalDate.parse(p[3]), done) : null;
            case "E": return (p.length >= 4) ? new Event(desc, java.time.LocalDate.parse(p[3]), done) : null;
            default:  return null;
        }
    }
}
