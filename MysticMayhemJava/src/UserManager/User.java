package UserManager;
import Warrior.Warrior;

public class User {
    public static int UserCount = 100000;
    public final int userID;
    public int xp;
    public final String userName;
    private float money;
    public Warrior Archer, Healer, Knight, Mage, MythicalCreature = null;
    public User(String userName) {
        this.userID = UserCount++;
        this.xp = 0;
        this.userName = userName;
        this.money = 500;
    }
    public void updateMoney(float money) {
        this.money += money;
    }
    public float getMoney() {
        return this.money;
    }
    public void updateXP(int xp) {
        this.xp += xp;
    }
}
