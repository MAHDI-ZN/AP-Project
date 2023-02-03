package DataBase;
import Users.*;
import UserInterface.*;

import java.util.HashMap;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * This class is the local database of the program and interacts with redis
 */
public class DataBase {

    private static HashMap<String, Student> studentDB = new HashMap<>();
    private static HashMap<String, Consultant> consultantDB = new HashMap<>();
    private static HashMap<String, Manager> managerDB = new HashMap<>();
    private static HashMap<String, Person> DataBase = new HashMap<>();

    /**
     * returns all students who have registered
     * @return Students
     */
    public static HashMap<String, Student> getStudentDB() {
        return studentDB;
    }

    /**
     * returns all consultants who have registered
     * @return Consultants
     */
    public static HashMap<String, Consultant> getConsultantDB() {
        return consultantDB;
    }

    /**
     * returns every person that have been registered
     * @return Persons
     */
    public static HashMap<String, Person> getDataBase() {
        return DataBase;
    }
    /**
     * returns all managers who have been registered
     * @return Managers
     */

    public static HashMap<String, Manager> getManagerDB() {
        return managerDB;
    }
    private static RedisClient client = RedisClient.create("redis://password@127.0.0.1:6379/0");
    private static StatefulRedisConnection connection = client.connect();
    private static RedisCommands syncCommands = connection.sync();
    private static Gson gson = new Gson();

    /**
     * adds a student to database
     * @param student to be added
     */
    public static void addStudent(Student student){
        DataBase.put(student.getUserName(), student);
        studentDB.put(student.getUserName(), student);
        syncCommands.set("studentDB", gson.toJson(studentDB));
        syncCommands.set("DataBase", gson.toJson(DataBase));
    }

    /**
     * adds a consultant to database
     * @param consultant to be added
     */
    public static void addConsultant(Consultant consultant){
        DataBase.put(consultant.getUserName(), consultant);
        consultantDB.put(consultant.getUserName(), consultant);
        syncCommands.set("consultantDB", gson.toJson(consultantDB));
        syncCommands.set("DataBase", gson.toJson(DataBase));
    }

    /**
     * adds a manager to database
     * @param manager to be added
     */
    public static void addManager(Manager manager){
        DataBase.put(manager.getUserName(), manager);
        managerDB.put(manager.getUserName(), manager);
        syncCommands.set("managerDB", gson.toJson(managerDB));
        syncCommands.set("DataBase", gson.toJson(DataBase));
    }

    /**
     * resets redis database with newly added information
     */
    public static void refreshDB(){
        syncCommands.set("studentDB", gson.toJson(studentDB));
        syncCommands.set("consultantDB", gson.toJson(consultantDB));
        syncCommands.set("managerDB", gson.toJson(managerDB));
        syncCommands.set("DataBase", gson.toJson(DataBase));
    }

    /**
     * retrieves information from redis database
     */
    public static void retrieveDB() {
        if (syncCommands.get("DataBase") == null) {
            UserInterface.initializeDB();
        } else {
            Type studentType = new TypeToken<HashMap<String, Student>>() {
            }.getType();
            Type consultantType = new TypeToken<HashMap<String, Consultant>>() {
            }.getType();
            Type managerType = new TypeToken<HashMap<String, Manager>>() {
            }.getType();
            studentDB = (HashMap<String, Student>) gson.fromJson((String) syncCommands.get("studentDB"), studentType);
            consultantDB = (HashMap<String, Consultant>) gson.fromJson((String) syncCommands.get("consultantDB"), consultantType);
            managerDB = (HashMap<String, Manager>) gson.fromJson((String) syncCommands.get("managerDB"), managerType);
            DataBase.putAll(studentDB);
            DataBase.putAll(managerDB);
            DataBase.putAll(consultantDB);
        }
    }
}
