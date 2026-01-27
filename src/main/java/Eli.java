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
        Task list[] = new Task[100];
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
                    System.out.println((i + 1) + ".[" + list[i].getStatusIcon() + "] "+ list[i].getDescription());
                }
            }
            else if (line.startsWith("mark")) {
                try {
                    int taskNum = Integer.parseInt(line.substring(4).trim()) - 1;
                    
                    // Check if the number is actually within the list range
                    if (taskNum >= 0 && taskNum < count) {
                        list[taskNum].markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  [" + list[taskNum].getStatusIcon() + "] " + list[taskNum].getDescription());
                    } else {
                        System.out.println("Error: That task number doesn't exist!");
                    }
                } catch (NumberFormatException e) {
                    // This runs if they type "mark 1a", "mark", or "mark two"
                    System.out.println("Error: Please enter a valid number after 'mark' (e.g., mark 1).");
                }
            }
            else if (line.startsWith("unmark")) {
                try {
                    int taskNum = Integer.parseInt(line.substring(6).trim()) - 1;
                    
                    if (taskNum >= 0 && taskNum < count) {
                        list[taskNum].unmarkAsDone();
                        System.out.println("OK, I've unmarked this task as not done yet:");
                        System.out.println("  [" + list[taskNum].getStatusIcon() + "] " + list[taskNum].getDescription());
                    } else {
                        System.out.println("Error: That task number doesn't exist!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a valid number after 'unmark' (e.g., unmark 1).");
                }
            }
            else {
                System.out.println("added: " + line);
                list[count] = new Task(line);
                count++;
            }
            
        }
        System.out.println("_________________________________");
        System.out.println("Urgh! You're finally leaving");
    }
}
