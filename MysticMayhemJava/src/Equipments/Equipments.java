package Equipments;

import java.util.HashMap;
import java.util.Map;

public class Equipments {
    protected String name;
    protected int extraAttack, extraDefense, extraSpeed, extraHealth, price;

    public static final Map<String, EquipmentCatalog> ARMOR_MAP = new HashMap<>();

    static {
        ARMOR_MAP.put("Chainmail", new EquipmentCatalog("Chainmail", 70, 0, 1, 0, -1));
        ARMOR_MAP.put("Regalia", new EquipmentCatalog("Regalia", 105, 0, 1, 0, 0));
        ARMOR_MAP.put("Fleece", new EquipmentCatalog("Fleece", 150, 0, 2, 1, -1));
    }

    public static final Map<String, EquipmentCatalog> ARTEFACT_MAP = new HashMap<>();

    static {
        ARTEFACT_MAP.put("Excalibur", new EquipmentCatalog("Excalibur", 150, 2, 0, 0, 0));
        ARTEFACT_MAP.put("Amulet", new EquipmentCatalog("Amulet", 200, 1, -1, 1, 1));
        ARTEFACT_MAP.put("Crystal", new EquipmentCatalog("Crystal", 210, 2, 1, -1, -1));
    }

    public String getName() {
        return this.name;
    }
}