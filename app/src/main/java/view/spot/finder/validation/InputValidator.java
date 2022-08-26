package view.spot.finder.validation;

public class InputValidator {

    public static boolean validate(String[] input) {
        if(input.length < 2) {
            System.err.printf("Too few arguments provided. Necessary: 2, provided: %s", input.length);
            return false;
        }
        if(input.length > 2) {
            System.err.printf("Too many arguments provided. Necessary: 2, provided: %s", input.length);
            return false;
        }

        int numberOfViewSpots;
        try {
            numberOfViewSpots = Integer.parseInt(input[1]);

        } catch (NumberFormatException e) {
            System.err.printf("Invalid number of view spots provided: %s! Please make sure to provide a positive integer!", input[1]);
            return false;
        }

        if (numberOfViewSpots < 0) {
            System.err.printf("Invalid number of view spots provided: %s! Please make sure to provide a positive integer!", input[1]);
            return false;
        }
        return true;
    }

}
