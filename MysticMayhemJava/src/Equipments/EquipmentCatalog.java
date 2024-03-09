package Equipments;

public class EquipmentCatalog {
    private final String name;
    private final int price;
    private final int extraAttack;
    private final int extraDefense;
    private final int extraHealth;
    private final int extraSpeed;

    public EquipmentCatalog(String name, int price, int extraAttack, int extraDefense, int extraHealth,
            int extraSpeed) {
        this.name = name;
        this.price = price;
        this.extraAttack = extraAttack;
        this.extraDefense = extraDefense;
        this.extraHealth = extraHealth;
        this.extraSpeed = extraSpeed;
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
}
