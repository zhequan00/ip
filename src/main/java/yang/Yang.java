package yang;

import java.util.ArrayList;
import java.util.Scanner;
import yang.task.Task;
import yang.task.Todo;
import yang.task.Deadline;
import yang.task.Event;
import yang.storage.Storage;

public class Yang {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Storage storage = Storage.defaultStorage();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            tasks.clear();
            tasks.addAll(storage.load());
        } catch (Exception ignored) {}

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
                } else if (input.startsWith("delete")) {
                  handleDelete(input);
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
        System.out.println("Hello! I'm Yang.Yang, your favourite bot assistant!");
        System.out.println("What can I help you with?");
        System.out.println("___________________________________________________");
    }

    private static void exitBot() {
        System.out.println("Bye bye! Thank you for using Yang assistant!");
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
        t.markDone();
        System.out.println("Perfect! This task is now marked as done:");
        System.out.println("  " + t);
        persist();
    }

    private static void handleUnmark(String input) throws YangException {
        int idx = Integer.parseInt(input.substring(7)) - 1;
        if (idx < 0 || idx >= tasks.size()) {
            throw new YangException("Invalid task number for unmark.");
        }
        Task t = tasks.get(idx);
        t.markUndone();
        System.out.println("Alright, I've marked this task as not done yet:");
        System.out.println("  " + t);
        persist();
    }

    private static void handleDelete(String input) throws YangException {
        int idx = Integer.parseInt(input.substring(6).trim()) - 1;

        Task removed = tasks.remove(idx);
        System.out.println("Understood, I've removed this task");
        System.out.println("  " + removed);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        persist();
    }

    private static void handleTodo(String input) throws YangException {
        String desc = input.substring(4).trim();
        if (desc.isEmpty()) {
            throw new YangException("Woops! The description of a todo must have a task!!");
        }
        Task t = new Todo(desc);
        tasks.add(t);
        printTaskAdded(t);
        persist();
    }

    private static void handleDeadline(String input) throws YangException {
        String[] parts = input.substring("deadline".length()).trim().split("/by", 2);
        if (parts.length < 2) throw new YangException("Usage: deadline <desc> /by <yyyy-mm-dd>");
        String desc = parts[0].trim();
        java.time.LocalDate by = java.time.LocalDate.parse(parts[1].trim()); // ISO date
        Task t = new yang.task.Deadline(desc, by);
        tasks.add(t);
        System.out.println("Perfect! I've added this task:\n  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        persist();
    }

    private static void handleEvent(String input) throws YangException {
        String rest = input.substring("event".length()).trim();
        String[] parts = rest.split("/at", 2);
        if (parts.length < 2) throw new YangException("Usage: event <desc> /at <yyyy-mm-dd>");
        String desc = parts[0].trim();
        java.time.LocalDate at = java.time.LocalDate.parse(parts[1].trim());
        Task t = new yang.task.Event(desc, at);
        tasks.add(t);
        System.out.println("Perfect! I've added this task:\n  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        persist();
    }


    private static void printTaskAdded(Task t) {
        System.out.println("Perfect! I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void persist() {
        try {
            storage.save(tasks);
        } catch (Exception ignored) { }
    }
}
