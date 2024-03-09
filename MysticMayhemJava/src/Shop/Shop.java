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
                //sell(user);
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
                //buyEquipment(user);
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
            } else if (input.equalsIgnoreCase("A")) {
                Map<String, WarriorCatalog> Archers = Warrior.ARCHER_MAP;
                printWarriorCatalog(Archers, "Archer");
                if (inventory[0][0] != null) {
                    while (true) {
                        System.out.println("You already have an Archer.");
                        System.out.println("Do you want to replace it? [Y/N]");
                        System.out.println("Existing archer will be sold for "
                                + ((Warrior) inventory[0][0]).getValue() * 0.9 + " coins.");
                        input = scanner.nextLine();
                        if (input.equalsIgnoreCase("Y")) {
                            user.setMoney(user.getMoney() + ((Warrior) inventory[0][0]).getValue() * 0.9);
                            inventory[0][0] = null;
                            break;
                        } else if (input.equalsIgnoreCase("N")) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please try again.");
                        }
                    }
                }
                if (inventory[0][0] == null) {
                    System.out.println("Which Archer do you want to buy? Enter the name.");
                    input = scanner.nextLine();
                    WarriorCatalog archer = Archers.get(input);
                    if (archer != null) {
                        if (user.getMoney() >= archer.getPrice()) {
                            user.setMoney(user.getMoney() - archer.getPrice());
                            inventory[0][0] = new Archer(input);
                            System.out.println("You bought " + input + " for " + archer.getPrice() + " coins.");
                        } else {
                            System.out.println("You don't have enough money to buy " + input + ".");
                        }
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }
            } else if (input.equalsIgnoreCase("H")) {
                Map<String, WarriorCatalog> Healers = Warrior.HEALER_MAP;
                printWarriorCatalog(Healers, "Healer");
                if (inventory[1][0] != null) {
                    while (true) {
                        System.out.println("You already have a Healer.");
                        System.out.println("Do you want to replace it? [Y/N]");
                        System.out.println("Existing healer will be sold for "
                                + ((Warrior) inventory[1][0]).getValue() * 0.9 + " coins.");
                        input = scanner.nextLine();
                        if (input.equalsIgnoreCase("Y")) {
                            user.setMoney(user.getMoney() + ((Warrior) inventory[1][0]).getValue() * 0.9);
                            inventory[1][0] = null;
                            break;
                        } else if (input.equalsIgnoreCase("N")) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please try again.");
                        }
                    }
                }
                if (inventory[1][0] == null) {
                    System.out.println("Which Healer do you want to buy? Enter the name.");
                    input = scanner.nextLine();
                    WarriorCatalog healer = Healers.get(input);
                    if (healer != null) {
                        if (user.getMoney() >= healer.getPrice()) {
                            user.setMoney(user.getMoney() - healer.getPrice());
                            inventory[1][0] = new Healer(input);
                            System.out.println("You bought " + input + " for " + healer.getPrice() + " coins.");
                        } else {
                            System.out.println("You don't have enough money to buy " + input + ".");
                        }
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }
            } else if (input.equalsIgnoreCase("K")) {
                Map<String, WarriorCatalog> Knights = Warrior.KNIGHT_MAP;
                printWarriorCatalog(Knights, "Knight");
                if (inventory[2][0] != null) {
                    while (true) {
                        System.out.println("You already have a Knight.");
                        System.out.println("Do you want to replace it? [Y/N]");
                        System.out.println("Existing knight will be sold for "
                                + ((Warrior) inventory[2][0]).getValue() * 0.9 + " coins.");
                        input = scanner.nextLine();
                        if (input.equalsIgnoreCase("Y")) {
                            user.setMoney(user.getMoney() + ((Warrior) inventory[2][0]).getValue() * 0.9);
                            inventory[2][0] = null;
                            break;
                        } else if (input.equalsIgnoreCase("N")) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please try again.");
                        }
                    }
                }
                if (inventory[2][0] == null) {
                    System.out.println("Which Knight do you want to buy? Enter the name.");
                    input = scanner.nextLine();
                    WarriorCatalog knight = Knights.get(input);
                    if (knight != null) {
                        if (user.getMoney() >= knight.getPrice()) {
                            user.setMoney(user.getMoney() - knight.getPrice());
                            inventory[2][0] = new Knight(input);
                            System.out.println("You bought " + input + " for " + knight.getPrice() + " coins.");
                        } else {
                            System.out.println("You don't have enough money to buy " + input + ".");
                        }
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }
            } else if (input.equalsIgnoreCase("M")) {
                Map<String, WarriorCatalog> Mages = Warrior.MAGE_MAP;
                printWarriorCatalog(Mages, "Mage");
                if (inventory[3][0] != null) {
                    while (true) {
                        System.out.println("You already have a Mage.");
                        System.out.println("Do you want to replace it? [Y/N]");
                        System.out.println("Existing mage will be sold for "
                                + ((Warrior) inventory[3][0]).getValue() * 0.9 + " coins.");
                        input = scanner.nextLine();
                        if (input.equalsIgnoreCase("Y")) {
                            user.setMoney(user.getMoney() + ((Warrior) inventory[3][0]).getValue() * 0.9);
                            inventory[3][0] = null;
                            break;
                        } else if (input.equalsIgnoreCase("N")) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please try again.");
                        }
                    }
                }
                if (inventory[3][0] == null) {
                    System.out.println("Which Mage do you want to buy? Enter the name.");
                    input = scanner.nextLine();
                    WarriorCatalog mage = Mages.get(input);
                    if (mage != null) {
                        if (user.getMoney() >= mage.getPrice()) {
                            user.setMoney(user.getMoney() - mage.getPrice());
                            inventory[3][0] = new Mage(input);
                            System.out.println("You bought " + input + " for " + mage.getPrice() + " coins.");
                        } else {
                            System.out.println("You don't have enough money to buy " + input + ".");
                        }
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }
            } else if (input.equalsIgnoreCase("MC")) {
                Map<String, WarriorCatalog> MythicalCreatures = Warrior.MYTHICALCREATURE_MAP;
                printWarriorCatalog(MythicalCreatures, "MythicalCreature");
                if (inventory[4][0] != null) {
                    while (true) {
                        System.out.println("You already have a MythicalCreature.");
                        System.out.println("Do you want to replace it? [Y/N]");
                        System.out.println("Existing mythical creature will be sold for "
                                + ((Warrior) inventory[4][0]).getValue() * 0.9 + " coins.");
                        input = scanner.nextLine();
                        if (input.equalsIgnoreCase("Y")) {
                            user.setMoney(user.getMoney() + ((Warrior) inventory[4][0]).getValue() * 0.9);
                            inventory[4][0] = null;
                            break;
                        } else if (input.equalsIgnoreCase("N")) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please try again.");
                        }
                    }
                }
                if (inventory[4][0] == null) {
                    System.out.println("Which MythicalCreature do you want to buy? Enter the name.");
                    input = scanner.nextLine();
                    WarriorCatalog mythicalCreature = MythicalCreatures.get(input);
                    if (mythicalCreature != null) {
                        if (user.getMoney() >= mythicalCreature.getPrice()) {
                            user.setMoney(user.getMoney() - mythicalCreature.getPrice());
                            inventory[4][0] = new MythicalCreature(input);
                            System.out
                                    .println("You bought " + input + " for " + mythicalCreature.getPrice() + " coins.");
                        } else {
                            System.out.println("You don't have enough money to buy " + input + ".");
                        }
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }
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
            if (warrior[0] != null) {
                System.out.println(warriorTypes[i] + " : " + ((Warrior) warrior[0]).getName());
                String armor = ((Equipments) warrior[1]).getName();
                String artefact = ((Equipments) warrior[2]).getName();
                System.out.println("\tArmor : " + armor);
                System.out.println("\tArtefact : " + artefact);
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