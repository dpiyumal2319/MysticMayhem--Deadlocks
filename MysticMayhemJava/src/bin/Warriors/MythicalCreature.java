package bin.Warriors;

import bin.Collections.WarriorInfo;

public class MythicalCreature extends Warrior{
    
    public MythicalCreature(String name) {
        WarriorInfo info = data.mythicalCreatures.get(name);
        this.name = info.name;
        this.attack = info.attack;
        this.defense = info.defense;
        this.health = info.health;
        this.speed = info.speed;
        this.price = info.price;
        this.value = info.price * 0.9;
        this.type = "MythicalCreature";
    }
}
