package commands;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;


public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Urgh! You're finally leaving");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
