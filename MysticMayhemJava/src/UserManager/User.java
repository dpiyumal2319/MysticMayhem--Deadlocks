package UserManager;

import Warrior.Warrior;

public class User {
    private static int UserCount = 100000;
    public final int userID;
    public int xp;
    public final String userName;
    private double money;
    public Warrior Archer, Healer, Knight, Mage, MythicalCreature;

    public User(String userName) {
        this.userID = UserCount++;
        this.xp = 0;
        this.userName = userName;
        this.money = 500;
    }

    public double getMoney() {
        return this.money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getxp() {
        return this.xp;
    }

    public Object[][] getInventory() {
        Object[][] inventory = {
                Archer == null ? null : Archer.getInfo(),
                Healer == null ? null : Healer.getInfo(),
                Knight == null ? null : Knight.getInfo(),
                Mage == null ? null : Mage.getInfo(),
                MythicalCreature == null ? null : MythicalCreature.getInfo()
        };
        return inventory;
    }

    public Warrior getWarrior(String warriorType) {
        switch (warriorType) {
            case "Archer":
                return Archer;
            case "Healer":
                return Healer;
            case "Knight":
                return Knight;
            case "Mage":
                return Mage;
            case "MythicalCreature":
                return MythicalCreature;
            default:
                return null;
        }
    }

    public void setWarrior(String warriorType, Warrior warrior) {
        switch (warriorType) {
            case "Archer":
                Archer = (Archer) warrior;
                break;
            case "Healer":
                Healer = warrior;
                break;
            case "Knight":
                Knight = warrior;
                break;
            case "Mage":
                Mage = warrior;
                break;
            case "MythicalCreature":
                MythicalCreature = warrior;
                break;
        }
    }
}