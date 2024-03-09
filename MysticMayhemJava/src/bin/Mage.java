package bin;


public class Mage extends Warrior{

    public Mage(String name) {
        WarriorInfo info = data.mages.get(name);
        this.name = info.name;
        this.attack = info.attack;
        this.defense = info.defense;
        this.health = info.health;
        this.speed = info.speed;
        this.price = info.price;
        this.value = info.price * 0.9;
        this.type = "Mage";
    }
}
