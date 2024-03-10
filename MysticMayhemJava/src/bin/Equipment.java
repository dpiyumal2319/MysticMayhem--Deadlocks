package bin.Equipments;

import bin.InventoryItem;

public class Equipments extends InventoryItem {
    protected int extraAttack, extraDefense, extraHealth, extraSpeed;

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
