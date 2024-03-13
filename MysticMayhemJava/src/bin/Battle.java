package bin;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
 * todo balance only healer remain sitauation 
 */

public abstract class Battle {
    private static int i = 0;
    private static int j = 0;
    private static int m = 0;
    private static int n = 0;

    public static void start(User user, Map<String, User> users) {
        System.out.println("Battle started!");
        // Choosing oponent
        User user2 = selectOponent(users, user.getxp());
        String battleGround = user2.homeGround;

        // Settiing up arrays for the battle
        // user array
        Warrior[] user1Attack = { user.squad.getArcher(battleGround), user.squad.getKnight(battleGround),
                user.squad.getMage(battleGround), user.squad.getHealer(battleGround),user.squad.getMythicalCreature(battleGround) };
        Warrior[] user1Defence = { user.squad.getArcher(battleGround), user.squad.getKnight(battleGround),
                user.squad.getMage(battleGround), user.squad.getHealer(),user.squad.getMythicalCreature(battleGround) };

        // oponent array
        Warrior[] user2Attack = { user2.squad.getArcher(battleGround), user2.squad.getKnight(battleGround),
                user2.squad.getMage(battleGround), user2.squad.getHealer(battleGround),user2.squad.getMythicalCreature(battleGround) };
        Warrior[] user2Defence = { user2.squad.getArcher(battleGround), user2.squad.getKnight(battleGround),
                user2.squad.getMage(battleGround), user2.squad.getHealer(), user2.squad.getMythicalCreature(battleGround) };

        // Soring arrays
        Arrays.sort(user2Attack, Comparator.comparing(Warrior::getBattleSpeed, Comparator.reverseOrder())
                .thenComparing(Warrior::getAttackPriority));
        Arrays.sort(user2Defence, Comparator.comparing(Warrior::getBattleDefense, Comparator.reverseOrder())
                .thenComparing(Warrior::getDefensePriority));

        Arrays.sort(user1Attack, Comparator.comparing(Warrior::getBattleSpeed, Comparator.reverseOrder())
                .thenComparing(Warrior::getAttackPriority));
        Arrays.sort(user1Defence, Comparator.comparing(Warrior::getBattleDefense, Comparator.reverseOrder())
                .thenComparing(Warrior::getDefensePriority));

        // Battle 10 turns
        Warrior attacker;
        Warrior defender;
        for (int i = 0; i < 10; i++) {
            if (!isBattleStillGoing(user1Defence, user2Defence))
                break;

            // User1 attack
            attacker = getWarriorUsr1Atk(user1Attack);
            defender = getWarriorUsr2Def(user2Defence);
            if (attacker.type == "Healer")
                defender = getLowestHealthWarrior(user1Defence);
            attack(attacker, defender);
            System.out.println("Your " + attacker.type + "[" + attacker.battleHealth + "] " + attacker.name
                    + " attacked " + user2.userName + "'s " + defender.type + "[" + defender.battleHealth + "] "
                    + defender.name);
            if (isAlive(defender))
                bonusAttack(attacker, defender);
            if (!isBattleStillGoing(user1Defence, user2Defence))
                break;

            // User2 attack
            attacker = getWarriorUsr2Atk(user2Attack);
            defender = getWarriorUsr1Def(user1Defence);
            if (attacker.type == "Healer")
                defender = getLowestHealthWarrior(user2Defence);
            attack(attacker, defender);
            System.out
                    .println(user2.userName + "'s " + attacker.type + "[" + attacker.battleHealth + "] " + attacker.name
                            + " attacked your " + defender.type + "[" + defender.battleHealth + "] " + defender.name);
            if (isAlive(defender))
                bonusAttack(attacker, defender);
        }
        if (!isBattleStillGoing(user1Defence, user2Defence)) {
            if (isUser1Won(user1Defence, user2Defence)) {
                System.out.println( user.userID+" won!");
                user.increaseXp(1);
                int exchangeMoney = user2.getMoney() / 10;
                user.increaseMoney(exchangeMoney);
                user2.decreaseMoney(exchangeMoney);
            } else {
                System.out.println(user2.userName+" won!");
                user2.increaseXp(1);
                int exchangeMoney = user.getMoney() / 10;
                user2.increaseMoney(exchangeMoney);
                user.decreaseMoney(exchangeMoney);
            }
        } else {
            System.out.println("Draw!");
        }
        user.squad.resetBattle();
        user2.squad.resetBattle();
        i = 0;
        j = 0;
        m = 0;
        n = 0;
    }

    public static User selectOponent(Map<String, User> users, int CurrentXP) {

        while (true) {
            // random umteger below 10
            // Convert map entries to a list
            List<Map.Entry<String, User>> entries = new ArrayList<>(users.entrySet());

            // Select a random entry
            Random random = new Random();
            Map.Entry<String, User> randomEntry = entries.get(random.nextInt(entries.size()));

            // Get the user from the random entry
            User randomUser = randomEntry.getValue();
            System.out.println("Do you want to battle with "+ randomUser.userName+" : "+randomUser.xp);
            Scanner scnner = new Scanner(System.in);
            while (true) {
                String choice = scnner.nextLine();
                System.out.println("Enter your choice[Y]/[N]");
                if (choice.equalsIgnoreCase("Y")) return randomUser;
                else if(choice.equalsIgnoreCase("N")) break;
                else System.out.println("Wrong Choice");
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
        for (int i = 0; i < 5; i++) {
            if (isAlive(user1[i])) {
                user1Alive = true;
                break;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (isAlive(user2[i])) {
                user2Alive = true;
                break;
            }
        }
        return user1Alive && user2Alive;
    }

    public static Boolean isUser1Won(Warrior[] user1, Warrior[] user2) {
        for (int i = 0; i < 5; i++) {
            if (isAlive(user2[i])) {
                return false;
            }
        }
        return true;
    }

    public static Warrior getWarriorUsr1Atk(Warrior[] warrior) {
        while (true) {
            if (isAlive(warrior[i])) {
                return warrior[i];
            }
            i++;
            if (i == 5) {
                i = 0;
            }
        }
    }

    public static Warrior getWarriorUsr2Atk(Warrior[] warrior) {
        while (true) {
            if (isAlive(warrior[j])) {
                return warrior[j];
            }
            j++;
            if (j == 5) {
                j = 0;
            }
        }
    }

    public static Warrior getWarriorUsr1Def(Warrior[] warrior) {
        while (true) {
            if (isAlive(warrior[m])) {
                return warrior[m];
            }
            m++;
            if (m == 5) {
                m = 0;
            }
        }
    }

    public static Warrior getWarriorUsr2Def(Warrior[] warrior) {
        while (true) {
            if (isAlive(warrior[n])) {
                return warrior[n];
            }
            n++;
            if (n == 5) {
                n = 0;
            }
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
            if (warrior[i].battleHealth < lowestHealthWarrior.battleHealth && isAlive(warrior[i])) {
                lowestHealthWarrior = warrior[i];
            }
        }
        return lowestHealthWarrior;
    }

    public static void attack(Warrior attacker, Warrior defWarrior) {
        if (attacker.type == "Healer") {
            defWarrior.battleHealth += attacker.battleAttack;
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

/*
 * public class Main {
 * public static void main(String[] args) {
 * // Your map
 * Map<String, Integer> map = new HashMap<>();
 * map.put("A", 5);
 * map.put("B", 10);
 * map.put("C", 15);
 * map.put("D", 20);
 * 
 * // Given number
 * int givenNumber = 12;
 * 
 * // Calculate distances and weights
 * Map<String, Double> weights = new HashMap<>();
 * double totalDistance = 0;
 * for (Map.Entry<String, Integer> entry : map.entrySet()) {
 * int distance = Math.abs(entry.getValue() - givenNumber);
 * double weight = 1.0 / distance;
 * weights.put(entry.getKey(), weight);
 * totalDistance += weight;
 * }
 * 
 * // Normalize weights
 * for (Map.Entry<String, Double> entry : weights.entrySet()) {
 * entry.setValue(entry.getValue() / totalDistance);
 * }
 * 
 * // Select random value based on weights
 * Random random = new Random();
 * double rand = random.nextDouble();
 * double sum = 0;
 * String selectedKey = null;
 * for (Map.Entry<String, Double> entry : weights.entrySet()) {
 * sum += entry.getValue();
 * if (rand <= sum) {
 * selectedKey = entry.getKey();
 * break;
 * }
 * }
 * 
 * // Output
 * System.out.println("Selected key: " + selectedKey);
 * }
 * }
 */

/*
 * import java.util.Arrays;
 * import java.util.Comparator;
 * 
 * class Some {
 * int attribute1;
 * int attribute2;
 * 
 * public Some(int attribute1, int attribute2) {
 * this.attribute1 = attribute1;
 * this.attribute2 = attribute2;
 * }
 * 
 * public int getAttribute1() {
 * return attribute1;
 * }
 * 
 * public int getAttribute2() {
 * return attribute2;
 * }
 * 
 * @Override
 * public String toString() {
 * return "Some{" +
 * "attribute1=" + attribute1 +
 * ", attribute2=" + attribute2 +
 * '}';
 * }
 * }
 * 
 * public class Main {
 * public static void main(String[] args) {
 * Some[] array = {
 * new Some(1, 5),
 * new Some(2, 6),
 * new Some(2, 4),
 * new Some(3, 2),
 * new Some(1, 3),
 * new Some(2, 2)
 * };
 * 
 * Arrays.sort(array, Comparator
 * .comparing(Some::getAttribute1, Comparator.reverseOrder())
 * .thenComparing(Some::getAttribute2));
 * 
 * System.out.println(Arrays.toString(array));
 * }
 * }
 */