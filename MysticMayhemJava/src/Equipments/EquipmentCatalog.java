package Equipments;

public class EquipmentCatalog {
    private final String name;
    private final int price;
    private final int extraAttack;
    private final int extraDefense;
    private final int extraHealth;
    private final int extraSpeed;
    private final String type;

    public EquipmentCatalog(String name, String type, int price, int extraAttack, int extraDefense, int extraHealth,
            int extraSpeed) {
        this.name = name;
        this.price = price;
        this.extraAttack = extraAttack;
        this.extraDefense = extraDefense;
        this.extraHealth = extraHealth;
        this.extraSpeed = extraSpeed;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getExtraAttack() {
        return extraAttack;
    }

    public int getExtraDefense() {
        return extraDefense;
    }

    public int getExtraHealth() {
        return extraHealth;
    }

    public int getExtraSpeed() {
        return extraSpeed;
    }
    public String getType() {
        return type;
    }
}
