package tasks;
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + getDescription();
    }

    @Override
    public String getDescription() {
        return this.description + " (by: " + this.by + ")";
    }

    @Override
    public String toFileFormat() {
        String isDoneString = getStatusIcon().contains("X") ? "1" : "0";
        return "D | " + isDoneString + " | " + this.description + " | " + this.by; 
    }
}
