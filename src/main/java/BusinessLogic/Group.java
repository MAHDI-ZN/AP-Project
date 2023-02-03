package BusinessLogic;

import java.util.ArrayList;

/**
 * This class allows consultant to have a group of students and assign tasks to all of them together
 */
public class Group {
    private int id;
    private ArrayList<String> studentUsernames = new ArrayList<>();
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * constructor of Group which takes an id and creates a group
     */
    public Group(int id) {
        this.id = id;
    }

    /**
     * returns id of the group
     * @return id
     */
    public int getId(){
        return id;
    }

    /**
     * Assigns task to all students of this group
     * @param task to be assigned
     */
    public void assignTask(Task task){
        tasks.add(task);
    }

    /**
     * Adds a student to this group
     * @param student to be added
     */
    public void addStudent(String student){
        studentUsernames.add(student);
    }

    /**
     * removes a student from this group
     * @param student to be removed
     */
    public void removeStudent(String student){
        for (int i = 0; i < studentUsernames.size(); i++) {
            if(student.equals(studentUsernames.get(i)))
                studentUsernames.remove(i);
        }
    }

}
