import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Eli {
    private static ArrayList<Task> list = new ArrayList<>();
    private static final String DIVIDER = "_________________________________";
    private static final String FILE_PATH = "./data/eli.txt";
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
        loadTasks(list);

        while (true) {
            System.out.println(DIVIDER);
            line = in.nextLine();
            System.out.println(DIVIDER);
            line = line.trim();
            String lines[] = line.split(" ", 2);
            switch (lines[0]) {
            case "bye":
                System.out.println("Urgh! You're finally leaving");
                return;
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." +list.get(i).toString());
                }
                break;
            case "mark":
                try {
                    int taskNum = Integer.parseInt(lines[1].trim()) - 1;

                    // Check if the number is actually within the list range
                    if (taskNum >= 0 && taskNum < list.size()) {
                        list.get(taskNum).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(taskNum).toString());
                        saveTasks(list);
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
                    if (taskNum >= 0 && taskNum < list.size()) {
                        list.get(taskNum).unmarkAsDone();
                        System.out.println("OK, I've unmarked this task as not done yet:");
                        System.out.println(list.get(taskNum).toString());
                        saveTasks(list);
                    } else {
                        System.out.println("Error: That task number doesn't exist!");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Please enter a valid number after 'unmark' (e.g., unmark 1).");
                }
                break;
            case "todo":
                try {
                    list.add(new Todo(lines[1].trim()));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(list.size() - 1).toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    saveTasks(list);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                try {
                    String deadlineDescription = lines[1].trim();
                    int byIndex = deadlineDescription.indexOf("/by");
                    String by = deadlineDescription.substring(byIndex + 3).trim();
                    deadlineDescription = deadlineDescription.substring(0, byIndex).trim();
                    if (by.isEmpty() || deadlineDescription.isEmpty()) {
                        System.out.println("Error: Empty description or deadline. Please provide both a description and a deadline (e.g., deadline return book /by Sunday).");
                        break;
                    }
                    list.add(new Deadline(deadlineDescription, by));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(list.size() - 1).toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    saveTasks(list);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Deadline must have a /by clause (e.g., deadline return book /by Sunday).");
                }
                break;
            case "event":
                try {
                    String deadlineDescription = lines[1].trim();
                    int fromIndex = deadlineDescription.indexOf("/from");
                    int toIndex = deadlineDescription.indexOf("/to");
                    String from, to;

                    if (fromIndex < toIndex) {
                        from = deadlineDescription.substring(fromIndex + 5, toIndex).trim();
                        to = deadlineDescription.substring(toIndex + 3).trim();
                        deadlineDescription = deadlineDescription.substring(0, fromIndex).trim();
                    } else {
                        from = deadlineDescription.substring(fromIndex + 5).trim();
                        to = deadlineDescription.substring(toIndex + 3, fromIndex).trim();
                        deadlineDescription = deadlineDescription.substring(0, toIndex).trim();
                    }

                    if (from.isEmpty() || to.isEmpty() || deadlineDescription.isEmpty()) {
                        System.out.println("Error: Empty description, start time, or end time. Please provide a description, start time, and end time (e.g., event project meeting /from Mon 2pm /to 4pm).");
                        break;
                    }
                    list.add(new Event(deadlineDescription, from, to));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(list.size() - 1).toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    saveTasks(list);
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                    System.out.println("Error: Event must have a /from and /to clause (e.g., event project meeting /from Mon 2pm /to 4pm).");
                }
                break;
            case "":
                // Ignore empty input
                break;
            case "delete":
                try {
                    int taskNum = Integer.parseInt(lines[1].trim()) - 1;
                    if (taskNum >= 0 && taskNum < list.size()) {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(list.get(taskNum).toString());
                        // Remove task from the list
                        list.remove(taskNum);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    } else {
                        System.out.println("Error: That task number doesn't exist!");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Please enter a valid number after 'delete' (e.g., delete 1).");
                }
                break;
            case "find":
                String keyword;
                try {
                    keyword = lines[1].trim();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Please provide a keyword to search for (e.g., find book).");
                    break;
                }
                System.out.println("Here are the matching tasks in your list:");
                int count = 0;
            
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getDescription().contains(keyword)) {
                        System.out.println((i + 1) + "." + list.get(i).toString());
                        count++;
                    }
                }
                
                if (count == 0) {
                    System.out.println("No matching tasks found.");
                }
                break;
            default:
                System.out.println("added: " + line);
                list.add(new Task(line));
                saveTasks(list);
            }
        }
    }

    private static void saveTasks(ArrayList<Task> list) {
        try {
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdirs(); // Creates the ./data directory if it doesn't exist
            }
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < list.size(); i++) {
                fw.write(list.get(i).toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static int loadTasks(ArrayList<Task> list) {
        int count = 0;
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return count; // Return 0 if no file exists yet
        }
        
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    continue;
                }
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String desc = parts[2];
                Task task = null;

                switch (type) {
                case "T":
                    task = new Todo(desc);
                    break;
                case "D":
                    if (parts.length >= 4) {
                        task = new Deadline(desc, parts[3]);
                    }
                    break;
                case "E":
                    if (parts.length >= 5) {
                        task = new Event(desc, parts[3], parts[4]);
                    }
                    break;
                default:
                    task = new Task(desc);
                    break;
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    list.add(task);
                    count++;
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return count;
    }
}
