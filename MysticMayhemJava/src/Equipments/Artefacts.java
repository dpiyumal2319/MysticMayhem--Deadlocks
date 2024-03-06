package Equipments;

public class Artefacts extends Equipments{
    public Artefacts(String name) {
        switch (name) {
            case "Excalibur" :
                this.name = "Excalibur";
                this.price = 150;
                this.extraAttack = 1;
                this.extraDefense = 1;
                this.extraHealth = 1;
                this.extraSpeed = 1;
                break;
            case "Ring of the Damned" :
                this.name = "Ring of the Damned";
                this.price = 100;
                this.extraAttack = 1;
                this.extraDefense = 1;
                this.extraHealth = 1;
                this.extraSpeed = 1;
                break;
            case "Crown of the Damned" : 
                this.name = "Crown of the Damned";
                this.price = 100;
                this.extraAttack = 1;
                this.extraDefense = 1;
                this.extraHealth = 1;
                this.extraSpeed = 1;
                break;
        }
    }
}
