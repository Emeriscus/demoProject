package catalog;

import catalog.utils.PasswordUtils;

import java.security.SecureRandom;
import java.util.Arrays;

public class PasswordMain {

    public static void main(String[] args) {

        String myPassword = "kaka";

        // Generate Salt. The generated value can be stored in DB.
        String salt = PasswordUtils.getSalt();

        // Protect user's password. The generated value can be stored in DB.
        String mySecurePassword = PasswordUtils.generateSecurePassword(myPassword, salt);

        // Print out protected password
        System.out.println("My secure password = " + mySecurePassword);
        System.out.println("Salt value = " + salt);
        System.out.println("---------------------------------------");

        String providedPassword = "myPassword123";

        // Encrypted and Base64 encoded password read from database
        String storedSecurePassword = "HhaNvzTsVYwS/x/zbYXlLOE3ETMXQgllqrDaJY9PD/U=";

        // Salt value stored in database
        String storedSalt = "EqdmPh53c9x33EygXpTpcoJvc4VXLK";

        boolean passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, storedSecurePassword, storedSalt);

        if (passwordMatch) {
            System.out.println("Provided user password " + providedPassword + " is correct.");
        } else {
            System.out.println("Provided password is incorrect");
        }
    }
}
