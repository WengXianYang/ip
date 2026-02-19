public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + getDescription();
    }
    
    @Override
    public String getDescription() {
        return this.description + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toFileFormat() {
        String isDoneString = getStatusIcon().contains("X") ? "1" : "0";
        return "E | " + isDoneString + " | " + this.description + " | " + this.from + " | " + this.to;
    }
}
