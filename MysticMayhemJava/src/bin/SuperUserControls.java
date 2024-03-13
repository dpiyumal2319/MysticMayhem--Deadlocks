package bin;

import java.io.Serializable;

public class SuperUserControls implements Serializable{
    private int money = 5000000;

    protected void giveMoneyFor(InventoryItem item) {
        money -= item.price;
    }

    protected void getMoneyFrom(InventoryItem item) {
        money += item.getValue();
    }

    public int getMoney() {
        return money;
    }
    public void incrementMoney(int amount) {
        money += amount;
    }
    public void decrementMoney(int amount) {
        money -= amount;
    }
}
