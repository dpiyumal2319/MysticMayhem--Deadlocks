package Warrior;

public class Healer extends Warrior {
    public Healer(String name) {
        switch (name) {
            case "Soother":
                this.name = "Soother";
                this.health = 9;
                this.attack = 10;
                this.defense = 8;
                this.speed = 6;
                this.price = 95;
                break;
            case "Medic":
                this.name = "Medic";
                this.health = 10;
                this.attack = 12;
                this.defense = 9;
                this.speed = 7;
                this.price = 125;
                break;
            case "Alchemist":
                this.name = "Alchemist";
                this.health = 13;
                this.attack = 13;
                this.defense = 13;
                this.speed = 13;
                this.price = 150;
                break;
            case "Saint":
                this.name = "Saint";
                this.health = 17;
                this.attack = 16;
                this.defense = 14;
                this.speed = 9;
                this.price = 200;
                break;
            case "Lifebringer":
                this.name = "Lifebringer";
                this.health = 19;
                this.attack = 17;
                this.defense = 15;
                this.speed = 12;
                this.price = 260;
                break;
        }
    }
}