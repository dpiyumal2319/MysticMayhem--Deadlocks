package bin;

import java.util.Scanner;
import bin.Warriors.*;

public class User extends SuperUserControls {
    private static final int UserIdStart = 100000;
    public final int userID;
    public int xp;
    public final String userName;
    private Squad squad;
    static Scanner scanner = new Scanner(System.in);
    static String input;

    public User(String userName, int currentUsers) {
        this.userID = UserIdStart + currentUsers + 1;
        this.xp = 0;
        this.userName = userName;
    }

    public void Store() {
        System.out.println("Welcome to the store " + this.userName + "!");
        printMoney();
        System.out.println("Do you want to buy[B] or sell[S]?");
        System.out.println("Inventory/Squad [I]");
        while (true) {
            System.out.println("Enter your choice [B/S/I]: ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("I"))
                printSquad();
            else if (input.equalsIgnoreCase("S")) {
                printMoney();
                System.out.println("Currently you can only sell Warriors");
                System.out.println("Which warrior do you want to sell?");
                System.out.println("Archer[A], Knight[K], Mage[M], Healer[H], Mythical Creature[MC]");
                while (true) {
                    System.out.println("Enter your choice, Which warrior you want to sell:");
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("A"))
                        sell(squad.getArcher());
                    else if (input.equalsIgnoreCase("K"))
                        sell(squad.getKnight());
                    else if (input.equalsIgnoreCase("M"))
                        sell(squad.getMage());
                    else if (input.equalsIgnoreCase("H"))
                        sell(squad.getHealer());
                    else if (input.equalsIgnoreCase("MC"))
                        sell(squad.getMythicalCreature());
                    else {
                        System.out.println("Invalid choice!");
                        continue;
                    }
                    break;
                }
            } else if (input.equalsIgnoreCase("B")) {
                printMoney();
                System.out.println("What do you want to buy?");
                System.out.println("Warrior[W], Equipment[E]");
                while (true) {
                    System.out.println("Enter your choice, What do you want to buy:");
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("W")) {
                        System.out.println("Which warrior do you want to buy?");
                        System.out.println("Archer[A], Knight[K], Mage[M], Healer[H], Mythical Creature[MC]");
                        while (true) {
                            System.out.println("Enter your choice, Which warrior you want to buy:");
                            input = scanner.nextLine();
                            if (input.equalsIgnoreCase("A")) {
                                buy(squad.getArcher(), "Archer");
                            } else if (input.equalsIgnoreCase("K")) {
                                buy(squad.getKnight(), "Knight");
                            } else if (input.equalsIgnoreCase("M")) {
                                buy(squad.getMage(), "Mage");
                            } else if (input.equalsIgnoreCase("H")) {
                                buy(squad.getHealer(), "Healer");
                            } else if (input.equalsIgnoreCase("MC")) {
                                buy(squad.getMythicalCreature(), "MythicalCreature");
                            } else {
                                System.out.println("Invalid choice!");
                                continue;
                            }
                            break;
                        }
                    } else {
                        System.out.println("Invalid choice!");
                        continue;
                    }
                }
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private void buy(InventoryItem item, String type) {
        if (item != null) {
            System.out.println("You already have this warrior!");
            System.out.println("Do you want to Sell it?");
            System.out.println("Yes[Y] or No[N]");
            while (true) {
                System.out.println("Enter your choice: ");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    sell(item);
                    break;
                } else if (input.equalsIgnoreCase("N")) {
                    return;
                } else {
                    System.out.println("Invalid choice!");
                }
            }
        }
        System.out.println("Do you want to buy a new " + type + "?");
        System.out.println("Yes[Y] or No[N]");
        while (true) {
            System.out.println("Enter your choice [Y/N]: ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("Y")){
                System.out.println("Which " + type + " do you want to buy?");
                InventoryItem.printMap(type);
                System.out.println("Enter the name of the " + type + " you want to buy: ");
                while (true) {
                    System.out.println("Enter your choice [name]: ");
                    input = scanner.nextLine();
                    if (InventoryItem.getWarriorMap(type).containsKey(input)) {
                        if (getMoney() >= InventoryItem.getWarriorMap(type).get("input").price) {
                            squad.setSquadMate(InventoryItem.getWarriorMap(type).get(input), type);
                            printMoney();
                            break;
                        } else {
                            System.out.println("You don't have enough money!");
                            break;
                        }
                    } else {
                        System.out.println("Invalid choice!");
                    }
                }
            }
        }
    }

    private void sell(InventoryItem item) {
        if (item == null) {
            System.out.println("You don't currently have this warrior!");
            return;
        } else {
            System.out.println("Do you want to sell your " + item.name + " for " + item.value + " coins?");
            System.out.println("Yes[Y] or No[N]");
            while (true) {
                System.out.println("Enter your choice: ");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    getMoneyFrom(item);
                    squad.setSquadMate(null, item.type);
                    System.out.println("You have sold your " + item.name + " for " + item.value + " coins.");
                    printMoney();
                    break;
                } else if (input.equalsIgnoreCase("N")) {
                    break;
                } else {
                    System.out.println("Invalid choice!");
                }
            }
        }
    }

    private void printMoney() {
        System.out.println("You have " + getMoney() + " coins.");
    }

    private void printSquad() {
        System.out.println("Your squad:");
        squad.getArcher().printInfo();
        squad.getKnight().printInfo();
        squad.getMage().printInfo();
        squad.getHealer().printInfo();
        squad.getMythicalCreature().printInfo();
    }
}