package Warrior;

public class Mage extends Warrior {
    public Mage(String name) {
        WarriorCatalog mage = MAGE_MAP.get(name);
        if (mage != null) {
            this.name = mage.getName();
            this.health = mage.getHealth();
            this.attack = mage.getAttack();
            this.defense = mage.getDefense();
            this.speed = mage.getSpeed();
            this.price = mage.getPrice();
            this.value = this.price;
        }
    }
}