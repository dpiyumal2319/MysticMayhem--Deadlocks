package bin;
import java.util.Map;
import java.util.Scanner;
import bin.User;
import bin.Warriors.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public abstract class Battle {
    private Map<String, User> filterUsersByXP(Map<String, User> Users, int current_xp) {
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
    private User getRandomUser(Map<String, User> Users, int current_xp) {
        // Filter users with XP approximately near to the current user's XP
        Map<String, User> filteredUsers = filterUsersByXP(Users, current_xp);
    
        // Randomly select a user from the filtered list
        Random random = new Random();
        int index = random.nextInt(filteredUsers.size());
        User opponent = filteredUsers.values().toArray(new User[0])[index];
    
        return opponent;
    }

    public void start_battle(User current_user,Map<String,User> Users){
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
     public void startBattleWithOpponent(User current_user, User opponent) {   
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
        
            
    }

}
