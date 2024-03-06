package Warrior;

public class Mage extends Warrior {
    public Mage(String name) {
        switch (name) {
            case "Warlock":
                this.name = "Warlock";
                this.health = 10;
                this.attack = 12;
                this.defense = 7;
                this.speed = 12;
                this.price = 100;
                break;
            case "Illusionist":
                this.name = "Illusionist";
                this.health = 12;
                this.attack = 13;
                this.defense = 8;
                this.speed = 14;
                this.price = 120;
                break;
            case "Enchanter":
                this.name = "Enchanter";
                this.health = 13;
                this.attack = 16;
                this.defense = 10;
                this.speed = 16;
                this.price = 160;
                break;
            case "Conjurer":
                this.name = "Conjurer";
                this.health = 14;
                this.attack = 18;
                this.defense = 15;
                this.speed = 12;
                this.price = 195;
                break;
            case "Eldritch":
                this.name = "Eldritch";
                this.health = 18;
                this.attack = 19;
                this.defense = 17;
                this.speed = 14;
                this.price = 270;
                break;
        }
    }
}