package de.itemis.mosig.dojo.junit5.dynamic;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Static helper that reads in a textfile via classloader and parses its content into a {@link Stream} of {@link ParsedTestRule} objects. Throws {@link
 * RuntimeException} if something goes wrong.
 *
 * Inspired by an original written by (c) Stefan Ludwig
 *
 * @see <a href="https://blog.novatec-gmbh.de/junit-5-parameter-resolver">https://blog.novatec-gmbh.de/junit-5-parameter-resolver</a>
 */
public final class TestRuleParser {

    public static final char EXPECTED_OUTPUT_SEPARATOR = '|';
    public static final char INPUT_SEPARATOR = ',';

    private TestRuleParser() {
    }

    public static Stream<ParsedTestRule> parseRuleFile(String ruleFileResourceName) {
        Stream<ParsedTestRule> result = new ArrayList<ParsedTestRule>().stream();

        try (InputStream resourceStream = TestRuleParser.class.getClassLoader().getResourceAsStream(ruleFileResourceName)) {
            if (resourceStream == null) {
                throw new RuntimeException("Could not find rule file resource '" + ruleFileResourceName + "'.");
            } else {
                result = readLines(resourceStream).stream().map(TestRuleParser::parseRule);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading test rule resource.", e);
        }

        return result;
    }

    public static List<String> readLines(InputStream inputStream) {
        List<String> result = new ArrayList<>();
        CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, decoder));

        String currentLine = null;
        try {
            while ((currentLine = reader.readLine()) != null) {
                result.add(currentLine);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read from stream.", e);
        } finally {
            try {
                reader.close();
                inputStream.close();
            } catch (IOException e) {
                System.err.println("Could not close resource: " + e.getMessage());
            }
        }
        return result;
    }

    private static ParsedTestRule parseRule(String rule) {
        String[] splittedRuleLine = Iterables.toArray(Splitter.on(EXPECTED_OUTPUT_SEPARATOR).split(rule), String.class);
        String unsplittedInputAndColumnNbr = splittedRuleLine[0];
        String[] splittedInputAndColumnNbr = Iterables.toArray(Splitter.on(INPUT_SEPARATOR).split(unsplittedInputAndColumnNbr), String.class);

        String input = splittedInputAndColumnNbr[0];
        String rawColumnNbr = splittedInputAndColumnNbr[1];
        Integer columnNbr = null;
        try {
            columnNbr = Integer.valueOf(rawColumnNbr.trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Could not read columnNbr '" + rawColumnNbr + "'.", e);
        }
        String rawExpectedResult = splittedRuleLine[1].trim();

        String expectedResult = rawExpectedResult.replaceAll("(\\\\n)+", "\n");
        return new ParsedTestRule(input, columnNbr, expectedResult);
    }
}