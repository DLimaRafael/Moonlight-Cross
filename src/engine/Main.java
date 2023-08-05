package engine;

import java.util.Scanner;

import battle.Battle;

public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Render.printTitle();
        Render.printSpacer(3);
        System.out.println("Which feature do you wish to test?");
        System.out.println("1. Battle");
        System.out.println("2. Exploration");
        System.out.println("3. Quit");
        int input = InputHandler.readInt("> ", 3);
        switch (input) {
            case 1:
                Battle battle = new Battle();
                battle.battleStart();
                break;
            case 2:
                System.out.println("Not ready yet.");
                scanner.next();
                break;
            case 3:
                break;
        }

        scanner.close();
    }
}
