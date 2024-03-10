package bin;

import bin.Warriors.Archer;
import bin.Warriors.Healer;
import bin.Warriors.Knight;
import bin.Warriors.Mage;
import bin.Warriors.MythicalCreature;

public abstract class Squad {
    private Archer archer;
    private Knight knight;
    private Mage mage;
    private Healer healer;
    private MythicalCreature mythicalCreature;

    public void setArcher(Archer archer) {
        this.archer = archer;
    }
    public void setKnight(Knight knight) {
        this.knight = knight;
    }
    public void setMage(Mage mage) {
        this.mage = mage;
    }
    public void setHealer(Healer healer) {
        this.healer = healer;
    }
    public void setMythicalCreature(MythicalCreature mythicalCreature) {
        this.mythicalCreature = mythicalCreature;
    }
    public Archer getArcher() {
        return archer;
    }
    public Knight getKnight() {
        return knight;
    }
    public Mage getMage() {
        return mage;
    }
    public Healer getHealer() {
        return healer;
    }
    public MythicalCreature getMythicalCreature() {
        return mythicalCreature;
    }
    void setSquadMate(InventoryItem warrior,String type) {
        switch (type) {
            case "Archer":
                setArcher((Archer) warrior);
                break;
            case "Knight":
                setKnight((Knight) warrior);
                break;
            case "Mage":
                setMage((Mage) warrior);
                break;
            case "Healer":
                setHealer((Healer) warrior);
                break;
            case "MythicalCreature":
                setMythicalCreature((MythicalCreature) warrior);
                break;
        }
    }

    void addSquadMate(String type) {
        InventoryItem warrior = null;
        switch (type) {
            case "Archer":
                warrior = new Archer(type);
                break;
            case "Knight":
                warrior = new Knight(type);
                break;
            case "Mage":
                warrior = new Mage(type);
                break;
            case "Healer":
                warrior = new Healer(type);
                break;
            case "MythicalCreature":
                warrior = new MythicalCreature(type);
                break;
        }
        setSquadMate(warrior, type);
    }
}
