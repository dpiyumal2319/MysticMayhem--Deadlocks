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
        printInventory(user);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to buy[B] or sell[S]?");
        System.out.println("To check your inventory : [I].");
        System.out.println("To exit the shop : [Q].");
        while (true) {
            System.out.println("Enter your choice of buy or sell.");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("B")) {
                buy(user);
            } else if (input.equalsIgnoreCase("S")) {
                sell(user);
            } else if (input.equalsIgnoreCase("Q")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equalsIgnoreCase("I")) {
                printInventory(user);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        System.out.println("Exiting shop.");
        scanner.close();
        return;
    }

    private static void sell(User user) {
        printMoney(user);
        System.out.println("You can only sell warriors for now.");
        System.out.println("Which warrior do you want to sell?");
        System.out.println("Archer[A], Healer[H], Knight[K], Mage[M], MythicalCreature[MC].");
        System.out.println("To check your inventory : [I].");
        System.out.println("To move back : [Q].");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your choice of warrior to sell.");
            String input = scanner.nextLine();
            switch (input) {
                case "A":
                    sellWarrior(user, "Archer");
                    break;
                case "H":
                    sellWarrior(user, "Healer");
                    break;
                case "K":
                    sellWarrior(user, "Knight");
                    break;
                case "M":
                    sellWarrior(user, "Mage");
                    break;
                case "MC":
                    sellWarrior(user, "MythicalCreature");
                    break;
                case "I":
                    printInventory(user);
                    break;
                case "Q":
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }

    }

    private static void sellWarrior(User user, String warriorType) {
        if (user.getWarrior(warriorType) == null) {
            System.out.println("You don't have a " + warriorType + ".");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to sell your " + warriorType + "? [Y/N].");
        while (true) {
            System.out.println("Enter your choice of selling the warrior.");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                double value = user.removeWarrior(warriorType) * 0.9;
                user.setMoney(user.getMoney() + value);
                System.out.println("You have sold your " + warriorType + " for " + value + " coins.");
                printMoney(user);
                break;
            } else if (input.equalsIgnoreCase("N")) {
                scanner.close();
                return;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        scanner.close();
        return;
    }

    private static void buy(User user) {
        printMoney(user);
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Do you want to buy a warrior[W] or an equipment[E] to a warrior?. To exit buy mode type [Q].");
        while (true) {
            System.out.println("Enter your choice of buy mode (Warrior or Equipment).");
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
        scanner.close();
    }

    private static void buyWarrior(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which warrior do you want to buy?");
        System.out.println("Archer[A], Healer[H], Knight[K], Mage[M], MythicalCreature[MC].");
        System.out.println("To check your inventory : [I].");
        System.out.println("To move back : [Q].");

        while (true) {
            System.out.println("Enter your choice of warrior to buy.");
            String input = scanner.nextLine();
            switch (input.toUpperCase()) {
                case "A":
                    buyOrReplaceWarrior(user, "Archer");
                    break;
                case "H":
                    buyOrReplaceWarrior(user, "Healer");
                    break;
                case "K":
                    buyOrReplaceWarrior(user, "Knight");
                    break;
                case "M":
                    buyOrReplaceWarrior(user, "Mage");
                    break;
                case "MC":
                    buyOrReplaceWarrior(user, "MythicalCreature");
                    break;
                case "I":
                    printInventory(user);
                    break;
                case "Q":
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
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
                System.out.println("Enter your choice of replacing the warrior.");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    sellWarrior(user, warriorType);
                    break;
                } else if (input.equalsIgnoreCase("N")) {
                    scanner.close();
                    return;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }
        Map<String, WarriorCatalog> warriors = Warrior.getWarriorMap(warriorType);
        if (user.getWarrior(warriorType) == null) {
            System.out.println(
                    "Which " + warriorType + " do you want to buy? Enter the name of the " + warriorType + ".");
            System.out.println("To abort the purchase : [Q].");
            printWarriorCatalog(warriorType);
            while (true) {
                System.out.println("Enter your choice of which " + warriorType + " to buy.");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Q")) {
                    scanner.close();
                    return;
                } else if (warriors.containsKey(input)) {
                    WarriorCatalog warrior = warriors.get(input);
                    if (user.getMoney() >= warrior.getPrice()) {
                        user.setMoney(user.getMoney() - warrior.getPrice());
                        user.setwarrior(warriorType, input.toLowerCase()); // Take lower case to match the warrior name
                                                                           // and remove case sensitivity
                        System.out.println("You have bought " + input + " for " + warrior.getPrice() + " coins.");
                        printMoney(user);
                        scanner.close();
                        return;
                    } else {
                        System.out.println("You don't have enough money to buy " + input + ".");
                        printMoney(user);
                        scanner.close();
                        return;
                    }
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }
        scanner.close();
        return;
    }

    private static void buyEquipment(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which warrior do you want to buy equipment for?");
        System.out.println("Archer[A], Healer[H], Knight[K], Mage[M], MythicalCreature[MC].");
        System.out.println("To check your inventory : [I].");
        System.out.println("To move back : [Q].");

        while (true) {
            System.out.println("Enter your choice of warrior to buy equipment for.");
            String input = scanner.nextLine();
            switch (input.toUpperCase()) {
                case "A":
                    buyEquipmentForWarrior(user, "Archer");
                    break;
                case "H":
                    buyEquipmentForWarrior(user, "Healer");
                    break;
                case "K":
                    buyEquipmentForWarrior(user, "Knight");
                    break;
                case "M":
                    buyEquipmentForWarrior(user, "Mage");
                    break;
                case "MC":
                    buyEquipmentForWarrior(user, "MythicalCreature");
                    break;
                case "I":
                    printInventory(user);
                    break;
                case "Q":
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }

    }

    private static void buyEquipmentForWarrior(User user, String warriorType) {
        Scanner scanner = new Scanner(System.in);
        if (user.getWarrior(warriorType) == null) {
            System.out.println("You don't have a " + warriorType + ".");
            scanner.close();
            return;
        }
        System.out.println("Which equipment do you want to buy for your " + warriorType + "?");
        System.out.println("Armor[A], Artefact[AR].");
        System.out.println("To check your inventory : [I].");
        System.out.println("To move back : [Q].");

        while (true) {
            System.out.println("Enter your choice of equipment to buy (Armor or Artefect).");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("A")) {
                buyOrReplaceEquipment(user, warriorType, "Armor");
            } else if (input.equalsIgnoreCase("AR")) {
                buyOrReplaceEquipment(user, warriorType, "Artefact");
            } else if (input.equalsIgnoreCase("I")) {
                printInventory(user);
            } else if (input.equalsIgnoreCase("Q")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        scanner.close();
        return;
    }

    private static void buyOrReplaceEquipment(User user, String warriorType, String equipmentType) {
        Scanner scanner = new Scanner(System.in);
        if (user.getWarrior(warriorType) == null) {
            System.out.println("You don't have a " + warriorType + ".");
            return;
        }
        if (user.getWarrior(warriorType).getEquipments(equipmentType) != null) {
            System.out.println("You already have a " + equipmentType + " for your " + warriorType + ".");
            System.out.println("Do you want to replace it? [Y/N]. Existing " + equipmentType + " will be destroyed.");
            while (true) {
                System.out.println("Enter your choice of replacing the equipment.");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    user.getWarrior(warriorType).removeEquipment(equipmentType);
                    System.out.println("You have removed the " + equipmentType + " for your " + warriorType + ".");
                    System.out.println("You have " + user.getMoney() + " coins.");
                    break;
                } else if (input.equalsIgnoreCase("N")) {
                    return;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }
        Map<String, EquipmentCatalog> equipments = Equipments.getEquipmentMap(equipmentType);
        if (user.getWarrior(warriorType).getEquipments(equipmentType) == null) {
            System.out.println("Which " + equipmentType + " do you want to buy for your " + warriorType + "?");
            System.out.println("To abort the purchase : [Q].");
            printEquipmentCatalog(equipmentType);
            while (true) {
                System.out.println("Enter your choice of which " + equipmentType + " to buy.");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Q")) {
                    return;
                } else if (equipments.containsKey(input)) {
                    EquipmentCatalog equipment = equipments.get(input);
                    if (user.getMoney() >= equipment.getPrice()) {
                        user.setMoney(user.getMoney() - equipment.getPrice());
                        user.getWarrior(warriorType).setEquipment(equipmentType, input.toLowerCase()); //Take lower case to match the equipment name and remove case sensitivity
                        System.out.println("You have bought " + input + " for " + equipment.getPrice() + " coins.");
                        printMoney(user);
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

    private static void printInventory(User user) {
        Object[][] inventory = user.getInventory();
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

    private static void printEquipmentCatalog(String equipmentType) {
        Map<String, EquipmentCatalog> equipments = Equipments.getEquipmentMap(equipmentType);
        System.out.println("Awailable " + equipmentType + "s:");
        for (Map.Entry<String, EquipmentCatalog> entry : equipments.entrySet()) {
            System.out.println("\t" + entry.getKey() + " : " + entry.getValue().getPrice() + " coins.");
            System.out.println("\t\tExtra Health: " + entry.getValue().getExtraHealth());
            System.out.println("\t\tExtra Attack: " + entry.getValue().getExtraAttack());
            System.out.println("\t\tExtra Defense: " + entry.getValue().getExtraDefense());
            System.out.println("\t\tExtra Speed: " + entry.getValue().getExtraSpeed());
        }
    }

    private static void printMoney(User user) {
        System.out.println("You have " + user.getMoney() + " coins.");
    }
}