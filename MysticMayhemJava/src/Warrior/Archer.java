package Warrior;

public class Archer extends Warrior {
    public Archer(String name) {
        switch (name) {
            case "Shooter":
                this.name = "Shooter";
                this.health = 6;
                this.attack = 11;
                this.defense = 4;
                this.speed = 9;
                this.price = 80;
                break;
            case "Ranger":
                this.name = "Ranger";
                this.health = 8;
                this.attack = 14;
                this.defense = 5;
                this.speed = 10;
                this.price = 115;
                break;
            case "Sunfire":
                this.name = "Sunfire";
                this.health = 7;
                this.attack = 15;
                this.defense = 5;
                this.speed = 14;
                this.price = 160;
                break;
            case "Zing":
                this.name = "Zing";
                this.health = 11;
                this.attack = 16;
                this.defense = 9;
                this.speed = 14;
                this.price = 200;
                break;
            case "Sagittarius":
                this.name = "Sagittarius";
                this.health = 12;
                this.attack = 18;
                this.defense = 7;
                this.speed = 17;
                this.price = 230;
                break;
        }



    }
}
