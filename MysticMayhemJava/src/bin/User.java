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

    public int getxp() {
        return xp;
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
                System.out.println("Quit[Q], Inventory[I]");
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
                    else if (input.equalsIgnoreCase("Q"))
                        break;
                    else if (input.equalsIgnoreCase("I"))
                        printSquad();
                    else {
                        System.out.println("Invalid choice!");
                        continue;
                    }
                }
            } else if (input.equalsIgnoreCase("B")) {
                printMoney();
                System.out.println("What do you want to buy?");
                System.out.println("Warrior[W], Equipment[E]");
                System.out.println("Quit[Q], Inventory[I]");
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
                                buyWarrior(squad.getArcher(), "Archer");
                            } else if (input.equalsIgnoreCase("K")) {
                                buyWarrior(squad.getKnight(), "Knight");
                            } else if (input.equalsIgnoreCase("M")) {
                                buyWarrior(squad.getMage(), "Mage");
                            } else if (input.equalsIgnoreCase("H")) {
                                buyWarrior(squad.getHealer(), "Healer");
                            } else if (input.equalsIgnoreCase("MC")) {
                                buyWarrior(squad.getMythicalCreature(), "MythicalCreature");
                            } else {
                                System.out.println("Invalid choice!");
                                continue;
                            }
                            break;
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
                        printSquad();
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
                printSquad();
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private void buyOrReplaceEquip(Warrior warrior, String type) {
        printMoney();
        if (warrior.getWoredrobe().getEquipment(type) != null) {
            System.out.println("You already have this equipment!");
            System.out.println("Do you want to replce it?. No money will be refunded.");
            System.out.println("Yes[Y] or No[N]");
            while (true) {
                System.out.println("Enter your choice [Y/N]:");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    warrior.getWoredrobe().removeEquipment(type);
                    System.out.println("Which " + type + " do you want to buy?");
                    InventoryItem.printMap(type);
                    printMoney();
                    System.out.println("Enter the name of the " + type + " you want to buy: ");
                    while (true) {
                        System.out.println("Enter your choice [name] of  " + type + " you want to buy");
                        input = scanner.nextLine();
                        if (InventoryItem.getEquipmentMap(type).containsKey(input)) {
                            InventoryItem newEquipment = warrior.getWoredrobe().addEquipment(type, input);
                            System.out
                                    .println("You have bought a new " + type + "for " + newEquipment.value + " coins.");
                            giveMoneyFor(newEquipment);
                            printMoney();
                            return;
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
        System.out.println("Do you want to buy a new " + type + "?");
        System.out.println("Yes[Y] or No[N]");
        while (true) {
            System.out.println("Enter your choice [Y/N]: ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                System.out.println("Which " + type + " do you want to buy?");
                InventoryItem.printMap(type);
                printMoney();
                System.out.println("Enter the name of the " + type + " you want to buy: ");
                while (true) {
                    System.out.println("Enter your choice [name]: ");
                    input = scanner.nextLine();
                    if (InventoryItem.getEquipmentMap(type).containsKey(input)) {
                        if (getMoney() >= InventoryItem.getEquipmentMap(type).get("input").price) {
                            InventoryItem newEquipment = warrior.getWoredrobe().addEquipment(type, input);
                            giveMoneyFor(newEquipment);
                            System.out
                                    .println("You have bought a new " + type + "for " + newEquipment.value + " coins.");
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

    private void buyWarrior(InventoryItem item, String type) {
        printMoney();
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
            if (input.equalsIgnoreCase("Y")) {
                System.out.println("Which " + type + " do you want to buy?");
                InventoryItem.printMap(type);
                printMoney();
                System.out.println("Enter the name of the " + type + " you want to buy: ");
                while (true) {
                    System.out.println("Enter your choice [name]: ");
                    input = scanner.nextLine();
                    if (InventoryItem.getWarriorMap(type).containsKey(input)) {
                        if (getMoney() >= InventoryItem.getWarriorMap(type).get("input").price) {
                            InventoryItem newWarrior = squad.addSquadMate(type);
                            giveMoneyFor(newWarrior);
                            System.out.println("You have bought a new " + type + "for " + newWarrior.value + " coins.");
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
        printMoney();
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