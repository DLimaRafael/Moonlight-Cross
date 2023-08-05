package engine;

import java.util.Scanner;

public final class InputHandler {
    static Scanner scanner = new Scanner(System.in);

    /*** Function for reading user input when presented with numbered choices. ***/
    public static int readInt (String prompt, int choices) {
        int input;

        do {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                input = -1;
                System.out.println("Invalid command.");
                scanner.nextLine();
            }
        } while (input < 1 || input > choices);
        return input;
    }

    /*** Function for reading user input when presented with choices that must be written. ***/
    public static String readString (String prompt, String[] choices) {
        String input;

        do {
            System.out.print(prompt);
            try {
                input = scanner.next();
            } catch (Exception e) {
                input = "";
                System.out.println("Invalid command.");
                scanner.nextLine();
            }
        } while (!isInChoices(input, choices));
        return input;
    }

    private static boolean isInChoices(String input, String[] choices) {
        for (String choice : choices) {
            if (input.equalsIgnoreCase(choice)) {
                return true;
            }
        }
        return false;
    }
}
