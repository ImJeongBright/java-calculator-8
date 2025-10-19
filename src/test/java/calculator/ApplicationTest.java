package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {

    @Test
    void 숫자_하나_입력() {
        run("5");
        assertThat(output()).contains("결과 : 5");
    }

    @Test
    void 기본_구분자_계산() {
        run("1,2:3");
        assertThat(output()).contains("결과 : 6");
    }

    @Test
    void 커스텀_구분자_계산() {
        run("//;\\n1;2;3");
        assertThat(output()).contains("결과 : 6");
    }

    @Test
    void 이스케이프_포함_커스텀_구분자() {
        run("//;\\n1");
        assertThat(output()).contains("결과 : 1");
    }

    @Test
    void 음수_입력_예외() {
        assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자아닌_문자_입력_예외() {
        assertThatThrownBy(() -> runException("1,a,2"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;1;2;3", "//\n1;2;3"})
    void 잘못된_형식_입력_예외(String input) {
        assertThatThrownBy(() -> runException(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}