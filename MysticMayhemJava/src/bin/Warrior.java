package bin;

public abstract class Warrior extends InventoryItem {
    protected int attack, defense, health, speed;
    public final int attackPriority;
    public final int defensePriority;
    public final String homeGround;
    public int battleAttack = -100, battleDefense = -100, battleHealth = -100, battleSpeed = -100, bonusTurns = -100;
    public float bonusAttackBuff = 0f, healPerAttack =  0f;

    public Warrior(int attackPriority, int defensePriority, String homeGround) {
        this.attackPriority = attackPriority;
        this.defensePriority = defensePriority;
        this.homeGround = homeGround;
    }

    private Waredrobe woredrobe = new Waredrobe();

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
        System.out.println("\t\tValue :" + value);
    }

    InventoryItem addEquipment(String type, String name) {
        InventoryItem item = null;
        if (type.equals("Armor")) {
            item = woredrobe.addArmor(name);
        } else if (type.equals("Artefact")) {
            item = woredrobe.addArtefact(name);
        }
        this.value += item.value;
        return item;
    }

    InventoryItem removeEquipment(String type) {
        InventoryItem item = null;
        if (type.equals("Armor")) {
            item = woredrobe.removeArmor();
        } else if (type.equals("Artefact")) {
            item = woredrobe.removeArtefact();
        }
        this.value -= item.value;
        return item;
    }

    public void prepareBattle(String battleGround) {
        switch (battleGround) {
            case "Hillcrest" :
                if (homeGround == "Highlanders") {
                    battleDefense = getDefense() + 1;
                    bonusAttackBuff = 0.2f;
                    bonusTurns = 1;
                } else if (battleGround == "Sunchildren" || battleGround == "Marshlanders") {
                    battleSpeed = getSpeed() - 1;
                }
                break;
            case "Marshland" :
                if (homeGround == "Marshlanders") {
                    battleDefense = getDefense() + 2;
                } else if (homeGround == "Sunchildren") {
                    battleAttack = getAttack() - 1;
                } else if (homeGround == "Mystics") {
                    battleSpeed = getSpeed() - 1;
                }
                break;
            case "Desert" :
                if (homeGround == "Marshlanders") {
                    battleHealth = getHealth() - 1;
                } else if (homeGround == "Sunchildren") {
                    battleAttack = getAttack() + 1;
                }
                break;
            case "Arcane" :
                if (homeGround == "Mystics") {
                    battleAttack = getAttack() + 2;
                    healPerAttack = 0.1f;
                } else if (homeGround == "Highlanders" || homeGround == "Marshlanders") {
                    battleSpeed = getSpeed() - 1;
                    battleDefense = getDefense() - 1;
                }
                break;
        }
        if (battleAttack == -100) battleAttack = getAttack();
        if (battleDefense == -100) battleDefense = getDefense();
        if (battleHealth == -100) battleHealth = getHealth();
        if (battleSpeed == -100) battleSpeed = getSpeed();
    }

    public void resetBattle() {
        battleAttack = -100;
        battleDefense = -100;
        battleHealth = -100;
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