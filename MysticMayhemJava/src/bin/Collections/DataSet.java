package bin.Collections;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataSet implements Serializable{
    public Map<String, WarriorInfo> archers;
    public Map<String, WarriorInfo> knights;
    public Map<String, WarriorInfo> healers;
    public Map<String, WarriorInfo> mages;
    public Map<String, WarriorInfo> mythicalCreatures;

    public Map<String, EquipmentInfo> armors;
    public Map<String, EquipmentInfo> artefacts;


    public DataSet() {

        archers = new HashMap<>();
        knights = new HashMap<>();
        healers = new HashMap<>();
        mages = new HashMap<>();
        mythicalCreatures = new HashMap<>();

        armors = new HashMap<>();
        artefacts = new HashMap<>();
    }
}
