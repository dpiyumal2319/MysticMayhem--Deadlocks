package bin;

import bin.Collections.*;

public class Equipment extends InventoryItem {
    private int extraAttack, extraDefense, extraHealth, extraSpeed;

    public Equipment(EquipmentInfo info, String type) {
        super(info.name,info.price, type, info.price*0.2);
        this.extraAttack = info.extraAttack;
        this.extraDefense = info.extraDefense;
        this.extraHealth = info.extraHealth;
        this.extraSpeed = info.extraSpeed;
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
