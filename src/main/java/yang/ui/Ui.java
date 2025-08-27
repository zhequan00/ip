package yang.ui;

import java.util.Scanner;
import yang.task.Task;
import yang.task.TaskList;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void greet() {
        System.out.println("Good day! I'm Yang your favourite bot assistant\nWhat can I do for you?");
    }
    public String readCommand() { return sc.nextLine().trim(); }

    public void showAdded(Task t, int count) {
        System.out.println("Perfect! I've added this task:\n  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
    }
    public void showList(TaskList tl) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tl.size(); i++) System.out.println((i+1) + ". " + tl.get(i));
    }

    public void showDeleted(Task t, int count) {
        System.out.println("Understood. I've removed this task:\n  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    public void showMarked(Task t) {
        System.out.println("Okay! I've marked this task as done:");
        System.out.println("  " + t);
    }

    public void showUnmarked(Task t) {
        System.out.println("Will do, I've marked this task as not done yet:");
        System.out.println("  " + t);
    }

    public void show(String s) {
        System.out.println(s);
    }
    public void bye() {
        System.out.println("Bye bye! Hope to see you again soon!");
    }
}

