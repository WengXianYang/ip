package commands;
// Command.java (Abstract Base Class)

import exceptions.EliException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EliException;

    public abstract boolean isExit();
}

