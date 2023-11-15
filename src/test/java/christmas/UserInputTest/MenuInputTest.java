package christmas.UserInputTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.controller.EventPlanner;
import org.junit.jupiter.api.Test;

public class MenuInputTest {
    @Test
    void testInvalidMenuInput() {
        EventPlanner eventPlanner = new EventPlanner();

        // 예외: 메뉴판에 없는 메뉴를 입력한 경우
        assertThatThrownBy(() -> eventPlanner.validateMenuInput("InvalidMenu-2"))
                .isInstanceOf(IllegalArgumentException.class);

        // 예외: 메뉴의 개수가 0 이하의 숫자인 경우
        assertThatThrownBy(() -> eventPlanner.validateMenuInput("Burger-0"))
                .isInstanceOf(IllegalArgumentException.class);

        // 예외: 메뉴 입력 형식이 잘못된 경우
        assertThatThrownBy(() -> eventPlanner.validateMenuInput("InvalidFormat"))
                .isInstanceOf(IllegalArgumentException.class);

        // 예외: 중복 메뉴를 입력한 경우
        assertThatThrownBy(() -> eventPlanner.validateMenuInput("Burger-1,Burger-2"))
                .isInstanceOf(IllegalArgumentException.class);

        // 예외: 음료만 주문한 경우
        assertThatThrownBy(() -> eventPlanner.validateMenuInput("Coke-1"))
                .isInstanceOf(IllegalArgumentException.class);

        // 예외: 주문한 메뉴의 개수가 20개를 초과하는 경우
        assertThatThrownBy(() -> eventPlanner.validateMenuInput("Burger-10,Fries-11"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
