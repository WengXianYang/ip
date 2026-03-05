package ui;
import java.util.Scanner;

/**
 * Handles all interactions with the user. Responsible for reading user input
 * and printing formatted messages to the console.
 */
public class Ui {
    private static final String DIVIDER = "_________________________________";
    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    
    public void showWelcome() {
        String logo = "            _____ _     ___ \n" 
        + "           | ____| |   |_ _|\n" 
        + "           |  _| | |    | | \n" 
        + "           | |___| |___ | | \n" 
        + "           |_____|_____|___|\n";
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("Don't you DARE crash me!");
        showLine();
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showLoadingError() {
        System.out.println("No existing data file found. Starting with an empty task list.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
