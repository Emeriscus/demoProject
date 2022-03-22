package catalog;

import java.util.InputMismatchException;
import java.util.List;

public class Validators {

    public static boolean isBlank(String text) {
        return text == null || text.isBlank();
    }

    public static boolean isEmpty(List<String> stringList) {
        return stringList == null || stringList.isEmpty();
    }

    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException nfe) {
            throw new IllegalStateException("Not number", nfe);
        }
    }
}
