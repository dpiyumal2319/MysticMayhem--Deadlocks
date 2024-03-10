package bin.Warriors;

import bin.Collections.WarriorInfo;

public class Healer extends Warrior{
    
    public Healer(String name) {
        WarriorInfo info = data.healers.get(name);
        this.name = info.name;
        this.attack = info.attack;
        this.defense = info.defense;
        this.health = info.health;
        this.speed = info.speed;
        this.price = info.price;
        this.value = info.price * 0.9;
        this.type = "Healer";
    }
}
