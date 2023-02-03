package Users;
import DataBase.*;
import BusinessLogic.*;
import java.util.ArrayList;

/**
 * This is a wrapper class for the Person, from which classes Student, Consultant and Manager in inherited
 */
public class Person {
    protected String name;
    protected String lasName;
    protected String userName;
    protected String password;
    protected ArrayList<String> messages = new ArrayList<>();
    protected ArrayList<String> results = new ArrayList<>();

    /**
     * creates a person object with specified information
     * @param name of the person
     * @param lasName of the person
     * @param userName of the person
     * @param password of the person
     */
    public Person(String name, String lasName, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.lasName = lasName;
        this.password = password;
    }

    /**
     * returns name of this person
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * returns last name of this person
     * @return Last name
     */
    public String getLasName() {
        return lasName;
    }

    /**
     * returns username of this person
     * @return Username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * returns received messages of this person
     * @return Messages
     */
    public ArrayList<String> getMessages() {
        return messages;
    }

    /**
     * sends a given message from this person to another person
     * @param message text of the message
     * @param username of the receiver of this message
     */
    public void sendMessage(String message, String username){
        DataBase.getDataBase().get(username).getMessages().add(message);
    }

    /**
     * returns password of this person
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * returns result summary of this person
     * @return Results
     */
    public ArrayList<String> getResults() {
        return results;
    }
}
