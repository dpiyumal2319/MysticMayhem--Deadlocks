package Shop;

import java.util.Map;
import java.util.Scanner;

import UserManager.User;
import Warrior.Warrior;
import Equipments.Equipments;

public abstract class Shop {
    static Map<Warrior, Equipments[]> inventory;

    public static void enterShop(User user) {
        System.out.println("Welcome to the shop " + user.userName + "!");
        System.out.println("You have " + user.getMoney() + " coins.");
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
                sell(user);
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
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "Do you want to buy a warrior[W] or an equipment[E] to a warrior?. To exit buy mode type [Q].");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("W")) {
                buyWarrior(user);
            } else if (input.equalsIgnoreCase("E")) {
                buyEquipment(user);
            } else if (input.equalsIgnoreCase("Q")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        System.out.println("Exiting buy mode.");
    }

    private static void buyWarrior(User user) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Which warrior do you want to buy?");
            System.out.println("Archer[A], Healer[H], Knight[K], Mage[M], MythicalCreature[MC].");
            System.out.println("To check your inventory : [I].");
            System.out.println("To exit buy mode : [Q].");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("I")) {
                printInventory(inventory);
            } else if(input.equalsIgnoreCase("A")) {
                
            }
        }
    }

    private static void printInventory(Map<Warrior, Equipments[]> inventory) {
        String[] warriorTypes = { "Archer", "Healer", "Knight", "Mage", "MythicalCreature" };
        int i = 0;
        for (Map.Entry<Warrior, Equipments[]> entry : inventory.entrySet()) {
            if (entry.getValue()[0] != null) {
                String armorName = entry.getValue()[0] == null ? "None" : entry.getValue()[0].getName();
                String artefactName = entry.getValue()[1] == null ? "None" : entry.getValue()[1].getName();
                System.out.println(warriorTypes[i] + ": " + entry.getKey().getName() + " Armor: " + armorName
                        + " Artefact: " + artefactName);
            } else {
                System.out.println("No " + warriorTypes[i++] + " found in inventory.");
            }
        }
    }
}