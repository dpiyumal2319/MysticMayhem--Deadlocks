import bin.Battle;
import bin.User;
import bin.UserManager;

import java.util.Map;
import java.util.Scanner;

// ANSI escape codes for text color
public static final String RESET = "\u001B[0m";
public static final String RED = "\u001B[31m";
public static final String GREEN = "\u001B[32m";
public static final String YELLOW = "\u001B[33m";
public static final String BLUE = "\u001B[34m";

public class App {
    public static void main(String[] args) throws Exception {
        
       
        System.out.println("  __  __                 _     _            __  __                   _                        ");
        System.out.println(" |  \\/  |               | |   (_)          |  \\/  |                 | |                       ");
        System.out.println(" | \\  / |  _   _   ___  | |_   _    ___    | \\  / |   __ _   _   _  | |__     ___   _ __ ___  ");
        System.out.println(" | |\\/| | | | | | / __| | __| | |  / __|   | |\\/| |  / _` | | | | | | '_ \\   / _ \\ | '_ ` _ \\ ");
        System.out.println(" | |  | | | |_| | \\__ \\ | |_  | | | (__    | |  | | | (_| | | |_| | | | | | |  __/ | | | | | |");
        System.out.println(" |_|  |_|  \\__, | |___/  \\__| |_|  \\___|   |_|  |_|  \\__,_|  \\__, | |_| |_|  \\___| |_| |_| |_|");
        System.out.println("            __/ |                                             __/ |                           ");
        System.out.println("           |___/                                             |___/                            "+"THE DEADLOCKS");

        
        System.out.println(YELLOW + "Welcome to Mystic Mayhem!" + RESET);
        Map<String, User> users = UserManager.loadUsers();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int nuOfEligibleUsers = eligibleUsers(users);
            System.out.println(YELLOW+"Login[L]    "+RESET+GREEN+"Register[R]     "+RESET+RED+"Exit[E]     "+RESET);
            System.out.print("Enter your choice"+YELLOW+"[L]"+RESET+"/"+GREEN+"[R]"+RESET+"/"+RED+"[E]"+RESET+": ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("L")) {
                System.out.print("\nEnter your username: ");

                String username = scanner.nextLine();
                if (users.containsKey(username)) {
                    User user = users.get(username);
                    System.out.println("Successfully logged in to " + user.userName + " : " + user.userID);
                    System.out.println("Welcome back, " + user.userName + "!");
                    System.out.println("\nYou have " + user.getMoney() + " \uD83D\uDCB0"+"gold coins.");
                    System.out.println("\nYou have " + user.getxp() + " xp.");
                    System.out.println("\nYour home ground is " + user.homeGround + ".");
                    user.printInventory();

                    System.out.println("To enter the shop, type '" + BLUE + "shop" + RESET + "'.");
                    System.out.println("To exit, type '" + BLUE + "exit" + RESET + "'.");
                    System.out.println("To enter the battle, type "+BLUE+"battle"+RESET+".");
                    while (true) {
                        System.out.print("\nEnter your choice[shop]/[exit]: ");

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
                            System.out.println("\n"+RED+"\u2717"+RESET+"Invalid choice!");
                        }
                    }
                } else {
                    System.out.println(RED + "User not found" + "\\u0021" + "!" + RESET);
                }
            } else if (choice.equalsIgnoreCase("R")) {
                System.out.print("\nEnter your username: ");
                String username = scanner.nextLine();
                if (users.containsKey(username)) {
                    System.out.println("\n ******** Username already exists! ********");
                } else {
                    // Selecting the Homeground by user choise
                    String homeGround;
                    while (true) {
                        System.out.println("\nSelect your home ground:");
                        System.out.println("Hillcrest [H]");
                        System.out.println("Marshland [M]");
                        System.out.println("Desert [D]");
                        System.out.println("Arcane [A]");
                        System.out.print("\nEnter the  corresponding letter to your home ground choice: ");
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
                    User user = new User(username, currentUsers++, homeGround);
                    users.put(username, user);
                    UserManager.saveNumberOfUsers(currentUsers);
                    UserManager.saveUsers(users);
                    System.out.println(YELLOW +"User registered successfully!"+RESET);
                    currentUsers++;
                }
            } else if (choice.equalsIgnoreCase("E")) {
                UserManager.saveUsers(users);
                break;
            } else {
                System.out.println("\n"+RED+"\u2717"+RESET+"Invalid choice!");
            }
        }
        scanner.close();
        System.out.println(YELLOW + "Goodbye!"+RESET);
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