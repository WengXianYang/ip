package commands;
// Command.java (Abstract Base Class)

import exceptions.EliException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents an abstract command that can be executed by the application. All
 * specific user commands (e.g., adding a task, deleting a task, exiting)
 * inherit from this base class and implement their own specific behaviors.
 */
public abstract class Command {

    /**
     * Executes the specific logic of the command. Subclasses will implement this
     * method to interact with the task list, display messages to the user, and save
     * state to the hard drive.
     *
     * @param tasks   The TaskList representing the current active list of tasks.
     * @param ui      The Ui instance handling all user input and console output.
     * @param storage The Storage instance handling the reading and writing of data
     *                to the file system.
     * @throws EliException If an error specific to the command's execution occurs
     *                      (e.g., referencing a task index that does not exist).
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EliException;

    /**
     * Indicates whether this command is an exit command that should terminate the
     * application's main loop.
     *
     * @return {@code true} if the application should exit after executing this
     *         command; {@code false} otherwise.
     */
    public abstract boolean isExit();
}

