package bin.Equipments;

import bin.Equipment;

public class Armor extends Equipment {

    public Armor(String name) {
        super(data.armors.get(name), "Armor");
    }
}
