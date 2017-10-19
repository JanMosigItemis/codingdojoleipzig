package de.itemis.mosig.dojo.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Shows lifecycle implications when using nested tests and JUnit5 lifecycle modifications. Have a look at stdout in order to comprehend the call hierarchy and
 * member lifecycle.
 */
public class NestedTestExample {
    private RomanConverter underTest;

    /**
     * Usually non static members get destroyed before every test because JUnit instantiates the class before every test.
     */
    private int i = 0;

    @BeforeEach
    public void setUp() {
        this.underTest = new RomanConverter();
        System.out.println("Main Test Instance setUp");
        i++;
        System.out.println("Main Test Instance Value of i: " + i);
    }

    @Test
    public void testSomething() {
        String result = underTest.convert(i);
        Assertions.assertNotNull(result);
    }

    /**
     * The nested class is only instantiated once, i. e. all tests are run on the same instance.
     */
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class NestedTest {

        @BeforeEach
        public void setUp() {
            System.out.println("Nested Test Instance setUp");
        }

        @Test
        public void nestedTestSomething() {
            String result = underTest.convert(1666);
            assertEquals("MDCLXVI", result);
        }

        @Test
        public void nestedTestSomethingDifferent() {
            String result = underTest.convert(10);
            assertEquals("X", result);
        }

        /**
         * This class has normal JUnit lifecycle, i. e. it is instantiated every time before each test.
         */
        @Nested
        class NestedNestedTest {

            @BeforeEach
            public void setUp() {
                System.out.println("NestedNestedTest Instance setUp");
            }

            @RepeatedTest(10)
            public void nestedNestedTestSomething() {
                String result = underTest.convert(5);
                assertEquals("V", result);
            }
        }
    }
}
