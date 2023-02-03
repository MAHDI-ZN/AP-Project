import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * User interface class in which all user related activities such as showing menus, user inputs etc. is implemented
 */
public class UserInterface {
    /**
     * this method shows the very first menu to sing in/up
     * @throws InterruptedException because of Thread.sleep() method
     */
    public static void run() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose your role:");
        System.out.println("1. Student");
        System.out.println("2. Consultant");
        System.out.println("3. Manager");
        System.out.println("4. Close the program");
        int role = scanner.nextInt();
        System.out.print("\033\143");
        System.out.flush();
        if(role == 4)
            System.exit(0);
        if(role == 1){
        System.out.println("1.Sign in");
        System.out.println("2.Sign up");
        int code = scanner.nextInt();
        System.out.print("\033\143");
        System.out.flush();
        if(code == 2){
            signUp();
}}
        signIn(role);
    }

    /**
     * checks if entered username and password belong to any student is database
     * @param username entered username to be checked
     * @param password entered password to be checked
     * @return True/False
     */
    private static boolean studentExists(String username, String password){
        if(DataBase.getStudentDB().containsKey(username))
            return DataBase.getStudentDB().get(username).getPassword().equals(password);
        return false;
    }

    /**
     * checks if entered username and password belong to a consultant in database
     * @param username entered username to be checked
     * @param password entered password to be checked
     * @return True/False
     */
    public static boolean consultantExists(String username, String password){
        if(DataBase.getConsultantDB().containsKey(username))
            return DataBase.getConsultantDB().get(username).getPassword().equals(password);
        return false;
    }

    /**
     * checks if the entered username and password belong to a manager in database
     * @param username entered username to be checked
     * @param password entered password to be checked
     * @return True/False
     */
    public static boolean managerExists(String username, String password){
        if(DataBase.getManagerDB().containsKey(username))
            return DataBase.getManagerDB().get(username).getPassword().equals(password);
        return false;
    }

    /**
     * shows manager menu and handles manager related input/output
     * @param Username of the manager who has logged in
     * @throws InterruptedException because of Thread.sleep() method
     */
    public static void manager(String Username) throws InterruptedException {

        System.out.println("Hello dear Manager, here is your options:");
        System.out.println("1. show consultants");
        System.out.println("2. result summery");
        System.out.println("3. send message");
        System.out.println("4. show received messages");
        System.out.println("5. add/remove consultant");
        System.out.println("6. return to top menu");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice){
            case 1:
                showConsultants(Username);
                break;
            case 2:
                String username;
                System.out.println("Enter Consultant's username:");
                username = scanner.nextLine();
                showResults(username);
                break;
            case 3:
                String message;
                System.out.println("Enter your message:");
                message = scanner.nextLine();
                System.out.println("Enter student's username;");
                String receiverUsername = scanner.nextLine();
                sendMessage(Username, message, receiverUsername);
                break;
            case 4:
                showMessages(Username);
                break;
            case 5:
                int code;
                System.out.println("What do you want to do?");
                System.out.println("1. add consultant");
                System.out.println("2. delete consultant");
                code = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter username");
                username = scanner.nextLine();
                if(code == 1)
                    addConsultant(Username, username);
                else
                    removeConsultant(Username, username);
                break;
            case 6:
                System.out.print("\033\143");
                System.out.flush();
                UserInterface.run();
            default:
                System.out.println("No such option!!");
                Thread.sleep(1000);
                System.out.print("\033\143");
                System.out.flush();
                UserInterface.manager(Username);
        }
        System.out.println("Done successfully!");
        Thread.sleep(1000);
        System.out.print("\033\143");
        System.out.flush();
        UserInterface.manager(Username);
    }

    /**
     * shows student menu and handles student related input/output
     * @param username of the student who has logged in
     * @throws InterruptedException because of Thread.sleep() method
     */
    public static void student(String username) throws InterruptedException {
        int code;
        Student student = DataBase.getStudentDB().get(username);
        Scanner scanner = new Scanner(System.in);
        if(!student.getQuizzes().isEmpty()){
            System.out.println("You Have a new Quiz!\nto answer the quiz, please enter 1:");
            scanner.nextInt();
            scanner.nextLine();
            Date start = new Date();
            student.quiz(student.getQuizzes().get(0));
            Date end = new Date();
            if((student.getQuizzes().get(0).getDuration()*1000)<(end.getTime()-start.getTime())){
                System.out.println("you ran out of time!\nyour quiz is invalid");
                student.getQuizzes().get(0).setInvalid();
            }
            else{
                System.out.println("well done!");
                student.getQuizzes().get(0).setValid();
            }
            student.getQuizzes().remove(0);
            Thread.sleep(3000);
            System.out.print("\033\143");
            System.out.flush();
            UserInterface.run();
        }
        System.out.println("Hello dear student, Here is your options:");
        System.out.println("1. Give feedback");
        System.out.println("2. Show tasks");
        System.out.println("3. Check tasks");
        System.out.println("4. Send message to consultant");
        System.out.println("5. Show messages");
        System.out.println("6. return to top menu");
        code = scanner.nextInt();
        scanner.nextLine();
        switch(code){
            case 1:
                int satisfactionLevel;
                System.out.println("How satisfied are you?");
                System.out.println("1. Very satisfied");
                System.out.println("2. satisfied");
                System.out.println("3. Not satisfied");
                satisfactionLevel = scanner.nextInt();
                scanner.nextLine();
                System.out.println("4. Enter you consultant's username");
                String username1 = scanner.nextLine();
                if(satisfactionLevel == 1)
                    giveFeedback(Feedback.VERY_SATISFIED,username1);
                else if (satisfactionLevel == 2)
                    giveFeedback(Feedback.SATISFIED,username1);
                else
                    giveFeedback(Feedback.NOT_SATISFIED,username1);
                break;
            case 2:
                showTasks(username);
                break;
            case 3:
                System.out.println("Well Done! which task did you do?");
                String task = scanner.nextLine();
                checkTasks(task, username);
                break;
            case 4:
                System.out.println("Who Do you want to send message to?");
                username1 = scanner.nextLine();
                System.out.println("What is your message?");
                String message = scanner.nextLine();
                sendMessage(username, message, username1);
                break;
            case 5:
                showMessages(username);
                break;
            case 6:
                System.out.print("\033\143");
                System.out.flush();
                UserInterface.run();
            default:
                System.out.println("No such option!!");
                Thread.sleep(1000);
                System.out.print("\033\143");
                System.out.flush();
                UserInterface.student(username);
        }
        System.out.println("Done successfully!");
        Thread.sleep(1000);
        System.out.print("\033\143");
        System.out.flush();
        UserInterface.student(username);
    }

    /**
     * show consultant menu and handles consultant related input/output
     * @param Username of the consultant who has logged in
     * @throws InterruptedException because of Thread.sleep() method
     */
    public static void consultant(String Username) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello dear consultant, here is your options:");
        System.out.println("1. Modify Tasks");
        System.out.println("2. Show results");
        System.out.println("3. Send message");
        System.out.println("4. Show messages");
        System.out.println("5. Declare quiz");
        System.out.println("6. Add/delete student");
        System.out.println("7. Return to top menu");
        int code = scanner.nextInt();
        scanner.nextLine();
        switch(code){
            case 1:
                System.out.println("Enter username");
                String username = scanner.nextLine();
                modifyTask(username);
                break;
            case 2:
                System.out.println("Enter student's username");
                String studentUsername = scanner.nextLine();
                showResults(studentUsername);
                break;
            case 3:
                System.out.println("Who do you want to send message to?");
                username = scanner.nextLine();
                System.out.println("Enter your message");
                String message = scanner.nextLine();
                sendMessage(Username, message, username);
                break;
            case 4:
                showMessages(Username);
                break;
            case 5:
                System.out.println("Enter student username");
                String stUsername = scanner.nextLine();
                System.out.println("Enter duration of the quiz");
                int duration = scanner.nextInt();
                declareQuiz(duration, Username, stUsername);
                break;
            case 6:
                System.out.println("What do you want to do?");
                System.out.println("1. Add student");
                System.out.println("2. Delete student");
                code = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter student's username");
                String username1 = scanner.nextLine();
                if(code==1){
                    System.out.println("enter the groupId you want to add student to");
                    int id = scanner.nextInt();
                    addStudent(id, username1, Username);}
                else{
                removeStudent(username1, Username);}
                break;
            case 7:
                System.out.print("\033\143");
                System.out.flush();
                UserInterface.run();
            default:
                System.out.println("No such option!!");
                Thread.sleep(1000);
                System.out.print("\033\143");
                System.out.flush();
                UserInterface.consultant(Username);
        }
        System.out.println("Done successfully!");
        Thread.sleep(1000);
        System.out.print("\033\143");
        System.out.flush();
        UserInterface.consultant(Username);
    }

    /**
     * modifies tasks of a student whenever his consultant wants to
     * @param username of the student
     */
    public static void modifyTask(String username){
        ArrayList<Task> newTasks = new ArrayList<>();
        newTasks.add(new Task("new task!", "1402/12/12"));
        newTasks.add(new Task("new task!@!", "1402/12/12"));
        DataBase.getStudentDB().get(username).setTasks(newTasks);
        DataBase.refreshDB();
    }

    /**
     * adds a student to a consultant's groups whenever consultant wants to
     * @param id of the group to which the student is going to be joined
     * @param studentUsername username of the new student
     * @param myUsername username of the consultant
     */
    public static void addStudent(int id, String studentUsername, String myUsername){
        String name, lastName, password;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student's name");
        name = scanner.nextLine();
        System.out.println("Enter student's lastName");
        lastName = scanner.nextLine();
        System.out.println("Enter student's password");
        password = scanner.nextLine();
        Student student = new Student(name, lastName, studentUsername, password, myUsername, id);
        DataBase.getStudentDB().put(studentUsername, student);
        DataBase.getDataBase().put(studentUsername, student);
        DataBase.refreshDB();
    }

    /**
     * removes a student from a consultant's groups whenever he wants to
     * @param studentUsername username of the student who is going to be removed
     * @param myUsername username of the consultant who is going to remove the student
     */
    public static void removeStudent(String studentUsername, String myUsername){
        DataBase.getConsultantDB().get(myUsername).removeStudent(studentUsername, DataBase.getStudentDB().get(studentUsername).getGroupId());
        DataBase.getStudentDB().remove(studentUsername);
        DataBase.getDataBase().remove(studentUsername);
        DataBase.refreshDB();
    }

    /**
     * declares a quiz for a student whenever the manager wants to
     * @param duration of the quiz
     * @param username of the consultant
     * @param studentUsername username of the student
     */
    public static void declareQuiz(int duration, String username, String studentUsername){
        DataBase.getConsultantDB().get(username).declareQuiz(studentUsername, duration);
        DataBase.refreshDB();
    }

    /**
     * allows a student to give feedback to their consultant
     * @param feedback which is taken from the student
     * @param username of the consultant
     */
    public static void giveFeedback(Feedback feedback, String username){
        Manager.getFeedBack(feedback, username);
        DataBase.refreshDB();
    }

    /**
     * shows tasks of a student
     * @param username of the student
     * @throws InterruptedException because of Thread.sleep() method
     */
    public static void showTasks(String username) throws InterruptedException {
        Student student = DataBase.getStudentDB().get(username);
        for (int i = 0; i < student.getTasks().size(); i++) {
            System.out.println(student.getTasks().get(i).getName()+".  Deadline:"+student.getTasks().get(i).getDeadline());
        }
        Thread.sleep(3000);
    }

    /**
     * checks and removes a task if a student has done it
     * @param taskName name of the task that has been done
     * @param username of the student who has done this task
     * @throws InterruptedException because of Thread.sleep() method
     */
    public static void checkTasks(String taskName, String username) throws InterruptedException {
        Student student = DataBase.getStudentDB().get(username);
        student.removeTask(taskName);
        student.getResults().add(taskName + " Done");
        DataBase.refreshDB();
        Thread.sleep(3000);
    }

    /**
     * shows consultants of a manager whenever the manger want
     * @param username of the manager
     * @throws InterruptedException because of Thread.sleep() method
     */
    public static void showConsultants(String username) throws InterruptedException {
        Manager manager = DataBase.getManagerDB().get(username);
        for (int i = 0; i < manager.getConsultants().size(); i++) {
            System.out.println(manager.getConsultants().get(i).getName()+" "+manager.getConsultants().get(i).getLasName());
        }
        Thread.sleep(3000);
    }

    /**
     * shows result summary of a Person, if student, it is going to show done tasks, if consultant, it is going to show received feedbacks
     * @param username of the Person
     * @throws InterruptedException because of Thread.sleep() method
     */
    public static void showResults(String username) throws InterruptedException {
        Person person = DataBase.getDataBase().get(username);
        for (int i = 0; i < person.getResults().size(); i++) {
            System.out.println(person.getResults().get(i));
        }
        Thread.sleep(10000);
    }

    /**
     * shows the messages that a person has received
     * @param username of the person
     * @throws InterruptedException because of Thread.sleep() method
     */
    public static void showMessages(String username) throws InterruptedException {
        Person person = DataBase.getDataBase().get(username);
        for (int i = 0; i < person.getMessages().size(); i++) {
            System.out.println(person.getMessages().get(i));
        }
        Thread.sleep(10000);
    }
    /**
     * sends the message whenever a Person wants to, it uses Polymorphism and acts differently based on role of the person who is sending the message
     * @param username of the message sender
     * @param message text of the message
     * @param receiverUserName username of the person who is going to receive the message
     */
    public static void sendMessage(String username, String message, String receiverUserName){
        DataBase.getDataBase().get(username).sendMessage(message, receiverUserName);
        DataBase.refreshDB();
    }

    /**
     * adds a consultant whenever the manager wants
     * @param managerUsername username of the manager who wants to add a consultant
     * @param username of the consultant who is going to be added
     */
    public static void addConsultant(String managerUsername, String username){
        String name, lastName, password;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter consultant's name");
        name = scanner.nextLine();
        System.out.println("Enter consultant's lastName");
        lastName = scanner.nextLine();
        System.out.println("Enter consultant's password");
        password = scanner.nextLine();
        Consultant consultant = new Consultant(name, lastName, username, password, managerUsername);
        DataBase.getConsultantDB().put(username, consultant);
        DataBase.getDataBase().put(username, consultant);
        DataBase.refreshDB();
    }

    /**
     * removes the consultant whenever the manager wants
     * @param managerUsername username of the manager who wants to remove a consultant
     * @param username of the consultant who is going to be removed
     */
    public static void removeConsultant(String managerUsername, String username){
        DataBase.getConsultantDB().remove(username);
        DataBase.getDataBase().remove(username);
        DataBase.getManagerDB().get(managerUsername).removeConsultant(username);
        DataBase.refreshDB();
    }

    /**
     * Initialized the database in case there is no information in redis
     */
    public static void initializeDB(){
        DataBase.addManager(new Manager("MAHDI","MOHAMMADI","WHITE_WOLF","WHATUPBRO"));
        DataBase.addConsultant(new Consultant("ZARA", "IVEDIK", "Z.A", "BROTH", "WHITE_WOLF"));
        DataBase.getConsultantDB().get("Z.A").addGroup(new Group(1));
        DataBase.refreshDB();
        DataBase.addStudent(new Student("hossein", "ghanbari", "ali", "alavi","Z.A",1));
        DataBase.getStudentDB().get("ali").addTask(new Task("Physics final", "1401/12/12"));
        DataBase.refreshDB();
    }

    /**
     * allows students to sign up and join the system
     * @throws InterruptedException because of Thread.sleep() method
     */
    public static void signUp() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;
        String confirm;
        System.out.println("Enter your desired username:");
        username = scanner.nextLine();
        System.out.println("Enter your desired password:");
        password = scanner.nextLine();
        System.out.println("Confirm your password:");
        confirm = scanner.nextLine();
        while(!password.equals(confirm)){
            System.out.println("Please enter your password correctly:");
            confirm = scanner.nextLine();
        }
        String name;
        String lastName;
        System.out.println("Enter your name:");
        name = scanner.nextLine();
        System.out.println("Enter your last name:");
        lastName = scanner.nextLine();
        System.out.println("Enter Group Id");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter your consultant's username");
        String us = scanner.nextLine();
        DataBase.addStudent(new Student(name, lastName, username, password, us, id));
        System.out.println("Student added successfully!");
        Thread.sleep(1000);
        System.out.print("\033\143");
        System.out.flush();
        UserInterface.run();
    }

    /**
     * allows users to log in
     * @param role student, consultant or manager
     * @throws InterruptedException because of Thread.sleep() method
     */
    public static void signIn(int role) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("password:");
        String password = scanner.nextLine();
        switch (role) {
            case 1 -> {
                if (studentExists(username, password)){
                    System.out.print("\033\143");
                    System.out.flush();
                    student(username);}
                else{
                    System.out.println("There is no such student.");
                    Thread.sleep(1000);
                    System.out.print("\033\143");
                    System.out.flush();
                    UserInterface.run();}
            }
            case 2 -> {
                if (consultantExists(username, password)){
                    System.out.print("\033\143");
                    System.out.flush();
                    consultant(username);}
                else{
                    System.out.println("There is no such Consultant");
                    Thread.sleep(1000);
                    System.out.print("\033\143");
                    System.out.flush();
                    UserInterface.run();}
            }
            case 3 -> {
                if (managerExists(username, password)){
                    System.out.print("\033\143");
                    System.out.flush();
                    manager(username);}
                else{
                    System.out.println("There is no such Manager!");
                    Thread.sleep(1000);
                    System.out.print("\033\143");
                    System.out.flush();
                    UserInterface.run();}
            }
            default -> {System.out.println("You've entered a wrong number!");
                Thread.sleep(1000);
                System.out.print("\033\143");
                System.out.flush();
                UserInterface.run();}
        }
    }
}