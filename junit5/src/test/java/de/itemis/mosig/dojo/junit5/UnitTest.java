package de.itemis.mosig.dojo.junit5;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Shows various minor new features of JUnit5
 */
public class UnitTest {

    @DisplayName("I have a totally different name")
    public void test123() {
    }

    @RepeatedTest(10)
    public void testRepeated() {
        System.out.println("testRepeated");
    }

    @Test
    public void groupedAssertions() {
        RomanConverter underTest = new RomanConverter();

        assertAll("converter",
                () -> assertEquals("D", underTest.convert(500), "Convert D"),
                () -> assertEquals("M", underTest.convert(1000), "Convert M"),
                () -> assertEquals("VI", underTest.convert(6), "Convert VI"));
    }

    @Test
    public void assertExceptions() {
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException();
        });
    }

    @Test
    public void assertTimeoutPreempt() {
        boolean result = assertTimeoutPreemptively(Duration.ofSeconds(2), () -> true);

        assertTrue(result);
    }

    @Disabled
    @Test
    public void iHaveBeenDisabled() {
        assert (false);
    }

    @ParameterizedTest
    @CsvSource({"500, D", "1000, M", "6, VI"})
    public void shouldConvertMultipleWithParameterSource(int input, String expectedResult) {
        String actualResult = new RomanConverter().convert(input);
        assertEquals(expectedResult, actualResult, "Encountered unexpected convert result.");
    }

    @Test
    public void onlyRunThisOnCIEnvironment() {
        String result = new RomanConverter().convert(166);

        Assumptions.assumingThat(System.getenv("CI") != null, () ->
                assertEquals("CLXVI", result)
        );
    }

    /**
     * E. g. with surefire:
     *
     * <pre>
     *  &lt;plugin&gt;
     *       &lt;artifactId&gt;maven-surefire-plugin&lt;/artifactId&gt;
     *       &lt;version&gt;2.19.1&lt;/version&gt;
     *       &lt;configuration&gt;
     *           &lt;properties&gt;
     *               &lt;includeTags&gt;fast&lt;/includeTags&gt;
     *           &lt;/properties&gt;
     *      &lt;/configuration&gt;
     * </pre>
     */
    @Tag("fast")
    @Test
    public void taggedTest() {
        String result = new RomanConverter().convert(666);
        assertEquals("DCLXVI", result);
    }
}
