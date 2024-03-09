package UserManager;

import Warrior.Warrior;

public class User {
    private static int UserCount = 100000;
    public final int userID;
    public int xp;
    public final String userName;
    private float money;
    public Warrior Archer, Healer, Knight, Mage, MythicalCreature;

    public User(String userName) {
        this.userID = UserCount++;
        this.xp = 0;
        this.userName = userName;
        this.money = 500;
    }

    public float getMoney() {
        return this.money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getxp() {
        return this.xp;
    }

    public Object[][] getInventory() {
        Object[][] inventory = {
                Archer.getInfo(),
                Healer.getInfo(),
                Knight.getInfo(),
                Mage.getInfo(),
                MythicalCreature.getInfo()
        };
        return inventory;
    }
}