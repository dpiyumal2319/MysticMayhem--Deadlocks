package Warrior;

public class Healer extends Warrior {
    public Healer(String name) {
        WarriorCatalog healer = HEALER_MAP.get(name);
        if (healer != null) {
            this.name = healer.getName();
            this.health = healer.getHealth();
            this.attack = healer.getAttack();
            this.defense = healer.getDefense();
            this.speed = healer.getSpeed();
            this.price = healer.getPrice();
            this.value = this.price;
        }
    }
}