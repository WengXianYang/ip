package parser;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import exceptions.EliException;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class Parser {

    public static Command parse(String fullCommand) throws EliException {
        if (fullCommand.trim().isEmpty()) {
            throw new EliException("Command cannot be empty.");
        }

        // Split the command into the action word and the rest of the details
        String[] parts = fullCommand.trim().split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            try {
                int index = Integer.parseInt(parts[1].trim()) - 1;
                return new MarkCommand(index);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new EliException("Please enter a valid number after 'mark' (e.g., mark 1).");
            }

        case "unmark":
            try {
                int index = Integer.parseInt(parts[1].trim()) - 1;
                return new UnmarkCommand(index);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new EliException("Please enter a valid number after 'unmark' (e.g., unmark 1).");
            }

        case "delete":
            try {
                int index = Integer.parseInt(parts[1].trim()) - 1;
                return new DeleteCommand(index);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new EliException("Please enter a valid number after 'delete' (e.g., delete 1).");
            }

        case "find":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new EliException("Please provide a keyword to search for (e.g., find book).");
            }
            return new FindCommand(parts[1].trim());

        case "todo":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new EliException("The description of a todo cannot be empty.");
            }
            return new AddCommand(new Todo(parts[1].trim()));

        case "deadline":
            try {
                String deadlineDescription = parts[1].trim();
                int byIndex = deadlineDescription.indexOf("/by");

                if (byIndex == -1) {
                    throw new EliException("Deadline must have a /by clause (e.g., deadline return book /by Sunday).");
                }

                String by = deadlineDescription.substring(byIndex + 3).trim();
                String desc = deadlineDescription.substring(0, byIndex).trim();

                if (by.isEmpty() || desc.isEmpty()) {
                    throw new EliException("Empty description or deadline. Please provide both.");
                }
                return new AddCommand(new Deadline(desc, by));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EliException("The description of a deadline cannot be empty.");
            }

        case "event":
            try {
                String eventDescription = parts[1].trim();
                int fromIndex = eventDescription.indexOf("/from");
                int toIndex = eventDescription.indexOf("/to");

                if (fromIndex == -1 || toIndex == -1) {
                    throw new EliException("Event must have a /from and /to clause (e.g., event project meeting /from Mon 2pm /to 4pm).");
                }

                String from, to, desc;
                if (fromIndex < toIndex) {
                    from = eventDescription.substring(fromIndex + 5, toIndex).trim();
                    to = eventDescription.substring(toIndex + 3).trim();
                    desc = eventDescription.substring(0, fromIndex).trim();
                } else {
                    from = eventDescription.substring(fromIndex + 5).trim();
                    to = eventDescription.substring(toIndex + 3, fromIndex).trim();
                    desc = eventDescription.substring(0, toIndex).trim();
                }

                if (from.isEmpty() || to.isEmpty() || desc.isEmpty()) {
                    throw new EliException("Empty description, start time, or end time. Please provide all.");
                }
                return new AddCommand(new Event(desc, from, to));
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new EliException("Error parsing event details. Ensure you use /from and /to correctly.");
            }

        default:
            throw new EliException("I'm sorry, but I don't know what that means. Please enter a valid command.");
        }
    }
}
