package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] code = generateSecretCode();
        if (code.length != 0) {
            bullGame(code);
        }

    }

    public static String[] generateSecretCode() {
        Scanner scanner = new Scanner(System.in);
        char[] symbols = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
        Random random = new Random();
        StringBuilder secretCode = new StringBuilder();
        System.out.println("Input the length of the secret code:");
        int len = 0;
        int numOfSymbols = 0;
        try {
            len = scanner.nextInt();
        } catch (Exception e) {
            System.out.format("Error: '%s' isn't a valid number.", len);
            return new String[0];

        }

        try {
            System.out.println("Input the number of possible symbols in the code:");
            numOfSymbols = scanner.nextInt();
        } catch (Exception e) {
            System.out.format("Error: '%s' isn't a valid number.", numOfSymbols);
            return new String[0];

        }
        if (numOfSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return new String[0];

        }

        if (len == 0) {
            System.out.println("Error: The length can't be zero.");
            return new String[0];
        }

        if (len > numOfSymbols) {
            System.out.format("Error: it's not possible to generate a code with a length of %s with %s unique symbols.", len, numOfSymbols);
            return new String[0];
        }

        System.out.println("The secret is prepared: " + "*".repeat(Math.max(0, len)) + " (0-9, " + "a-" + symbols[numOfSymbols - 1] + ").");

        if (len < 36) {
            while (secretCode.length() != len) {
                int randoSymbol = random.nextInt(numOfSymbols);
                if (secretCode.indexOf(String.valueOf(symbols[randoSymbol])) == -1) {
                    secretCode.append(symbols[randoSymbol]);
                }
            }
        }

        System.out.println("Okay, let's start a game!");
        return secretCode.toString().split("");
    }

    public static void bullGame(String[] code) {
        Scanner scanner = new Scanner(System.in);
        String strCode = Arrays.toString(code);
        int bullCount = 0;
        int cowCount;
        int turnCount = 1;
        while (bullCount != code.length) {
            bullCount = 0;
            cowCount = 0;
            System.out.println("Turn " + turnCount + ":");
            String[] input = scanner.next().split("");
            for (int i = 0; i < code.length; i++) {
                if (input[i].equals(code[i])) {
                    List<String> list = new ArrayList<>(Arrays.asList(input));
                    if (list.stream().distinct().count() == 1) {
                        bullCount = 1;
                    } else {
                        bullCount++;
                    }

                } else if (strCode.contains(input[i])) {
                    cowCount++;
                }
            }

            if (bullCount > 0 && cowCount > 0) {
                System.out.println("Grade: " + bullCount + " bull(s) and " + cowCount + " cow(s).");
            } else if (bullCount > 0 && cowCount == 0) {
                System.out.println("Grade: " + bullCount + " bull(s).");
            } else if (bullCount == 0 && cowCount > 0) {
                System.out.println("Grade: " + cowCount + " cow(s).");
            } else {
                System.out.println("Grade: None.");
            }
            turnCount++;

        }
        System.out.println("Congratulations! You guessed the secret code.");
    }
}
