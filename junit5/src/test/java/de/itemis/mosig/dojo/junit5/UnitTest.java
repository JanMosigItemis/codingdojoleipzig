package de.itemis.mosig.dojo.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitTest {

    @DisplayName("Huhu")
    @RepeatedTest(10)
    public void testMe() {
    }

    @Test
    public void groupedAssertions() {
        assertAll(() -> Assertions.assertTrue(true, () -> "first"), () -> assertTrue(true, () -> "second"));
    }

    @Test
    public void assertExceptions() {
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException();
        });
    }

    @Test
    public void assertTimeout() {
        boolean result = assertTimeoutPreemptively(Duration.ofSeconds(2), () -> true);

        assertTrue(result);
    }
}
