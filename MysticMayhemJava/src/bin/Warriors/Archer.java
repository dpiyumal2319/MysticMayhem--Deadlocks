package bin.Warriors;

import bin.Warrior;

public class Archer extends Warrior{
    
    public Archer(String name) {
        super(1, 3, data.archers.get(name), "Archer");
    }
}
