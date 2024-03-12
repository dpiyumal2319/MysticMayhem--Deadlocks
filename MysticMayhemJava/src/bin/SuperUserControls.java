package bin;

import java.io.Serializable;

public class SuperUserControls implements Serializable{
    private int money = 500;

    protected void giveMoneyFor(InventoryItem item) {
        money -= item.price;
    }

    protected void getMoneyFrom(InventoryItem item) {
        money += item.getValue();
    }

    public int getMoney() {
        return money;
    }
}
