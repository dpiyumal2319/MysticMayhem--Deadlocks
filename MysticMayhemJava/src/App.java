import bin.Battle;
import bin.User;
import bin.UserManager;

import java.util.Map;
import java.util.Scanner;

/*Todo
 * Balance when other users and current user do not have all warriors.
 */

public class App {
    public static void main(String[] args) throws Exception {
        int nuOfEligibleUsers = 0;
        System.out.println("Welcome to Mystic Mayhem!");
        Map<String, User> users = UserManager.loadUsers();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            nuOfEligibleUsers = eligibleUsers(users);
            System.out.println("Currently there are " + nuOfEligibleUsers + " you can battle with.");
            System.out.println("Login[L]");
            System.out.println("Register[R]");
            System.out.println("Exit[E]");
            System.out.println("DeleteAccount[D]");
            System.out.print("Enter your choice[L]/[R]/[E]/[D]: ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("D")){
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();
                if (users.containsKey(username)) {
                    users.remove(username);
                    UserManager.saveUsers(users);
                    System.out.println("User deleted successfully!");
                } else {
                    System.out.println("User not found!");
                }
            }
            else if (choice.equalsIgnoreCase("L")) {
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();
                if (users.containsKey(username)) {
                    User user = users.get(username);
                    System.out.println("Successfully logged in to " + user.userName + " : " + user.userID);
                    System.out.println("Welcome back, " + user.userName + "!");
                    System.out.println("You have " + user.getMoney() + " gold coins.");
                    System.out.println("You have " + user.getxp() + " xp.");
                    System.out.println("Your home ground is " + user.homeGround + ".");
                    user.printInventory();
                    System.out.println("To enter the shop, type 'shop'.");
                    System.out.println("To exit, type 'exit'.");
                    System.out.println("To enter the battle, type 'battle'.");
                    while (true) {
                        System.out.print("Enter your choice[shop]/[exit]/[battle]: ");
                        String input = scanner.nextLine();
                        if (input.equals("shop")) {
                            boolean save = user.Store();
                            if (save) {
                                UserManager.saveUsers(users);
                                System.out.println("Saved successfully!");
                            } else {
                                System.out.println("Exitted without saving!");
                            }
                        } else if (input.equals("exit")) {
                            break;
                        } else if (input.equals("battle")) {
                            if (user.isAllWarriorsAwailable()) {
                                if (nuOfEligibleUsers < 1) {
                                    System.out.println("Not enough users to start the battle!");
                                } else {
                                    Battle.start(user, users);
                                    UserManager.saveUsers(users);
                                }
                            } else {
                                System.out.println(
                                        "You do not have all warriors. Please buy all warriors to enter the battle.");
                            }
                        } else {
                            System.out.println("Invalid choice!");
                        }
                    }
                } else {
                    System.out.println("User not found!");
                }
            } else if (choice.equalsIgnoreCase("R")) {
                String name;
                String username;
                System.out.print("Enter your username: ");
                username = scanner.nextLine();
                System.out.print("Enter your name: ");
                name = scanner.nextLine();
                if (users.containsKey(username)) {
                    System.out.println("Username already exists!");
                } else {
                    // Selecting the Homeground by user choise
                    String homeGround;
                    while (true) {
                        System.out.println("Select your home ground:");
                        System.out.println("Hillcrest [H]");
                        System.out.println("Marshland [M]");
                        System.out.println("Desert [D]");
                        System.out.println("Arcane [A]");
                        System.out.print("Enter the  corresponding letter to your home ground choice: ");
                        String homeGroundChoice;

                        homeGroundChoice = scanner.nextLine();
                        // According to the users choice we adding the homeground to the user
                        if (homeGroundChoice.equalsIgnoreCase("H")) {
                            homeGround = "Hillcrest";
                            break;
                        } else if (homeGroundChoice.equalsIgnoreCase("M")) {
                            homeGround = "Marshland";
                            break;
                        } else if (homeGroundChoice.equalsIgnoreCase("D")) {
                            homeGround = "Desert";
                            break;
                        } else if (homeGroundChoice.equalsIgnoreCase("A")) {
                            homeGround = "Arcane";
                            break;
                        } else {
                            System.out.println("Invalid option! Please try again.");
                        }
                    }
                    int currentUsers = UserManager.loadNumberOfUsers();
                    User user = new User(name ,username, currentUsers++, homeGround);
                    users.put(username, user);
                    UserManager.saveNumberOfUsers(currentUsers);
                    UserManager.saveUsers(users);
                    System.out.println("User registered successfully!");
                    currentUsers++;
                }
            } else if (choice.equalsIgnoreCase("E")) {
                UserManager.saveUsers(users);
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
        scanner.close();
        System.out.println("Goodbye!");
    }

    public static int eligibleUsers(Map<String, User> users) {
        int count = 0;
        for (User user : users.values()) {
            if (user.isAllWarriorsAwailable()) {
                count++;
            }
        }
        return count;
    }
}