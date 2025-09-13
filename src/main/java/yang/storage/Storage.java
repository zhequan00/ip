package yang.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import yang.task.Task;

public final class Storage {
    private final Path file;

    public Storage(Path file) {
        this.file = file;
    }

    public static Storage defaultStorage() {
        return new Storage(Paths.get("data", "yang.txt"));
    }

    public List<Task> load() throws IOException {
        assert file != null : "storage path must be set";
        if (Files.notExists(file)) {
            Files.createDirectories(file.getParent());
            Files.createFile(file);
            return new ArrayList<>();
        }

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        List<Task> out = new ArrayList<>();
        for (String ln : lines) {
            String s = ln.trim();
            if (!s.isEmpty()) {
                Task t = Task.fromStorage(s);
                if (t != null) {
                    out.add(t);
                }
            }
        }
        return out;
    }

    public void save(List<Task> tasks) throws IOException {
        assert tasks != null : "tasks to save must not be null";
        Files.createDirectories(file.getParent());
        List<String> lines = new ArrayList<>(tasks.size());
        for (Task t : tasks) {
            lines.add(t.toStorage());
        }
        Files.write(file, lines, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
