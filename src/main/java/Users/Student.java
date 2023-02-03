package Users;
import BusinessLogic.*;
import DataBase.*;
import Users.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a wrapper class for the Student role that is extended from Person
 */
public class Student extends Person{
    private int groupId;
    private ArrayList<Task> tasks = new ArrayList<>();
    private ArrayList<Quiz> quizzes = new ArrayList<>();
    private String consultantUsername;

    /**
     * returns all quizzes that the student has ever done
     * @return quizzes
     */
    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    private ArrayList<Lesson> lessons = new ArrayList<>();

    /**
     * returns student's lessons
     * @return Lessons
     */
    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    /**
     * returns student's unchecked tasks
     * @return Tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * returns id of the group that this student is a member of
     * @return id
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * This constructor allows students to be created
     * @param name of the student
     * @param lasName of the student
     * @param userName of the student
     * @param password of the student
     * @param consultant of the student
     * @param groupId of the student
     */
    public Student(String name, String lasName, String userName, String password, String consultant, int groupId) {
        super(name, lasName, userName, password);
        this.consultantUsername = consultant;
        this.groupId = groupId;
        DataBase.getConsultantDB().get(consultant).addStudent(groupId, this.getUserName());
    }

    /**
     * removes task of this student
     * @param name of the task to be removed
     */
    public void removeTask(String name){
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getName().equals(name)){
                tasks.remove(i);
                break;}
        }
    }

    /**
     * this is a special purpose method for students that is overridden from the sendMessage method of Person
     * @param message to be sent
     * @param username of the receiver
     */
    @Override
    public void sendMessage(String message, String username){
        super.sendMessage("Hi I'm your student\n"+message, username);
    }

    /**
     * assigns a series of tasks to this student, used to modify tasks
     * @param tasks to be assigned
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * allows student to answer his quiz
     * @param quiz to be answered
     */
    public void quiz(Quiz quiz){
        System.out.println(quiz.getQuestions().get(0));
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        quiz.getAnswers().add(answer);
    }

    /**
     * adds a quiz to this students quiz list
     * @param quiz to be added
     */
    public void addQuiz(Quiz quiz){
        quizzes.add(quiz);
    }

    /**
     * this method adds a task to this student's tasks
     * @param task to be added
     */
    public void addTask(Task task){
        this.tasks.add(task);
    }
}
