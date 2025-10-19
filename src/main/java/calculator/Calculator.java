package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public static final String defaultDelimiter = ",:";


    public String[] separateString(String input) {

        String delimiter = "[,:]";
        String number = input;

        if(input.startsWith("\\")) {
            Pattern pattern = Pattern.compile("//(.)\n(.*)");
            Matcher matcher = pattern.matcher(input);

            String[] customDelimiter = verificationMatcher(matcher, delimiter, number);

            delimiter = Pattern.quote(customDelimiter[0]);
            number = customDelimiter[1];
        }

        return number.split(delimiter);
    }

    public String[] verificationMatcher(Matcher matcher, String delimiter, String number) {
        if(matcher.find()) {
            delimiter = matcher.group(0);
            number = matcher.group(1);
        }

        return new String[]{delimiter, number};
    }

    public int calculateSum(String input) {

        int sum = 0;

        String[] separatedString = separateString(input);

        for (String s : separatedString) {

            if(!s.isEmpty())
                sum += Integer.parseInt(s);

        }

        return sum;
    }


}
