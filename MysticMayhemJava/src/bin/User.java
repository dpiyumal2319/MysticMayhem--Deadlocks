package bin;

import java.util.Scanner;
import java.util.jar.Attributes.Name;

import javax.print.attribute.standard.MediaSize.NA;

import java.util.Map;
import bin.Collections.*;
import bin.Warriors.*;

public class User extends SuperUserControls {
    private static final int UserIdStart = 100000;
    public final String name;
    public final int userID;
    public final String homeGround;
    public int xp;
    public final String userName;
    public Squad squad;
    static Scanner scanner = new Scanner(System.in);
    static String input;

    public User(String name,String userName, int currentUsers, String homeGround) {
        this.name = name;
        this.userID = UserIdStart + currentUsers + 1;
        this.xp = 0;
        this.userName = userName;
        this.homeGround = homeGround;
        this.squad = new Squad();
    }

    public int getxp() {
        return xp;
    }

    public void increaseXp(int amount) {
        xp += amount;
    }

    public void decreaseXp(int amount) {
        xp -= amount;
    }

    public boolean Store() {
        System.out.println("Welcome to the store " + this.userName + "!");
        System.out.println("You can always select Exit without saving[QS] in main menu if you don't want to save your changes.");
        printMoney();
        System.out.println("Do you want to buy[B] or sell[S]?");
        System.out.println("Inventory/Squad [I]");
        System.out.println("Quit[Q]");
        while (true) {
            System.out.println("Enter your choice [B/S/I/Q/QS]: ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("QS")) {
                return false;
            }
            if (input.equalsIgnoreCase("Q")) {
                break;
            } else if (input.equalsIgnoreCase("I"))
                printInventory();
            else if (input.equalsIgnoreCase("S")) {
                printMoney();
                System.out.println("Currently you can only sell Warriors");
                if (isAllWarriorsAwailable()) {
                    System.out.println("You don't have any warriors to sell!");
                    continue;
                } else {
                    System.out.println("Which warrior do you want to sell?");
                    System.out.println("Archer[A], Knight[K], Mage[M], Healer[H], Mythical Creature[MC]");
                    System.out.println("Quit[Q], Inventory[I]");
                    while (true) {
                        System.out.println(
                                "Enter your choice, Which warrior you want to sell [A/K/M/H/MC] or Quit[Q]/Inventory[I]: ");
                        input = scanner.nextLine();
                        if (input.equalsIgnoreCase("A"))
                            sellWarrior(squad.getArcher());
                        else if (input.equalsIgnoreCase("K"))
                            sellWarrior(squad.getKnight());
                        else if (input.equalsIgnoreCase("M"))
                            sellWarrior(squad.getMage());
                        else if (input.equalsIgnoreCase("H"))
                            sellWarrior(squad.getHealer());
                        else if (input.equalsIgnoreCase("MC"))
                            sellWarrior(squad.getMythicalCreature());
                        else if (input.equalsIgnoreCase("Q"))
                            break;
                        else if (input.equalsIgnoreCase("I"))
                            printInventory();
                        else {
                            System.out.println("Invalid choice!");
                            continue;
                        }
                    }
                }
            } else if (input.equalsIgnoreCase("B")) {
                printMoney();
                System.out.println("What do you want to buy?");
                System.out.println("Warrior[W], Equipment[E]");
                System.out.println("Quit[Q], Inventory[I]");
                while (true) {
                    System.out.println("Enter your choice, What do you want to buy [W/E/Q/I]: ");
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("W")) {
                        System.out.println("Which warrior do you want to buy?");
                        System.out.println("Archer[A], Knight[K], Mage[M], Healer[H], Mythical Creature[MC]");
                        System.out.println("Quit[Q], Inventory[I]");
                        while (true) {
                            System.out.println(
                                    "Enter your choice, Which warrior you want to buy [A/K/M/H/MC] or Quit[Q]/Inventory[I]:");
                            input = scanner.nextLine();
                            if (input.equalsIgnoreCase("A")) {
                                buyWarrior(squad.getArcher(), "Archer");
                            } else if (input.equalsIgnoreCase("K")) {
                                buyWarrior(squad.getKnight(), "Knight");
                            } else if (input.equalsIgnoreCase("M")) {
                                buyWarrior(squad.getMage(), "Mage");
                            } else if (input.equalsIgnoreCase("H")) {
                                buyWarrior(squad.getHealer(), "Healer");
                            } else if (input.equalsIgnoreCase("MC")) {
                                buyWarrior(squad.getMythicalCreature(), "MythicalCreature");
                            } else if (input.equalsIgnoreCase("Q")) {
                                break;
                            } else if (input.equalsIgnoreCase("I")) {
                                printInventory();
                            } else {
                                System.out.println("Invalid choice!");
                                continue;
                            }
                        }
                    } else if (input.equalsIgnoreCase("E")) {
                        System.out.println("For which warrior do you want to buy equipment?");
                        System.out.println("Archer[A], Knight[K], Mage[M], Healer[H], Mythical Creature[MC]");
                        while (true) {
                            System.out.println("Enter your choice, Which warrior you want to buy equipment:");
                            input = scanner.nextLine();
                            if (input.equalsIgnoreCase("A")) {
                                buyEquipment(squad.getArcher());
                            } else if (input.equalsIgnoreCase("K")) {
                                buyEquipment(squad.getKnight());
                            } else if (input.equalsIgnoreCase("M")) {
                                buyEquipment(squad.getMage());
                            } else if (input.equalsIgnoreCase("H")) {
                                buyEquipment(squad.getHealer());
                            } else if (input.equalsIgnoreCase("MC")) {
                                buyEquipment(squad.getMythicalCreature());
                            } else {
                                System.out.println("Invalid choice!");
                                continue;
                            }
                            break;
                        }
                    } else if (input.equalsIgnoreCase("Q")) {
                        break;
                    } else if (input.equalsIgnoreCase("I")) {
                        printInventory();
                    } else {
                        System.out.println("Invalid choice!");
                        continue;
                    }
                }
            } else {
                System.out.println("Invalid choice!");
            }
        }
        System.out.println("Thank you for visiting the store " + this.userName + "!");
        System.out.println("Goodbye!");
        return true;
    }

    private void buyEquipment(Warrior warrior) {
        printMoney();
        if (warrior == null) {
            System.out.println("You don't have this warrior!");
            return;
        }
        System.out.println("Which equipment do you want to buy?");
        System.out.println("Armor[A], Artefact[AR]");
        System.out.println("Quit[Q], Inventory[I]");
        while (true) {
            System.out.println("Enter your choice [A/AR/Q/I]: ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("A")) {
                buyOrReplaceEquip(warrior, "Armor");
            } else if (input.equalsIgnoreCase("AR")) {
                buyOrReplaceEquip(warrior, "Artefact");
            } else if (input.equalsIgnoreCase("Q")) {
                break;
            } else if (input.equalsIgnoreCase("I")) {
                printInventory();
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private void buyOrReplaceEquip(Warrior warrior, String type) {
        printMoney();
        if (warrior.getWoredrobe().getEquipment(type) != null) {
            System.out.println("You already have this equipment!");
            System.out.println("Do you want to remove it?. No money will be refunded.");
            System.out.println("Yes[Y] or No[N]");
            while (true) {
                System.out.println("Enter your choice [Y/N]:");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    warrior.removeEquipment(type);
                    System.out.println("You have removed the " + type + " from your " + warrior.type + ".");
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
            if (input.equalsIgnoreCase("Y")) {
                System.out.println("Which " + type + " do you want to buy?");
                printEquipmentMap(type);
                printMoney();
                System.out.println("Enter the name of the " + type + " you want to buy: ");
                while (true) {
                    System.out.println("Enter your choice of equipment [name], Quit[Q], Inventory[I]:  ");
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("Q")) {
                        return;
                    } else if (input.equalsIgnoreCase("I")) {
                        printInventory();
                    } else if (InventoryItem.getEquipmentMap(type).containsKey(input)) {
                        if (getMoney() >= InventoryItem.getEquipmentMap(type).get(input.toLowerCase()).price) {
                            InventoryItem newEquipment = warrior.addEquipment(type, input.toLowerCase());
                            giveMoneyFor(newEquipment);
                            System.out
                                    .println("You have bought a new " + type + "for " + newEquipment.price + " coins.");
                            printMoney();
                            return;
                        } else {
                            System.out.println("You don't have enough money!");
                            break;
                        }
                    } else {
                        System.out.println("Invalid choice!");
                    }
                }
            } else if (input.equalsIgnoreCase("N")) {
                return;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private void buyWarrior(Warrior warrior, String type) {
        printMoney();
        if (warrior != null) {
            System.out.println("You already have this warrior!");
            sellWarrior(warrior);
        } else if (warrior == null) {
            System.out.println("Do you want to buy a new " + type + "?");
            System.out.println("Yes[Y] or No[N]");
            while (true) {
                System.out.println("Enter your choice [Y/N]: ");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    System.out.println("Which " + type + " do you want to buy?");
                    printWarriorMap(type);
                    printMoney();
                    System.out.println("Enter the name of the " + type + " you want to buy: ");
                    while (true) {
                        System.out.println("Enter your choice OF warrior [name]: , Quit[Q]");
                        input = scanner.nextLine();
                        if (InventoryItem.getWarriorMap(type).containsKey(input)) {
                            if (getMoney() >= InventoryItem.getWarriorMap(type).get(input.toLowerCase()).price) {
                                InventoryItem newWarrior = addSquadMate(type, input.toLowerCase());
                                giveMoneyFor(newWarrior);
                                System.out
                                        .println("You have bought a new " + type + " for " + newWarrior.price
                                                + " coins.");
                                printMoney();
                                return;
                            } else {
                                System.out.println("You don't have enough money!");
                                break;
                            }
                        } else if (input.equalsIgnoreCase("Q")) {
                            return;
                        } else {
                            System.out.println("Invalid choice!");
                        }
                    }
                }
            }
        }
    }

    private void sellWarrior(Warrior warrior) {
        printMoney();
        if (warrior == null) {
            System.out.println("You don't currently have this warrior!");
            return;
        } else {
            System.out.println("Do you want to sell your " + warrior.name + " for " + warrior.getValue() + " coins?");
            System.out.println("Yes[Y] or No[N]");
            while (true) {
                System.out.println("Enter your choice: ");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    getMoneyFrom(warrior);
                    removeSquadMate(warrior.type);
                    System.out.println("You have sold your " + warrior.name + " for " + warrior.getValue() + " coins.");
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

    public void printInventory() {
        System.out.println("Your squad:");
        if (squad.getArcher() != null) {
            squad.getArcher().printInfo();
        } else {
            System.out.println("\tArcher: None");
        }
        if (squad.getKnight() != null) {
            squad.getKnight().printInfo();
        } else {
            System.out.println("\tKnight: None");
        }
        if (squad.getMage() != null) {
            squad.getMage().printInfo();
        } else {
            System.out.println("\tMage: None");
        }
        if (squad.getHealer() != null) {
            squad.getHealer().printInfo();
        } else {
            System.out.println("\tHealer: None");
        }
        if (squad.getMythicalCreature() != null) {
            squad.getMythicalCreature().printInfo();
        } else {
            System.out.println("\tMythical Creature: None");
        }
        printMoney();
    }

    private void printWarriorMap(String type) {
        Map<String, WarriorInfo> warriorMap = InventoryItem.getWarriorMap(type);
        if (warriorMap != null) {
            for (String key : warriorMap.keySet()) {
                System.out.println(key + ":"+ warriorMap.get(key).price+ " coins");
                System.out.print("\tHomeLand: " + warriorMap.get(key).homeLand);
                System.out.print("\tAttack: " + warriorMap.get(key).attack);
                System.out.print("\tDefense: " + warriorMap.get(key).defense);
                System.out.print("\tHealth: " + warriorMap.get(key).health);
                System.out.println("\tSpeed: " + warriorMap.get(key).speed);
            }
        }
    }
    

    private void printEquipmentMap(String type) {
        Map<String, EquipmentInfo> equipmentMap = InventoryItem.getEquipmentMap(type);
        if (equipmentMap != null) {
            for (String key : equipmentMap.keySet()) {
                System.out.println(key + ":"+ equipmentMap.get(key).price + " coins");
                System.out.print("\tAttck: " + equipmentMap.get(key).extraAttack);
                System.out.print("\tDefense: " + equipmentMap.get(key).extraDefense);
                System.out.print("\tHealth: " + equipmentMap.get(key).extraHealth);
                System.out.println("\tSpeed: " + equipmentMap.get(key).extraSpeed);
            }
        }
    }
    

    private void removeSquadMate(String type) {
        switch (type) {
            case "Archer":
                squad.setArcher(null);
                break;
            case "Knight":
                squad.setKnight(null);
                break;
            case "Mage":
                squad.setMage(null);
                break;
            case "Healer":
                squad.setHealer(null);
                break;
            case "MythicalCreature":
                squad.setMythicalCreature(null);
                break;
        }
    }

    private Warrior addSquadMate(String type, String name) {
        if (type == "Archer") {
            Archer archer = new Archer(name);
            squad.setArcher(archer);
            return archer;
        } else if (type == "Knight") {
            Knight knight = new Knight(name);
            squad.setKnight(knight);
            return knight;
        } else if (type == "Mage") {
            Mage mage = new Mage(name);
            squad.setMage(mage);
            return mage;
        } else if (type == "Healer") {
            Healer healer = new Healer(name);
            squad.setHealer(healer);
            return healer;
        } else if (type == "MythicalCreature") {
            MythicalCreature mythicalCreature = new MythicalCreature(name);
            squad.setMythicalCreature(mythicalCreature);
            return mythicalCreature;
        } else {
            return null;
        }
    }

    public Boolean isAllWarriorsAwailable() {
        if (squad.getArcher() == null || squad.getKnight() == null || squad.getMage() == null
                || squad.getHealer() == null
                || squad.getMythicalCreature() == null) {
            return false;
        } else {
            return true;
        }
    }
}