package Equipments;

public class Artefacts extends Equipments{
    public Artefacts(String name) {
        switch (name) {
            case "Excalibur" :
                this.name = "Excalibur";
                this.price = 150;
                this.extraAttack = 2;
                this.extraDefense = 0;
                this.extraHealth = 0;
                this.extraSpeed = 0;
                break;
            case "Amulet" :
                this.name = "Amulet";
                this.price = 200;
                this.extraAttack = 1;
                this.extraDefense = -1;
                this.extraHealth = 1;
                this.extraSpeed = 1;
                break;
            case "Crystal" : 
                this.name = "Crystal";
                this.price = 210;
                this.extraAttack = 2;
                this.extraDefense = 1;
                this.extraHealth = -1;
                this.extraSpeed = -1;
                break;
        }
    }
}
