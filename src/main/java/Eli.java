import commands.Command;
import exceptions.EliException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;
import parser.Parser;

public class Eli {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Eli(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (EliException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (EliException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Eli("./data/eli.txt").run();
    }
}
