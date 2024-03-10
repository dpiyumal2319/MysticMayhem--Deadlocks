package bin;

import bin.Equipments.Armor;
import bin.Equipments.Artefact;

public class Waredrobe{
    protected int AttackBoost, DefenseBoost, HealthBoost, SpeedBoost;

    Armor armor;
    Artefact artefact;

    public Armor getArmor() {
        return armor;
    }

    public Artefact getArtefact() {
        return artefact;
    }
}
