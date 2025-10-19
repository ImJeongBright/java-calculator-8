package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {


    private String[] separateString(String input) {

        String delimiter = "[,:]";
        String number = input;

        if(input.startsWith("//")) {
            Pattern pattern = Pattern.compile("//(.)\n(.*)");
            Matcher matcher = pattern.matcher(input);

            String[] customDelimiter = checkMatcher(matcher, delimiter, number);

            delimiter = Pattern.quote(customDelimiter[0]);
            number = customDelimiter[1];
        }

        return number.split(delimiter);
    }

    private String[] checkMatcher(Matcher matcher, String delimiter, String number) {
        if(matcher.find()) {
            delimiter = matcher.group(1);
            number = matcher.group(2);
        }

        else if(!matcher.find()) throw new IllegalArgumentException();

        return new String[]{delimiter, number};
    }

    public int calculateSum(String input) {

        if (input == null || input.isEmpty()) {
            return 0;
        }

        int sum = 0;

        String[] separatedString = separateString(input);

        for (String s : separatedString) {

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
        if(number < 0) throw new IllegalArgumentException();
    }


}
