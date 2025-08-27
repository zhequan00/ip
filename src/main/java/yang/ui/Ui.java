package yang.ui;

import java.util.Scanner;
import yang.task.Task;
import yang.task.TaskList;

/**
 * Handles all user-facing input and output for the Yang bot.
 * <p>
 * The {@code Ui} class provides methods to:
 * <ul>
 *   <li>Display greetings, prompts, and farewell messages</li>
 *   <li>Read user commands from standard input</li>
 *   <li>Show feedback when tasks are added, removed, marked, or unmarked</li>
 *   <li>Display the current task list or error messages</li>
 * </ul>
 * This class does not contain business logic—it only manages console I/O.
 * </p>
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints the initial greeting message to the user.
     */
    public void greet() {
        System.out.println("Good day! I'm Yang your favourite bot assistant\nWhat can I do for you?");
    }

    /**
     * Reads the next user command from standard input.
     *
     * @return the trimmed input line entered by the user
     */
    public String readCommand() { return sc.nextLine().trim(); }

    /**
     * Shows confirmation that a task has been added, along with the current task count.
     *
     * @param t     the task that was added
     * @param count the new total number of tasks
     */
    public void showAdded(Task t, int count) {
        System.out.println("Perfect! I've added this task:\n  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    /**
     * Displays all tasks currently in the task list.
     *
     * @param tl the {@link TaskList} to display
     */
    public void showList(TaskList tl) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tl.size(); i++) System.out.println((i+1) + ". " + tl.get(i));
    }

    /**
     * Shows confirmation that a task has been deleted, along with the current task count.
     *
     * @param t     the task that was removed
     * @param count the new total number of tasks
     */
    public void showDeleted(Task t, int count) {
        System.out.println("Understood. I've removed this task:\n  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    /**
     * Shows confirmation that a task has been marked as done.
     *
     * @param t the task that was marked
     */
    public void showMarked(Task t) {
        System.out.println("Okay! I've marked this task as done:");
        System.out.println("  " + t);
    }

    /**
     * Shows confirmation that a task has been marked as not done.
     *
     * @param t the task that was unmarked
     */
    public void showUnmarked(Task t) {
        System.out.println("Will do, I've marked this task as not done yet:");
        System.out.println("  " + t);
    }

    /**
     * Prints a message directly to the user.
     *
     * @param s the message to display
     */
    public void show(String s) {
        System.out.println(s);
    }

    /**
     * Prints the farewell message to the user.
     */
    public void bye() {
        System.out.println("Bye bye! Hope to see you again soon!");
    }
}

