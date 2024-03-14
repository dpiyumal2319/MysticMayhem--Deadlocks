package bin;

public abstract class UIElements {
    // Text colors
    public static int timeBetween = 50;
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String PINK = "\u001B[95m";
    public static final String BOLD = "\u001B[1m";

    // Background colors
    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String MAGENTA_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";
    public static final String PINK_BACKGROUND = "\u001B[105m";

    // StringSet
    public static final String rightArrowSet = RED + " >> >> >> >> >> >> >> " + RESET;
    public static final String hearsSet = PINK + " <3  >>  <3  >>  <3  >>  <3 " + RESET;

    public static String RedArrows(float damage) {
        return RED + " >> >> >> " + "[-" + String.format("%.1f", damage) + "]" + " >> >> >> " + RESET;
    }

    public static String Hearts(float heal) {
        return PINK + " <3 >> <3 >> " + "[+" + String.format("%.1f", heal) + "]" + " >> <3 >> <3 " + RESET;
    }



    public static void printLogo() {
        System.out.println(
                "  __  __                 _     _            __  __                   _                        ");
        System.out.println(
                " |  \\/  |               | |   (_)          |  \\/  |                 | |                       ");
        System.out.println(
                " | \\  / |  _   _   ___  | |_   _    ___    | \\  / |   __ _   _   _  | |__     ___   _ __ ___  ");
        System.out.println(
                " | |\\/| | | | | | / __| | __| | |  / __|   | |\\/| |  / _` | | | | | | '_ \\   / _ \\ | '_ ` _ \\ ");
        System.out.println(
                " | |  | | | |_| | \\__ \\ | |_  | | | (__    | |  | | | (_| | | |_| | | | | | |  __/ | | | | | |");
        System.out.println(
                " |_|  |_|  \\__, | |___/  \\__| |_|  \\___|   |_|  |_|  \\__,_|  \\__, | |_| |_|  \\___| |_| |_| |_|");
        System.out.println(
                "            __/ |                                             __/ |                           ");
        System.out.println(
                "           |___/                                             |___/                            "
                        + "-THE DEADLOCKS-");
        System.out.println("\n\n");
    }

    public static void clearTerminal() {
        // Use ANSI escape codes to clear the terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public static void printShopBanner(String userName) {
        String storeWelcomeMsg = "Welcome to the store " + userName + "!";
        // ANSI escape sequence for bold text
        String bold = "\033[1m";

        // ANSI escape sequence for resetting text style
        String reset = "\033[0m";

        // Print top frame line
        System.out.println(bold + "+" + "-".repeat(storeWelcomeMsg.length() + 4) + "+" + reset);

        // Print storeWelcomeMsg with left and right frame lines
        System.out.println(bold + "|  " + storeWelcomeMsg + "  |" + reset);

        // Print bottom frame line
        System.out.println(bold + "+" + "-".repeat(storeWelcomeMsg.length() + 4) + "+" + reset + '\n');

        System.out.println("\n\n");
    }
}