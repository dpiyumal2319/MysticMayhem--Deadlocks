package bin.Collections;

public class EquipmentInfo{
    public final String name;
    public final int price;
    public final int extraAttack;
    public final int extraDefense;
    public final int extraHealth;
    public final int extraSpeed;

    public EquipmentInfo(String name, int price, int extraAttack, int extraDefense, int extraHealth, int extraSpeed) {
        this.name = name;
        this.price = price;
        this.extraAttack = extraAttack;
        this.extraDefense = extraDefense;
        this.extraHealth = extraHealth;
        this.extraSpeed = extraSpeed;
    }
}
