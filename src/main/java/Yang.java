import java.util.Scanner;

public class Yang {
    public static void main(String[] args) {
        System.out.println("___________________________");
        System.out.println("Hello! I'm Yang");
        System.out.println("What can I do for you?");
        System.out.println("___________________________");

        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("_______________________________________");
                System.out.println("Bye bye! Hope to see you again soon!");
                System.out.println("_______________________________________");
                break;
            } else {
                System.out.println("_______________________________________");
                System.out.println(input);
                System.out.println("_______________________________________");
            }
        }

        sc.close();

    }
}
