package bin;

import java.util.Random;
import java.util.Scanner;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import bin.Warriors.*;

public abstract class Battle {
    private static int user1AttackPointer = 0;
    private static int user2AttackPointer = 0;
    private static int user1DefencePointer = 0;
    private static int user2DefencePointer = 0;

    public static void start(User currentUser, Map<String, User> users) {
        UIElements.printLogo();
        System.out.println(UIElements.MAGENTA + "Select your Opponent!!" + UIElements.RESET);
        Scanner scanner = new Scanner(System.in);

        // Choosing a random oponent
        User opponent = null;
        while (true) {
            // random umteger below 10
            // Convert map entries to a list
            List<Map.Entry<String, User>> entries = new ArrayList<>(users.entrySet());

            // Select a random entry
            Random random = new Random();
            Map.Entry<String, User> randomEntry = entries.get(random.nextInt(entries.size()));

            // Get the user from the random entry
            User randomUser = randomEntry.getValue();
            if (randomUser == currentUser || !randomUser.isAllWarriorsAwailable())
                continue;
            System.out.println("\nDo you want to battle with " + UIElements.BLUE + randomUser.userName
                    + " : " + randomUser.xp + " XP" + UIElements.RESET + " ?\n");
            while (true) {
                System.out.println("Yes[Y] or No[N], to exit battle[Q]");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("Y")) {
                    opponent = randomUser;
                    break;
                } else if (choice.equalsIgnoreCase("N"))
                    break;
                else if (choice.equalsIgnoreCase("Q")) {
                    System.out.println("Exiting battle...");
                    UIElements.wait(500);
                    return;
                } else
                    System.out.println("Wrong Choice");
            }
            if (opponent != null)
                break;
        }
        // scanner.close();
        String battleGround = opponent.homeGround;

        // Settiing up arrays for the battle

        // user array
        Warrior[] userAttack = { currentUser.squad.getArcher(battleGround), currentUser.squad.getKnight(battleGround),
                currentUser.squad.getMage(battleGround), currentUser.squad.getHealer(battleGround),
                currentUser.squad.getMythicalCreature(battleGround) };
        Warrior[] userDefence = { currentUser.squad.getArcher(battleGround), currentUser.squad.getKnight(battleGround),
                currentUser.squad.getMage(battleGround), currentUser.squad.getHealer(),
                currentUser.squad.getMythicalCreature(battleGround) };

        // oponent array
        Warrior[] oponentAttack = { opponent.squad.getArcher(battleGround), opponent.squad.getKnight(battleGround),
                opponent.squad.getMage(battleGround), opponent.squad.getHealer(battleGround),
                opponent.squad.getMythicalCreature(battleGround) };
        Warrior[] opponentDefence = { opponent.squad.getArcher(battleGround), opponent.squad.getKnight(battleGround),
                opponent.squad.getMage(battleGround), opponent.squad.getHealer(),
                opponent.squad.getMythicalCreature(battleGround) };

        // Soring arrays accorfing to battle speed, battle defence and attack priority
        Arrays.sort(oponentAttack, Comparator.comparing(Warrior::getBattleSpeed, Comparator.reverseOrder())
                .thenComparing(Warrior::getAttackPriority));
        Arrays.sort(opponentDefence, Comparator.comparing(Warrior::getBattleDefense)
                .thenComparing(Warrior::getDefensePriority));

        Arrays.sort(userAttack, Comparator.comparing(Warrior::getBattleSpeed, Comparator.reverseOrder())
                .thenComparing(Warrior::getAttackPriority));
        Arrays.sort(userDefence, Comparator.comparing(Warrior::getBattleDefense)
                .thenComparing(Warrior::getDefensePriority));

        // Battle of 10 turns
        Warrior attacker;
        Warrior defender;
        float preHealth;
        System.out.println("\nBattle " + UIElements.GREEN + currentUser.userName + UIElements.RESET + " vs "
                + UIElements.RED + opponent.userName + UIElements.RESET + " on " + UIElements.YELLOW_BACKGROUND + UIElements.BLACK
                 + battleGround + " ground" + UIElements.RESET + "!!" + "\n");

        // Printing your squad
        System.out.println(UIElements.GREEN + "Your Squad" + UIElements.RESET + " : ");
        UIElements.wait(1000);
        for (int i = 0; i < 5; i++) {
            userAttack[i].printBattleInfo();
        }

        UIElements.wait(2000);
        // Printing oponent's squad
        System.out.println(UIElements.RED + "\n " + opponent.userName + "'s Squad" + UIElements.RESET + " : ");
        UIElements.wait(1000);
        for (int i = 0; i < 5; i++) {
            oponentAttack[i].printBattleInfo();
        }

        UIElements.wait(2000);
        System.out.println(
                "\nDisplay Structure: Attacker's_Owner Type[Health before attack] Name ~~ ~~ ~~ ~~ ~~ Reciever's_Owner Type[Health After Attack] Name\n");

        UIElements.wait(500);

        // Starting battle
        System.out.print(UIElements.YELLOW + "Battle starting in: ");
        for (int i = 5; i > 0; i--) {
            System.out.print(i + " ");
            UIElements.wait(750);
        }
        System.out.println("Start!\n" + UIElements.RESET);
        UIElements.wait(500);

        for (int i = 0; i < 10; i++) {
            if (!isBattleStillGoing(userDefence, opponentDefence))
                break;

            // Current User attack
            attacker = getWarriorUsr1Atk(userAttack);
            // If attacker is healer, then defender will be the lowest health warrior
            if (attacker instanceof Healer)
                defender = getLowestHealthWarrior(userDefence);
            else
                defender = getWarriorUsr2Def(opponentDefence);
            preHealth = attacker.battleHealth;
            float damage = attack(attacker, defender);

            // Printing the attack
            if (attacker instanceof Healer) {
                System.out.println(UIElements.GREEN + UIElements.BOLD + "You healing" + UIElements.RESET + " : Your "
                        + attacker.type + "[" + String.format("%.1f", preHealth) + "] " + attacker.name
                        + UIElements.Hearts(damage) + " Your " + defender.type + "["
                        + String.format("%.1f", defender.battleHealth)
                        + "] " + defender.name + "\n");

            } else {
                System.out.println(UIElements.GREEN + UIElements.BOLD + "You attacking" + UIElements.RESET + " : Your "
                        + attacker.type + "[" + String.format("%.1f", preHealth) + "] " + attacker.name
                        + UIElements.RedArrows(damage) + opponent.userName + "'s " + defender.type + "["
                        + String.format("%.1f", defender.battleHealth)
                        + "] "
                        + defender.name + UIElements.RED + (isAlive(defender) ? "\n" : " Died.\n") + UIElements.RESET);
            }
            // For the attackers healed right after they attack
            if (attacker.healPerAttack > 0)
                System.out.println(UIElements.hearsSet + "Attacker healed "
                        + attacker.type + "[" + String.format("%.1f", attacker.battleHealth) + "] " + attacker.name
                        + "\n");
            UIElements.wait(1000);
            // If attacker has bonus turns, then bonus attack
            if (attacker.bonusTurns > 0) {
                if (attacker instanceof Healer)
                    defender = getLowestHealthWarrior(userDefence);
                else
                    defender = getWarriorUsr2Def(opponentDefence);
                preHealth = attacker.battleHealth;
                damage = bonusAttack(attacker, defender);

                if (attacker instanceof Healer) {
                    System.out.println(UIElements.GREEN + UIElements.BOLD + "You healing" + UIElements.RESET + " : Your "
                            + UIElements.YELLOW + "Bonus turn " + UIElements.RESET
                            + attacker.type + "[" + String.format("%.1f", preHealth) + "] " + attacker.name
                            + UIElements.Hearts(damage) + " Your " + defender.type + "["
                            + String.format("%.1f", defender.battleHealth)
                            + "] " + defender.name + "\n");
                } else {
                    System.out.println(UIElements.GREEN + UIElements.BOLD + "You attacking" + UIElements.RESET + " : Your "
                            + UIElements.YELLOW + "Bonus Attack " + UIElements.RESET + attacker.type + "["
                            + String.format("%.1f", preHealth)
                            + "] " + attacker.name
                            + UIElements.RedArrows(damage) + opponent.userName + "'s " + defender.type + "["
                            + String.format("%.1f", defender.battleHealth)
                            + "] "
                            + defender.name + UIElements.RED + (isAlive(defender) ? "\n" : " Died.\n") + UIElements.RESET);
                }
                if (attacker.healPerAttack > 0)
                    System.out.println(UIElements.hearsSet + "Attacker healed "
                            + attacker.type + "[" + String.format("%.1f", attacker.battleHealth) + "] " + attacker.name
                            + "\n");
                UIElements.wait(1000);
            }

            // Break loop if battle is over
            if (!isBattleStillGoing(userDefence, opponentDefence))
                break;

            // Opponent attack
            attacker = getWarriorUsr2Atk(oponentAttack);
            if (attacker instanceof Healer)
                defender = getLowestHealthWarrior(opponentDefence);
            else
                defender = getWarriorUsr1Def(userDefence);
            preHealth = attacker.battleHealth;
            damage = attack(attacker, defender);
            if (attacker instanceof Healer) {
                System.out.println(UIElements.RED + UIElements.BOLD + "Opponent healing" + UIElements.RESET + " : "
                        + opponent.userName + " " + attacker.type + "[" + String.format("%.1f", preHealth)
                        + "] "
                        + attacker.name + UIElements.Hearts(damage) + opponent.userName + " : " + defender.type
                        + "[" + String.format("%.1f", defender.battleHealth) + "] " + defender.name + "\n");
            } else {
                System.out.println(UIElements.RED + UIElements.BOLD + "Opponent attacking" + UIElements.RESET + " : "
                        + opponent.userName + "'s " + attacker.type + "[" + String.format("%.1f", preHealth)
                        + "] "
                        + attacker.name + UIElements.RedArrows(damage) + "Your " + defender.type + "["
                        + String.format("%.1f", defender.battleHealth) + "] "
                        + defender.name + UIElements.RED + (isAlive(defender) ? "\n" : " Died.\n") + UIElements.RESET);
            }
            if (attacker.healPerAttack > 0)
                System.out.println(UIElements.hearsSet + "Attacker healed "
                        + attacker.type + "[" + String.format("%.1f", attacker.battleHealth) + "] " + attacker.name
                        + "\n");
            UIElements.wait(1000);
            if (attacker.bonusTurns > 0) {
                if (attacker instanceof Healer)
                    defender = getLowestHealthWarrior(opponentDefence);
                else
                    defender = getWarriorUsr2Def(userDefence);
                preHealth = attacker.battleHealth;
                damage = bonusAttack(attacker, defender);
                if (attacker instanceof Healer) {
                    System.out.println(UIElements.RED + UIElements.BOLD + "Opponent healing" + UIElements.RESET + " : "
                            + opponent.userName + "'s" + UIElements.YELLOW + "Bonus turn " + UIElements.RESET + attacker.type
                            + "["
                            + String.format("%.1f", preHealth)
                            + "] "
                            + attacker.name + UIElements.Hearts(damage) + opponent.userName + " : " + defender.type
                            + "[" + String.format("%.1f", defender.battleHealth) + "] " + defender.name + "\n");
                } else {
                    System.out.println(UIElements.RED + UIElements.BOLD + "Opponent attacking" + UIElements.RESET + " : "
                            + opponent.userName + "'s " + UIElements.YELLOW + "Bonus turn " + UIElements.RESET + attacker.type
                            + "[" + String.format("%.1f", preHealth) + "] " + attacker.name
                            + UIElements.RedArrows(damage) + "Your " + defender.type + "["
                            + String.format("%.1f", defender.battleHealth)
                            + "] " + defender.name + UIElements.RED + (isAlive(defender) ? "\n" : " Died.\n")
                            + UIElements.RESET);
                }
                if (attacker.healPerAttack > 0)
                    System.out.println(UIElements.hearsSet + "Attacker healed "
                            + attacker.type + "[" + String.format("%.1f", attacker.battleHealth) + "] " + attacker.name
                            + "\n");
                UIElements.wait(1000);
            }
        }
        // Checking if all warriors of either team are dead
        UIElements.wait(1000);
        if (!isBattleStillGoing(userDefence, opponentDefence)) {
            // If User won
            if (isUser1Won(userDefence, opponentDefence)) {
                System.out.println(UIElements.GREEN + "Congratulations " + UIElements.RESET + currentUser.name
                        + "! You won the battle!\n");
                currentUser.increaseXp(1);
                int exchangeMoney = opponent.getMoney() / 10;
                currentUser.incrementMoney(exchangeMoney);
                opponent.decrementMoney(exchangeMoney);
                System.out.println("You won " + UIElements.YELLOW + exchangeMoney + UIElements.RESET + " coins." + "You have "
                        + UIElements.YELLOW + currentUser.getMoney() + UIElements.RESET +
                        " coins now.");
                System.out.println("You won " + UIElements.YELLOW + " 1 " + UIElements.RESET + " XP. You have " + UIElements.YELLOW
                        + currentUser.xp + UIElements.RESET + " XP now.");
                // If oponent won
            } else {
                System.out.println(UIElements.RED + " " + opponent.userName + " Won!\n" + UIElements.RESET);
                int exchangeMoney = currentUser.getMoney() / 10;
                opponent.incrementMoney(exchangeMoney);
                opponent.increaseXp(1);
                currentUser.decrementMoney(exchangeMoney);
                System.out.println("You lost " + UIElements.YELLOW + exchangeMoney + UIElements.RESET + " coins." + "You have "
                        + UIElements.YELLOW + currentUser.getMoney() + UIElements.RESET +
                        " coins now.");
            }
        } else {
            System.out.println(UIElements.CYAN + "Draw!" + UIElements.RESET);
        }
        currentUser.squad.resetBattle();
        opponent.squad.resetBattle();
        user1AttackPointer = 0;
        user2AttackPointer = 0;
        user1DefencePointer = 0;
        user2DefencePointer = 0;
        System.out.println(UIElements.MAGENTA + "\n\nBattle Ended!" + UIElements.RESET);
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // Check whether the warrior is alive
    public static Boolean isAlive(Warrior warrior) {
        return warrior.battleHealth > 0;
    }

    // Check whether the battle is still going
    public static Boolean isBattleStillGoing(Warrior[] user1, Warrior[] user2) {
        Boolean user1Alive = false;
        Boolean user2Alive = false;
        for (int n = 0; n < 5; n++) {
            if (isAlive(user1[n])) {
                user1Alive = true;
                break;
            }
        }
        for (int n = 0; n < 5; n++) {
            if (isAlive(user2[n])) {
                user2Alive = true;
                break;
            }
        }
        return user1Alive && user2Alive;
    }

    // Check whether user1 won
    public static Boolean isUser1Won(Warrior[] user1, Warrior[] user2) {
        for (int x = 0; x < 5; x++) {
            if (isAlive(user2[x])) {
                return false;
            }
        }
        return true;
    }

    // Methods to get the warrior to attack and defend
    public static Warrior getWarriorUsr1Atk(Warrior[] warrior) {
        Warrior warr = null;
        while (true) {
            if (isAlive(warrior[user1AttackPointer])) {
                warr = warrior[user1AttackPointer];
            }
            user1AttackPointer++;
            if (user1AttackPointer == 5) {
                user1AttackPointer = 0;
            }
            if (warr != null)
                return warr;
        }
    }

    public static Warrior getWarriorUsr2Atk(Warrior[] warrior) {
        Warrior warr = null;
        while (true) {
            if (isAlive(warrior[user2AttackPointer])) {
                warr = warrior[user2AttackPointer];
            }
            user2AttackPointer++;
            if (user2AttackPointer == 5) {
                user2AttackPointer = 0;
            }
            if (warr != null)
                return warr;
        }
    }

    public static Warrior getWarriorUsr1Def(Warrior[] warrior) {
        Warrior warr = null;
        while (true) {
            if (isAlive(warrior[user1DefencePointer])) {
                warr = warrior[user1DefencePointer];
            }
            user1DefencePointer++;
            if (user1DefencePointer == 5) {
                user1DefencePointer = 0;
            }
            if (warr != null)
                return warr;
        }
    }

    public static Warrior getWarriorUsr2Def(Warrior[] warrior) {
        Warrior warr = null;
        while (true) {
            if (isAlive(warrior[user2DefencePointer])) {
                warr = warrior[user2DefencePointer];
            }
            user2DefencePointer++;
            if (user2DefencePointer == 5) {
                user2DefencePointer = 0;
            }
            if (warr != null)
                return warr;
        }
    }

    // Method to get the lowest health warrior
    public static Warrior getLowestHealthWarrior(Warrior[] warrior) {
        Warrior lowestHealthWarrior = warrior[0];
        for (int k = 0; k < 5; k++) {
            if (isAlive(warrior[k])) {
                lowestHealthWarrior = warrior[k];
                break;
            }
        }
        for (int l = 0; l < 5; l++) {
            if (warrior[l].battleHealth < lowestHealthWarrior.battleHealth && isAlive(warrior[l])) {
                lowestHealthWarrior = warrior[l];
            }
        }
        return lowestHealthWarrior;
    }

    // Methods to attack and bonus attack
    public static float attack(Warrior attacker, Warrior defWarrior) {
        if (attacker instanceof Healer) {
            defWarrior.battleHealth += attacker.battleAttack * 0.1;
            attacker.battleHealth += attacker.healPerAttack * attacker.battleHealth;
            return attacker.battleAttack * 0.1f;
        }
        defWarrior.battleHealth -= (0.5f * attacker.battleAttack - 0.1f * defWarrior.battleDefense);
        attacker.battleHealth += attacker.healPerAttack * attacker.battleHealth;
        return (0.5f * attacker.battleAttack - 0.1f * defWarrior.battleDefense);
    }

    public static float bonusAttack(Warrior attWarrior, Warrior defWarrior) {
        float damage = 0;
        for (int i = 0; i < attWarrior.bonusTurns; i++) {
            if (attWarrior instanceof Healer) {
                float battleDamage = 0.1f * attWarrior.battleAttack;
                defWarrior.battleHealth += battleDamage + battleDamage * attWarrior.bonusAttackBuff;
                attWarrior.battleHealth += attWarrior.healPerAttack * attWarrior.battleHealth;
                damage += battleDamage + battleDamage * attWarrior.bonusAttackBuff;
                continue;
            }
            float battleDamage = 0.5f * attWarrior.battleAttack - 0.1f * defWarrior.battleDefense;
            defWarrior.battleHealth -= battleDamage + battleDamage * attWarrior.bonusAttackBuff;
            attWarrior.battleHealth += attWarrior.healPerAttack * attWarrior.battleHealth;
            damage += battleDamage + battleDamage * attWarrior.bonusAttackBuff;
        }
        return damage;
    }
}
