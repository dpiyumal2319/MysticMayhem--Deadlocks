package Shop;

import java.util.Map;
import java.util.Scanner;

import Warrior.Archer;
import Warrior.Healer;
import Warrior.Knight;
import Warrior.Mage;
import Warrior.MythicalCreature;
import Warrior.Warrior;
import Equipments.Equipments;
import UserManager.User;
import Warrior.WarriorCatalog;
import Equipments.EquipmentCatalog;

public abstract class Shop {
    static Object[][] inventory;

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

    // private static void buyWarrior(User user) {
    // Scanner scanner = new Scanner(System.in);
    // System.out.println("Which warrior do you want to buy?");
    // System.out.println("Archer[A], Healer[H], Knight[K], Mage[M],
    // MythicalCreature[MC].");
    // System.out.println("To check your inventory : [I].");
    // System.out.println("To exit buy mode : [Q].");
    // while (true) {
    // String input = scanner.nextLine();
    // if (input.equalsIgnoreCase("I")) {
    // printInventory(inventory);
    // } else if (input.equalsIgnoreCase("A")) {
    // if (user.getArcher() != null) {
    // System.out.println("You already have an Archer.");
    // System.out.println("Do you want to replace it? [Y/N]. Existing archer will be
    // sold for "
    // + user.getArcher().getValue() * 0.9 + " coins.");
    // while (true) {
    // input = scanner.nextLine();
    // if (input.equalsIgnoreCase("Y")) {
    // Warrior currArcher = (Archer) user.getArcher();
    // user.setMoney(user.getMoney() + currArcher.getValue() * 0.9);
    // user.setArcher(null);
    // } else if (input.equalsIgnoreCase("N")) {
    // break;
    // } else {
    // System.out.println("Invalid input. Please try again.");
    // }
    // }
    // }
    // if (user.getArcher() == null) {
    // Map<String, WarriorCatalog> Archers = Warrior.ARCHER_MAP;
    // printWarriorCatalog(Archers, "Archer");
    // System.out.println("Which Archer do you want to buy? Enter the name of the
    // Archer.");
    // while (true) {
    // input = scanner.nextLine();
    // WarriorCatalog archer = Archers.get(input);
    // if (archer != null) {
    // if (user.getMoney() >= archer.getPrice()) {
    // user.setMoney(user.getMoney() - archer.getPrice());
    // user.setArcher(new Archer(input));
    // System.out
    // .println("You have bought " + input + " for " + archer.getPrice() + "
    // coins.");
    // inventory = user.getInventory();
    // break;
    // } else {
    // System.out.println("You don't have enough money to buy " + input + ".");
    // break;
    // }
    // } else {
    // System.out.println("Invalid input. Please try again.");
    // }
    // }
    // }
    // } else if (input.equalsIgnoreCase("H")) {
    // if (user.getHealer() != null) {
    // System.out.println("You already have a Healer.");
    // System.out.println("Do you want to replace it? [Y/N]. Existing healer will be
    // sold for "
    // + user.getHealer().getValue() * 0.9 + " coins.");
    // while (true) {
    // input = scanner.nextLine();
    // if (input.equalsIgnoreCase("Y")) {
    // Warrior currHealer = (Healer) user.getHealer();
    // user.setMoney(user.getMoney() + currHealer.getValue() * 0.9);
    // user.setHealer(null);
    // } else if (input.equalsIgnoreCase("N")) {
    // break;
    // } else {
    // System.out.println("Invalid input. Please try again.");
    // }
    // }
    // }
    // if (user.getHealer() == null) {
    // Map<String, WarriorCatalog> Healers = Warrior.HEALER_MAP;
    // printWarriorCatalog(Healers, "Healer");
    // System.out.println("Which Healer do you want to buy? Enter the name of the
    // Healer.");
    // while (true) {
    // input = scanner.nextLine();
    // WarriorCatalog healer = Healers.get(input);
    // if (healer != null) {
    // if (user.getMoney() >= healer.getPrice()) {
    // user.setMoney(user.getMoney() - healer.getPrice());
    // user.setHealer(new Healer(input));
    // System.out
    // .println("You have bought " + input + " for " + healer.getPrice() + "
    // coins.");
    // inventory = user.getInventory();
    // break;
    // } else {
    // System.out.println("You don't have enough money to buy " + input + ".");
    // break;
    // }
    // } else {
    // System.out.println("Invalid input. Please try again.");
    // }
    // }
    // }
    // } else if (input.equalsIgnoreCase("K")) {
    // if (user.getKnight() != null) {
    // System.out.println("You already have a Knight.");
    // System.out.println("Do you want to replace it? [Y/N]. Existing knight will be
    // sold for "
    // + user.getKnight().getValue() * 0.9 + " coins.");
    // while (true) {
    // input = scanner.nextLine();
    // if (input.equalsIgnoreCase("Y")) {
    // Warrior currKnight = (Knight) user.getKnight();
    // user.setMoney(user.getMoney() + currKnight.getValue() * 0.9);
    // user.setKnight(null);
    // } else if (input.equalsIgnoreCase("N")) {
    // break;
    // } else {
    // System.out.println("Invalid input. Please try again.");
    // }
    // }
    // }
    // if (user.getKnight() == null) {
    // Map<String, WarriorCatalog> Knights = Warrior.KNIGHT_MAP;
    // printWarriorCatalog(Knights, "Knight");
    // System.out.println("Which Knight do you want to buy? Enter the name of the
    // Knight.");
    // while (true) {
    // input = scanner.nextLine();
    // WarriorCatalog knight = Knights.get(input);
    // if (knight != null) {
    // if (user.getMoney() >= knight.getPrice()) {
    // user.setMoney(user.getMoney() - knight.getPrice());
    // user.setKnight(new Knight(input));
    // System.out
    // .println("You have bought " + input + " for " + knight.getPrice() + "
    // coins.");
    // inventory = user.getInventory();
    // break;
    // } else {
    // System.out.println("You don't have enough money to buy " + input + ".");
    // break;
    // }
    // } else {
    // System.out.println("Invalid input. Please try again.");
    // }
    // }
    // }
    // } else if (input.equalsIgnoreCase("M")) {
    // if (user.getMage() != null) {
    // System.out.println("You already have a Mage.");
    // System.out.println("Do you want to replace it? [Y/N]. Existing mage will be
    // sold for "
    // + user.getMage().getValue() * 0.9 + " coins.");
    // while (true) {
    // input = scanner.nextLine();
    // if (input.equalsIgnoreCase("Y")) {
    // Warrior currMage = (Mage) user.getMage();
    // user.setMoney(user.getMoney() + currMage.getValue() * 0.9);
    // user.setMage(null);
    // } else if (input.equalsIgnoreCase("N")) {
    // break;
    // } else {
    // System.out.println("Invalid input. Please try again.");
    // }
    // }
    // }
    // if (user.getMage() == null) {
    // Map<String, WarriorCatalog> Mages = Warrior.MAGE_MAP;
    // printWarriorCatalog(Mages, "Mage");
    // System.out.println("Which Mage do you want to buy? Enter the name of the
    // Mage.");
    // while (true) {
    // input = scanner.nextLine();
    // WarriorCatalog mage = Mages.get(input);
    // if (mage != null) {
    // if (user.getMoney() >= mage.getPrice()) {
    // user.setMoney(user.getMoney() - mage.getPrice());
    // user.setMage(new Mage(input));
    // System.out.println("You have bought " + input + " for " + mage.getPrice() + "
    // coins.");
    // inventory = user.getInventory();
    // break;
    // } else {
    // System.out.println("You don't have enough money to buy " + input + ".");
    // break;
    // }
    // } else {
    // System.out.println("Invalid input. Please try again.");
    // }
    // }
    // }
    // } else if (input.equalsIgnoreCase("MC")) {
    // if (user.getMythicalCreature() != null) {
    // System.out.println("You already have a MythicalCreature.");
    // System.out.println("Do you want to replace it? [Y/N]. Existing mythical
    // creature will be sold for "
    // + user.getMythicalCreature().getValue() * 0.9 + " coins.");
    // while (true) {
    // input = scanner.nextLine();
    // if (input.equalsIgnoreCase("Y")) {
    // Warrior currMythicalCreature = (MythicalCreature) user.getMythicalCreature();
    // user.setMoney(user.getMoney() + currMythicalCreature.getValue() * 0.9);
    // user.setMythicalCreature(null);
    // } else if (input.equalsIgnoreCase("N")) {
    // break;
    // } else {
    // System.out.println("Invalid input. Please try again.");
    // }
    // }
    // }
    // if (user.getMythicalCreature() == null) {
    // Map<String, WarriorCatalog> MythicalCreatures = Warrior.MYTHICALCREATURE_MAP;
    // printWarriorCatalog(MythicalCreatures, "MythicalCreature");
    // System.out.println(
    // "Which MythicalCreature do you want to buy? Enter the name of the
    // MythicalCreature.");
    // while (true) {
    // input = scanner.nextLine();
    // WarriorCatalog mythicalCreature = MythicalCreatures.get(input);
    // if (mythicalCreature != null) {
    // if (user.getMoney() >= mythicalCreature.getPrice()) {
    // user.setMoney(user.getMoney() - mythicalCreature.getPrice());
    // user.setMythicalCreature(new MythicalCreature(input));
    // System.out.println(
    // "You have bought " + input + " for " + mythicalCreature.getPrice() + "
    // coins.");
    // inventory = user.getInventory();
    // break;
    // } else {
    // System.out.println("You don't have enough money to buy " + input + ".");
    // break;
    // }
    // } else {
    // System.out.println("Invalid input. Please try again.");
    // }
    // }
    // }
    // } else if (input.equalsIgnoreCase("Q")) {
    // break;
    // } else {
    // System.out.println("Invalid input. Please try again.");
    // }
    // }
    // }

    private void buyWarrior(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which warrior do you want to buy?");
        System.out.println("Archer[A], Healer[H], Knight[K], Mage[M], MythicalCreature[MC].");
        System.out.println("To check your inventory : [I].");
        System.out.println("To exit buy mode : [Q].");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("A")) {
                buyOrReplaceWarrior(user, Warrior.ARCHER_MAP, "Archer");
            } else if (input.equalsIgnoreCase("H")) {
                buyOrReplaceWarrior(user, Warrior.HEALER_MAP, "Healer");
            } else if (input.equalsIgnoreCase("K")) {
                buyOrReplaceWarrior(user, Warrior.KNIGHT_MAP, "Knight");
            } else if (input.equalsIgnoreCase("M")) {
                buyOrReplaceWarrior(user, Warrior.MAGE_MAP, "Mage");
            } else if (input.equalsIgnoreCase("MC")) {
                buyOrReplaceWarrior(user, Warrior.MYTHICALCREATURE_MAP, "MythicalCreature");
            } else if (input.equalsIgnoreCase("I")) {
                printInventory(inventory);
            } else if (input.equalsIgnoreCase("Q")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void buyOrReplaceWarrior(User user, Map<String, WarriorCatalog> map, String warriorType) {
        Scanner scanner = new Scanner(System.in);
        if (user.getWarrior(warriorType) != null) {
            System.out.println("You already have a " + warriorType + ".");
            System.out.println("Do you want to replace it? [Y/N]. Existing " + warriorType + " will be sold for "
                    + user.getWarrior(warriorType).getValue() * 0.9 + " coins.");
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    Warrior currWarrior = user.getWarrior(warriorType);
                    user.setMoney(user.getMoney() + currWarrior.getValue() * 0.9);
                    user.setWarrior(warriorType, null);
                    break;
                } else if (input.equalsIgnoreCase("N")) {
                    return;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }
        if (user.getWarrior(warriorType) == null) {
            printWarriorCatalog(map, warriorType);
            System.out.println("Which " + warriorType + " do you want to buy? Enter the name of the " + warriorType + ".");
            while (true) {
                String input = scanner.nextLine();
                WarriorCatalog warrior = map.get(input);
                if (warrior != null) {
                    if (user.getMoney() >= warrior.getPrice()) {
                        user.setMoney(user.getMoney() - warrior.getPrice());
                        user.setWarrior(warriorType, warrior);
                        System.out.println("You have bought " + input + " for " + warrior.getPrice() + " coins.");
                        inventory = user.getInventory();
                        break;
                    } else {
                        System.out.println("You don't have enough money to buy " + input + ".");
                        break;
                    }
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
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

    private static void printWarriorCatalog(Map<String, WarriorCatalog> warriors, String warriorType) {
        System.out.println("Awailable " + warriorType + "s:");
        for (Map.Entry<String, WarriorCatalog> entry : warriors.entrySet()) {
            System.out.println("\t" + entry.getKey() + " : " + entry.getValue().getPrice() + " coins.");
            System.out.println("\t\tHealth: " + entry.getValue().getHealth());
            System.out.println("\t\tAttack: " + entry.getValue().getAttack());
            System.out.println("\t\tDefense: " + entry.getValue().getDefense());
            System.out.println("\t\tSpeed: " + entry.getValue().getSpeed());
        }
    }

    private static void printEquipmentCatalog(Map<String, EquipmentCatalog> equipments, String equipmentType) {
        System.out.println("Awailable " + equipmentType + "s:");
        for (Map.Entry<String, EquipmentCatalog> entry : equipments.entrySet()) {
            System.out.println("\t" + entry.getKey() + " : " + entry.getValue().getPrice() + " coins.");
            System.out.println("\t\tExtra Health: " + entry.getValue().getExtraHealth());
            System.out.println("\t\tExtra Attack: " + entry.getValue().getExtraAttack());
            System.out.println("\t\tExtra Defense: " + entry.getValue().getExtraDefense());
            System.out.println("\t\tExtra Speed: " + entry.getValue().getExtraSpeed());
        }
    }
}