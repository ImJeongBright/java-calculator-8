package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("빈 문자열 또는 null을 입력하면 0을 반환한다")
    void calculate_empty_or_null_string() {
        assertThat(calculator.calculateSum("")).isEqualTo(0);
        assertThat(calculator.calculateSum(null)).isEqualTo(0);
    }

    @Test
    @DisplayName("숫자 하나만 입력하면 해당 숫자를 반환한다")
    void calculate_single_number() {
        assertThat(calculator.calculateSum("5")).isEqualTo(5);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "'1,2:3', 6",
            "'1,2,3', 6",
            "'5:5', 10"
    })
    @DisplayName("기본 구분자(쉼표, 콜론)로 분리된 숫자의 합을 반환한다")
    void calculate_with_default_delimiters(String input, int expected) {
        assertThat(calculator.calculateSum(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("커스텀 구분자를 사용하여 합계를 계산한다")
    void calculate_with_custom_delimiter() {
        String input = "//;\n1;2;3";
        assertThat(calculator.calculateSum(input)).isEqualTo(6);
    }

    @Test
    @DisplayName("음수가 포함된 경우 예외가 발생한다")
    void calculate_with_negative_number_throws_exception() {
        String input = "1,-2,3";
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateSum(input);
        });
    }

    @Test
    @DisplayName("숫자가 아닌 문자가 포함된 경우 예외가 발생한다")
    void calculate_with_non_numeric_throws_exception() {
        String input = "1,a,2";
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateSum(input);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;1;2;3", "//\n1;2;3"})
    @DisplayName("커스텀 구분자 형식이 잘못된 경우 예외가 발생한다")
    void calculate_with_invalid_format_throws_exception(String input) {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateSum(input);
        });
    }
}