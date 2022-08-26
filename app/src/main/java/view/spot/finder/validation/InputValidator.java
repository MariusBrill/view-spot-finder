package view.spot.finder.validation;

/**
 * Utility class for validating user inputs.
 */
public class InputValidator {

    /**
     * Takes a String array representing a user input. Returns true if the input is valid. An input array is valid if it
     * contains exactly two elements. The first element can be any instance of {@link java.lang.String}. The second element
     * needs to be a positive integer.
     * @param input A String array as described above.
     * @return Returns true if the array fulfills the criteria described above.
     */
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
