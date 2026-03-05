# Eli User Guide

Eli is a **desktop app for managing tasks**, optimized for use via a Command Line Interface (CLI). If you can type fast, Eli can manage your daily tasks faster than traditional GUI apps. Just don't crash him!

---

## Quick Start

1. Ensure you have Java `17` installed in your Computer.
2. Download the latest `eli.jar` from the releases section.
3. Copy the file to the folder you want to use as the home folder for your task list.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar eli.jar` command to run the application.
5. You should see Eli's welcome message and logo appear. Type a command to start!

---

## Features 

## Command Summary

| Action | Format, Examples |
|--------|------------------|
| **Todo** | `todo <description>` <br> e.g., `todo borrow book` |
| **Deadline** | `deadline <description> /by <date/time>` <br> e.g., `deadline submit report /by Friday 5pm` |
| **Event** | `event <description> /from <start> /to <end>` <br> e.g., `event team meeting /from 2pm /to 4pm` |
| **List** | `list` |
| **Mark** | `mark <task_number>` <br> e.g., `mark 1` |
| **Unmark** | `unmark <task_number>` <br> e.g., `unmark 1` |
| **Delete** | `delete <task_number>` <br> e.g., `delete 3` |
| **Find** | `find <keyword>` <br> e.g., `find meeting` |
| **Exit** | `bye` |

### Adding a Todo: `todo`
Adds a task without any date or time attached to it.

* **Format:** `todo <description>`
* **Example:** `todo read book`

### Adding a Deadline: `deadline`
Adds a task that needs to be done before a specific date/time.

* **Format:** `deadline <description> /by <deadline>`
* **Example:** `deadline return book /by Sunday`

### Adding an Event: `event`
Adds a task that starts at a specific time and ends at a specific time.

* **Format:** `event <description> /from <start time> /to <end time>`
* **Example:** `event project meeting /from Mon 2pm /to 4pm`

### Listing all tasks: `list`
Shows a list of all tasks currently tracked by Eli.

* **Format:** `list`

### Marking a task as done: `mark`
Marks a specific task in the list as completed.

* **Format:** `mark <task_number>`
* **Example:** `mark 1` marks the 1st task in the list as done.

### Unmarking a task: `unmark`
Marks a specific task in the list as not completed yet.

* **Format:** `unmark <task_number>`
* **Example:** `unmark 1`

### Deleting a task: `delete`
Removes a specific task from the list permanently.

* **Format:** `delete <task_number>`
* **Example:** `delete 2` removes the 2nd task in the list.

### Finding a task: `find`
Finds tasks whose descriptions contain the given keyword.

* **Format:** `find <keyword>`
* **Example:** `find book` returns all tasks with the word "book" in their description.

### Exiting the program: `bye`
Exits the application and saves your data.

* **Format:** `bye`

---

## Data Storage
Eli data is saved in the hard disk automatically after every command that changes the data. There is no need to save manually. 
The data is saved in a folder called `data` in a file named `eli.txt` (`./data/eli.txt`).

---
