package Equipments;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Equipments implements Serializable{
    protected String name, type;
    protected int extraAttack, extraDefense, extraSpeed, extraHealth, price;

    public static final Map<String, EquipmentCatalog> ARMOR_MAP = new HashMap<>();
    public static final Map<String, EquipmentCatalog> ARTEFACT_MAP = new HashMap<>();

    static {
        ARMOR_MAP.put("chainmail", new EquipmentCatalog("Chainmail", "Armor", 70, 0, 1, 0, -1));
        ARMOR_MAP.put("regalia", new EquipmentCatalog("Regalia", "Armor", 105, 0, 1, 0, 0));
        ARMOR_MAP.put("fleece", new EquipmentCatalog("Fleece", "Armor", 150, 0, 2, 1, -1));

        ARTEFACT_MAP.put("excalibur", new EquipmentCatalog("Excalibur", "Artefact", 150, 2, 0, 0, 0));
        ARTEFACT_MAP.put("amulet", new EquipmentCatalog("Amulet", "Artefact", 200, 1, -1, 1, 1));
        ARTEFACT_MAP.put("crystal", new EquipmentCatalog("Crystal", "Artefact", 210, 2, 1, -1, -1));
    }

    public Equipments(String equipmentType, String equipmentName) {
        EquipmentCatalog equipment = null;
        switch (equipmentType) {
            case "Armor":
                equipment = ARMOR_MAP.get(equipmentName);
                break;
            case "Artefact":
                equipment = ARTEFACT_MAP.get(equipmentName);
                break;
        }
        if (equipment != null) {
            this.name = equipment.getName();
            this.extraAttack = equipment.getExtraAttack();
            this.extraDefense = equipment.getExtraDefense();
            this.extraSpeed = equipment.getExtraSpeed();
            this.extraHealth = equipment.getExtraHealth();
            this.price = equipment.getPrice();
            this.type = equipment.getType();
        }
    }

    public static Map<String, EquipmentCatalog> getEquipmentMap(String equipmentType) {
        switch (equipmentType) {
            case "Armor":
                return ARMOR_MAP;
            case "Artefact":
                return ARTEFACT_MAP;
            default:
                return null;
        }
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getExtraAttack() {
        return this.extraAttack;
    }

    public int getExtraDefense() {
        return this.extraDefense;
    }

    public int getExtraHealth() {
        return this.extraHealth;
    }

    public int getExtraSpeed() {
        return this.extraSpeed;
    }
}