package bin;

import java.io.Serializable;

public class SuperUserControls implements Serializable{
    private int money = 5000;

    protected void giveMoneyFor(InventoryItem item) {
        money -= item.price;
    }

    protected void getMoneyFrom(InventoryItem item) {
        money += item.getValue();
    }

    public int getMoney() {
        return money;
    }

    public void increaseMoney(int amount) {
        money += amount;
    }

    public void decreaseMoney(int amount) {
        money -= amount;
    }
}
