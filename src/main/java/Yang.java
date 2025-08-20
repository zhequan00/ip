import java.util.Scanner;

public class Yang {
    public static void main(String[] args) {
        System.out.println("___________________________________________________");
        System.out.println("Hello! I'm Yang, your favourite chatbot assistant!");
        System.out.println("What can I do for you?");
        System.out.println("___________________________________________________");

        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("_______________________________________");
                System.out.println("Bye bye! Hope to see you again soon!");
                System.out.println("_______________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("_______________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i+1) + ". " + tasks[i]);
                }
                System.out.println("_______________________________________");
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("_______________________________________");
                System.out.println("added: " + input);
                System.out.println("_______________________________________");
            }
        }

        sc.close();

    }
}
