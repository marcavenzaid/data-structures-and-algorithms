package marcavenzaid.lib.asker;

import java.util.Scanner;

public final class Asker {

    private static Scanner sc = new Scanner(System.in);

    public static String askString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    public static int askInt(String msg) throws Exception {
        System.out.print(msg);
        String input = sc.nextLine();
        if (isValidNumericInput(input)) {
            return Integer.parseInt(input);
        }
        throw new Exception("Invalid input!");
    }

    public static int askInt(String msg, int min, int max) throws Exception {
        System.out.print(msg);
        String input = sc.nextLine();
        if (isValidNumericInput(input)) {
            int parsedInput = Integer.parseInt(input);
            if (parsedInput < min || parsedInput > max) {
                throw new Exception("Invalid input! Input should be between " + min + " [inclusive] and " + max
                        + " [inclusive].");
            }
            return parsedInput;
        }
        throw new Exception("Invalid input!");
    }

    public static boolean isValidIndex(int val) throws IndexOutOfBoundsException {
        if (val >= 0) {
            return true;
        }
        throw new IndexOutOfBoundsException("Invalid index! Index should be greater than 0.");
    }

    public static boolean isValidIndex(int val, int max) throws IndexOutOfBoundsException {
        if (val >= 0 && val <= max){
            return true;
        }
        throw new IndexOutOfBoundsException("Invalid index! Index should be between 0 [inclusive] and " + max
                + " [inclusive].");
    }

    private static boolean isValidNumericInput(String inputStr) {
        boolean isValid = true;
        int i = 0;
        if (inputStr.length() > 1 && (inputStr.charAt(0) == '-' || inputStr.charAt(0) == '+')) {
            i = 1;
        }
        for (; i < inputStr.length(); ++i) {
            if (!Character.isDigit(inputStr.charAt(i))) {
                isValid = false;
            }
        }
        return isValid;
    }

    public static void main(String[] args) {
        try {
            String s = Asker.askString("Enter a string: ");
            System.out.println("You entered: " + s);

            int i = Asker.askInt("Enter an integer: ");
            System.out.println("You entered: " + i);

            int j = Asker.askInt("Enter an integer between 1 and 10: ", 1, 10);
            System.out.println("You entered: " + j);

            boolean isValidIndex = Asker.isValidIndex(5);
            System.out.println("Is valid index: " + isValidIndex);

            boolean isValidIndex2 = Asker.isValidIndex(5, 10);
            System.out.println("Is valid index: " + isValidIndex2);

            boolean isValidNumericInput = Asker.isValidNumericInput("12345");
            System.out.println("Is valid numeric input: " + isValidNumericInput);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
