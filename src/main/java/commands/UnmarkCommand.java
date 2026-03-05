package commands;
import exceptions.EliException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;


public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EliException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new EliException("That task number doesn't exist!");
        }

        Task task = tasks.get(taskIndex);
        task.unmarkAsDone();
        ui.showMessage("OK, I've unmarked this task as not done yet:");
        ui.showMessage("  " + task.toString());

        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
