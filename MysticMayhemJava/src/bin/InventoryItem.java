package bin;

import java.io.Serializable;
import java.util.Map;

import bin.Collections.EquipmentInfo;
import bin.Collections.WarriorInfo;

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

    public static void printMap(String type) {
        Map<String, WarriorInfo> warriorMap = getWarriorMap(type);
        Map<String, EquipmentInfo> equipmentMap = getEquipmentMap(type);
        if (warriorMap != null) {
            for (String key : warriorMap.keySet()) {
                System.out.println(key + ":");
                System.out.println("Attack: " + warriorMap.get(key).attack);
                System.out.println("Defense: " + warriorMap.get(key).defense);
                System.out.println("Health: " + warriorMap.get(key).health);
                System.out.println("Speed: " + warriorMap.get(key).speed);
                System.out.println("Price: " + warriorMap.get(key).price);
            }
        } else if (equipmentMap != null) {
            for (String key : equipmentMap.keySet()) {
                System.out.println(key + ":");
                System.out.println("Attack: " + equipmentMap.get(key).extraAttack);
                System.out.println("Defense: " + equipmentMap.get(key).extraDefense);
                System.out.println("Health: " + equipmentMap.get(key).extraDefense);
                System.out.println("Speed: " + equipmentMap.get(key).extraDefense);
                System.out.println("Price: " + equipmentMap.get(key).price);
            }
        }
    }
}
