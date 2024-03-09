package bin;

import java.io.Serializable;
import java.util.Map;


public class InventoryItem implements Serializable{
    public static DataSet data = new DataSet();

    public String name;
    public int price;
    public double value;
    public String type;

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
