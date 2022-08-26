package view.spot.finder.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputValidatorTest {
    @Test
    void isValidInput() {
        String[] input = {"foo", "1"};

        boolean result = InputValidator.validate(input);

        Assertions.assertTrue(result);
    }

    @Test
    void noNumberInput() {
        String[] input = {"foo", "bar"};

        boolean result = InputValidator.validate(input);

        Assertions.assertFalse(result);
    }

    @Test
    void negativeNumberInput() {
        String[] input = {"foo", "-1"};

        boolean result = InputValidator.validate(input);

        Assertions.assertFalse(result);
    }

    @Test
    void arrayTooSmall() {
        String[] input = {"foo"};

        boolean result = InputValidator.validate(input);

        Assertions.assertFalse(result);
    }

    @Test
    void arrayTooBig() {
        String[] input = {"f", "oo", "bar"};

        boolean result = InputValidator.validate(input);

        Assertions.assertFalse(result);
    }

}
