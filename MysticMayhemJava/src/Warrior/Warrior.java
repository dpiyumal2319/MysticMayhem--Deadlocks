package Warrior;

import Equipments.Equipments;

abstract public class Warrior {
    protected String name;
    protected int health, attack, defense, speed, price;
    
    protected int extraHealth, extraAttack, extraDefense, extraSpeed;
    protected float value;
    protected Equipments armor, artefact = null;

    public Equipments[] getEquipments() {
        Equipments[] equipments = {armor, artefact};
        return equipments;
    }

    public String getName() {
        return this.name;
    }
}
