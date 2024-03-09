package bin;

public class Warrior extends InventoryItem{
    protected int attack, defense, health, speed;

    private int extraAttack, extraDefense, extraHealth, extraSpeed;
    
    private Armor armor;
    private Artefact artefect;

    void addArmor(Armor armor, User user) {
        this.armor = armor;
        this.extraAttack = armor.extraAttack;
        this.extraDefense = armor.extraDefense;
        this.extraHealth = armor.extraHealth;
        this.extraSpeed = armor.extraSpeed;
        this.value += armor.value;
        user.buySomething(armor);
    }

    void addArtefect(Artefact artefect, User user) {
        this.artefect = artefect;
        this.extraAttack = artefect.extraAttack;
        this.extraDefense = artefect.extraDefense;
        this.extraHealth = artefect.extraHealth;
        this.extraSpeed = artefect.extraSpeed;
        this.value += artefect.value;
        user.buySomething(artefect); 
    }

    InventoryItem removeArmor() {
        Armor temp = armor;
        armor = null;
        extraAttack = 0;
        extraDefense = 0;
        extraHealth = 0;
        extraSpeed = 0;
        value -= temp.value;
        return temp;
    }

    InventoryItem removeArtefect() {
        Artefact temp = artefect;
        artefect = null;
        extraAttack = 0;
        extraDefense = 0;
        extraHealth = 0;
        extraSpeed = 0;
        value -= temp.value;
        return temp;
    }

    InventoryItem[] getInventoryItem() {
        InventoryItem[] items = new InventoryItem[2];
        items[0] = armor;
        items[1] = artefect;
        return items;
    }

    public int getAttack() {
        return attack + extraAttack;
    }

    public int getDefense() {
        return defense + extraDefense;
    }

    public int getHealth() {
        return health + extraHealth;
    }

    public int getSpeed() {
        return speed + extraSpeed;
    }

    public String getName() {
        return name;
    }
}
