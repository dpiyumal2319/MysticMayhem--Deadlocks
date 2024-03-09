package Warrior;

import Equipments.Equipments;
import java.util.HashMap;
import java.util.Map;

abstract public class Warrior {
    protected String name;
    protected int health, attack, defense, speed, price;
    
    protected int extraHealth, extraAttack, extraDefense, extraSpeed;
    protected float value;
    protected Equipments armor, artefact;

    public static final Map<String, WarriorCatalog> ARCHER_MAP = new HashMap<>();
    public static final Map<String, WarriorCatalog> KNIGHT_MAP = new HashMap<>();
    public static final Map<String, WarriorCatalog> HEALER_MAP = new HashMap<>();
    public static final Map<String, WarriorCatalog> MAGE_MAP = new HashMap<>();
    public static final Map<String, WarriorCatalog> MYTHICALCREATURE_MAP = new HashMap<>();

    static {
        ARCHER_MAP.put("Shooter", new WarriorCatalog("Shooter", 6, 11, 4, 9, 80));
        ARCHER_MAP.put("Ranger", new WarriorCatalog("Ranger", 8, 14, 5, 10, 115));
        ARCHER_MAP.put("Sunfire", new WarriorCatalog("Sunfire", 7, 15, 5, 14, 160));
        ARCHER_MAP.put("Zing", new WarriorCatalog("Zing", 11, 16, 9, 14, 200));
        ARCHER_MAP.put("Sagittarius", new WarriorCatalog("Sagittarius", 12, 18, 7, 17, 230));

        KNIGHT_MAP.put("Squire", new WarriorCatalog("Squire", 7, 8, 9, 8, 85));
        KNIGHT_MAP.put("Cavalier", new WarriorCatalog("Cavalier", 7, 10, 12, 10, 110));
        KNIGHT_MAP.put("Templar", new WarriorCatalog("Templar", 12, 14, 16, 12, 155));
        KNIGHT_MAP.put("Zoro", new WarriorCatalog("Zoro", 13, 17, 16, 14, 180));
        KNIGHT_MAP.put("Swiftblade", new WarriorCatalog("Swiftblade", 17, 18, 20, 13, 250));

        HEALER_MAP.put("Soother", new WarriorCatalog("Soother", 9, 10, 8, 6, 95));
        HEALER_MAP.put("Medic", new WarriorCatalog("Medic", 10, 12, 9, 7, 125));
        HEALER_MAP.put("Alchemist", new WarriorCatalog("Alchemist", 13, 13, 13, 13, 150));
        HEALER_MAP.put("Saint", new WarriorCatalog("Saint", 17, 16, 14, 9, 200));
        HEALER_MAP.put("Lifebringer", new WarriorCatalog("Lifebringer", 19, 17, 15, 12, 260));

        MAGE_MAP.put("Warlock", new WarriorCatalog("Warlock", 10, 12, 7, 12, 100));
        MAGE_MAP.put("Illusionist", new WarriorCatalog("Illusionist", 12, 13, 8, 14, 120));
        MAGE_MAP.put("Enchanter", new WarriorCatalog("Enchanter", 13, 16, 10, 16, 160));
        MAGE_MAP.put("Conjurer", new WarriorCatalog("Conjurer", 14, 18, 15, 12, 195));
        MAGE_MAP.put("Eldritch", new WarriorCatalog("Eldritch", 18, 19, 17, 14, 270));
        
        MYTHICALCREATURE_MAP.put("Dragon", new WarriorCatalog("Dragon", 15, 12, 14, 8, 120));
        MYTHICALCREATURE_MAP.put("Basilisk", new WarriorCatalog("Basilisk", 10, 15, 11, 12, 165));
        MYTHICALCREATURE_MAP.put("Hydra", new WarriorCatalog("Hydra", 15, 12, 16, 11, 205));
        MYTHICALCREATURE_MAP.put("Phoenix", new WarriorCatalog("Phoenix", 17, 17, 13, 19, 275));
        MYTHICALCREATURE_MAP.put("Pegasus", new WarriorCatalog("Pegasus", 20, 14, 18, 20, 340));
    }

    public Object[] getInfo() {
        Object[] info = {this ,armor, artefact};
        return info;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return (int) this.value;
    }

    public int getHealth() {
        return this.health + this.extraHealth;
    }

    public int getAttack() {
        return this.attack + this.extraAttack;
    }

    public int getDefense() {
        return this.defense + this.extraDefense;
    }

    public int getSpeed() {
        return this.speed + this.extraSpeed;
    }

    public int getPrice() {
        return this.price;
    }

    public void setArmor(Equipments armor) {
        this.armor = armor;
    }
}
