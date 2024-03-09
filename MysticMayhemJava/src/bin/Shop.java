package bin;

import java.util.Scanner;
import java.util.Map;

public abstract class Shop {

    public static void enterShop(User user) {
        System.out.println("Welcome to the shop " + user.userName + "!");
        printMoney(user);
        printInventory(user);
        System.out.println("Do you want to buy[B] or sell[S]? To check your inventory : [I]. To exit the shop : [Q].");
        Scanner scannerShop0 = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your choice of buy or sell.");
            String input = scannerShop0.nextLine();
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
    }

    private static void sell(User user) {
        InventoryItem[] inventory = user.getInventory();
        printMoney(user);
        System.out.println("You can only sell warriors for now.");
        System.out.println("Which warrior do you want to sell?");
        System.out.println("Archer[A], Healer[H], Knight[K], Mage[M], MythicalCreature[MC]. To check your inventory : [I]. To move back : [Q].");
        Scanner scannerShop1 = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your choice of warrior to sell.");
            String input = scannerShop1.nextLine();
            if (input.equalsIgnoreCase("A")) {
                sellWarrior(user, inventory[0]);
            } else if (input.equalsIgnoreCase("H")) {
                sellWarrior(user, inventory[1]);
            } else if (input.equalsIgnoreCase("K")) {
                sellWarrior(user, inventory[2]);
            } else if (input.equalsIgnoreCase("M")) {
                sellWarrior(user, inventory[3]);
            } else if (input.equalsIgnoreCase("MC")) {
                sellWarrior(user, inventory[4]);
            } else if (input.equalsIgnoreCase("I")) {
                printInventory(user);
            } else if (input.equalsIgnoreCase("Q")) {
                return;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void sellWarrior(User user, InventoryItem warrior) {
        printMoney(user);
        if (warrior == null) {
            System.out.println("You don't have a " + warrior.name + ".");
            return;
        }
        Scanner scannerShop2 = new Scanner(System.in);
        System.out.println("Do you want to sell your " + warrior.type + "? [Y/N]" + " Existing " + warrior.name + " will be sold for " + warrior.value + " coins.");
        while (true) {
            System.out.println("Enter your choice of selling the warrior.");
            String input = scannerShop2.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                if (warrior.type == "Archer") user.removeArcher();
                else if (warrior.type == "Healer") user.removeHealer();
                else if (warrior.type == "Knight") user.removeKnight();
                else if (warrior.type == "Mage") user.removeMage();
                else if (warrior.type == "MythicalCreature") user.removeMythicalCreature();
                printMoney(user);
                break;
            } else if (input.equalsIgnoreCase("N")) {
                return;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void buy(User user) {
        printMoney(user);
        Scanner scannerShop3 = new Scanner(System.in);
        System.out.println("Do you want to buy a warrior[W] or an equipment[E] to a warrior?. To exit buy mode type [Q].");
        System.out.println("Inventory : [I]." );
        while (true) {
            System.out.println("Enter your choice of buy mode (Warrior or Equipment).");
            String input = scannerShop3.nextLine();
            if (input.equalsIgnoreCase("W")) {
                buyWarrior(user);
            } else if (input.equalsIgnoreCase("E")) {
                buyEquipment(user);
            } else if (input.equalsIgnoreCase("Q")) {
                break;
            } else if (input.equalsIgnoreCase("I")) {
                printInventory(user);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void buyWarrior(User user) {
        printMoney(user);
        InventoryItem[] inventory = user.getInventory();
        Scanner scannerShop4 = new Scanner(System.in);
        System.out.println("Which warrior do you want to buy?");
        System.out.println("Archer[A], Healer[H], Knight[K], Mage[M], MythicalCreature[MC]. To check your inventory : [I]. To move back : [Q].");
        while (true) {
            System.out.println("Enter your choice of warrior to buy.");
            String input = scannerShop4.nextLine();
            if (input.equalsIgnoreCase("A")) {
                buyOrReplaceWarrior(user, inventory[0], "Archer");
            } else if (input.equalsIgnoreCase("H")) {
                buyOrReplaceWarrior(user, inventory[1], "Healer");
            } else if (input.equalsIgnoreCase("K")) {
                buyOrReplaceWarrior(user, inventory[2], "Knight");
            } else if (input.equalsIgnoreCase("M")) {
                buyOrReplaceWarrior(user, inventory[3], "Mage");
            } else if (input.equalsIgnoreCase("MC")) {
                buyOrReplaceWarrior(user, inventory[4], "MythicalCreature");
            } else if (input.equalsIgnoreCase("I")) {
                printInventory(user);
            } else if (input.equalsIgnoreCase("Q")) {
                return;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void buyOrReplaceWarrior(User user, InventoryItem warrior, String warriorType) {
        printMoney(user);
        Scanner scannerShop5 = new Scanner(System.in);
        if (warrior != null) sellWarrior(user, warrior);
        printWarriorCatalog(warriorType);
        System.out.println("Enter the name of the " + warriorType + " to buy. To abort the purchase : [Q].");
        while (true) {
            System.out.println("Enter your choice of which " + warriorType + " to buy.");
            String input = scannerShop5.nextLine();
            if (input.equalsIgnoreCase("Q")) {
                return;
            } else if (InventoryItem.getWarriorMap(warriorType).containsKey(input)) {
                if (user.getMoney() >= InventoryItem.getWarriorMap(warriorType).get(input).price) {
                    if (warriorType == "Archer") user.addArcher(new Archer(input.toLowerCase()));
                    else if (warriorType == "Healer") user.addHealer(new Healer(input.toLowerCase()));
                    else if (warriorType == "Knight") user.addKnight(new Knight(input.toLowerCase()));
                    else if (warriorType == "Mage") user.addMage(new Mage(input.toLowerCase()));
                    else if (warriorType == "MythicalCreature") user.addMythicalCreature(new MythicalCreature(input.toLowerCase()));
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

    private static void buyEquipment(User user) {
        printMoney(user);
        InventoryItem[] inventory = user.getInventory();
        Scanner scannerShop6 = new Scanner(System.in);
        System.out.println("Which warrior do you want to buy equipment for?");
        System.out.println("Archer[A], Healer[H], Knight[K], Mage[M], MythicalCreature[MC]. To check your inventory : [I]. To move back : [Q].");
        while (true) {
            System.out.println("Enter your choice of warrior to buy equipment for.");
            String input = scannerShop6.nextLine();
            if (input.equalsIgnoreCase("A")) {
                buyEquipmentForWarrior(user, inventory[0]);
            } else if (input.equalsIgnoreCase("H")) {
                buyEquipmentForWarrior(user, inventory[1]);
            } else if (input.equalsIgnoreCase("K")) {
                buyEquipmentForWarrior(user, inventory[2]);
            } else if (input.equalsIgnoreCase("M")) {
                buyEquipmentForWarrior(user, inventory[3]);
            } else if (input.equalsIgnoreCase("MC")) {
                buyEquipmentForWarrior(user, inventory[4]);
            } else if (input.equalsIgnoreCase("I")) {
                printInventory(user);
            } else if (input.equalsIgnoreCase("Q")) {
                return;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void buyEquipmentForWarrior(User user, InventoryItem warrior) {
        printMoney(user);
        Scanner scannerShop7 = new Scanner(System.in);
        if (warrior == null) {
            System.out.println("You don't have a " + warrior.type + ".");
            return;
        }
        System.out.println("Which equipment do you want to buy for your " + warrior.type + "?");
        System.out.println("Armor[A], Artefact[AR]. To check your inventory : [I]. To move back : [Q].");
        while (true) {
            System.out.println("Enter your choice of equipment to buy (Armor or Artefect).");
            String input = scannerShop7.nextLine();
            if (input.equalsIgnoreCase("A")) {
                buyOrReplaceEquipment(user,(Warrior) warrior, "Armor");
            } else if (input.equalsIgnoreCase("AR")) {
                buyOrReplaceEquipment(user,(Warrior) warrior, "Artefact");
            } else if (input.equalsIgnoreCase("I")) {
                printInventory(user);
            } else if (input.equalsIgnoreCase("Q")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void buyOrReplaceEquipment(User user, Warrior warrior, String equipmentType) {
        printMoney(user);
        if (warrior == null) {
            System.out.println("You don't have a " + warrior.type + ".");
            return;
        }
        Scanner scannerShop8 = new Scanner(System.in);
        InventoryItem[] equipments = warrior.getInventoryItem();
        if (equipments[0] != null && equipmentType == "Armor") {
            System.out.println("You already have a " + equipmentType + " for your " + warrior.type + ".");
            System.out.println("Do you want to replace it? [Y/N]. Existing " + equipmentType + " will be destroyed.");
            while (true) {
                System.out.println("Enter your choice of replacing the equipment.");
                String input = scannerShop8.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    warrior.removeArmor();
                    System.out.println("You have removed the " + equipmentType + " for your " + warrior.type + ".");
                    System.out.println("You have " + user.getMoney() + " coins.");
                    break;
                } else if (input.equalsIgnoreCase("N")) {
                    return;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        } else if (equipments[1] != null && equipmentType == "Artefact") {
            System.out.println("You already have a " + equipmentType + " for your " + warrior.type + ".");
            System.out.println("Do you want to replace it? [Y/N]. Existing " + equipmentType + " will be destroyed.");
            while (true) {
                System.out.println("Enter your choice of replacing the equipment.");
                String input = scannerShop8.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    warrior.removeArtefect();
                    System.out.println("You have removed the " + equipmentType + " for your " + warrior.type + ".");
                    System.out.println("You have " + user.getMoney() + " coins.");
                    break;
                } else if (input.equalsIgnoreCase("N")) {
                    return;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }
        printEquipmentCatalog(equipmentType);
        System.out.println("Enter your choice of which " + equipmentType + " to buy for your " + warrior.type + ". To abort the purchase : [Q].");
        while (true) {
            System.out.println("Enter your choice of which " + equipmentType + " to buy.");
            String input = scannerShop8.nextLine();
            if (input.equalsIgnoreCase("Q")) {
                return;
            } else if (InventoryItem.getEquipmentMap(equipmentType).containsKey(input)) {
                if (user.getMoney() >= InventoryItem.getEquipmentMap(equipmentType).get(input).price) {
                    if (equipmentType == "Armor") warrior.addArmor(new Armor(input.toLowerCase()), user);
                    else if (equipmentType == "Artefact") warrior.addArtefect(new Artefact(input.toLowerCase()), user);
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

    private static void printMoney(User user) {
        System.out.println("You have " + user.getMoney() + " coins.");
    }

    private static void printInventory(User user) {
        String[] warriorTypes = { "Archer", "Healer", "Knight", "Mage", "MythicalCreature" };
        String[] equipmentTypes = { "Armor", "Artefact" };

        System.out.println("Your inventory:");
        InventoryItem[] inventory = user.getInventory();
        for (int i = 0; i < 5; i++) {
            if (inventory[i] != null) {
                System.out.println(warriorTypes[i] + " : " + inventory[i].name);
                Warrior Warrior = (Warrior) inventory[i];
                InventoryItem[] items = Warrior.getInventoryItem();
                for (int j = 0; j < 2; j++) {
                    if (items[j] != null) {
                        System.out.println("\t" + equipmentTypes[j] + " : " + items[j].name);
                    } else {
                        System.out.println("\t" + equipmentTypes[j] + " : None");
                    }
                }
                System.out.println("\tHealth : " + Warrior.getHealth());
                System.out.println("\tAttack : " + Warrior.getAttack());
                System.out.println("\tDefense : " + Warrior.getDefense());
                System.out.println("\tSpeed : " + Warrior.getSpeed());
                System.out.println("\tValue : " + Warrior.value);
            }
        }
    }

    private static void printWarriorCatalog(String warriorType) {
        Map<String, WarriorInfo> warriors = InventoryItem.getWarriorMap(warriorType);
        System.out.println("Awailable " + warriorType + "s:");
        for (Map.Entry<String, WarriorInfo> entry : warriors.entrySet()) {
            System.out.println("\t" + entry.getKey() + " : " + entry.getValue().price + " coins.");
            System.out.println("\t\tHealth: " + entry.getValue().health);
            System.out.println("\t\tAttack: " + entry.getValue().attack);
            System.out.println("\t\tDefense: " + entry.getValue().defense);
            System.out.println("\t\tSpeed: " + entry.getValue().speed);
        }
    }

    private static void printEquipmentCatalog(String equipmentType) {
        Map<String, EquipmentInfo> equipments = InventoryItem.getEquipmentMap(equipmentType);
        System.out.println("Awailable " + equipmentType + "s:");
        for (Map.Entry<String, EquipmentInfo> entry : equipments.entrySet()) {
            System.out.println("\t" + entry.getKey() + " : " + entry.getValue().price + " coins.");
            System.out.println("\t\tExtra Health: " + entry.getValue().extraHealth);
            System.out.println("\t\tExtra Attack: " + entry.getValue().extraAttack);
            System.out.println("\t\tExtra Defense: " + entry.getValue().extraDefense);
            System.out.println("\t\tExtra Speed: " + entry.getValue().extraSpeed);
        }
    }
}