package bin;


import bin.Warriors.*;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public abstract class Battle {
    private static int user1AttackPointer = 0;
    private static int user2AttackPointer = 0;
    private static int user1DefencePointer = 0;
    private static int user2DefencePointer = 0;

    public static void start(User currentUser, Map<String, User> users) {
        System.out.println("Battle started!");
        // Choosing oponent
        User opponent = selectOponent(users, currentUser);
        String battleGround = opponent.homeGround;

        // Settiing up arrays for the battle
        // user array
        Warrior[] user1Attack = { currentUser.squad.getArcher(battleGround), currentUser.squad.getKnight(battleGround),
                currentUser.squad.getMage(battleGround), currentUser.squad.getHealer(battleGround),
                currentUser.squad.getMythicalCreature(battleGround) };
        Warrior[] user1Defence = { currentUser.squad.getArcher(battleGround), currentUser.squad.getKnight(battleGround),
                currentUser.squad.getMage(battleGround), currentUser.squad.getHealer(),
                currentUser.squad.getMythicalCreature(battleGround) };

        // oponent array
        Warrior[] user2Attack = { opponent.squad.getArcher(battleGround), opponent.squad.getKnight(battleGround),
                opponent.squad.getMage(battleGround), opponent.squad.getHealer(battleGround),
                opponent.squad.getMythicalCreature(battleGround) };
        Warrior[] user2Defence = { opponent.squad.getArcher(battleGround), opponent.squad.getKnight(battleGround),
                opponent.squad.getMage(battleGround), opponent.squad.getHealer(),
                opponent.squad.getMythicalCreature(battleGround) };

        // Soring arrays
        Arrays.sort(user2Attack, Comparator.comparing(Warrior::getBattleSpeed, Comparator.reverseOrder())
                .thenComparing(Warrior::getAttackPriority));
        Arrays.sort(user2Defence, Comparator.comparing(Warrior::getBattleDefense)
                .thenComparing(Warrior::getDefensePriority));

        Arrays.sort(user1Attack, Comparator.comparing(Warrior::getBattleSpeed, Comparator.reverseOrder())
                .thenComparing(Warrior::getAttackPriority));
        Arrays.sort(user1Defence, Comparator.comparing(Warrior::getBattleDefense)
                .thenComparing(Warrior::getDefensePriority));

        // Battle 10 turns
        Warrior attacker;
        Warrior defender;
        for (int i = 0; i < 10; i++) {
            if (!isBattleStillGoing(user1Defence, user2Defence))
                break;
            // User1 attack
            attacker = getWarriorUsr1Atk(user1Attack);
            if (attacker instanceof Healer)
                defender = getLowestHealthWarrior(user1Defence);
            else
                defender = getWarriorUsr2Def(user2Defence);
            attack(attacker, defender);
            if (attacker instanceof Healer)
                System.out.println("Your " + attacker.type + "[" + attacker.battleHealth + "] " + attacker.name
                        + " healed your " + defender.type + "[" + defender.battleHealth + "] "
                        + defender.name);
            else
                System.out.println("Your " + attacker.type + "[" + attacker.battleHealth + "] " + attacker.name
                        + " attacked " + opponent.userName + "'s " + defender.type + "[" + defender.battleHealth + "] "
                        + defender.name);
            if (attacker.bonusTurns > 0) {
                if (attacker instanceof Healer)
                    defender = getLowestHealthWarrior(user1Defence);
                else
                    defender = getWarriorUsr2Def(user2Defence);
                bonusAttack(attacker, defender);
            }
            if (!isBattleStillGoing(user1Defence, user2Defence))
                break;

            // User2 attack
            attacker = getWarriorUsr2Atk(user2Attack);
            if (attacker instanceof Healer)
                defender = getLowestHealthWarrior(user2Defence);
            else
                defender = getWarriorUsr1Def(user1Defence);
            attack(attacker, defender);
            if (attacker instanceof Healer)
                System.out.println(opponent.userName + "'s " + attacker.type + "[" + attacker.battleHealth + "] "
                        + attacker.name + " healed your " + defender.type + "[" + defender.battleHealth + "] "
                        + defender.name);
            else
                System.out.println(opponent.userName + "'s " + attacker.type + "[" + attacker.battleHealth + "] "
                        + attacker.name + " attacked " + "your " + defender.type + "[" + defender.battleHealth + "] "
                        + defender.name);
            if (attacker.bonusTurns > 0) {
                if (attacker.type == "Healer")
                    defender = getLowestHealthWarrior(user2Defence);
                else
                    defender = getWarriorUsr2Def(user1Defence);
            }
        }
        if (!isBattleStillGoing(user1Defence, user2Defence)) {
            if (isUser1Won(user1Defence, user2Defence)) {
                System.out.println(currentUser.userID + " won!");
                currentUser.increaseXp(1);
                int exchangeMoney = opponent.getMoney() / 10;
                currentUser.incrementMoney(exchangeMoney);
                opponent.decrementMoney(exchangeMoney);
                System.out.println("You won " + exchangeMoney + " coins." + "You have " + currentUser.getMoney()
                        + " coins now.");
                System.out.println("You won 1 XP");
            } else {
                System.out.println(opponent.userName + " won!");
                opponent.increaseXp(1);
                int exchangeMoney = currentUser.getMoney() / 10;
                opponent.incrementMoney(exchangeMoney);
                currentUser.decrementMoney(exchangeMoney);
                System.out.println(opponent.userName + " won " + exchangeMoney + " coins." + opponent.userName
                        + " has " + opponent.getMoney() + " coins now.");
                System.out.println(opponent.userName + " won 1 XP");
            }
        } else {
            System.out.println("Draw!");
        }
        currentUser.squad.resetBattle();
        opponent.squad.resetBattle();
        user1AttackPointer = 0;
        user2AttackPointer = 0;
        user1DefencePointer = 0;
        user2DefencePointer = 0;
    }

    public static User selectOponent(Map<String, User> users, User currentUser) {

        while (true) {
            // random umteger below 10
            // Convert map entries to a list
            List<Map.Entry<String, User>> entries = new ArrayList<>(users.entrySet());

            // Select a random entry
            Random random = new Random();
            Map.Entry<String, User> randomEntry = entries.get(random.nextInt(entries.size()));

            // Get the user from the random entry
            User randomUser = randomEntry.getValue();
            if (randomUser == currentUser || !randomUser.isAllWarriorsAwailable()) continue;
            System.out.println("Do you want to battle with " + randomUser.userName + " : " + randomUser.xp);
            Scanner scnner = new Scanner(System.in);
            while (true) {
                System.out.println("Enter your choice[Y]/[N]");
                String choice = scnner.nextLine();
                if (choice.equalsIgnoreCase("Y")) {
                    return randomUser;
                } else if (choice.equalsIgnoreCase("N"))
                    break;
                else
                    System.out.println("Wrong Choice");
            }
        }

        // Calculate distances and weights

        // Map<String, Double> weights = new HashMap<>();
        // double totalDistance = 0;

        // for (Map.Entry<String, User> entry : users.entrySet()) {
        // int distance = Math.abs(entry.getValue().getxp() - CurrentXP);
        // double weight = 1.0 / distance;
        // weights.put(entry.getKey(), weight);
        // totalDistance += weight;
        // }

        // // Normalize weights
        // for (Map.Entry<String, Double> entry : weights.entrySet()) {
        // entry.setValue(entry.getValue() / totalDistance);
        // }

        // // Select random value based on weights
        // String selectedOponent = null;
        // while (true) {
        // double rand = Math.random();
        // double sum = 0;
        // String selectedKey = null;
        // while (selectedKey == null) {
        // for (Map.Entry<String, Double> entry : weights.entrySet()) {
        // sum += entry.getValue();
        // if (rand <= sum) {
        // selectedKey = entry.getKey();
        // break;
        // }
        // }
        // }
        // User selecUser = users.get(selectedKey);
        // System.out.println("Do you want to battle with");
        // System.out.println(selecUser.userName + " with " + selecUser.getxp() + "
        // XP");
        // while (true) {
        // System.out.println("Yes[Y] or No[N]");
        // String input = System.console().readLine();
        // if (input.equals("Y")) {
        // selectedOponent = selectedKey;
        // break;
        // } else if (input.equals("N")) {
        // break;
        // }
        // }
        // if (selectedOponent != null) {
        // break; // Break out of the outer while loop
        // }
        // }

        // // Output
        // if (selectedOponent != null) {
        // return users.get(selectedOponent);
        // }
        // return null;
    }

    public static Boolean isAlive(Warrior warrior) {
        return warrior.battleHealth > 0;

    }

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

    public static Boolean isUser1Won(Warrior[] user1, Warrior[] user2) {
        for (int x = 0; x < 5; x++) {
            if (isAlive(user2[x])) {
                return false;
            }
        }
        return true;
    }

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

    public static void attack(Warrior attacker, Warrior defWarrior) {
        if (attacker instanceof Healer) {
            defWarrior.battleHealth += attacker.battleAttack * 0.1;
            attacker.battleHealth += attacker.healPerAttack * attacker.battleHealth;
            return;
        }
        defWarrior.battleHealth -= (0.5 * attacker.battleAttack - 0.1 * defWarrior.battleDefense);
        attacker.battleHealth += attacker.healPerAttack * attacker.battleHealth;
    }

    public static void bonusAttack(Warrior attWarrior, Warrior defWarrior) {
        for (int i = 0; i < attWarrior.bonusTurns; i++) {
            float battleDamage = 0.5f * attWarrior.battleAttack - 0.1f * defWarrior.battleDefense;
            defWarrior.battleHealth -= battleDamage + battleDamage * attWarrior.bonusAttackBuff;
            attWarrior.battleHealth += attWarrior.healPerAttack * attWarrior.battleHealth;
            System.out.println("Bonus attack from " + attWarrior.name + "[" + attWarrior.battleHealth + "] " + " to "
                    + defWarrior.name + "[" + defWarrior.battleHealth + "]");
        }
    }
}

