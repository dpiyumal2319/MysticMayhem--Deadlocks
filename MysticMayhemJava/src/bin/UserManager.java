package bin;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import bin.Warriors.*;

public class UserManager implements Serializable {
    private static final String USERS_FILE = "users.ser";
    private static final String NUMBER_OF_USERS_FILE = "numberOfUsers.ser";

    public static void saveNumberOfUsers(int numberOfUsers) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(NUMBER_OF_USERS_FILE))) {
            out.writeInt(numberOfUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int loadNumberOfUsers() {
        File file = new File(NUMBER_OF_USERS_FILE);
        if (!file.exists()) {
            return 0; // Return 0 if the file does not exist
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return in.readInt();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void saveUsers(Map<String, User> users) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            out.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, User> loadUsers() {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            return generateDummyMap();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            return (Map<String, User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    // This method is used to generate dummy users for battle.
    private static Map<String, User> generateDummyMap() {
        Map<String, User> users = new HashMap<>();
        int dummyUserNu = -1000;

        // -1000 to -999 are reserved for dummy users
        // Dummy 1
        User user1 = new User("GeraltOfRivia", "whitewolf", dummyUserNu++, "Marshland");
        user1.squad.setArcher(new Archer("ranger"));
        user1.squad.getArcher().getWoredrobe().addArmor("chainmail");
        user1.squad.setKnight(new Knight("squire"));
        user1.squad.setMage(new Mage("warlock"));
        user1.squad.setHealer(new Healer("medic"));
        user1.squad.getHealer().getWoredrobe().addArtefact("amulet");
        user1.squad.setMythicalCreature(new MythicalCreature("dragon"));
        user1.increaseXp(32);
        user1.decreaseMoney(285);
        users.put(user1.userName, user1);

        // Dummy 2
        User user2 = new User("YenneferOfVengerberg", "sorceress", dummyUserNu++, "Arcane");
        user2.squad.setArcher(new Archer("shooter"));
        user2.squad.getArcher().getWoredrobe().addArmor("regalia");
        user2.squad.getArcher().getWoredrobe().addArtefact("crystal");
        user2.squad.setKnight(new Knight("templar"));
        user2.squad.setMage(new Mage("enchanter"));
        user2.squad.getMage().getWoredrobe().addArmor("fleece");
        user2.squad.setHealer(new Healer("saint"));
        user2.squad.getHealer().getWoredrobe().addArtefact("amulet");
        user2.squad.setMythicalCreature(new MythicalCreature("phoenix"));
        user2.increaseXp(45);
        user2.increaseMoney(100);
        users.put(user2.userName, user2);

        // Dummy 3
        User user3 = new User("Cirilla", "ladyofspace", dummyUserNu++, "Desert");
        user3.squad.setArcher(new Archer("sagittarius"));
        user3.squad.getArcher().getWoredrobe().addArmor("chainmail");
        user3.squad.setKnight(new Knight("swiftblade"));
        user3.squad.setMage(new Mage("eldritch"));
        user3.squad.getMage().getWoredrobe().addArmor("regalia");
        user3.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user3.squad.setHealer(new Healer("lifebringer"));
        user3.squad.getHealer().getWoredrobe().addArmor("fleece");
        user3.squad.getHealer().getWoredrobe().addArtefact("crystal");
        user3.squad.setMythicalCreature(new MythicalCreature("hydra"));
        user3.increaseXp(60);
        user3.increaseMoney(50);
        users.put(user3.userName, user3);

        // Dummy 4
        User user4 = new User("TrissMerigold", "mage", dummyUserNu++, "Arcane");
        user4.squad.setArcher(new Archer("sunfire"));
        user4.squad.getArcher().getWoredrobe().addArmor("regalia");
        user4.squad.getArcher().getWoredrobe().addArtefact("crystal");
        user4.squad.setKnight(new Knight("cavalier"));
        user4.squad.setMage(new Mage("illusionist"));
        user4.squad.getMage().getWoredrobe().addArmor("fleece");
        user4.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user4.squad.setHealer(new Healer("alchemist"));
        user4.squad.getHealer().getWoredrobe().addArmor("fleece");
        user4.squad.getHealer().getWoredrobe().addArtefact("amulet");
        user4.squad.setMythicalCreature(new MythicalCreature("basilisk"));
        user4.increaseXp(75);
        user4.increaseMoney(100);
        users.put(user4.userName, user4);

        // Dummy 5
        User user5 = new User("Dandelion", "bard", dummyUserNu++, "Marshland");
        user5.squad.setArcher(new Archer("zing"));
        user5.squad.getArcher().getWoredrobe().addArmor("chainmail");
        user5.squad.setKnight(new Knight("zoro"));
        user5.squad.setMage(new Mage("conjurer"));
        user5.squad.getMage().getWoredrobe().addArmor("regalia");
        user5.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user5.squad.setHealer(new Healer("medic"));
        user5.squad.getHealer().getWoredrobe().addArmor("fleece");
        user5.squad.setMythicalCreature(new MythicalCreature("dragon"));
        user5.increaseXp(90);
        user5.decreaseMoney(100);
        users.put(user5.userName, user5);

        // Dummy 6
        User user6 = new User("Vesemir", "witcher", dummyUserNu++, "Hillcrest");
        user6.squad.setArcher(new Archer("sagittarius"));
        user6.squad.getArcher().getWoredrobe().addArmor("chainmail");
        user6.squad.setKnight(new Knight("swiftblade"));
        user6.squad.setMage(new Mage("eldritch"));
        user6.squad.getMage().getWoredrobe().addArmor("regalia");
        user6.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user6.squad.setHealer(new Healer("lifebringer"));
        user6.squad.getHealer().getWoredrobe().addArmor("fleece");
        user6.squad.getHealer().getWoredrobe().addArtefact("crystal");
        user6.squad.setMythicalCreature(new MythicalCreature("hydra"));
        user6.increaseXp(105);
        user6.increaseMoney(70);
        users.put(user6.userName, user6);

        // Dummy 7
        User user7 = new User("Ciri", "princessOfCintra", dummyUserNu++, "Hillcrest");
        user7.squad.setArcher(new Archer("ranger"));
        user7.squad.getArcher().getWoredrobe().addArmor("chainmail");
        user7.squad.setKnight(new Knight("templar"));
        user7.squad.setMage(new Mage("enchanter"));
        user7.squad.getMage().getWoredrobe().addArmor("fleece");
        user7.squad.setHealer(new Healer("saint"));
        user7.squad.getHealer().getWoredrobe().addArtefact("amulet");
        user7.squad.setMythicalCreature(new MythicalCreature("phoenix"));
        user7.increaseXp(20);
        user7.decreaseMoney(300);
        users.put(user7.userName, user7);

        // Dummy 8
        User user8 = new User("Eskel", "witcherEskil", dummyUserNu++, "Marshland");
        user8.squad.setArcher(new Archer("sunfire"));
        user8.squad.getArcher().getWoredrobe().addArmor("regalia");
        user8.squad.getArcher().getWoredrobe().addArtefact("crystal");
        user8.squad.setKnight(new Knight("cavalier"));
        user8.squad.setMage(new Mage("illusionist"));
        user8.squad.getMage().getWoredrobe().addArmor("fleece");
        user8.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user8.squad.setHealer(new Healer("alchemist"));
        user8.squad.getHealer().getWoredrobe().addArmor("fleece");
        user8.squad.getHealer().getWoredrobe().addArtefact("amulet");
        user8.squad.setMythicalCreature(new MythicalCreature("basilisk"));
        user8.increaseXp(135);
        user8.increaseMoney(700);
        users.put(user8.userName, user8);

        // Dummy 9
        User user9 = new User("Roach", "horse", dummyUserNu++, "Desert");
        user9.squad.setArcher(new Archer("zing"));
        user9.squad.getArcher().getWoredrobe().addArmor("chainmail");
        user9.squad.setKnight(new Knight("zoro"));
        user9.squad.setMage(new Mage("conjurer"));
        user9.squad.getMage().getWoredrobe().addArmor("regalia");
        user9.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user9.squad.setHealer(new Healer("medic"));
        user9.squad.getHealer().getWoredrobe().addArmor("fleece");
        user9.squad.setMythicalCreature(new MythicalCreature("dragon"));
        user9.increaseXp(150);
        user9.increaseMoney(400);
        users.put(user9.userName, user9);

        // Dummy 10
        User user10 = new User("ZoltanChivay", "dwarf", dummyUserNu++, "Arcane");
        user10.squad.setArcher(new Archer("sagittarius"));
        user10.squad.getArcher().getWoredrobe().addArmor("chainmail");
        user10.squad.setKnight(new Knight("swiftblade"));
        user10.squad.setMage(new Mage("eldritch"));
        user10.squad.getMage().getWoredrobe().addArmor("regalia");
        user10.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user10.squad.setHealer(new Healer("lifebringer"));
        user10.squad.getHealer().getWoredrobe().addArmor("fleece");
        user10.squad.getHealer().getWoredrobe().addArtefact("crystal");
        user10.squad.setMythicalCreature(new MythicalCreature("hydra"));
        user10.increaseXp(165);
        user10.decreaseMoney(100);
        users.put(user10.userName, user10);

        // Dummy 11
        User user11 = new User("TyrionLannister", "imp", dummyUserNu++, "Arcane");
        user11.squad.setArcher(new Archer("sagittarius"));
        user11.squad.getArcher().getWoredrobe().addArmor("chainmail");
        user11.squad.setKnight(new Knight("swiftblade"));
        user11.squad.setMage(new Mage("eldritch"));
        user11.squad.getMage().getWoredrobe().addArmor("regalia");
        user11.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user11.squad.setHealer(new Healer("lifebringer"));
        user11.squad.getHealer().getWoredrobe().addArmor("fleece");
        user11.squad.getHealer().getWoredrobe().addArtefact("crystal");
        user11.squad.setMythicalCreature(new MythicalCreature("hydra"));
        user11.increaseXp(180);
        user11.increaseMoney(200);
        users.put(user11.userName, user11);

        // Dummy 12
        User user12 = new User("AryaStark", "assassin", dummyUserNu++, "Marshland");
        user12.squad.setArcher(new Archer("sunfire"));
        user12.squad.getArcher().getWoredrobe().addArmor("regalia");
        user12.squad.getArcher().getWoredrobe().addArtefact("crystal");
        user12.squad.setKnight(new Knight("cavalier"));
        user12.squad.setMage(new Mage("illusionist"));
        user12.squad.getMage().getWoredrobe().addArmor("fleece");
        user12.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user12.squad.setHealer(new Healer("alchemist"));
        user12.squad.getHealer().getWoredrobe().addArmor("fleece");
        user12.squad.getHealer().getWoredrobe().addArtefact("amulet");
        user12.squad.setMythicalCreature(new MythicalCreature("basilisk"));
        user12.increaseXp(195);
        user12.decreaseMoney(300);
        users.put(user12.userName, user12);

        // Dummy 13
        User user13 = new User("JonSnow", "nightWatch", dummyUserNu++, "Hillcrest");
        user13.squad.setArcher(new Archer("zing"));
        user13.squad.getArcher().getWoredrobe().addArmor("chainmail");
        user13.squad.setKnight(new Knight("zoro"));
        user13.squad.setMage(new Mage("conjurer"));
        user13.squad.getMage().getWoredrobe().addArmor("regalia");
        user13.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user13.squad.setHealer(new Healer("medic"));
        user13.squad.getHealer().getWoredrobe().addArmor("fleece");
        user13.squad.setMythicalCreature(new MythicalCreature("dragon"));
        user13.increaseXp(210);
        user13.increaseMoney(400);
        users.put(user13.userName, user13);

        // Dummy 14
        User user14 = new User("SansaStark", "queenOfNorth", dummyUserNu++, "Arcane");
        user14.squad.setArcher(new Archer("sagittarius"));
        user14.squad.getArcher().getWoredrobe().addArmor("chainmail");
        user14.squad.setKnight(new Knight("swiftblade"));
        user14.squad.setMage(new Mage("eldritch"));
        user14.squad.getMage().getWoredrobe().addArmor("regalia");
        user14.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user14.squad.setHealer(new Healer("lifebringer"));
        user14.squad.getHealer().getWoredrobe().addArmor("fleece");
        user14.squad.getHealer().getWoredrobe().addArtefact("crystal");
        user14.squad.setMythicalCreature(new MythicalCreature("hydra"));
        user14.increaseXp(225);
        user14.decreaseMoney(500);
        users.put(user14.userName, user14);

        // Dummy 15
        User user15 = new User("BranStark", "raven", dummyUserNu++, "Desert");
        user15.squad.setArcher(new Archer("sunfire"));
        user15.squad.getArcher().getWoredrobe().addArmor("regalia");
        user15.squad.getArcher().getWoredrobe().addArtefact("crystal");
        user15.squad.setKnight(new Knight("cavalier"));
        user15.squad.setMage(new Mage("illusionist"));
        user15.squad.getMage().getWoredrobe().addArmor("fleece");
        user15.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user15.squad.setHealer(new Healer("alchemist"));
        user15.squad.getHealer().getWoredrobe().addArmor("fleece");
        user15.squad.getHealer().getWoredrobe().addArtefact("amulet");
        user15.squad.setMythicalCreature(new MythicalCreature("basilisk"));
        user15.increaseXp(240);
        user15.increaseMoney(600);
        users.put(user15.userName, user15);

        // Dummy 16
        User user16 = new User("JohnDoe", "human", dummyUserNu++, "Arcane");
        user16.squad.setArcher(new Archer("sagittarius"));
        user16.squad.getArcher().getWoredrobe().addArmor("chainmail");
        user16.squad.setKnight(new Knight("swiftblade"));
        user16.squad.setMage(new Mage("eldritch"));
        user16.squad.getMage().getWoredrobe().addArmor("regalia");
        user16.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user16.squad.setHealer(new Healer("lifebringer"));
        user16.squad.getHealer().getWoredrobe().addArmor("fleece");
        user16.squad.getHealer().getWoredrobe().addArtefact("crystal");
        user16.squad.setMythicalCreature(new MythicalCreature("hydra"));
        user16.increaseXp(255);
        user16.decreaseMoney(700);
        users.put(user16.userName, user16);

        // Dummy 17
        User user17 = new User("JaneSmith", "Somehuman", dummyUserNu++, "Arcane");
        user17.squad.setArcher(new Archer("sagittarius"));
        user17.squad.getArcher().getWoredrobe().addArmor("chainmail");
        user17.squad.setKnight(new Knight("swiftblade"));
        user17.squad.setMage(new Mage("eldritch"));
        user17.squad.getMage().getWoredrobe().addArmor("regalia");
        user17.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user17.squad.setHealer(new Healer("lifebringer"));
        user17.squad.getHealer().getWoredrobe().addArmor("fleece");
        user17.squad.getHealer().getWoredrobe().addArtefact("crystal");
        user17.squad.setMythicalCreature(new MythicalCreature("hydra"));
        user17.increaseXp(270);
        user17.increaseMoney(800);
        users.put(user17.userName, user17);

        // Dummy 18
        User user18 = new User("AliceJohnson", "sweeny", dummyUserNu++, "Marshland");
        user18.squad.setArcher(new Archer("sunfire"));
        user18.squad.getArcher().getWoredrobe().addArmor("regalia");
        user18.squad.getArcher().getWoredrobe().addArtefact("crystal");
        user18.squad.setKnight(new Knight("cavalier"));
        user18.squad.setMage(new Mage("illusionist"));
        user18.squad.getMage().getWoredrobe().addArmor("fleece");
        user18.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user18.squad.setHealer(new Healer("alchemist"));
        user18.squad.getHealer().getWoredrobe().addArmor("fleece");
        user18.squad.getHealer().getWoredrobe().addArtefact("amulet");
        user18.squad.setMythicalCreature(new MythicalCreature("basilisk"));
        user18.increaseXp(285);
        user18.decreaseMoney(900);
        users.put(user18.userName, user18);

        // Dummy 19
        User user19 = new User("RobertJohnson", "robert", dummyUserNu++, "Hillcrest");
        user19.squad.setArcher(new Archer("zing"));
        user19.squad.getArcher().getWoredrobe().addArmor("chainmail");
        user19.squad.setKnight(new Knight("zoro"));
        user19.squad.setMage(new Mage("conjurer"));
        user19.squad.getMage().getWoredrobe().addArmor("regalia");
        user19.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user19.squad.setHealer(new Healer("medic"));
        user19.squad.getHealer().getWoredrobe().addArmor("fleece");
        user19.squad.setMythicalCreature(new MythicalCreature("dragon"));
        user19.increaseXp(300);
        user19.increaseMoney(1000);
        users.put(user19.userName, user19);

        // Dummy 20
        User user20 = new User("EmilyDavis", "davis", dummyUserNu++, "Arcane");
        user20.squad.setArcher(new Archer("sagittarius"));
        user20.squad.getArcher().getWoredrobe().addArmor("chainmail");
        user20.squad.setKnight(new Knight("swiftblade"));
        user20.squad.setMage(new Mage("eldritch"));
        user20.squad.getMage().getWoredrobe().addArmor("regalia");
        user20.squad.getMage().getWoredrobe().addArtefact("excalibur");
        user20.squad.setHealer(new Healer("lifebringer"));
        user20.squad.getHealer().getWoredrobe().addArmor("fleece");
        user20.squad.getHealer().getWoredrobe().addArtefact("crystal");
        user20.squad.setMythicalCreature(new MythicalCreature("hydra"));
        user20.increaseXp(315);
        user20.decreaseMoney(1100);
        users.put(user20.userName, user20);

        return users;
    }
}