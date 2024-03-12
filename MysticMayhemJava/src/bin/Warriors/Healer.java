package bin.Warriors;

import bin.Warrior;
import bin.Collections.WarriorInfo;

public class Healer extends Warrior{
    
    public Healer(String name) {
        super(5, 1, data.healers.get(name).homeLand);
        WarriorInfo info = data.healers.get(name);
        this.name = info.name;
        this.attack = info.attack;
        this.defense = info.defense;
        this.health = info.health;
        this.speed = info.speed;
        this.price = info.price;
        this.value = info.price * 0.9;
        this.type = "Healer";
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

        battleAttack = battleAttack*-1;
    }
}
