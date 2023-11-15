package christmas;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.controller.EventPlanner;
import org.junit.jupiter.api.Test;

public class DateInputTest {
    @Test
    void testInvalidDateInput() {
        EventPlanner eventPlanner = new EventPlanner();

        // 예외: 날짜가 int 값이 아닌 경우
        assertThatThrownBy(() -> eventPlanner.validateDate("abc"))
                .isInstanceOf(IllegalArgumentException.class);

        // 예외: 날짜가 1 이상 31 이하의 숫자가 아닌 경우
        assertThatThrownBy(() -> eventPlanner.validateDate("0"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
