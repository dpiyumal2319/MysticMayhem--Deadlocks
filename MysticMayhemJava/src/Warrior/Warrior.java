package Warrior;

import Equipments.Equipments;
import java.util.HashMap;
import java.util.Map;

public class Warrior {
    protected String name, type;
    protected int health, attack, defense, speed, price;
    
    protected int extraHealth, extraAttack, extraDefense, extraSpeed;
    protected float value;
    protected Equipments armor, artefact;

    public static final Map<String, WarriorCatalog> ARCHER_MAP = new HashMap<>();
    public static final Map<String, WarriorCatalog> KNIGHT_MAP = new HashMap<>();
    public static final Map<String, WarriorCatalog> HEALER_MAP = new HashMap<>();
    public static final Map<String, WarriorCatalog> MAGE_MAP = new HashMap<>();
    public static final Map<String, WarriorCatalog> MYTHICALCREATURE_MAP = new HashMap<>();

    //Keys are in lower case to remove case sensitivity by adding .toLowerCase() in the switch case
    static {
        ARCHER_MAP.put("shooter", new WarriorCatalog("Shooter","Archer", 6, 11, 4, 9, 80));
        ARCHER_MAP.put("ranger", new WarriorCatalog("Ranger","Archer", 8, 14, 5, 10, 115));
        ARCHER_MAP.put("sunfire", new WarriorCatalog("Sunfire","Archer", 7, 15, 5, 14, 160));
        ARCHER_MAP.put("zing", new WarriorCatalog("Zing","Archer", 11, 16, 9, 14, 200));
        ARCHER_MAP.put("sagittarius", new WarriorCatalog("Sagittarius","Archer", 12, 18, 7, 17, 230));

        KNIGHT_MAP.put("squire", new WarriorCatalog("Squire","Knight", 7, 8, 9, 8, 85));
        KNIGHT_MAP.put("cavalier", new WarriorCatalog("Cavalier","Knight", 7, 10, 12, 10, 110));
        KNIGHT_MAP.put("templar", new WarriorCatalog("Templar","Knight", 12, 14, 16, 12, 155));
        KNIGHT_MAP.put("zoro", new WarriorCatalog("Zoro","Knight", 13, 17, 16, 14, 180));
        KNIGHT_MAP.put("swiftblade", new WarriorCatalog("Swiftblade","Knight", 17, 18, 20, 13, 250));


        HEALER_MAP.put("soother", new WarriorCatalog("Soother","Healer", 9, 10, 8, 6, 95));
        HEALER_MAP.put("medic", new WarriorCatalog("Medic","Healer", 10, 12, 9, 7, 125));
        HEALER_MAP.put("alchemist", new WarriorCatalog("Alchemist","Healer", 13, 13, 13, 13, 150));
        HEALER_MAP.put("saint", new WarriorCatalog("Saint","Healer", 17, 16, 14, 9, 200));
        HEALER_MAP.put("lifebringer", new WarriorCatalog("Lifebringer","Healer", 19, 17, 15, 12, 260));

        MAGE_MAP.put("warlock", new WarriorCatalog("Warlock","Mage", 10, 12, 7, 12, 100));
        MAGE_MAP.put("illusionist", new WarriorCatalog("Illusionist","Mage", 12, 13, 8, 14, 120));
        MAGE_MAP.put("enchanter", new WarriorCatalog("Enchanter","Mage", 13, 16, 10, 16, 160));
        MAGE_MAP.put("conjurer", new WarriorCatalog("Conjurer","Mage", 14, 18, 15, 12, 195));
        MAGE_MAP.put("eldritch", new WarriorCatalog("Eldritch","Mage", 18, 19, 17, 14, 270));

        MYTHICALCREATURE_MAP.put("dragon", new WarriorCatalog("Dragon", "MythicalCreature",15, 12, 14, 8, 120));
        MYTHICALCREATURE_MAP.put("dragon", new WarriorCatalog("Dragon","MythicalCreature", 15, 12, 14, 8, 120));
        MYTHICALCREATURE_MAP.put("basilisk", new WarriorCatalog("Basilisk","MythicalCreature", 10, 15, 11, 12, 165));
        MYTHICALCREATURE_MAP.put("hydra", new WarriorCatalog("Hydra","MythicalCreature", 15, 12, 16, 11, 205));
        MYTHICALCREATURE_MAP.put("phoenix", new WarriorCatalog("Phoenix","MythicalCreature", 17, 17, 13, 19, 275));
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
            this.type = warrior.getType();
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

    public Equipments getEquipments(String equipmentType) {
        switch (equipmentType) {
            case "Armor":
                return this.armor;
            case "Artefact":
                return this.artefact;
            default:
                return null;
        }
    }

    public void setEquipment(String equipmentType, String equipmentName) {
        Equipments equipment = new Equipments(equipmentType, equipmentName);
        switch (equipmentType) {
            case "Armor":
                this.armor = equipment;
                this.extraAttack = equipment.getExtraAttack();
                this.extraDefense = equipment.getExtraDefense();
                this.extraSpeed = equipment.getExtraSpeed();
                this.extraHealth = equipment.getExtraHealth();
                this.value += equipment.getPrice() * 0.2;
                break;
            case "Artefact":
                this.artefact = equipment;
                this.extraAttack = equipment.getExtraAttack();
                this.extraDefense = equipment.getExtraDefense();
                this.extraSpeed = equipment.getExtraSpeed();
                this.extraHealth = equipment.getExtraHealth();
                this.value += equipment.getPrice() * 0.2;
                break;
        }
    }

    public void removeEquipment
    (String equipmentType) {
        switch (equipmentType) {
            case "Armor":
                this.value -= this.armor.getPrice() * 0.2;
                this.armor = null;
                this.extraAttack = 0;
                this.extraDefense = 0;
                this.extraSpeed = 0;
                this.extraHealth = 0;
                break;
            case "Artefact":
                this.value -= this.artefact.getPrice() * 0.2;
                this.artefact = null;
                this.extraAttack = 0;
                this.extraDefense = 0;
                this.extraSpeed = 0;
                this.extraHealth = 0;
                break;
        }
    }
}
