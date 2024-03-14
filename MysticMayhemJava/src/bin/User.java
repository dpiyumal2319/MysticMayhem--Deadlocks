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

    // ANSI escape codes for text color
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLACK = "\u001B[30m";
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

    public User(String userName, int currentUsers, String homeGround) {
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
        String storeWelcomeMsg = "Welcome to the store " + this.userName + "!" ;
        // ANSI escape sequence for bold text
        String bold = "\033[1m";
        
        // ANSI escape sequence for resetting text style
        String reset = "\033[0m";
        
        // Print top frame line
        System.out.println(bold + "+" + "-".repeat(storeWelcomeMsg.length() + 4) + "+" + reset);
        
        // Print storeWelcomeMsg with left and right frame lines
        System.out.println(bold + "|  " + storeWelcomeMsg + "  |" + reset);
        
        // Print bottom frame line
        System.out.println(bold + "+" + "-".repeat(storeWelcomeMsg.length() + 4) + "+" + reset+ '\n');

        System.out.println("You can always select Exit without saving[QS] in main menu if you don't want to save your changes.\n");
        System.out.println(MAGENTA+"You're in the shop :"+RESET);
        printMoney();
        System.out.println("\n" +BLUE + "buy[B]\n" + "sell[S]?" + RESET);
        System.out.println(BLUE +"Inventory/Squad [I]"+RESET);
        System.out.println(RED + "Quit[Q]" + RESET);
        System.out.println(RED+"Quit without saving[QS] \n"+RESET);
        while (true) {
            System.out.print("Enter your choice "+ GREEN+ "[B/S/I/Q/QS]"+ RESET +": ");
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
                    System.out.println("\nWhich warrior do you want to sell?\n");
                    System.out.println(BLUE+"Archer[A], Knight[K], Mage[M], Healer[H], Mythical Creature[MC]"+RESET);
                    System.out.println(RED + "Quit[Q]" + RESET + BLUE+"\nInventory[I]\n"+RESET);
                    while (true) {
                        System.out.print("Enter your choice, Which warrior you want to sell "+GREEN+"[A/K/M/H/MC]"+RESET+" or " +RED+"Quit[Q]"+RESET+BLUE+"/Inventory[I]: "+RESET);
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
                            System.out.println("\n"+RED+"Invalid choice!"+RESET);
                            continue;
                        }
                    }
                }
            } else if (input.equalsIgnoreCase("B")) {
                System.out.print("\n");
                printMoney();
                System.out.println("What do you want to buy?\n");
                
                System.out.println(BLUE+"Warrior[W]\nEquipment[E]\n\nInventory[I]\n"+RED+"Quit[Q]\n"+RESET);
                
                while (true) {
                    System.out.print("Enter your choice, What do you want to buy "+BRIGHT_GREEN+"[W/E/I/Q]: "+RESET);
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("W")) {
                        System.out.println("\nWhich warrior do you want to buy?\n");
                        System.out.println(BLUE + " Archer[A]\n Knight[K]\n Mage[M]\n Healer[H]\n Mythical\n Creature[MC]\n" + RESET );
                        System.out.println(RED + " Quit[Q]\n" + RESET+BLUE+" Inventory[I]\n"+RESET);
                        while (true) {
                            System.out.print("Enter your choice, Which warrior you want to buy "+GREEN+"[A/K/M/H/MC]"+RESET+" or "+RED + "Quit[Q]" + RESET+BLUE+"/Inventory[I]: "+RESET);
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
                                System.out.println("\n"+RED+"Invalid choice!"+RESET);
                                continue;
                            }
                        }
                    } else if (input.equalsIgnoreCase("E")) {
                        System.out.println("For which warrior do you want to buy equipment?");
                        System.out.println(BLUE+"Archer[A], Knight[K], Mage[M], Healer[H], Mythical Creature[MC]"+RESET);
                        while (true) {
                            System.out.print("\nEnter your choice, Which warrior you want to buy equipment "+BRIGHT_GREEN+ "[A,K,M,H,MC]: "+RESET);
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
                                System.out.println("\n"+RED+"Invalid choice!"+RESET);
                                continue;
                            }
                            break;
                        }
                    } else if (input.equalsIgnoreCase("Q")) {
                        break;
                    } else if (input.equalsIgnoreCase("I")) {
                        printInventory();
                    } else {
                        System.out.println("\n"+RED+"Invalid choice!"+RESET);
                        continue;
                    }
                }
            } else {
                System.out.println("\n"+RED+"Invalid choice!"+RESET);
            }
        }

        System.out.println("\nThank you for visiting the store " + this.userName + "!\n");
        System.out.println("\u001B[1mGoodbye!\u001B[0m");
        return true;
    }

    private void buyEquipment(Warrior warrior) {
        printMoney();
        if (warrior == null) {
            System.out.println("You don't have this warrior!\n");
            return;
        }
        System.out.println("\nWhich equipment do you want to buy?\n");
        System.out.println(BLUE+"Armor[A]\nArtefact[AR]"+RESET);
        System.out.println(RED+"\nQuit[Q]"+RESET+BLUE+"\nInventory[I]"+RESET);
        while (true) {
            System.out.print("\nEnter your choice "+GREEN+"[A/AR/Q/I]"+RESET+": ");
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
                System.out.println("\n"+RED+"Invalid choice!"+RESET);
            }
        }
    }

    private void buyOrReplaceEquip(Warrior warrior, String type) {
        printMoney();
        if (warrior.getWoredrobe().getEquipment(type) != null) {
            System.out.println(RED+"You already have this equipment!\n"+RESET);
            System.out.println("Do you want to remove it?. "+RED+"No money will be refunded."+RESET);
            System.out.println("\nYes[Y] or No[N]");
            while (true) {
                System.out.println("Enter your choice [Y/N]:");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    warrior.removeEquipment(type);
                    System.out.println("\nYou have removed the " + type + " from your " + warrior.type + ".");
                    break;
                } else if (input.equalsIgnoreCase("N")) {
                    return;
                } else {
                    System.out.println("\n"+RED+"Invalid choice!"+RESET);
                }
            }
        }
        System.out.println("Do you want to buy a new " + type + "?\n");
        System.out.println("Yes[Y] or No[N]");
        while (true) {
            System.out.print("Enter your choice "+BRIGHT_GREEN+"[Y/N]: "+RESET);
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                System.out.println("\nWhich " + type + " do you want to buy?\n");
                printEquipmentMap(type);
                printMoney();
                while (true) {
                    System.out.print("Enter the type of " +type+BLUE+" [name] "+RESET+"or "+RED+"Quit[Q]"+RESET+BLUE+"/Inventory[I]: "+RESET);
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("Q")) {
                        return;
                    } else if (input.equalsIgnoreCase("I")) {
                        printInventory();
                    } else if (InventoryItem.getEquipmentMap(type).containsKey(input)) {
                        if (getMoney() >= InventoryItem.getEquipmentMap(type).get(input.toLowerCase()).price) {
                            InventoryItem newEquipment = warrior.addEquipment(type, input.toLowerCase());
                            giveMoneyFor(newEquipment);
                            System.out.println("\nYou have bought a new " + type + "for " + newEquipment.price + " coins.");
                            printMoney();
                            return;
                        } else {
                            System.out.println("\n"+RED+"You don't have enough money!"+RESET);
                            break;
                        }
                    } else {
                        System.out.println("\n"+RED+"Invalid choice!"+RESET);
                    }
                }
            } else if (input.equalsIgnoreCase("N")) {
                return;
            } else {
                System.out.println("\n"+RED+"Invalid choice!"+RESET);
            }
        }
    }

    private void buyWarrior(Warrior warrior, String type) {
        printMoney();
        if (warrior != null) {
            System.out.println(RED+"\nYou already have this warrior!"+RESET);
            sellWarrior(warrior);
        } else if (warrior == null) {
            System.out.println("Do you want to buy a new " + type + "?");
            System.out.println("Yes[Y] or No[N]");
            while (true) {
                System.out.print("Enter your choice "+BRIGHT_GREEN+"[Y/N]: "+RESET);
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    System.out.println("Which " + type + " do you want to buy?\n");
                    printWarriorMap(type);
                    printMoney();
                    while (true) {
                        System.out.println("Enter the type of " +type+BLUE+" [name] "+RESET+"or "+RED+"Quit[Q]:"+RESET);
                        input = scanner.nextLine();
                        if (InventoryItem.getWarriorMap(type).containsKey(input)) {
                            if (getMoney() >= InventoryItem.getWarriorMap(type).get(input.toLowerCase()).price) {
                                InventoryItem newWarrior = addSquadMate(type, input.toLowerCase());
                                giveMoneyFor(newWarrior);
                                System.out.println("You have bought a new " + type + " for " + newWarrior.price+ " coins.");
                                printMoney();
                                return;
                            } else {
                                System.out.println(RED+"\nYou don't have enough money!"+ RESET);
                                break;
                            }
                        } else if (input.equalsIgnoreCase("Q")) {
                            return;
                        } else {
                            System.out.println("\n"+RED+"Invalid choice!"+RESET);
                        }
                    }
                }
            }
        }
    }

    private void sellWarrior(Warrior warrior) {
        printMoney();
        if (warrior == null) {
            System.out.println("\nYou don't currently have this warrior!");
            return;
        } else {
            System.out.println("Do you want to sell your " + warrior.name + " for " + warrior.getValue() + " coins?");
            System.out.println("Yes[Y] or No[N]");
            while (true) {
                System.out.println("\nEnter your choice "+BRIGHT_GREEN+ "[Y/N]: "+RESET);
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    getMoneyFrom(warrior);
                    removeSquadMate(warrior.type);
                    System.out.println("\nYou have sold your " + warrior.name + " for " + warrior.getValue() + " coins.");
                    printMoney();
                    break;
                } else if (input.equalsIgnoreCase("N")) {
                    break;
                } else {
                    System.out.println("\n"+RED+"\u2717"+RESET+"Invalid choice!");
                }
            }
        }
    }

    private void printMoney() {
        System.out.println(YELLOW+"You have " + getMoney() + " coins."+RESET);
    }

    public void printInventory() {
        System.out.println("Your squad:");
        if (squad.getArcher() != null) {
            squad.getArcher().printInfo();
        } else {
            System.out.println(CYAN+"\tArcher: None"+RESET);
        }
        if (squad.getKnight() != null) {
            squad.getKnight().printInfo();
        } else {
            System.out.println(CYAN+"\tKnight: None"+RESET);
        }
        if (squad.getMage() != null) {
            squad.getMage().printInfo();
        } else {
            System.out.println(CYAN+"\tMage: None"+RESET);
        }
        if (squad.getHealer() != null) {
            squad.getHealer().printInfo();
        } else {
            System.out.println(CYAN+"\tHealer: None"+RESET);
        }
        if (squad.getMythicalCreature() != null) {
            squad.getMythicalCreature().printInfo();
        } else {
            System.out.println(CYAN+"\tMythical Creature: None"+RESET);
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