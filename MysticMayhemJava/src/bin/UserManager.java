package bin;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager implements Serializable {
    private static final String USERS_FILE = "users.ser";
    private static final String NUMBER_OF_USERS_FILE = "numberOfUsers.ser";

    public static void saveNumberOfUsers(int numberOfUsers) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(NUMBER_OF_USERS_FILE))) {
            out.writeInt(numberOfUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int loadNumberOfUsers() {
        File file = new File(NUMBER_OF_USERS_FILE);
        if (!file.exists()) {
            return 0; // Return 0 if the file does not exist
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return in.readInt();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void saveUsers(Map<String, User> users) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            out.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, User> loadUsers() {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            return new HashMap<>();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            return (Map<String, User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
