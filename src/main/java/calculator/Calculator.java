package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private String[] separateString(String input) {
        String processedInput = input.replace("\\n", "\n");

        String delimiter = "[,:]";
        String numbersText = processedInput;

        if (processedInput.startsWith("//")) {
            Pattern pattern = Pattern.compile("//(.)\n(.*)");
            Matcher matcher = pattern.matcher(processedInput);

            String[] resultFromMatcher = checkMatcher(matcher, delimiter, numbersText);

            delimiter = Pattern.quote(resultFromMatcher[0]);
            numbersText = resultFromMatcher[1];
        }

        return numbersText.split(delimiter);
    }

    private String[] checkMatcher(Matcher matcher, String delimiter, String number) {
        if (matcher.find()) {
            delimiter = matcher.group(1);
            number = matcher.group(2);
        } else {
            throw new IllegalArgumentException();
        }
        return new String[]{delimiter, number};
    }

    public int calculateSum(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        int sum = 0;
        String[] separatedString = separateString(input);

        for (String s : separatedString) {
            if (s.isEmpty()) {
                continue;
            }
            try {
                int number = Integer.parseInt(s);
                validateNumber(number);
                sum += number;
            } catch (NumberFormatException n) {
                throw new IllegalArgumentException();
            }
        }
        return sum;
    }

    public void validateNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException();
        }
    }
}