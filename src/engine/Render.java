package engine;

public final class Render {
    public static void printSeparator(char separator, int count) {
        for (int i=0; i < count; i++) {
            System.out.print(separator);
        }
        System.out.println();
    }

    public static void printSeparator(char separator) {
        for (int i=0; i < 20; i++) {
            System.out.print(separator);
        }
        System.out.println();
    }

    public static void printSpacer(int count) {
        for (int i=0;i<count;i++)
        System.out.println();
    }

    public static void printTitle() {
        printSeparator('=', 40);
        System.out.println("      M O O N L I G H T  C R O S S");
        printSeparator('=', 40);
        System.out.println("          Mist Covers the Moon");
        System.out.println("       Yet You Stand Untarnished");
        System.out.println("             By Its Embrace");
    }

    public static void clearScreen() {
        for (int i=0; i<100; i++) {
            System.out.println();
        }
    }
}