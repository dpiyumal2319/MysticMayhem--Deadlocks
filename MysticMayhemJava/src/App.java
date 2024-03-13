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
        System.out.println("Welcome to Mystic Mayhem!");
        Map<String, User> users = UserManager.loadUsers();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(YELLOW+"Login[L]    "+RESET+GREEN+"Register[R]     "+RESET+RED+"Exit[E]     "+RESET);
            System.out.print("Enter your choice"+YELLOW+"[L]"+RESET+"/"+GREEN+"[R]"+RESET+"/"+RED+"[E]"+RESET+": ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("L")) {
                System.out.print("\nEnter your username: ");
                String username = scanner.nextLine();
                if (users.containsKey(username)) {
                    User user = users.get(username);
                    System.out.println("Successfully logged in to "+ user.userName+ " : " + user.userID);
                    System.out.println("Welcome back, " + user.userName + "!");
                    System.out.println("\nYou have " + user.getMoney() + " \uD83D\uDCB0"+"gold coins.");
                    System.out.println("\nYou have " + user.getxp() + " xp.");
                    System.out.println("\nYour home ground is " + user.homeGround + ".");
                    user.printInventory();
                    System.out.println("To enter the shop, type '" + BLUE + "shop" + RESET + "'.");
                    System.out.println("To exit, type '" + BLUE + "exit" + RESET + "'.");
                    while (true) {
                        System.out.print("Enter your choice[shop]/[exit]: ");
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
            } else if (choice.equalsIgnoreCase("R")) {
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();
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
                    User user = new User(username, currentUsers++, homeGround);
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
}