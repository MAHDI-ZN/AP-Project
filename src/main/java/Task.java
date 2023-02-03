/**
 * Task class allows the consultant to assign tasks to students with a certain deadline and description
 */
public class Task {
    private String name;
    private String deadline;
    /**
     * This constructor takes a name and a deadline and makes a Task object
     */
    public Task(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    /**
     * returns name of the Task
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * returns deadline of the Task
     * @return deadline
     */
    public String getDeadline() {
        return deadline;
    }
}
