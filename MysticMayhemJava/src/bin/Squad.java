package bin;

import bin.Warriors.*;

import java.io.Serializable;

public class Squad implements Serializable{
    private Archer archer;
    private Knight knight;
    private Mage mage;
    private Healer healer;
    private MythicalCreature mythicalCreature;

    public Squad() {
        archer = null;
        knight = null;
        mage = null;
        healer = null;
        mythicalCreature = null;
    }

    void setArcher(Archer archer) {
        this.archer = archer;
    }
    void setKnight(Knight knight) {
        this.knight = knight;
    }
    void setMage(Mage mage) {
        this.mage = mage;
    }
    void setHealer(Healer healer) {
        this.healer = healer;
    }
    void setMythicalCreature(MythicalCreature mythicalCreature) {
        this.mythicalCreature = mythicalCreature;
    }
    Archer getArcher() {
        return archer;
    }
    Knight getKnight() {
        return knight;
    }
    Mage getMage() {
        return mage;
    }
    Healer getHealer() {
        return healer;
    }
    MythicalCreature getMythicalCreature() {
        return mythicalCreature;
    }

    Archer getArcher(String battleGround) {
        archer.prepareBattle(battleGround);
        return archer;
    }

    Knight getKnight(String battleGround) {
        knight.prepareBattle(battleGround);
        return knight;
    }

    Mage getMage(String battleGround) {
        mage.prepareBattle(battleGround);
        return mage;
    }

    Healer getHealer(String battleGround) {
        healer.prepareBattle(battleGround);
        return healer;
    }

    MythicalCreature getMythicalCreature(String battleGround) {
        mythicalCreature.prepareBattle(battleGround);
        return mythicalCreature;
    }

    void resetBattle() {
        archer.resetBattle();
        knight.resetBattle();
        mage.resetBattle();
        healer.resetBattle();
        mythicalCreature.resetBattle();
    }
    // void setSquadMate(Warrior warrior,String type) {
    //     switch (type) {
    //         case "Archer":
    //             setArcher((Archer) warrior);
    //             break;
    //         case "Knight":
    //             setKnight((Knight) warrior);
    //             break;
    //         case "Mage":
    //             setMage((Mage) warrior);
    //             break;
    //         case "Healer":
    //             setHealer((Healer) warrior);
    //             break;
    //         case "MythicalCreature":
    //             setMythicalCreature((MythicalCreature) warrior);
    //             break;
    //     }
    // }

    // Warrior addSquadMate(String type, String name) {
    //     Warrior warrior = null;
    //     switch (type) {
    //         case "Archer":
    //             warrior = new Archer(name);
    //             break;
    //         case "Knight":
    //             warrior = new Knight(name);
    //             break;
    //         case "Mage":
    //             warrior = new Mage(name);
    //             break;
    //         case "Healer":
    //             warrior = new Healer(name);
    //             break;
    //         case "MythicalCreature":
    //             warrior = new MythicalCreature(name);
    //             break;
    //     }
    //     setSquadMate(warrior, type);
    //     return warrior;
    // }
}
