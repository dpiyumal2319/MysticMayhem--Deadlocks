package bin.Collections;

public class WarriorInfo {
    public final String name;
    public final int health;
    public final int attack;
    public final int defense;
    public final int speed;
    public final int price;
    public final String homeLand;

    public WarriorInfo(String name, int health, int attack, int defense, int speed, int price, String homeLand) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.price = price;
        this.homeLand = homeLand;
    }
}