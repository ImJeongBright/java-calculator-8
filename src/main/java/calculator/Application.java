package calculator;

import static calculator.view.InputView.inputString;
import static calculator.view.OutputView.*;

public class Application {

    public static void main(String[] args) {

        String inputString = inputString();
        Calculator calculator = new Calculator();

        intro();
        result(calculator.calculateSum(inputString));
    }


}
