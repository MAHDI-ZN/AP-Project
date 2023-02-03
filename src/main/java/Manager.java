import java.util.ArrayList;

/**
 * This is a wrapper class for the manager which is extended from Person
 */
public class Manager extends Person{
    /**
     * creates a manager object with specified states
     * @param name of the manager
     * @param lasName of the manager
     * @param userName ot the manager
     * @param password of the manager
     */
    public Manager(String name, String lasName, String userName, String password) {
        super(name, lasName, userName, password);
    }
    private ArrayList<Consultant> consultants = new ArrayList<>();

    /**
     * special purpose method for manager that overrides sendMessage method of Person
     * @param message text of the message
     * @param username of the receiver
     */
    @Override
    public void sendMessage(String message, String username){
        super.sendMessage("Hi I'm your manager\n"+message, username);
    }

    /**
     * adds a consultant to the manager's list of consultants
     * @param consultant to be added
     */
    public void addConsultant(Consultant consultant){
        consultants.add(consultant);
    }

    /**
     * removes a consultant from manager's list of consultants
     * @param consultant to be removed
     */
    public void removeConsultant(String consultant){
        for (int i = 0; i < consultants.size(); i++) {
            if(consultant.equals(consultants.get(i).getUserName()))
                consultants.remove(i);
        }
    }

    /**
     * returns all consultants of this manager
     * @return Consultants
     */
    public ArrayList<Consultant> getConsultants() {
        return consultants;
    }

    /**
     * recieves feedbacks of the students about a certain consultant
     * @param feedback that has been taken from the students
     * @param username of the consultant about whom feedback is taken
     */
    public static void getFeedBack(Feedback feedback, String username){
        DataBase.getConsultantDB().get(username).results.add(feedback.toString());
    }
}
