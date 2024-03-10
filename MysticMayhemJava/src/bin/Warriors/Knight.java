package bin.Warriors;

import bin.Warrior;
import bin.Collections.WarriorInfo;

public class Knight extends Warrior{
    
    public Knight(String name) {
        WarriorInfo info = data.knights.get(name);
        this.name = info.name;
        this.attack = info.attack;
        this.defense = info.defense;
        this.health = info.health;
        this.speed = info.speed;
        this.price = info.price;
        this.value = info.price * 0.9;
        this.type = "Knight";
    }
}
