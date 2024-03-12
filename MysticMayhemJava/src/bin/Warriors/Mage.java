package bin.Warriors;

import bin.Warrior;

public class Mage extends Warrior{

    public Mage(String name) {
        super(4, 5, data.mages.get(name), "Mage");
    }
}
