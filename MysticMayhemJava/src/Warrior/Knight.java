package Warrior;

public class Knight extends Warrior {
    public Knight(String name) {
        switch (name) {
            case "Squire":
                this.name = "Squire";
                this.health = 7;
                this.attack = 8;
                this.defense = 9;
                this.speed = 8;
                this.price = 85;
                break;
            case "Cavalier":
                this.name = "Cavalier";
                this.health = 7;
                this.attack = 10;
                this.defense = 12;
                this.speed = 10;
                this.price = 110;
                break;
            case "Templar":
                this.name = "Templar";
                this.health = 12;
                this.attack = 14;
                this.defense = 16;
                this.speed = 12;
                this.price = 155;
                break;
            case "Zoro":
                this.name = "Zoro";
                this.health = 13;
                this.attack = 17;
                this.defense = 16;
                this.speed = 14;
                this.price = 180;
                break;
            case "Swiftblade":
                this.name = "Swiftblade";
                this.health = 17;
                this.attack = 18;
                this.defense = 20;
                this.speed = 13;
                this.price = 250;
                break;
        }
    }
}
