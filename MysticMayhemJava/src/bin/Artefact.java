package bin;


public class Artefact extends Equipment{
    
    public Artefact(String name) {
        EquipmentInfo info = data.artefacts.get(name);
        this.name = info.name;
        this.price = info.price;
        this.extraAttack = info.extraAttack;
        this.extraDefense = info.extraDefense;
        this.extraHealth = info.extraHealth;
        this.extraSpeed = info.extraSpeed;
        this.value = info.price * 0.2;
        this.type = "Artefact";
    }
}
