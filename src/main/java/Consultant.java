import java.util.HashMap;
import java.util.Scanner;

/**
 * This is a wrapper class for the consultant role which is inherited from Person
 */
public class Consultant extends Person{
    private String managerUsername;
    private HashMap<Integer, Group> groups = new HashMap<>();

    /**
     * this is constructor of consultant class
     * @param name of the consultant
     * @param lasName of the consultant
     * @param userName of the consultant
     * @param password of the consultant
     * @param manager of the consultant
     */
    public Consultant(String name, String lasName, String userName, String password, String manager) {
        super(name, lasName, userName, password);
        this.managerUsername = manager;
        DataBase.getManagerDB().get(manager).addConsultant(this);
    }

    /**
     * this is a special purpose method for consultants that is overridden from the sendMessage method of Person
     * @param message to be sent
     * @param username of the receiver
     */
    @Override
    public void sendMessage(String message, String username){
        super.sendMessage("Hi I'm your consultant\n"+message, username);
    }

    /**
     * this method allows consultant to declare quiz to validate students' studies
     * @param stUsername username of the student which is going to do the task
     * @param duration of the quiz in <STRONG>seconds</STRONG>
     */
    public void declareQuiz(String stUsername, int duration){
        Quiz quiz = new Quiz(duration);
        System.out.println("Enter Question:");
        Scanner scanner = new Scanner(System.in);
        String question = scanner.nextLine();
        quiz.getQuestions().add(question);
        DataBase.getStudentDB().get(stUsername).addQuiz(quiz);
    }

    /**
     * allows consultant to add student to his groups
     * @param groupId id of the group in which student is going to be joined
     * @param student that is going to be joined to the group
     */
    public void addStudent(int groupId, String student){
        groups.get(groupId).addStudent(student);
    }

    /**
     * allows consultant to remove student from his groups
     * @param student to be removed
     * @param groupId id of the group from which the student is going to be deleted
     */
    public void removeStudent(String student, int groupId){
        groups.get(groupId).removeStudent(student);
    }

    /**
     * this method adds a new group to the consultant's list of groups
     * @param group to be added
     */
    public void addGroup(Group group){
        this.groups.put(group.getId(), group);
    }
}
