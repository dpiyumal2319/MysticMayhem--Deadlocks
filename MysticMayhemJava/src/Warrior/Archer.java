package Warrior;

public class Archer extends Warrior {
    public Archer(String name) {
        WarriorCatalog archer = ARCHER_MAP.get(name);
        if (archer != null) {
            this.name = archer.getName();
            this.health = archer.getHealth();
            this.attack = archer.getAttack();
            this.defense = archer.getDefense();
            this.speed = archer.getSpeed();
            this.price = archer.getPrice();
        }
    }
}
