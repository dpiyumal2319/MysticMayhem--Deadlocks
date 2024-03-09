package UserManager;
import Equipments.Equipments;
import Warrior.Warrior;
import java.util.HashMap;
import java.util.Map;

public class User {
    public static int UserCount = 100000;
    public final int userID;
    public int xp;
    public final String userName;
    private float money;
    public Warrior Archer , Healer, Knight, Mage, MythicalCreature = null;
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

    public Map<Warrior, Equipments[]> getInventory() {
        Map<Warrior, Equipments[]> inventory = new HashMap<Warrior, Equipments[]>();
        inventory.put(Archer, Archer.getEquipments());
        inventory.put(Healer, Healer.getEquipments());
        inventory.put(Knight, Knight.getEquipments());
        inventory.put(Mage, Mage.getEquipments());
        inventory.put(MythicalCreature, MythicalCreature.getEquipments());
        return inventory;
    }
}
