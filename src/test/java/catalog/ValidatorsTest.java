package catalog;

import org.junit.jupiter.api.Test;

import java.util.List;

import static catalog.Validators.isBlank;
import static catalog.Validators.isEmpty;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidatorsTest {

    @Test
    public void testIsBlank() {
        assertTrue(isBlank(null));
        assertTrue(isBlank(""));
        assertTrue(isBlank("    "));
        assertFalse(isBlank("a"));
        assertFalse(isBlank("     a       "));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(isEmpty(null));
        assertTrue(isEmpty(List.of()));
        assertFalse(isEmpty(List.of("aaa")));
        assertFalse(isEmpty(List.of("aaa", "bbb")));
    }
}