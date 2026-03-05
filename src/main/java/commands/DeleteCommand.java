package commands;
import exceptions.EliException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;


public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified index of the task to delete.
     *
     * @param taskIndex The zero-based index of the task to be deleted from the list.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EliException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new EliException("That task number doesn't exist!");
        }

        Task removedTask = tasks.delete(taskIndex);
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage("  " + removedTask.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");

        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
