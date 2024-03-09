import UserManager.User;
import Shop.Shop;
public class App {
    public static void main(String[] args) throws Exception {
        User user = new User("John");
        System.out.println(user.getMoney());
        Shop.enterShop(user);
    }
}