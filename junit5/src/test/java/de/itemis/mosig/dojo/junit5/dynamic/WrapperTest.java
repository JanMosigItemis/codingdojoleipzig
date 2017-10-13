package de.itemis.mosig.dojo.junit5.dynamic;

import de.itemis.mosig.dojo.junit5.Wrapper;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the class Wrapper. Tests are written in a custom mini DSL. The test rules are loaded at runtime from a text file and interpreted by the help of JUnit5's
 * TestFactory feature.
 *
 * Inspired by an original written by (c) Stefan Ludwig
 *
 * @see <a href="https://blog.novatec-gmbh.de/junit-5-parameter-resolver">https://blog.novatec-gmbh.de/junit-5-parameter-resolver</a>
 */
public class WrapperTest {

    private static final String TEST_RULES_RESOURCE_NAME = "rules.txt";

    /**
     * Read the rules and generate a dynamic test at runtime for each read rule line.
     *
     * @return A {@link Stream} of {@link DynamicTest} objects corresponding to the contents of the test rule file.
     */
    @TestFactory
    public Stream<DynamicTest> generateTests() {
        Stream<ParsedTestRule> parsedRules = TestRuleParser.parseRuleFile(TEST_RULES_RESOURCE_NAME);
        Stream<DynamicTest> result = parsedRules.map(rule -> {
            String testName = constructTestNameForRule(rule);
            Executable testCase = constructTestCaseForRule(rule);
            return DynamicTest.dynamicTest(testName, testCase);
        });

        return result;
    }

    private Executable constructTestCaseForRule(ParsedTestRule testRule) {
        return () -> {
            String result = Wrapper.wrap(testRule.getInput(), testRule.getColumnSize());
            assertEquals(testRule.getExpectedResult(), result);
        };
    }

    private String constructTestNameForRule(ParsedTestRule testRule) {
        return "wrapper.wrap('" + testRule.getInput() + "'," + testRule.getColumnSize()
                + ") == '" + testRule.getExpectedResult() + "'";
    }
}
