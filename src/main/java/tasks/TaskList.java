package tasks;

import java.util.ArrayList;

/**
 * Represents the list of tasks and provides operations to modify and access
 * them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList using an existing list of tasks.
     *
     * @param tasks An ArrayList of Task objects to initialize the list with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The Task object to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns a task from the list at the specified index.
     *
     * @param index The zero-based index of the task to remove.
     * @return The Task object that was removed.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list at the specified index without removing it.
     *
     * @param index The zero-based index of the task to retrieve.
     * @return The requested Task object.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the total number of tasks currently in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
}
