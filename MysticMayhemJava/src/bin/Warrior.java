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
        System.out.println("/tArmor :" + (woredrobe.getArmor() == null ? "None" : woredrobe.getArmor().name));
        System.out.println("/tArtefact :" + (woredrobe.getArtefact() == null ? "None" : woredrobe.getArtefact().name));
        System.out.println("/tAttak :" + getAttack());
        System.out.println("/tDefense :" + getDefense());
        System.out.println("/tHealth :" + getHealth());
        System.out.println("/tSpeed :" + getSpeed());
        System.out.println("/tValue :" + value);
    }

    InventoryItem removeEquipment(String type) {
        if (type.equals("Armor")) {
            return woredrobe.removeArmor();
        } else if (type.equals("Artefact")) {
            return woredrobe.removeArtefact();
        }
        return null;
    }

    InventoryItem addEquipment(String type, String name) {
        if (type.equals("Armor")) {
            return woredrobe.addArmor(name);
        } else if (type.equals("Artefact")) {
            return woredrobe.addArtefact(name);
        }
        return null;
    }
}
