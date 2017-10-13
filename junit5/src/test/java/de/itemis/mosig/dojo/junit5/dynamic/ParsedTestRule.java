package de.itemis.mosig.dojo.junit5.dynamic;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * Java object representation of a parsed line of test rules.
 */
public final class ParsedTestRule {

    private final String input;
    private final int columnSize;
    private final String expectedResult;

    /**
     * Construct new instance.
     *
     * @param input          must not be {@code null} or empty
     * @param columnSize     must be > 0
     * @param expectedResult must not be {@code null} or empty
     */
    public ParsedTestRule(String input, int columnSize, String expectedResult) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(input));
        this.input = input;
        Preconditions.checkArgument(columnSize > 0);
        this.columnSize = columnSize;
        Preconditions.checkArgument(!Strings.isNullOrEmpty(expectedResult));
        this.expectedResult = expectedResult;
    }

    public String getInput() {
        return input;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public String getExpectedResult() {
        return expectedResult;
    }
}
