import commands.Command;
import exceptions.EliException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;
import parser.Parser;

/**
 * The main entry point for the Eli chatbot application. Initializes the user
 * interface, storage, and task list, and runs the main program loop.
 */
public class Eli {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an Eli instance, initializing the required UI, storage, and task
     * list components.
     *
     * @param filePath The file path where the task data is stored and loaded from.
     */
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

    /**
     * Runs the main loop of the application. Continuously reads user commands,
     * parses them, executes them, and displays the output until the user issues an
     * exit command.
     */
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
