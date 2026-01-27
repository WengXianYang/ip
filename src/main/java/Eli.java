import java.util.Scanner;

public class Eli {
    public static void main(String[] args) {
        String logo = " _____ _     ___ \n"
            + "           | ____| |   |_ _|\n"
            + "           |  _| | |    | | \n"
            + "           | |___| |___ | | \n"
            + "           |_____|_____|___|\n";
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");

        String line;
        Scanner in = new Scanner(System.in);
        
        while (true) {
            System.out.println("_________________________________");
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            System.out.println("_________________________________");
            System.out.println(line);
        }
        System.out.println("_________________________________");
        System.out.println("Bye. Hope to see you again soon!");
    }
}
