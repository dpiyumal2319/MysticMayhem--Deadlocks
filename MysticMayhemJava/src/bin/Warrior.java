package bin;

import bin.Collections.*;

public abstract class Warrior extends InventoryItem {
    private int attack, defense, health, speed;
    public final int attackPriority;
    public final int defensePriority;
    public final String homeLand;
    public int battleAttack = -100, battleDefense = -100, battleSpeed = -100, bonusTurns = -100;
    public float battleHealth = -100f;
    public float bonusAttackBuff = 0f, healPerAttack =  0f;
    private Waredrobe woredrobe = new Waredrobe();

    public Warrior(int attackPriority, int defensePriority,WarriorInfo info, String type) {
        super(info.name, info.price, type, info.price * 0.9);
        this.attack = info.attack;
        this.defense = info.defense;
        this.health = info.health;
        this.speed = info.speed;
        this.attackPriority = attackPriority;
        this.defensePriority = defensePriority;
        this.homeLand = info.homeLand;
    }

    public int getAttack() {
        return attack + woredrobe.getAttackBoost();
    }

    public int getDefense() {
        return defense + woredrobe.getDefenseBoost();
    }

    public int getHealth() {
        return health + woredrobe.getHealthBoost();
    }

    public int getSpeed() {
        return speed + woredrobe.getSpeedBoost();
    }

    public String getName() {
        return name;
    }

    public Waredrobe getWoredrobe() {
        return woredrobe;
    }

    public void printInfo() {
        System.out.println("\t" + type + " : " + name);
        System.out.println("\t\tArmor :" + (woredrobe.getArmor() == null ? "None" : woredrobe.getArmor().name));
        System.out.println("\t\tArtefact :" + (woredrobe.getArtefact() == null ? "None" : woredrobe.getArtefact().name));
        System.out.println("\t\tAttak :" + getAttack());
        System.out.println("\t\tDefense :" + getDefense());
        System.out.println("\t\tHealth :" + getHealth());
        System.out.println("\t\tSpeed :" + getSpeed());
        System.out.println("\t\tValue :" + this.getValue());
    }

    InventoryItem addEquipment(String type, String name) {
        InventoryItem item = null;
        if (type.equals("Armor")) {
            item = woredrobe.addArmor(name);
        } else if (type.equals("Artefact")) {
            item = woredrobe.addArtefact(name);
        }
        this.incrementValue(item.getValue());
        return item;
    }

    InventoryItem removeEquipment(String type) {
        InventoryItem item = null;
        if (type.equals("Armor")) {
            item = woredrobe.removeArmor();
        } else if (type.equals("Artefact")) {
            item = woredrobe.removeArtefact();
        }
        this.decrementValue(item.getValue());
        return item;
    }

    public void prepareBattle(String battleGround) {
        switch (battleGround) {
            case "Hillcrest" :
                if (homeLand.equals("Highlanders")) {
                    battleDefense = getDefense() + 1;
                    bonusAttackBuff = 0.2f;
                    bonusTurns = 1;
                } else if (homeLand.equals("Sunchildren") || homeLand.equals("Marshlanders")) {
                    battleSpeed = getSpeed() - 1;
                }
                break;
            case "Marshland" :
                if (homeLand.equals("Marshlanders")) {
                    battleDefense = getDefense() + 2;
                } else if (homeLand.equals("Sunchildren")) {
                    battleAttack = getAttack() - 1;
                } else if (homeLand.equals("Mystics")) {
                    battleSpeed = getSpeed() - 1;
                }
                break;
            case "Desert" :
                if (homeLand.equals("Marshlanders")) {
                    battleHealth = getHealth() - 1;
                } else if (homeLand.equals("Sunchildren")) {
                    battleAttack = getAttack() + 1;
                }
                break;
            case "Arcane" :
                if (homeLand.equals("Mystics")) {
                    battleAttack = getAttack() + 2;
                    healPerAttack = 0.1f;
                } else if (homeLand.equals("Highlanders") || homeLand.equals("Marshlanders")) {
                    battleSpeed = getSpeed() - 1;
                    battleDefense = getDefense() - 1;
                }
                break;
        }
        if (battleAttack == -100) battleAttack = getAttack();
        if (battleDefense == -100) battleDefense = getDefense();
        if (battleHealth == -100) battleHealth = getHealth();
        if (battleSpeed == -100) battleSpeed = getSpeed();
        if (bonusTurns == -100) bonusTurns = 0;
    }

    int getBattleSpeed() {
        return battleSpeed;
    }

    int getBattleDefense() {
        return battleDefense;
    }

    public int getAttackPriority() {
        return attackPriority;
    }

    public int getDefensePriority() {
        return defensePriority;
    }

    public void resetBattle() {
        battleAttack = -100;
        battleDefense = -100;
        battleHealth = -100f;
        battleSpeed = -100;
        bonusTurns = -100;
        bonusAttackBuff = 0f;
        healPerAttack = 0f;
    }
}


/*
 * In Hillcrest, the attack and defence of highlanders increase by 1 while the speed of
marshlanders and sunchildren decrease by 1. When attacking in Hillcrest, each highlander
can follow each of their turns with a bonus turn with 20% of their attack power.
In Marshland, the defence of marshlanders increases by 2 while the attack of sunchildren
decreases by 1. The speed of mystics also decreases by 1.
In Desert, the health of marshlanders decreases by 1 while the attack of sunchildren
increases by 1.
In Arcane, the attack of mystics increases by 2 while the speed and defence of highlanders
and marshlanders decrease by 1. When attacking in arcane, mystics increase their own
health by 10% after each of their turns.

 */

 /*4
  * public void prepareBattle(String battleGround) {
        switch (battleGround) {
            case "Hillcrest" :
                if (homeLand.equals("Highlanders")) {
                    battleDefense = getDefense() + 1;
                    bonusAttackBuff = 0.2f;
                    bonusTurns = 1;
                } else if (homeLand == "Sunchildren" || homeLand == "Marshlanders") {
                    battleSpeed = getSpeed() - 1;
                }
                break;
            case "Marshland" :
                if (homeLand == "Marshlanders") {
                    battleDefense = getDefense() + 2;
                } else if (homeLand == "Sunchildren") {
                    battleAttack = getAttack() - 1;
                } else if (homeLand == "Mystics") {
                    battleSpeed = getSpeed() - 1;
                }
                break;
            case "Desert" :
                if (homeLand == "Marshlanders") {
                    battleHealth = getHealth() - 1;
                } else if (homeLand == "Sunchildren") {
                    battleAttack = getAttack() + 1;
                }
                break;
            case "Arcane" :
                if (homeLand == "Mystics") {
                    battleAttack = getAttack() + 2;
                    healPerAttack = 0.1f;
                } else if (homeLand == "Highlanders" || homeLand == "Marshlanders") {
                    battleSpeed = getSpeed() - 1;
                    battleDefense = getDefense() - 1;
                }
                break;
        }
        if (battleAttack == -100) battleAttack = getAttack();
        if (battleDefense == -100) battleDefense = getDefense();
        if (battleHealth == -100) battleHealth = getHealth();
        if (battleSpeed == -100) battleSpeed = getSpeed();
        if (bonusTurns == -100) bonusTurns = 0;
    }
  */