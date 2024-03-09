package Shop;

import java.util.Map;
import java.util.Scanner;
import Warrior.Warrior;
import Equipments.Equipments;
import UserManager.User;
import Warrior.WarriorCatalog;
import Equipments.EquipmentCatalog;

public abstract class Shop {
    static Object[][] inventory;

    public static void enterShop(User user) {
        System.out.println("Welcome to the shop " + user.userName + "!");
        printMoney(user);
        inventory = user.getInventory();
        printInventory(inventory);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to buy[B] or sell[S]?");
            System.out.println("To check your inventory : [I].");
            System.out.println("To exit the shop : [Q].");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("B")) {
                buy(user);
            } else if (input.equalsIgnoreCase("S")) {
                // sell(user);
            } else if (input.equalsIgnoreCase("Q")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equalsIgnoreCase("I")) {
                printInventory(inventory);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        System.out.println("Exiting shop.");
        return;
    }

    private static void buy(User user) {
        printMoney(user);
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Do you want to buy a warrior[W] or an equipment[E] to a warrior?. To exit buy mode type [Q].");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("W")) {
                buyWarrior(user);
            } else if (input.equalsIgnoreCase("E")) {
                // buyEquipment(user);
            } else if (input.equalsIgnoreCase("Q")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        System.out.println("Exiting buy mode.");
        scanner.close();
    }

    private static void buyWarrior(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which warrior do you want to buy?");
        System.out.println("Archer[A], Healer[H], Knight[K], Mage[M], MythicalCreature[MC].");
        System.out.println("To check your inventory : [I].");
        System.out.println("To move back : [Q].");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("A")) {
                buyOrReplaceWarrior(user, "Archer");
            } else if (input.equalsIgnoreCase("H")) {
                buyOrReplaceWarrior(user, "Healer");
            } else if (input.equalsIgnoreCase("K")) {
                buyOrReplaceWarrior(user, "Knight");
            } else if (input.equalsIgnoreCase("M")) {
                buyOrReplaceWarrior(user, "Mage");
            } else if (input.equalsIgnoreCase("MC")) {
                buyOrReplaceWarrior(user, "MythicalCreature");
            } else if (input.equalsIgnoreCase("I")) {
                printInventory(inventory);
            } else if (input.equalsIgnoreCase("Q")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void buyOrReplaceWarrior(User user, String warriorType) {
        Scanner scanner = new Scanner(System.in);
        if (user.getWarrior(warriorType) != null) {
            System.out.println("You already have a " + warriorType + ".");
            System.out.println("Do you want to replace it? [Y/N]. Existing " + warriorType + " will be sold for "
                    + user.getWarrior(warriorType).getValue() * 0.9 + " coins.");
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    double value = user.removeWarrior(warriorType);
                    user.setMoney(user.getMoney() + value * 0.9);
                    System.out.println("You have sold your " + warriorType + " for " + value * 0.9 + " coins.");
                    System.out.println("You have " + user.getMoney() + " coins.");
                    break;
                } else if (input.equalsIgnoreCase("N")) {
                    return;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }
        Map<String, WarriorCatalog> warriors = Warrior.getWarriorMap(warriorType);
        if (user.getWarrior(warriorType) == null) {
            System.out.println("Which " + warriorType + " do you want to buy? Enter the name of the " + warriorType + ".");
            System.out.println("To abort the purchase : [Q].");
            printWarriorCatalog(warriorType);
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Q")) {
                    return;
                } else if (warriors.containsKey(input)) {
                    WarriorCatalog warrior = warriors.get(input);
                    if (user.getMoney() >= warrior.getPrice()) {
                        user.setMoney(user.getMoney() - warrior.getPrice());
                        user.setwarrior(warriorType, input.toLowerCase()); // Take lower case to match the warrior name and remove case sensitivity
                        System.out.println("You have bought " + input + " for " + warrior.getPrice() + " coins.");
                        printMoney(user);
                        inventory = user.getInventory();
                        return;
                    } else {
                        System.out.println("You don't have enough money to buy " + input + ".");
                        printMoney(user);
                        return;
                    }
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }
    }

    private static void buyEquipment(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which warrior do you want to buy equipment for?");
        System.out.println("Archer[A], Healer[H], Knight[K], Mage[M], MythicalCreature[MC].");
        System.out.println("To check your inventory : [I].");
        System.out.println("To move back : [Q].");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("A")) {
                buyEquipmentForWarrior(user, "Archer");
            } else if (input.equalsIgnoreCase("H")) {
                buyEquipmentForWarrior(user, "Healer");
            } else if (input.equalsIgnoreCase("K")) {
                buyEquipmentForWarrior(user, "Knight");
            } else if (input.equalsIgnoreCase("M")) {
                buyEquipmentForWarrior(user, "Mage");
            } else if (input.equalsIgnoreCase("MC")) {
                buyEquipmentForWarrior(user, "MythicalCreature");
            } else if (input.equalsIgnoreCase("I")) {
                printInventory(inventory);
            } else if (input.equalsIgnoreCase("Q")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void printInventory(Object[][] inventory) {
        String[] warriorTypes = { "Archer", "Healer", "Knight", "Mage", "MythicalCreature" };
        int i = 0;
        for (Object[] warrior : inventory) {
            if (warrior != null) {
                System.out.println(warriorTypes[i] + " : " + ((Warrior) warrior[0]).getName());
                String armor = warrior[1] == null ? "None" : ((Equipments) warrior[1]).getName();
                String artefact = warrior[2] == null ? "None" : ((Equipments) warrior[2]).getName();
                System.out.println("\tArmor : " + armor);
                System.out.println("\tArtefact : " + artefact);
                System.out.println("\tHealth : " + ((Warrior) warrior[0]).getHealth());
                System.out.println("\tAttack : " + ((Warrior) warrior[0]).getAttack());
                System.out.println("\tDefense : " + ((Warrior) warrior[0]).getDefense());
                System.out.println("\tSpeed : " + ((Warrior) warrior[0]).getSpeed());
                System.out.println("\tValue : " + ((Warrior) warrior[0]).getValue());
            } else {
                System.out.println(warriorTypes[i] + " : None");
            }
            i++;
        }
    }

    private static void printWarriorCatalog(String warriorType) {
        Map<String, WarriorCatalog> warriors = Warrior.getWarriorMap(warriorType);
        System.out.println("Awailable " + warriorType + "s:");
        for (Map.Entry<String, WarriorCatalog> entry : warriors.entrySet()) {
            System.out.println("\t" + entry.getKey() + " : " + entry.getValue().getPrice() + " coins.");
            System.out.println("\t\tHealth: " + entry.getValue().getHealth());
            System.out.println("\t\tAttack: " + entry.getValue().getAttack());
            System.out.println("\t\tDefense: " + entry.getValue().getDefense());
            System.out.println("\t\tSpeed: " + entry.getValue().getSpeed());
        }
    }



    private static void printMoney(User user) {
        System.out.println("You have " + user.getMoney() + " coins.");
    }
}