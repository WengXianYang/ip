package tasks;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + getDescription();
    }

    @Override
    public String toFileFormat() {
        String isDoneString = getStatusIcon().contains("X") ? "1" : "0";
        return "T | " + isDoneString + " | " + getDescription();
    }
}
