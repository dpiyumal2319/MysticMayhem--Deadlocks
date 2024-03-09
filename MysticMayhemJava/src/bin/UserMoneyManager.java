package bin;

import java.io.Serializable;

public class UserMoneyManager implements Serializable{
    int money;

    protected void buyItem(InventoryItem item) {
        money -= item.price;
    }

    protected void sellItem(InventoryItem item) {
        money += item.value;
    }
}
