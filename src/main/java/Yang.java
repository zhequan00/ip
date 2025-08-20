import java.util.Scanner;
import java.util.ArrayList;

public class Yang {
    public static void main(String[] args) {
        System.out.println("___________________________________________________");
        System.out.println("Hello! I'm Yang, your favourite chatbot assistant!");
        System.out.println("What can I do for you?");
        System.out.println("___________________________________________________");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String input = sc.nextLine().trim();

            if (input.equals("bye")) {
                System.out.println("_______________________________________");
                System.out.println("Bye bye! Hope to see you again soon!");
                System.out.println("_______________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("_______________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println("_______________________________________");
            } else if (input.startsWith("mark")) {
                int idx = Integer.parseInt(input.substring(5)) - 1;
                Task t = tasks.get(idx);
                t.markAsDone();
                System.out.println("_______________________________________");
                System.out.println("Perfect! I've marked this task as done");
                System.out.println(" " + t);
                System.out.println("_______________________________________");
            } else if (input.startsWith("unmark")) {
                int idx = Integer.parseInt(input.substring(7)) - 1;
                Task t = tasks.get(idx);
                t.markAsUndone();
                System.out.println("_______________________________________");
                System.out.println("Alright, I've marked this task as not done yet");
                System.out.println(" " + t);
                System.out.println("_______________________________________");
            } else if (input.startsWith("todo")) {
                String desc = input.substring(5).trim();
                Task t = new Todo(desc);
                tasks.add(t);
                System.out.println("Perfect. I've added this task:\n  " + t);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("deadline")) {
                String[] parts = input.substring(9).split("/by", 2);
                Task t = new Deadline(parts[0].trim(), parts[1].trim());
                tasks.add(t);
                System.out.println("Perfect. I've added this task:\n  " + t);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("event")) {
                String[] parts = input.substring(6).split("/from|/to");
                Task t = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                tasks.add(t);
                System.out.println("Perfect. I've added this task:\n  " + t);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
               System.out.println("Unknown command" + input);
            }
        }

        sc.close();

    }
}
