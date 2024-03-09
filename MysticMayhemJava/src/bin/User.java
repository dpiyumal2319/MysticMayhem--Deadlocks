package bin;

public class User extends UserMoneyManager{
    private static final int UserIdStart = 100000;
    public final int userID;
    public int xp;
    public final String userName;
    private Warrior Archer, Healer, Knight, Mage, MythicalCreature;

    public User(String userName, int currentUsers) {
        this.userID = UserIdStart + currentUsers + 1;
        this.xp = 0;
        this.userName = userName;
        this.money = 500;
    }

    public double getMoney() {
        return this.money;
    }

    //Decrement money when buying equipments
    public void buySomething(InventoryItem item) {
        buyItem(item);
    }

    void addArcher(Archer archer) {
        buyItem(archer);
        this.Archer = archer;
    }
    InventoryItem removeArcher() {
        InventoryItem temp = Archer;
        sellItem(temp);
        Archer = null;
        return temp;
    }

    void addHealer(Healer healer) {
        buyItem(healer);
        this.Healer = healer;
    }
    InventoryItem removeHealer() {
        InventoryItem temp = Healer;
        sellItem(temp);
        Healer = null;
        return temp;
    }

    void addKnight(Knight knight) {
        buyItem(knight);
        this.Knight = knight;
    }
    InventoryItem removeKnight() {
        InventoryItem temp = Knight;
        sellItem(temp);
        Knight = null;
        return temp;
    }

    void addMage(Mage mage) {
        buyItem(mage);
        this.Mage = mage;
    }
    InventoryItem removeMage() {
        InventoryItem temp = Mage;
        sellItem(temp);
        Mage = null;
        return temp;
    }

    void addMythicalCreature(MythicalCreature mythicalCreature) {
        buyItem(mythicalCreature);
        this.MythicalCreature = mythicalCreature;
    }
    InventoryItem removeMythicalCreature() {
        InventoryItem temp = MythicalCreature;
        sellItem(temp);
        MythicalCreature = null;
        return temp;
    }

    InventoryItem[] getInventory() {
        InventoryItem[] inventory = new InventoryItem[5];
        inventory[0] = Archer;
        inventory[1] = Healer;
        inventory[2] = Knight;
        inventory[3] = Mage;
        inventory[4] = MythicalCreature;
        return inventory;
    }

    public int getxp() {
        return this.xp;
    }
}