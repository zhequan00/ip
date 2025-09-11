package yang.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> initial) {
        this.tasks = new ArrayList<>(initial);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int idx) {
        assert idx >= 0 && idx < tasks.size() : "index out of bounds";
        return tasks.get(idx);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task remove(int idx) {
        return tasks.remove(idx);
    }

    public void mark(int idx) {
        assert idx >= 0 && idx < tasks.size() : "index out of bounds";
        tasks.get(idx).markDone();
    }

    public void unmark(int idx) {
        assert idx >= 0 && idx < tasks.size() : "index out of bounds";
        tasks.get(idx).markUndone();
    }

    public List<Task> asList() {
        return tasks;
    }
}
