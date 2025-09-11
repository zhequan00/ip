package yang;

import yang.parser.Parser;
import yang.storage.Storage;
import yang.task.TaskList;
import yang.ui.Ui;

/**
 * The Yang class represents the main entry point for the task manager bot.
 * It processes user input, manages tasks, and handles command execution.
 */
public class Yang {

    private static final String COMMAND_EXIT = "bye";

    private final Storage storage = Storage.defaultStorage();
    private final Ui ui = new Ui();
    private TaskList tasks = new TaskList();

    /** Starts the interactive loop for the Yang bot. */
    public void run() {
        loadTasksIfAny();
        ui.greet();

        while (true) {
            String input = ui.readCommand();
            if (isExit(input)) {
                ui.bye();
                break;
            }
            try {
                process(input);
            } catch (IllegalArgumentException e) {
                ui.show(e.getMessage());
            }
        }
    }

    private void loadTasksIfAny() {
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception ignored) {
        }
    }

    private boolean isExit(String input) {
        return input != null && COMMAND_EXIT.equalsIgnoreCase(input.trim());
    }

    private void process(String input) {
        try {
            var result = Parser.apply(input, tasks);
            switch (result.type) {
            case ADDED -> ui.showAdded(result.task, tasks.size());
            case DELETED -> ui.showDeleted(result.task, tasks.size());
            case LIST -> ui.showList(tasks);
            case MARKED -> ui.showMarked(result.task);
            case UNMARKED -> ui.showUnmarked(result.task);
            case FIND -> ui.showFound(tasks, result.keyword);
            default -> { }
            }
            storage.save(tasks.asList());
        } catch (YangException e) {
            ui.show(e.getMessage());
        } catch (Exception ignored) {
        }
    }

    public String getResponse(String input) {
        return "Yang heard: " + input;
    }

    /** Program entry point. */
    public static void main(String[] args) {
        new Yang().run();
    }
}
