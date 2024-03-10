package bin;

public class Equipment extends InventoryItem {
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
