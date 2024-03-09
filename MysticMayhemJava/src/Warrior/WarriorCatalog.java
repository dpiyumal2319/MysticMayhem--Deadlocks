package Warrior;

import java.io.Serializable;

public class WarriorCatalog implements Serializable{
    private final String name;
    private final int health;
    private final int attack;
    private final int defense;
    private final int speed;
    private final int price;
    private final String type;

    public WarriorCatalog(String name, String type, int health, int attack, int defense, int speed, int price) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.price = price;
        this.type = type;
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
    public String getType() {
        return type;
    }
}
