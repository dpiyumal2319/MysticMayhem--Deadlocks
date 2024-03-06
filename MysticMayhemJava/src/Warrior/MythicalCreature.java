package Warrior;

public class MythicalCreature extends Warrior {
    public MythicalCreature(String name) {
        switch (name) {
            case "Dragon":
                this.name = "Dragon";
                this.health = 15;
                this.attack = 12;
                this.defense = 14;
                this.speed = 8;
                this.price = 120;
                break;
            case "Basilisk":
                this.name = "Basilisk";
                this.health = 10;
                this.attack = 15;
                this.defense = 11;
                this.speed = 12;
                this.price = 165;
                break;
            case "Hydra":
                this.name = "Hydra";
                this.health = 15;
                this.attack = 12;
                this.defense = 16;
                this.speed = 11;
                this.price = 205;
                break;
            case "Phoenix":
                this.name = "Phoenix";
                this.health = 17;
                this.attack = 17;
                this.defense = 13;
                this.speed = 19;
                this.price = 275;
                break;
            case "Pegasus":
                this.name = "Pegasus";
                this.health = 20;
                this.attack = 14;
                this.defense = 18;
                this.speed = 20;
                this.price = 340;
                break;
        }
    }
}

