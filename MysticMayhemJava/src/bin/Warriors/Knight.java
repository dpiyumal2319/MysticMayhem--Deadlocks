package bin.Warriors;

import bin.Warrior;

public class Knight extends Warrior{
    
    public Knight(String name) {
        super(2, 4, data.knights.get(name), "Knight");
    }
}
