import DataBase.*;
import UserInterface.*;
/**
 * The Main class from which execution flow of the program begins
 */
public class Main {
    /**
     * main method of Main class
     * @param args arguments to be passed to main method(empty)
     * @throws InterruptedException this is because we use Thread.sleep() function
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.print("\033\143");
        System.out.flush();
        System.out.println("Hi\nWelcome to our educational consulting system");
        Thread.sleep(4000);
        System.out.print("\033\143");
        System.out.flush();
        DataBase.retrieveDB();
        UserInterface.run();

    }
}