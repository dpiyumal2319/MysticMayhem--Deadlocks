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

    Equipment getEquipment(String type) {
        if (type.equals("Armor")) {
            return armor;
        } else if (type.equals("Artefact")) {
            return artefact;
        }
        return null;
    }

    Equipment addArmor(String name) {
        armor = new Armor(name);
        AttackBoost += armor.getExtraAttack();
        DefenseBoost += armor.getExtraDefense();
        HealthBoost += armor.getExtraHealth();
        SpeedBoost += armor.getExtraSpeed();
        return armor;
    }

    Equipment addArtefact(String name) {
        artefact = new Artefact(name);
        AttackBoost += artefact.getExtraAttack();
        DefenseBoost += artefact.getExtraDefense();
        HealthBoost += artefact.getExtraHealth();
        SpeedBoost += artefact.getExtraSpeed();
        return artefact;
    }

    Equipment removeArmor() {
        Armor temp = armor;
        armor = null;
        AttackBoost -= temp.getExtraAttack();
        DefenseBoost -= temp.getExtraDefense();
        HealthBoost -= temp.getExtraHealth();
        SpeedBoost -= temp.getExtraSpeed();
        return temp;
    }
    
    Equipment removeArtefact() {
        Artefact temp = artefact;
        artefact = null;
        AttackBoost -= temp.getExtraAttack();
        DefenseBoost -= temp.getExtraDefense();
        HealthBoost -= temp.getExtraHealth();
        SpeedBoost -= temp.getExtraSpeed();
        return temp;
    }

    public int getAttackBoost() {
        return AttackBoost;
    }

    public int getDefenseBoost() {
        return DefenseBoost;
    }

    public int getHealthBoost() {
        return HealthBoost;
    }

    public int getSpeedBoost() {
        return SpeedBoost;
    }
}
