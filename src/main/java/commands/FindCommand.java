package commands;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;


public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword of type String to search for in task descriptions.
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the matching tasks in your list:");
        int count = 0;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                ui.showMessage((i + 1) + "." + task.toString());
                count++;
            }
        }

        if (count == 0) {
            ui.showMessage("No matching tasks found.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
