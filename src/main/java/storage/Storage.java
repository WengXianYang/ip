package storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.EliException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws EliException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new EliException("File not found.");
        }

        try (Scanner fileScanner = new Scanner(file)) {
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
                    throw new EliException("Invalid task type in file.");
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    loadedTasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new EliException("Error loading tasks: " + e.getMessage());
        }
        return loadedTasks;
    }

    public void save(TaskList tasks) throws EliException {
        try {
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new EliException("Error saving tasks: " + e.getMessage());
        }
    }
}
