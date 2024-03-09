package Warrior;

public class Knight extends Warrior {
    public Knight(String name) {
        WarriorCatalog knight = KNIGHT_MAP.get(name);
        if (knight != null) {
            this.name = knight.getName();
            this.health = knight.getHealth();
            this.attack = knight.getAttack();
            this.defense = knight.getDefense();
            this.speed = knight.getSpeed();
            this.price = knight.getPrice();
            this.value = this.price;
        }
    }
}
