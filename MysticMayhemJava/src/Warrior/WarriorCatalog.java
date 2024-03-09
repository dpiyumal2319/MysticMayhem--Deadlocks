package Warrior;

public class WarriorCatalog {
    private final String name;
    private final int health;
    private final int attack;
    private final int defense;
    private final int speed;
    private final int price;

    public WarriorCatalog(String name, int health, int attack, int defense, int speed, int price) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getPrice() {
        return price;
    }
}
