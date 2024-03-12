import bin.User;
import bin.UserManager;

import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Mystic Mayhem!");
        Map<String, User> users = UserManager.loadUsers();
        int currentUsers = users.size();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            scanner.nextLine();
            if (choice == "1") {
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();
                if (users.containsKey(username)) {
                    User user = users.get(username);
                    System.out.println("Welcome back, " + user.userName + "!");
                    System.out.println("You have " + user.getMoney() + " gold coins.");
                    System.out.println("You have " + user.getxp() + " xp.");
                    System.out.println("To enter the shop, type 'shop'.");
                    System.out.println("To exit, type 'exit'.");
                    while (true) {
                        System.out.print("Enter your choice: ");
                        String input = scanner.nextLine();
                        if (input.equals("shop")) {
                            user.Store();
                            UserManager.saveUsers(users);
                        } else if (input.equals("exit")) {
                            break;
                        } else {
                            System.out.println("Invalid choice!");
                        }
                    }
                } else {
                    System.out.println("User not found!");
                }
            } else if (choice == "2") {
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();
                if (users.containsKey(username)) {
                    System.out.println("Username already exists!");
                } else {
                    User user = new User(username, currentUsers);
                    users.put(username, user);
                    UserManager.saveUsers(users);
                    System.out.println("User registered successfully!");
                    currentUsers++;
                }
            } else if (choice == "3") {
                UserManager.saveUsers(users);
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }
}