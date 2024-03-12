package bin.Collections;

public class WarriorInfo {
    public final int health;
    public final int attack;
    public final int defense;
    public final int speed;
    public final String homeLand;
    public final int price;
    public final String name;

    public WarriorInfo(String name, int health, int attack, int defense, int speed, int price, String homeLand) {
        this.name = name;
        this.price = price;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.homeLand = homeLand;
    }
}