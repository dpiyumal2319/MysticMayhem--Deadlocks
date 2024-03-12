package bin.Warriors;

import bin.Warrior;
import bin.Collections.WarriorInfo;

public class Archer extends Warrior{
    
    public Archer(String name) {
        super(1, 3, data.archers.get(name).homeLand);
        WarriorInfo info = data.archers.get(name);
        this.name = info.name;
        this.attack = info.attack;
        this.defense = info.defense;
        this.health = info.health;
        this.speed = info.speed;
        this.price = info.price;
        this.value = info.price * 0.9;
        this.type = "Archer";
    }
}
