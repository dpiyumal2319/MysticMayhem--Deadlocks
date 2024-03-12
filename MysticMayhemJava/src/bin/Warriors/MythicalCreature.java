package bin.Warriors;

import bin.Warrior;

public class MythicalCreature extends Warrior{
    
    public MythicalCreature(String name) {
        super(3, 4, data.mythicalCreatures.get(name), "MythicalCreature");
    }
}
