package bin;

import bin.Equipments.*;
import java.io.Serializable;

public class Waredrobe implements Serializable{
    protected int AttackBoost, DefenseBoost, HealthBoost, SpeedBoost;

    Armor armor;
    Artefact artefact;

    public Waredrobe() {
        AttackBoost = 0;
        DefenseBoost = 0;
        HealthBoost = 0;
        SpeedBoost = 0;
    }

    public Armor getArmor() {
        return armor;
    }

    public Artefact getArtefact() {
        return artefact;
    }

    Equipments getEquipment(String type) {
        if (type.equals("Armor")) {
            return armor;
        } else if (type.equals("Artefact")) {
            return artefact;
        }
        return null;
    }
    Equipments addEquipment(String type, String name) {
        if (type.equals("Armor")) {
            armor = new Armor(name);
            AttackBoost += armor.getExtraAttack();
            DefenseBoost += armor.getExtraDefense();
            HealthBoost += armor.getExtraHealth();
            SpeedBoost += armor.getExtraSpeed();
            return armor;
        } else if (type.equals("Artefact")) {
            artefact = new Artefact(name);
            AttackBoost += artefact.getExtraAttack();
            DefenseBoost += artefact.getExtraDefense();
            HealthBoost += artefact.getExtraHealth();
            SpeedBoost += artefact.getExtraSpeed();
            return artefact;
        }
        return null;
    }
    Equipments removeEquipment(String type) {
        if (type.equals("Armor")) {
            Armor temp = armor;
            armor = null;
            AttackBoost -= temp.getExtraAttack();
            DefenseBoost -= temp.getExtraDefense();
            HealthBoost -= temp.getExtraHealth();
            SpeedBoost -= temp.getExtraSpeed();
            return temp;
        } else if (type.equals("Artefact")) {
            Artefact temp = artefact;
            artefact = null;
            AttackBoost -= temp.getExtraAttack();
            DefenseBoost -= temp.getExtraDefense();
            HealthBoost -= temp.getExtraHealth();
            SpeedBoost -= temp.getExtraSpeed();
            return temp;
        }
        return null;
    }
}
