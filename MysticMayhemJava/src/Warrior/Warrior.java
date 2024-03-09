package Warrior;

import Equipments.Equipments;
import java.util.HashMap;
import java.util.Map;

public class Warrior {
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
        ARCHER_MAP.put("Shooter", new WarriorCatalog("Shooter","Archer", 6, 11, 4, 9, 80));
        ARCHER_MAP.put("Ranger", new WarriorCatalog("Ranger","Archer", 8, 14, 5, 10, 115));
        ARCHER_MAP.put("Sunfire", new WarriorCatalog("Sunfire","Archer", 7, 15, 5, 14, 160));
        ARCHER_MAP.put("Zing", new WarriorCatalog("Zing","Archer", 11, 16, 9, 14, 200));
        ARCHER_MAP.put("Sagittarius", new WarriorCatalog("Sagittarius","Archer", 12, 18, 7, 17, 230));

        KNIGHT_MAP.put("Squire", new WarriorCatalog("Squire","Knight", 7, 8, 9, 8, 85));
        KNIGHT_MAP.put("Cavalier", new WarriorCatalog("Cavalier","Knight", 7, 10, 12, 10, 110));
        KNIGHT_MAP.put("Templar", new WarriorCatalog("Templar","Knight", 12, 14, 16, 12, 155));
        KNIGHT_MAP.put("Zoro", new WarriorCatalog("Zoro","Knight", 13, 17, 16, 14, 180));
        KNIGHT_MAP.put("Swiftblade", new WarriorCatalog("Swiftblade","Knight", 17, 18, 20, 13, 250));


        HEALER_MAP.put("Soother", new WarriorCatalog("Soother","Healer", 9, 10, 8, 6, 95));
        HEALER_MAP.put("Medic", new WarriorCatalog("Medic","Healer", 10, 12, 9, 7, 125));
        HEALER_MAP.put("Alchemist", new WarriorCatalog("Alchemist","Healer", 13, 13, 13, 13, 150));
        HEALER_MAP.put("Saint", new WarriorCatalog("Saint","Healer", 17, 16, 14, 9, 200));
        HEALER_MAP.put("Lifebringer", new WarriorCatalog("Lifebringer","Healer", 19, 17, 15, 12, 260));

        MAGE_MAP.put("Warlock", new WarriorCatalog("Warlock","Mage", 10, 12, 7, 12, 100));
        MAGE_MAP.put("Illusionist", new WarriorCatalog("Illusionist","Mage", 12, 13, 8, 14, 120));
        MAGE_MAP.put("Enchanter", new WarriorCatalog("Enchanter","Mage", 13, 16, 10, 16, 160));
        MAGE_MAP.put("Conjurer", new WarriorCatalog("Conjurer","Mage", 14, 18, 15, 12, 195));
        MAGE_MAP.put("Eldritch", new WarriorCatalog("Eldritch","Mage", 18, 19, 17, 14, 270));
        
        MYTHICALCREATURE_MAP.put("Dragon", new WarriorCatalog("Dragon", "MythicalCreature",15, 12, 14, 8, 120));
        MYTHICALCREATURE_MAP.put("Dragon", new WarriorCatalog("Dragon","MythicalCreature", 15, 12, 14, 8, 120));
        MYTHICALCREATURE_MAP.put("Basilisk", new WarriorCatalog("Basilisk","MythicalCreature", 10, 15, 11, 12, 165));
        MYTHICALCREATURE_MAP.put("Hydra", new WarriorCatalog("Hydra","MythicalCreature", 15, 12, 16, 11, 205));
        MYTHICALCREATURE_MAP.put("Phoenix", new WarriorCatalog("Phoenix","MythicalCreature", 17, 17, 13, 19, 275));
    }

    public Warrior(String warriorType, String name) {
        WarriorCatalog warrior = null;
        switch (warriorType) {
            case "Archer":
                warrior = ARCHER_MAP.get(name);
                break;
            case "Knight":
                warrior = KNIGHT_MAP.get(name);
                break;
            case "Healer":
                warrior = HEALER_MAP.get(name);
                break;
            case "Mage":
                warrior = MAGE_MAP.get(name);
                break;
            case "MythicalCreature":
                warrior = MYTHICALCREATURE_MAP.get(name);
                break;
        }
        if (warrior != null) {
            this.name = warrior.getName();
            this.health = warrior.getHealth();
            this.attack = warrior.getAttack();
            this.defense = warrior.getDefense();
            this.speed = warrior.getSpeed();
            this.price = warrior.getPrice();
            this.value = this.price;
        }
    }

    public static Map<String, WarriorCatalog> getWarriorMap(String warriorType) {
        switch (warriorType) {
            case "Archer":
                return ARCHER_MAP;
            case "Knight":
                return KNIGHT_MAP;
            case "Healer":
                return HEALER_MAP;
            case "Mage":
                return MAGE_MAP;
            case "MythicalCreature":
                return MYTHICALCREATURE_MAP;
            default:
                return null;
        }
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
