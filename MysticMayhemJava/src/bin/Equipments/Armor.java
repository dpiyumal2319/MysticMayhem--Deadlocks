package bin.Equipments;

import bin.Collections.EquipmentInfo;

public class Armor extends Equipments {

    public Armor(String name) {
        EquipmentInfo info = data.armors.get(name);
        this.name = info.name;
        this.price = info.price;
        this.extraAttack = info.extraAttack;
        this.extraDefense = info.extraDefense;
        this.extraHealth = info.extraHealth;
        this.extraSpeed = info.extraSpeed;
        this.value = info.price * 0.2;
        this.type = "Armor";
    }
}
