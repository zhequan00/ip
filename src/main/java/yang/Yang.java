package yang;

import yang.storage.Storage;
import yang.task.TaskList;
import yang.ui.Ui;
import yang.parser.Parser;

/**
 * The Yang class represents the main entry point for the task manager bot.
 * It processes user input, manages tasks, and handles command execution.
 */
public class Yang {
    private final Storage storage = Storage.defaultStorage();
    private final Ui ui = new Ui();
    private TaskList tasks = new TaskList();

/**
 * Starts the interactive loop (REPL) for the Yang bot.
 * <ul>
 *   <li>Attempts to load previously saved tasks from {@link Storage}.</li>
 */
    public void run() {
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception ignored) {}
        ui.greet();

        while (true) {
            String cmd = ui.readCommand();
            if (cmd.equals("bye")) { ui.bye(); break; }

            try {
                var result = Parser.apply(cmd, tasks);

                switch (result.type) {
                    case ADDED -> ui.showAdded(result.task, tasks.size());
                    case DELETED -> ui.showDeleted(result.task, tasks.size());
                    case LIST -> ui.showList(tasks);
                    case MARKED -> ui.showMarked(result.task);
                    case UNMARKED -> ui.showUnmarked(result.task);
                    default -> {}
                }

                storage.save(tasks.asList());
            } catch (YangException e) {
                ui.show(e.getMessage());
            } catch (Exception ignored) { }
        }
    }

/**
 * Program entry point. Creates and runs a {@link Yang} instance.
 */
    public static void main(String[] args) {
        new Yang().run();
    }
}


