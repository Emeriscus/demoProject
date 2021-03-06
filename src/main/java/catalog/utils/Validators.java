package catalog.utils;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

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
            return false;
        }
    }

    public static boolean isValidYear(int year) {
        return year >= 1000 && year <= LocalDate.now().getYear();
    }

    public static boolean isValidQuantity(int quantity) {
        return quantity >= 0;
    }

    public static boolean isValidEmail(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=().])(?=\\S+$).{8,20}$";
        Pattern pat = Pattern.compile(passwordRegex);
        if (password == null) {
            return false;
        }
        return pat.matcher(password).matches();
    }

    public static boolean isValidAudio
            (String title, List<String> performers, List<String> composers, int yearOfPublication, int quantity) {
        if (isBlank(title)) {
            throw new IllegalArgumentException("title cannot be empty");
        }
        if (isEmpty(performers) || isEmpty(composers)) {
            throw new IllegalArgumentException("performers and composers cannot be empty");
        }
        if (!isValidYear(yearOfPublication)) {
            throw new IllegalArgumentException("The year must be between 1000 and the current year");
        }
        if (!isValidQuantity(quantity)) {
            throw new IllegalArgumentException("The quantity must not above 0");
        }
        return true;
    }

    public static boolean isValidBook(String title, List<String> authors, int yearOfPublication, int quantity) {
        if (isBlank(title)) {
            throw new IllegalArgumentException("title cannot be empty");
        }
        if (isEmpty(authors)) {
            throw new IllegalArgumentException("The authors cannot be empty");
        }
        if (!isValidYear(yearOfPublication)) {
            throw new IllegalArgumentException("The year must be between 1000 and the current year");
        }
        if (!isValidQuantity(quantity)) {
            throw new IllegalArgumentException("The quantity must not above 0");
        }
        return true;
    }
}
