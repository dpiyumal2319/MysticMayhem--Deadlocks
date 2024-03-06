package Equipments;

public class Armor extends Equipments{
    public Armor(String name) {
        switch (name) {
            case "Chainmail" :
                this.name = "Chainmail";
                this.price = 70;
                this.extraAttack = 0;
                this.extraDefense = 1;
                this.extraHealth = 0;
                this.extraSpeed = -1;
                break;
            case "Regalia" :
                this.name = "Regalia";
                this.price = 105;
                this.extraAttack = 0;
                this.extraDefense = 1;
                this.extraHealth = 0;
                this.extraSpeed = 0;
                break;
            case "Fleece" : 
                this.name = "Fleece";
                this.price = 150;
                this.extraAttack = 0;
                this.extraDefense = 2;
                this.extraHealth = 1;
                this.extraSpeed = -1;
                break;
        }
    }
}
