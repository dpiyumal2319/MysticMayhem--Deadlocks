package bin;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager implements Serializable{
    private static final String USERS_FILE = "users.ser";

    public static void saveUsers(Map<String, User> users) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            out.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public static Map<String, User> loadUsers() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            return (Map<String, User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}