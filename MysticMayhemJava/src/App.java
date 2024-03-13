import bin.Battle;
import bin.User;
import bin.UserManager;

import java.util.Map;
import java.util.Scanner;



public class App {
    // ANSI escape codes for text color
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
        
       
        System.out.println("  __  __                 _     _            __  __                   _                        ");
        System.out.println(" |  \\/  |               | |   (_)          |  \\/  |                 | |                       ");
        System.out.println(" | \\  / |  _   _   ___  | |_   _    ___    | \\  / |   __ _   _   _  | |__     ___   _ __ ___  ");
        System.out.println(" | |\\/| | | | | | / __| | __| | |  / __|   | |\\/| |  / _` | | | | | | '_ \\   / _ \\ | '_ ` _ \\ ");
        System.out.println(" | |  | | | |_| | \\__ \\ | |_  | | | (__    | |  | | | (_| | | |_| | | | | | |  __/ | | | | | |");
        System.out.println(" |_|  |_|  \\__, | |___/  \\__| |_|  \\___|   |_|  |_|  \\__,_|  \\__, | |_| |_|  \\___| |_| |_| |_|");
        System.out.println("            __/ |                                             __/ |                           ");
        System.out.println("           |___/                                             |___/                            "+"-THE DEADLOCKS-");

        
        System.out.println(BRIGHT_YELLOW + "\n\nWelcome to Mystic Mayhem!\n" + RESET);
        Map<String, User> users = UserManager.loadUsers();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int nuOfEligibleUsers = eligibleUsers(users);
            System.out.println(BRIGHT_MAGENTA+"Login[L]\n"+RESET+BRIGHT_GREEN+"Register[R]\n"+RESET+BRIGHT_RED+"Exit[E]\n"+RESET);
            System.out.print("Enter your choice"+BRIGHT_MAGENTA+" [L]"+RESET+"/"+BRIGHT_GREEN+"[R]"+RESET+"/"+BRIGHT_RED+"[E]"+RESET+": ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("L")) {
                System.out.print("\nEnter your username: ");

                String username = scanner.nextLine();
                if (users.containsKey(username)) {
                    User user = users.get(username);
                    System.out.println("\nSuccessfully logged in to " + user.userName + " : " + user.userID);
                    System.out.println(BRIGHT_YELLOW+"Welcome back, " + user.userName + "!"+RESET);
                    System.out.println(CYAN+"You have " + user.getxp() + " xp."+RESET);
                    System.out.println(YELLOW+"You have " + user.getMoney()+" gold coins."+RESET);
                    System.out.println("\nYour home ground is " + user.homeGround + ".\n");
                    user.printInventory();

                    System.out.println("To enter the shop, type '" + BLUE + "shop" + RESET + "'.");
                    System.out.println("To exit, type '" + BLUE + "exit" + RESET + "'.");
                    System.out.println("To enter the battle, type "+BLUE+"'battle'"+RESET+".");
                    while (true) {
                        System.out.print("\nEnter your choice "+BRIGHT_GREEN+"[shop]/[exit]/[battle]: "+RESET);

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
                            System.out.println("\n"+BRIGHT_RED+"\u2717"+RESET+"Invalid choice!");
                        }
                    }
                } else {
                    System.out.println(BRIGHT_RED + "User not found" + "!" + RESET);
                }
            } else if (choice.equalsIgnoreCase("R")) {
                System.out.print("\nEnter your username: ");
                String username = scanner.nextLine();
                if (users.containsKey(username)) {
                    System.out.println(RED+"\n ******** Username already exists! ********\n"+RESET);
                } else {
                    // Selecting the Homeground by user choise
                    String homeGround;
                    while (true) {
                        System.out.println("\nSelect your home ground:\n");
                        System.out.println(BLUE+"Hillcrest [H]"+RESET);
                        System.out.println(BLUE+"Marshland [M]"+RESET);
                        System.out.println(BLUE+"Desert [D]"+RESET);
                        System.out.println(BLUE+"Arcane [A]"+RESET);
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
                            System.out.println(RED+"Invalid option! Please try again."+RESET);
                        }
                    }
                    int currentUsers = UserManager.loadNumberOfUsers();
                    User user = new User(username, currentUsers++, homeGround);
                    users.put(username, user);
                    UserManager.saveNumberOfUsers(currentUsers);
                    UserManager.saveUsers(users);
                    System.out.println(BRIGHT_GREEN +"User registered successfully!\n"+RESET);
                    currentUsers++;
                }
            } else if (choice.equalsIgnoreCase("E")) {
                UserManager.saveUsers(users);
                break;
            } else {
                System.out.println("\n"+BRIGHT_RED+"Invalid choice!"+RESET);
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