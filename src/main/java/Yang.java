import java.util.ArrayList;
import java.util.Scanner;

public class Yang {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greetUser();

        while (true) {
            String input = sc.nextLine().trim();
            try {
                if (input.equals("bye")) {
                    exitBot();
                    break;
                } else if (input.equals("list")) {
                    printList();
                } else if (input.startsWith("mark")) {
                    handleMark(input);
                } else if (input.startsWith("unmark")) {
                    handleUnmark(input);
                } else if (input.startsWith("todo")) {
                    handleTodo(input);
                } else if (input.startsWith("deadline")) {
                    handleDeadline(input);
                } else if (input.startsWith("event")) {
                    handleEvent(input);
                } else {
                    throw new YangException("Apologies!! But I'm not sure what that means :(");
                }
            } catch (YangException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    private static void greetUser() {
        System.out.println("Hello! I'm Yang, your favourite chatbot assistant!");
        System.out.println("What can I help you with?");
        System.out.println("___________________________________________________");
    }

    private static void exitBot() {
        System.out.println("Bye bye!! Hope to see you again soon!");
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void handleMark(String input) throws YangException {
        int idx = Integer.parseInt(input.substring(5)) - 1;
        if (idx < 0 || idx >= tasks.size()) {
            throw new YangException("Invalid task number for mark.");
        }
        Task t = tasks.get(idx);
        t.markAsDone();
        System.out.println("Perfect! This task is now marked as done:");
        System.out.println("  " + t);
    }

    private static void handleUnmark(String input) throws YangException {
        int idx = Integer.parseInt(input.substring(7)) - 1;
        if (idx < 0 || idx >= tasks.size()) {
            throw new YangException("Invalid task number for unmark.");
        }
        Task t = tasks.get(idx);
        t.markAsUndone();
        System.out.println("Alright, I've marked this task as not done yet:");
        System.out.println("  " + t);
    }

    private static void handleTodo(String input) throws YangException {
        String desc = input.substring(4).trim();
        if (desc.isEmpty()) {
            throw new YangException("Woops! The description of a todo must have a task!!");
        }
        Task t = new Todo(desc);
        tasks.add(t);
        printTaskAdded(t);
    }

    private static void handleDeadline(String input) throws YangException {
        String[] parts = input.substring(8).trim().split("/by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new YangException("Deadline format should be: deadline <task> /by <time>");
        }
        Task t = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.add(t);
        printTaskAdded(t);
    }

    private static void handleEvent(String input) throws YangException {
        String[] parts = input.substring(5).trim().split("/from|/to");
        if (parts.length < 3) {
            throw new YangException("Event format should be: event <task> /from <time> /to <time>");
        }
        Task t = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        tasks.add(t);
        printTaskAdded(t);
    }

    private static void printTaskAdded(Task t) {
        System.out.println("Perfect! I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
