package bin;

import java.io.Serializable;
import java.util.Map;

import bin.Collections.DataSet;
import bin.Collections.EquipmentInfo;
import bin.Collections.WarriorInfo;

public class InventoryItem implements Serializable{
    public static DataSet data = new DataSet();

    public final String name;
    public final int price;
    private double value;
    public final String type;

    //Making static data set to be used by all instances of InventoryItem
    static {
    data.archers.put("shooter", new WarriorInfo("Shooter", 6, 11, 4, 9, 80, "Highlanders"));
    data.archers.put("ranger", new WarriorInfo("Ranger", 8, 14, 5, 10, 115, "Highlanders"));
    data.archers.put("sunfire", new WarriorInfo("Sunfire", 7, 15, 5, 14, 160, "Sunchildren"));
    data.archers.put("zing", new WarriorInfo("Zing", 11, 16, 9, 14, 200, "Sunchildren"));
    data.archers.put("sagittarius", new WarriorInfo("Sagittarius", 12, 18, 7, 17, 230, "Mystics"));

    data.knights.put("squire", new WarriorInfo("Squire", 7, 8, 9, 8, 85, "Marshlanders"));
    data.knights.put("cavalier", new WarriorInfo("Cavalier", 7, 10, 12, 10, 110, "Marshlanders"));
    data.knights.put("templar", new WarriorInfo("Templar", 12, 14, 16, 12, 155, "Sunchildren"));
    data.knights.put("zoro", new WarriorInfo("Zoro", 13, 17, 16, 14, 180, "Highlanders"));
    data.knights.put("swiftblade", new WarriorInfo("Swiftblade", 17, 18, 20, 13, 250, "Marshlanders"));

    data.healers.put("soother", new WarriorInfo("Soother", 9, 10, 8, 6, 95, "Sunchildren"));
    data.healers.put("medic", new WarriorInfo("Medic", 10, 12, 9, 7, 125, "Marshlanders"));
    data.healers.put("alchemist", new WarriorInfo("Alchemist", 13, 13, 13, 13, 150, "Marshlanders"));
    data.healers.put("saint", new WarriorInfo("Saint", 17, 16, 14, 9, 200, "Mystics"));
    data.healers.put("lifebringer", new WarriorInfo("Lifebringer", 19, 17, 15, 12, 260, "Sunchildren"));

    data.mages.put("warlock", new WarriorInfo("Warlock", 10, 12, 7, 12, 100, "Marshlanders"));
    data.mages.put("illusionist", new WarriorInfo("Illusionist", 12, 13, 8, 14, 120, "Mystics"));
    data.mages.put("enchanter", new WarriorInfo("Enchanter", 13, 16, 10, 16, 160, "Highlanders"));
    data.mages.put("conjurer", new WarriorInfo("Conjurer", 14, 18, 15, 12, 195, "Highlanders"));
    data.mages.put("eldritch", new WarriorInfo("Eldritch", 18, 19, 17, 14, 270, "Mystics"));

    data.mythicalCreatures.put("dragon", new WarriorInfo("Dragon", 15, 12, 14, 8, 120, "MythicalCreatures"));
    data.mythicalCreatures.put("basilisk", new WarriorInfo("Basilisk", 10, 15, 11, 12, 165, "MythicalCreatures"));
    data.mythicalCreatures.put("hydra", new WarriorInfo("Hydra", 15, 12, 16, 11, 205, "MythicalCreatures"));
    data.mythicalCreatures.put("phoenix", new WarriorInfo("Phoenix", 17, 17, 13, 19, 275, "MythicalCreatures"));
    
    data.armors.put("chainmail", new EquipmentInfo("Chainmail", 70, 0, 1, 0, -1));
    data.armors.put("regalia", new EquipmentInfo("Regalia", 105, 0, 1, 0, 0));
    data.armors.put("fleece", new EquipmentInfo("Fleece", 150, 0, 2, 1, -1));

    data.artefacts.put("excalibur", new EquipmentInfo("Excalibur", 150, 2, 0, 0, 0));
    data.artefacts.put("amulet", new EquipmentInfo("Amulet", 200, 1, -1, 1, 1));
    data.artefacts.put("crystal", new EquipmentInfo("Crystal", 210, 2, 1, -1, -1));
    }

    public InventoryItem(String name, int price, String type, double value) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.value = value;
    }

    public int getValue() {
        return (int) value;
    }

    void incrementValue(double increment) {
        this.value += increment;
    }

    void decrementValue(double decrement) {
        this.value -= decrement;
    }

    public static Map<String, WarriorInfo> getWarriorMap(String type) {
        switch (type) {
            case "Archer":
                return data.archers;
            case "Healer":
                return data.healers;
            case "Knight":
                return data.knights;
            case "MythicalCreature":
                return data.mythicalCreatures;
            case "Mage":
                return data.mages;
            default:
                return null;
        }
    }

    public static Map<String, EquipmentInfo> getEquipmentMap(String type) {
        switch (type) {
            case "Armor":
                return data.armors;
            case "Artefact":
                return data.artefacts;
            default:
                return null;
        }
    }
}


/*
 * archers.put("shooter", new WarriorInfo("Shooter", 6, 11, 4, 9, 80, "Highlanders"));
        archers.put("ranger", new WarriorInfo("Ranger", 8, 14, 5, 10, 115, "Highlanders"));
        archers.put("sunfire", new WarriorInfo("Sunfire", 7, 15, 5, 14, 160, "Sunchildren"));
        archers.put("zing", new WarriorInfo("Zing", 11, 16, 9, 14, 200, "Sunchildren"));
        archers.put("sagittarius", new WarriorInfo("Sagittarius", 12, 18, 7, 17, 230, "Mystics"));

        knights.put("squire", new WarriorInfo("Squire", 7, 8, 9, 8, 85, "Marshlanders"));
        knights.put("cavalier", new WarriorInfo("Cavalier", 7, 10, 12, 10, 110, "Marshlanders"));
        knights.put("templar", new WarriorInfo("Templar", 12, 14, 16, 12, 155, "Sunchildren"));
        knights.put("zoro", new WarriorInfo("Zoro", 13, 17, 16, 14, 180, "Highlanders"));
        knights.put("swiftblade", new WarriorInfo("Swiftblade", 17, 18, 20, 13, 250, "Marshlanders"));

        healers.put("soother", new WarriorInfo("Soother", 9, 10, 8, 6, 95, "Sunchildren"));
        healers.put("medic", new WarriorInfo("Medic", 10, 12, 9, 7, 125, "Marshlanders"));
        healers.put("alchemist", new WarriorInfo("Alchemist", 13, 13, 13, 13, 150, "Marshlanders"));
        healers.put("saint", new WarriorInfo("Saint", 17, 16, 14, 9, 200, "Mystics"));
        healers.put("lifebringer", new WarriorInfo("Lifebringer", 19, 17, 15, 12, 260, "Sunchildren"));

        mages.put("warlock", new WarriorInfo("Warlock", 10, 12, 7, 12, 100, "Marshlanders"));
        mages.put("illusionist", new WarriorInfo("Illusionist", 12, 13, 8, 14, 120, "Mystics"));
        mages.put("enchanter", new WarriorInfo("Enchanter", 13, 16, 10, 16, 160, "Highlanders"));
        mages.put("conjurer", new WarriorInfo("Conjurer", 14, 18, 15, 12, 195, "Highlanders"));
        mages.put("eldritch", new WarriorInfo("Eldritch", 18, 19, 17, 14, 270, "Mystics"));

        mythicalCreatures.put("dragon", new WarriorInfo("Dragon", 15, 12, 14, 8, 120, "MythicalCreatures"));
        mythicalCreatures.put("basilisk", new WarriorInfo("Basilisk", 10, 15, 11, 12, 165, "MythicalCreatures"));
        mythicalCreatures.put("hydra", new WarriorInfo("Hydra", 15, 12, 16, 11, 205, "MythicalCreatures"));
        mythicalCreatures.put("phoenix", new WarriorInfo("Phoenix", 17, 17, 13, 19, 275, "MythicalCreatures"));

        armors.put("chainmail", new EquipmentInfo("Chainmail", 70, 0, 1, 0, -1));
        armors.put("regalia", new EquipmentInfo("Regalia", 105, 0, 1, 0, 0));
        armors.put("fleece", new EquipmentInfo("Fleece", 150, 0, 2, 1, -1));

        artefacts.put("excalibur", new EquipmentInfo("Excalibur", 150, 2, 0, 0, 0));
        artefacts.put("amulet", new EquipmentInfo("Amulet", 200, 1, -1, 1, 1));
        artefacts.put("crystal", new EquipmentInfo("Crystal", 210, 2, 1, -1, -1));
 */