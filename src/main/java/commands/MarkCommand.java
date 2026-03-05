package commands;
import exceptions.EliException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;


public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EliException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new EliException("That task number doesn't exist!");
        }

        Task task = tasks.get(taskIndex);
        task.markAsDone();
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage("  " + task.toString());

        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
