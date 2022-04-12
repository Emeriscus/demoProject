package catalog.utils;

import org.junit.jupiter.api.Test;

import static catalog.utils.PasswordGenerator.*;
import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    @Test
    void generateAndVerifySecurePasswordTest() {

        String password = "aaiawsduhSDF@.4535";
        String salt = generateSalt();
        String storedSecurePassword = generateSecurePassword(password, salt);

        String providedPassword = "aaiawsduhSDF@.4535";
        String newSecurePassword = generateSecurePassword(providedPassword, salt);

        assertEquals(newSecurePassword, storedSecurePassword);
        assertTrue(verifyUserPassword(providedPassword, storedSecurePassword, salt));
    }
}