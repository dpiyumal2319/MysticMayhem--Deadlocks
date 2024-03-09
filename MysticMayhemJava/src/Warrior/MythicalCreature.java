package Warrior;

public class MythicalCreature extends Warrior {
    public MythicalCreature(String name) {
        WarriorCatalog mythicalCreature = MYTHICALCREATURE_MAP.get(name);
        if (mythicalCreature != null) {
            this.name = mythicalCreature.getName();
            this.health = mythicalCreature.getHealth();
            this.attack = mythicalCreature.getAttack();
            this.defense = mythicalCreature.getDefense();
            this.speed = mythicalCreature.getSpeed();
            this.price = mythicalCreature.getPrice();
        }
    }
}

