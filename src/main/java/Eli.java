import java.util.Scanner;

public class Eli {
    public static void main(String[] args) {
        String logo = " _____ _     ___ \n"
            + "           | ____| |   |_ _|\n"
            + "           |  _| | |    | | \n"
            + "           | |___| |___ | | \n"
            + "           |_____|_____|___|\n";
        System.out.println("Hello! I'm " + logo);
        System.out.println("Don't you DARE crash me!");

        String line;
        Scanner in = new Scanner(System.in);
        String list[] = new String[100];
        int count = 0;
        
        while (true) {
            System.out.println("_________________________________");
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }

            
            System.out.println("_________________________________");
            if (line.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
            }
            else {
                System.out.println("added: " + line);
            }
            list[count] = line;
            count++;
        }
        System.out.println("_________________________________");
        System.out.println("Urgh! You're finally leaving");
    }
}
