package Equipments;

public class Armor extends Equipments{
    public Armor(String name) {
        EquipmentCatalog armor = ARMOR_MAP.get(name);
        if (armor != null) {
            this.name = armor.getName();
            this.price = armor.getPrice();
            this.extraAttack = armor.getExtraAttack();
            this.extraDefense = armor.getExtraDefense();
            this.extraHealth = armor.getExtraHealth();
            this.extraSpeed = armor.getExtraSpeed();
        }
    }
}
