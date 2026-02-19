import java.util.Scanner;

public class Eli {
    private static final int MAX_TASKS = 100;
    private static final String DIVIDER = "_________________________________";
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
        Task list[] = new Task[MAX_TASKS];
        int count = 0;

        while (true) {
            System.out.println(DIVIDER);
            line = in.nextLine();
            System.out.println(DIVIDER);
            line = line.trim();
            String lines[] = line.split(" ", 6);
            switch (lines[0]) {
            case "bye":
                System.out.println("Urgh! You're finally leaving");
                return;
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + "." +list[i].toString());
                }
                break;
            case "mark":
                try {
                    int taskNum = Integer.parseInt(lines[1].trim()) - 1;

                    // Check if the number is actually within the list range
                    if (taskNum >= 0 && taskNum < count) {
                        list[taskNum].markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list[taskNum].toString());
                    } else {
                        System.out.println("Error: That task number doesn't exist!");
                    }
                    // This runs if they type "mark 1a", "mark", or "mark two"
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Please enter a valid number after 'mark' (e.g., mark 1).");
                }
                break;
            case "unmark":
                try {
                    int taskNum = Integer.parseInt(lines[1].trim()) - 1;
                    if (taskNum >= 0 && taskNum < count) {
                        list[taskNum].unmarkAsDone();
                        System.out.println("OK, I've unmarked this task as not done yet:");
                        System.out.println(list[taskNum].toString());
                    } else {
                        System.out.println("Error: That task number doesn't exist!");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Please enter a valid number after 'unmark' (e.g., unmark 1).");
                }
                break;
            case "todo":
                if (lines.length == 1) {
                    System.out.println("Error: The description of a todo cannot be empty.");
                } else {
                    list[count] = new Todo(line.substring(lines[0].length()).trim());
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list[count].toString());
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
                break;
            case "deadline":
                if (lines.length < 4) {
                    System.out.println("Error: Missing description.");
                } else {
                    String deadlineDescription = line.substring(lines[0].length()).trim();
                    int byIndex = deadlineDescription.indexOf("/by");
                    if (byIndex == -1) {
                        System.out.println("Error: Deadline must have a /by clause.");
                        break;
                    }
                    String by = deadlineDescription.substring(byIndex + 3).trim();
                    deadlineDescription = deadlineDescription.substring(0, byIndex).trim();
                    list[count] = new Deadline(deadlineDescription, by);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list[count].toString());
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
                break;
            case "event":
                if (lines.length < 6) {
                    System.out.println("Error: Missing description.");
                } else {
                    String deadlineDescription = line.substring(lines[0].length()).trim();
                    int fromIndex = deadlineDescription.indexOf("/from");
                    int toIndex = deadlineDescription.indexOf("/to");
                    String from, to;

                    if (fromIndex == -1 || toIndex == -1) {
                        System.out.println("Error: Event must have a /from and /to clause.");
                        break;
                    }
                    if (fromIndex < toIndex) {
                        from = deadlineDescription.substring(fromIndex + 5, toIndex).trim();
                        to = deadlineDescription.substring(toIndex + 3).trim();
                        deadlineDescription = deadlineDescription.substring(0, fromIndex).trim();
                    } else {
                        from = deadlineDescription.substring(fromIndex + 5).trim();
                        to = deadlineDescription.substring(toIndex + 3, fromIndex).trim();
                        deadlineDescription = deadlineDescription.substring(0, toIndex).trim();
                    }
                    list[count] = new Event(deadlineDescription, from, to);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list[count].toString());
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
                break;
            case "":
                // Ignore empty input
                break;
            case "delete":
                try {
                    int taskNum = Integer.parseInt(lines[1].trim()) - 1;
                    if (taskNum >= 0 && taskNum < count) {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(list[taskNum].toString());
                        // Shift tasks left to fill the gap
                        for (int i = taskNum; i < count - 1; i++) {
                            list[i] = list[i + 1];
                        }
                        list[count - 1] = null; // Clear the last slot
                        count--;
                        System.out.println("Now you have " + count + " tasks in the list.");
                    } else {
                        System.out.println("Error: That task number doesn't exist!");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Please enter a valid number after 'delete' (e.g., delete 1).");
                }
                break;
            default:
                System.out.println("added: " + line);
                list[count] = new Task(line);
                count++;
            }
        }
    }
}
