package bin;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;



public abstract class Battle {
    private static Map<String, User> filterUsersByXP(Map<String, User> Users, int current_xp) {
        // Create a filtered map to store users with XP approximately near to the current user's XP
        Map<String, User> filteredUsers = new HashMap<>();
    
        // Define a threshold for XP difference
        int xpThreshold = 5; // Adjust this threshold as needed
    
        // Iterate through all users and add those with XP within the threshold to the filtered map
        for (Map.Entry<String, User> entry : Users.entrySet()) {
            User user = entry.getValue();
            if (Math.abs(user.getxp() - current_xp) <= xpThreshold) {
                filteredUsers.put(entry.getKey(), user);
            }
        }
    
        return filteredUsers;
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

    public static void start_battle(User current_user,Map<String,User> Users){
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
//Attack call function
    static void attack(Warrior attacker, Warrior defender) {
        double damage ;
        // Calculate the damage based on the attacker's attack and defender's defense
        double normal_damage = 0.5*(attacker.battleAttack) - 0.1*(defender.battleDefense);
        if (attacker.bonusTurns>0){           
            double bonus_damage = (normal_damage)*(attacker.bonusAttackBuff);
            damage = normal_damage + bonus_damage;
        }
        else{
            damage = normal_damage;
        }
        defender.battleHealth = defender.battleHealth - damage;
        attacker.battleHealth = defender.battleHealth + (attacker.battleHealth)*(attacker.healPerAttack);
       
        
        System.out.println(attacker.name + " attacked " + defender.name + " for " + damage + " damage!");
        
    }

     public static void startBattleWithOpponent(User current_user, User opponent) {   
        Warrior[] squad_current_user = new Warrior[5];
        Warrior[] squad_opponent = new Warrior[5];
        //Assigning the warriors to the squad of the currentuser
        squad_current_user[0] = current_user.squad.getArcher(current_user.homeGround);
        squad_current_user[1] = current_user.squad.getHealer(current_user.homeGround);
        squad_current_user[2] = current_user.squad.getKnight(current_user.homeGround);
        squad_current_user[3] = current_user.squad.getMage(current_user.homeGround);
        squad_current_user[4] = current_user.squad.getMythicalCreature(current_user.homeGround);
        //Assigning the warriors to the squad of the opponent
        squad_opponent[0] = opponent.squad.getArcher(opponent.homeGround);
        squad_opponent[1] = opponent.squad.getHealer(opponent.homeGround);
        squad_opponent[2] = opponent.squad.getKnight(opponent.homeGround);
        squad_opponent[3] = opponent.squad.getMage(opponent.homeGround);
        squad_opponent[4] = opponent.squad.getMythicalCreature(opponent.homeGround);

        //ge the copys of squad_arrays
        Warrior[] speed_current_user = squad_current_user;
        Warrior[] defence_current_user = squad_current_user;
        Warrior[] speed_opponent = squad_opponent;
        Warrior[] defense_opponent = squad_current_user;
        
        // Sort by speed (descending) using custom comparator
        Comparator<Warrior> speedDescendingComparator = (w1, w2) -> Integer.compare(w2.battleSpeed, w1.battleSpeed);

        // Sort by defense (ascending) using lambda expression
        Comparator<Warrior> defenseAscendingComparator = Comparator.comparingInt(w -> w.battleDefense);

        // Sort copies based on specific criteria
        Arrays.sort(speed_current_user, speedDescendingComparator);
        Arrays.sort(defence_current_user, defenseAscendingComparator);
        Arrays.sort(speed_opponent, speedDescendingComparator);
        Arrays.sort(defense_opponent, defenseAscendingComparator);
        // Start the battle
        int num_of_battles = 0;
        boolean current_user_turn = true;
    
        while (num_of_battles < 10) {
            for (int i = 0; i < 5; i++) {
                // Get the current warriors
                Warrior current_warrior;
                Warrior opponent_warrior;
                if (current_user_turn) {
                    current_warrior = speed_current_user[i];
                    opponent_warrior = defense_opponent[i];
                } else {
                    current_warrior = speed_opponent[i];
                    opponent_warrior = defence_current_user[i];
                }
                
                // Attack the opponent's warrior
                attack(current_warrior, opponent_warrior);
                
                // Check if the opponent's warrior is defeated
                if (opponent_warrior.battleHealth <= 0) {
                    System.out.println(opponent_warrior.name + " is dead");
                    // Remove the defeated warrior from the opponent's squad
                    if (current_user_turn) {
                        defense_opponent[i] = null;
                    } else {
                        defence_current_user[i] = null;
                    }
                }
                
                // Check if both squads are completely defeated
                if (Arrays.stream(defense_opponent).allMatch(w -> w == null) &&
                    Arrays.stream(defence_current_user).allMatch(w -> w == null)) {
                    System.out.println("The battle ended in a draw!");
                    return;
                }

                 // Check if the current warrior is a Highlander in Hillcrest and grant bonus turn
                 if (current_user.homeGround.equals("Hillcrest") && current_warrior.name == "Highlander") {
                    // Highlander gets a bonus turn with 20% of their attack power
                    double bonus_damage = 0.2 * current_warrior.battleAttack;
                    double bonus_turn_damage = 0.5 * current_warrior.battleAttack - 0.1 * opponent_warrior.battleDefense + bonus_damage;
                    opponent_warrior.battleHealth -= bonus_turn_damage;

                    // Output bonus turn details
                    System.out.println(current_warrior.name + " takes a bonus turn and attacks " + opponent_warrior.name + " for " + bonus_turn_damage + " damage!");
                }
            }
            
            // Alternate turns
            current_user_turn = !current_user_turn;
            num_of_battles++;
        }
        
        // Check if any squad has warriors remaining
        if (Arrays.stream(defense_opponent).allMatch(w -> w == null)) {
            System.out.println("Congratulations! You have defeated " + opponent.userName + "!");
            // Update the XP of the current user
            current_user.xp = current_user.getxp() + 1;
            // Update the coin value of the current user
            current_user.incrementMoney((int) (opponent.getMoney() * 0.1));
            // Update the coin value of the opponent
            opponent.decrementMoney((int) (current_user.getMoney() * 0.1));
        } else if (Arrays.stream(defence_current_user).allMatch(w -> w == null)) {
            System.out.println(opponent.userName + " has defeated you!");
            // Update the XP of the current user
            opponent.xp = opponent.getxp() + 1;
            // Update the coin value of the current user
            opponent.incrementMoney((int) (current_user.getMoney() * 0.1));
            // Update the coin value of the opponent
            current_user.decrementMoney((int) (opponent.getMoney() * 0.1));
        }
        

            
    }

}


