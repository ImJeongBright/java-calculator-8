package calculator.view;

public class OutputView {

    public static void intro() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
    }

    public static void result(int calculatedNumber) {
        System.out.println("결과 : " + calculatedNumber);
    }


}
