package bin.Warriors;

import bin.Warrior;

public class Healer extends Warrior{
    
    public Healer(String name) {
        super(5, 1, data.healers.get(name), "Healer");
    }

    public void prepareBattle(String battleGround) {
        switch (battleGround) {
            case "Hillcrest" :
                if (homeLand == "Highlanders") {
                    battleDefense = getDefense() + 1;
                    bonusAttackBuff = 0.2f;
                    bonusTurns = 1;
                } else if (battleGround == "Sunchildren" || battleGround == "Marshlanders") {
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

        battleAttack = battleAttack*-1;
    }
}
