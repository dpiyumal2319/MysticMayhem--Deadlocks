package bin;

public abstract class Warrior extends InventoryItem {
    protected int attack, defense, health, speed;

    private Waredrobe woredrobe = new Waredrobe();

    public int getAttack() {
        return attack + woredrobe.getAttackBoost();
    }

    public int getDefense() {
        return defense + woredrobe.getDefenseBoost();
    }

    public int getHealth() {
        return health + woredrobe.getHealthBoost();
    }

    public int getSpeed() {
        return speed + woredrobe.getSpeedBoost();
    }

    public String getName() {
        return name;
    }

    public Waredrobe getWoredrobe() {
        return woredrobe;
    }

    public void printInfo() {
        System.out.println(type + " : " + name);
        System.out.println("\t\tArmor :" + (woredrobe.getArmor() == null ? "None" : woredrobe.getArmor().name));
        System.out.println("\t\tArtefact :" + (woredrobe.getArtefact() == null ? "None" : woredrobe.getArtefact().name));
        System.out.println("\t\tAttak :" + getAttack());
        System.out.println("\t\tDefense :" + getDefense());
        System.out.println("\t\tHealth :" + getHealth());
        System.out.println("\t\tSpeed :" + getSpeed());
        System.out.println("\t\tValue :" + value);
    }

    InventoryItem addEquipment(String type, String name) {
        InventoryItem item = null;
        if (type.equals("Armor")) {
            item = woredrobe.addArmor(name);
        } else if (type.equals("Artefact")) {
            item = woredrobe.addArtefact(name);
        }
        this.value += item.value;
        return item;
    }

    InventoryItem removeEquipment(String type) {
        InventoryItem item = null;
        if (type.equals("Armor")) {
            item = woredrobe.removeArmor();
        } else if (type.equals("Artefact")) {
            item = woredrobe.removeArtefact();
        }
        this.value -= item.value;
        return item;
    }
}
