package bin.Warriors;


import bin.InventoryItem;
import bin.Waredrobe;

public abstract class Warrior extends InventoryItem{
    protected int attack, defense, health, speed;

    private int extraAttack, extraDefense, extraHealth, extraSpeed;

    private Waredrobe woredrobe = new Waredrobe();

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

    public Waredrobe getWoredrobe() {
        return woredrobe;
    }

    public void printInfo() {
        System.out.println(type + " : " + name);
        System.out.println("/tArmor :" + (woredrobe.getArmor() == null ? "None" : woredrobe.getArmor().name));
        System.out.println("/tArtefact :" + (woredrobe.getArtefact() == null ? "None" : woredrobe.getArtefact().name));
        System.out.println("/tAttak :" + getAttack());
        System.out.println("/tDefense :" + getDefense());
        System.out.println("/tHealth :" + getHealth());
        System.out.println("/tSpeed :" + getSpeed());
        System.out.println("/tValue :" + value);
    }
}
