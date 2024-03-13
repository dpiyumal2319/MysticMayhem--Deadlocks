package bin;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import bin.Warriors.Healer;

public abstract class Battle {

    public static void start_battle(User current_user, Map<String, User> Users) {
        System.out.println("Welcome to the battle!");
        // Get the XP of the current user
        int current_xp = current_user.getxp();
        // Randomly select a user from the Users map based on their XP
        User opponent = getRandomUser(Users, current_xp);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to battle " + opponent.userName + "? (Y/N)");
            String choice = scanner.nextLine().trim().toUpperCase();
            if (choice.equalsIgnoreCase("Y")) {
                // Start the battle with the selected opponent
                startBattleWithOpponent(current_user, opponent);
                break;
            } else if (choice.equalsIgnoreCase("N")) {
                // Get another random user
                opponent = getRandomUser(Users, current_xp);
            } else {
                System.out.println("Invalid choice! Please enter Y or N.");
            }
        }
    }

    private static User getRandomUser(Map<String, User> Users, int current_xp) {
        // Filter users with XP approximately near to the current user's XP
        Map<String, User> filteredUsers = filterUsersByXP(Users, current_xp);

        // Randomly select a user from the filtered list
        Random random = new Random();
        int index = random.nextInt(filteredUsers.size());
        User opponent = filteredUsers.values().toArray(new User[0])[index];

        return opponent;
    }

    private static Map<String, User> filterUsersByXP(Map<String, User> Users, int current_xp) {
        // Create a filtered map to store users with XP approximately near to the
        // current user's XP
        Map<String, User> filteredUsers = new HashMap<>();

        // Define a threshold for XP difference
        int xpThreshold = 50; // Adjust this threshold as needed

        // Iterate through all users and add those with XP within the threshold to the
        // filtered map
        for (Map.Entry<String, User> entry : Users.entrySet()) {
            User user = entry.getValue();
            if (Math.abs(user.getxp() - current_xp) <= xpThreshold) {
                filteredUsers.put(entry.getKey(), user);
            }
        }

        return filteredUsers;
    }

    private static void sortAttackArray(Warrior[] array) {
        Arrays.sort(array, (w1, w2) -> {
            if (w1.battleSpeed != w2.battleSpeed) {
                return Integer.compare(w2.battleSpeed, w1.battleSpeed); // Sort by battleSpeed descending
            } else {
                return Integer.compare(w1.attackPriority, w2.attackPriority); // Sort by attackPriority ascending
            }
        });
    }

    private static void sortDefenseArray(Warrior[] array) {
        Arrays.sort(array, (w1, w2) -> {
            if (w1.battleDefense != w2.battleDefense) {
                return Integer.compare(w1.battleDefense, w2.battleDefense); // Sort by battleDefense ascending
            } else {
                return Integer.compare(w1.defensePriority, w2.defensePriority); // Sort by defensePriority ascending
            }
        });
    }

    public static void startBattleWithOpponent(User current_user, User opponent) {
        // Get the warriors of the current user and the opponent
        Warrior[] squad_current_user = new Warrior[5];
        Warrior[] squad_opponent = new Warrior[5];
        squad_current_user[0] = current_user.squad.getArcher(current_user.homeGround);
        squad_current_user[1] = current_user.squad.getMage(current_user.homeGround);
        squad_current_user[2] = current_user.squad.getKnight(current_user.homeGround);
        squad_current_user[3] = current_user.squad.getMage(current_user.homeGround);
        squad_current_user[4] = current_user.squad.getMythicalCreature(current_user.homeGround);
        squad_opponent[0] = opponent.squad.getArcher(opponent.homeGround);
        squad_opponent[1] = opponent.squad.getMage(opponent.homeGround);
        squad_opponent[2] = opponent.squad.getKnight(opponent.homeGround);
        squad_opponent[3] = opponent.squad.getMage(opponent.homeGround);
        squad_opponent[4] = opponent.squad.getMythicalCreature(opponent.homeGround);

        // Arrays for current_user's squad
        Warrior[] squad_current_user_attack = new Warrior[5];
        Warrior[] squad_current_user_defense = new Warrior[5];

        // Arrays for opponent's squad
        Warrior[] squad_opponent_attack = new Warrior[5];
        Warrior[] squad_opponent_defense = new Warrior[5];

        // Populate attack and defense arrays for current_user's squad
        for (int i = 0; i < 5; i++) {
            squad_current_user_attack[i] = squad_current_user[i];
            squad_current_user_defense[i] = squad_current_user[i];
        }

        // Populate attack and defense arrays for opponent's squad
        for (int i = 0; i < 5; i++) {
            squad_opponent_attack[i] = squad_opponent[i];
            squad_opponent_defense[i] = squad_opponent[i];
        }

        // Sort arrays for current_user's squad
        sortAttackArray(squad_current_user_attack);
        sortDefenseArray(squad_current_user_defense);

        // Sort arrays for opponent's squad
        sortAttackArray(squad_opponent_attack);
        sortDefenseArray(squad_opponent_defense);

        int number_of_turns = 0;
        while (number_of_turns < 10) {
            Warrior attacker_current_user = getAttacker(squad_current_user_attack);
            Warrior attacker_opponent = getAttacker(squad_opponent_attack);
            if (attacker_current_user == null) {
                System.out.println("You lost the battle!");
                break;
            } else if (attacker_opponent == null) {
                System.out.println("You won the battle!");
                break;
            }

            if (attacker_current_user instanceof Healer) {
                Warrior lowest_health = getWeakestAttacker(squad_current_user);
                attack(attacker_current_user, lowest_health);
            } else {
                Warrior defender_opponent = getDefender(squad_opponent_defense);
                attack(attacker_current_user, defender_opponent);
            }
            if (attacker_opponent instanceof Healer) {
                Warrior lowest_health = getWeakestAttacker(squad_opponent);
                attack(attacker_opponent, lowest_health);
            } else {
                Warrior defender_curent_user = getDefender(squad_current_user_defense);
                attack(attacker_opponent, defender_curent_user);
            }
            number_of_turns++;
        }
    }

    static int i = 0, j = 0;

    private static Warrior getAttacker(Warrior[] squad) {
        if (i == 5) {
            i = 0;
        }
        while (i < 5) {
            if (squad[i].battleHealth > 0) {
                return squad[i];
            }
            i++;
        }
        return null;
    }

    private static Warrior getDefender(Warrior[] squad) {
        if (j == 5) {
            j = 0;
        }
        while (j < 5) {
            if (squad[j].getHealth() > 0) {
                return squad[j];
            }
            j++;
        }
        return null;
    }

    private static Warrior getWeakestAttacker(Warrior[] squad) {
        Warrior weakestAttacker = null;
        int minHealth = Integer.MAX_VALUE; // Set initial minimum health to the maximum possible value

        for (Warrior warrior : squad) {
            if (warrior != null && warrior.battleHealth < minHealth) {
                minHealth = warrior.battleHealth;
                weakestAttacker = warrior;
            }
        }

        return weakestAttacker;
    }

    private static void attack(Warrior attacker, Warrior defender) {
        defender.battleHealth -= attacker.battleAttack * 0.5 - defender.battleDefense * 0.1;

    }
}