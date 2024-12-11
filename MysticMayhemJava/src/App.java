import bin.Battle;
import bin.UIElements;
import bin.User;
import bin.UserManager;

import java.util.Map;
import java.util.Scanner;

public class App {
    // ANSI escape codes for text color
    private static final int readTime = 2000;
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String BRIGHT_GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_MAGENTA = "\u001B[95m";
    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static void main(String[] args) throws Exception {
        UIElements.clearTerminal();
        int nuOfEligibleUsers = 0;

        System.out.println("Open in maximize window for better experience.");
        System.out.println("Launching Mystic Mayhem...");
        UIElements.wait(2500);
        System.out.println(BRIGHT_YELLOW + "\n\nWelcome to Mystic Mayhem!\n" + RESET);
        UIElements.wait(1000);
        Map<String, User> users = UserManager.loadUsers();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            UIElements.clearTerminal();
            UIElements.printLogo();
            nuOfEligibleUsers = eligibleUsers(users);
            System.out.println(BRIGHT_MAGENTA + "Login[L]\n" + RESET + BRIGHT_GREEN + "Register[R]\n" + RESET
                    + BRIGHT_RED + "Exit[E]\n" + RESET);
            System.out.print("Enter your choice" + BRIGHT_MAGENTA + " [L]" + RESET + "/" + BRIGHT_GREEN + "[R]" + RESET
                    + "/" + BRIGHT_RED + "[E]" + RESET + ": ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("L")) {
                System.out.print("\nEnter your username: ");

                String username = scanner.nextLine();
                if (users.containsKey(username)) {
                    System.out.println("Logging in...");
                    UIElements.wait(1250);
                    User user = users.get(username);
                    System.out.println("\nSuccessfully logged in to " + user.userName + " : " + user.userID);
                    UIElements.wait(500);
                    while (true) {
                        UIElements.clearTerminal();
                        UIElements.printLogo();
                        UIElements.wait(UIElements.timeBetween);
                        System.out.println(BRIGHT_YELLOW + "Welcome back, " + user.name + "!" + RESET);
                        UIElements.wait(UIElements.timeBetween);
                        System.out.println(CYAN + "You have " + user.getxp() + " xp." + RESET);
                        UIElements.wait(UIElements.timeBetween);
                        System.out.println("\nYour home ground is " + user.homeGround + ".\n");
                        UIElements.wait(UIElements.timeBetween);
                        user.printInventory();
                        UIElements.wait(UIElements.timeBetween);
                        System.out.println("To enter the shop, type '" + BRIGHT_YELLOW + "shop" + RESET + "'.");
                        System.out.println("To exit, type '" + BRIGHT_YELLOW + "exit" + RESET + "'.");
                        System.out.println("To enter the battle, type " + BRIGHT_YELLOW + "'battle'" + RESET + ".");
                        System.out.println("To reset your account, type " + BRIGHT_YELLOW + "'reset'" + RESET + ".");
                        System.out.print(
                                "\nEnter your choice " + BRIGHT_GREEN + "[shop]/[exit]/[battle]/[reset]: " + RESET);

                        String input = scanner.nextLine();
                        if (input.equals("reset")) {
                            while (true) {
                                System.out.println(
                                        "Are you sure you want to reset your account?" + BRIGHT_GREEN + "[Y/N]"
                                                + RESET);
                                String reset = scanner.nextLine();
                                if (reset.equalsIgnoreCase("Y")) {
                                    user.resetUser();
                                    UserManager.saveUsers(users);
                                    System.out.println("Account reset successfully!");
                                    UIElements.wait(readTime);
                                    break;
                                } else if (reset.equalsIgnoreCase("N")) {
                                    System.out.println("Account reset cancelled!");
                                    UIElements.wait(readTime);
                                    break;
                                } else {
                                    System.out.println(RED + "Invalid choice!" + RESET);
                                    UIElements.wait(500);
                                }
                            }
                        } else if (input.equals("shop")) {
                            ;
                            boolean save = user.Store();
                            if (save) {
                                UserManager.saveUsers(users);
                                System.out.println("Saving...");
                                UIElements.wait(2000);
                                System.out.println("Saved successfully!");
                                UIElements.wait(500);
                            } else {
                                users = UserManager.loadUsers();
                                user = users.get(username);
                                System.out.println("Exitting without saving!");
                                UIElements.wait(2000);
                            }
                        } else if (input.equals("exit")) {
                            break;
                        } else if (input.equals("battle")) {
                            if (user.isAllWarriorsAwailable()) {
                                if (nuOfEligibleUsers < 1) {
                                    System.out.println("Not enough users to start the battle!");
                                    UIElements.wait(readTime);
                                } else {
                                    UIElements.clearTerminal();
                                    Battle.start(user, users);
                                    System.out.println("Saving...");
                                    UIElements.wait(1000);
                                    UserManager.saveUsers(users);
                                    System.out.println("Saved successfully!");
                                    UIElements.wait(500);
                                }
                            } else {
                                System.out.println(
                                        "You do not have all warriors. Please buy all warriors to enter the battle.");
                                UIElements.wait(readTime);
                            }
                        } else {
                            System.out.println("\n" + BRIGHT_RED + "Invalid choice!" + RESET);
                            UIElements.wait(500);
                        }
                    }
                } else {
                    System.out.println(BRIGHT_RED + "User not found" + "!" + RESET);
                    UIElements.wait(readTime);
                }
            } else if (choice.equalsIgnoreCase("R")) {
                String name;
                String username;
                System.out.print("Enter your username: ");
                username = scanner.nextLine();
                System.out.print("Enter your name: ");
                name = scanner.nextLine();
                if (users.containsKey(username)) {
                    System.out.println(RED + "\n ******** Username already exists! ********\n" + RESET);
                    UIElements.wait(readTime);
                } else {
                    // Selecting the Homeground by user choise
                    String homeGround;
                    while (true) {
                        System.out.println("\nSelect your home ground:\n");
                        System.out.println(BRIGHT_YELLOW + "Hillcrest [H]" + RESET);
                        System.out.println(BRIGHT_YELLOW + "Marshland [M]" + RESET);
                        System.out.println(BRIGHT_YELLOW + "Desert [D]" + RESET);
                        System.out.println(BRIGHT_YELLOW + "Arcane [A]" + RESET);
                        System.out.print("\nEnter the corresponding letter to your home ground choice: ");
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
                            System.out.println(RED + "Invalid option! Please try again." + RESET);
                        }
                    }
                    int currentUsers = UserManager.loadNumberOfUsers();
                    User user = new User(name, username, currentUsers++, homeGround);
                    users.put(username, user);
                    UserManager.saveNumberOfUsers(currentUsers);
                    UserManager.saveUsers(users);
                    System.out.println(BRIGHT_GREEN + "User registered successfully!\n" + RESET);
                    UIElements.wait(readTime);
                    currentUsers++;
                }
            } else if (choice.equalsIgnoreCase("E")) {
                UserManager.saveUsers(users);
                break;
            } else {
                System.out.println("\n" + BRIGHT_RED + "Invalid choice!" + RESET);
                UIElements.wait(readTime);
            }
        }
        scanner.close();
        System.out.println(YELLOW + "Goodbye!" + RESET);
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