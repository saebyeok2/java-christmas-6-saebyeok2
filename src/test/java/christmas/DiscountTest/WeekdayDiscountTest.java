package christmas.DiscountTest;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.Order;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class WeekdayDiscountTest {
    @Test
    void 평일_할인_테스트() {
        Map<Menu, Integer> orderList = new HashMap<>();
        Discount discount = new Discount();

        orderList.put(Menu.CHOCOLATECAKE, 2);
        orderList.put(Menu.BARBECUERIBS, 1);

        Order order = new Order(5, orderList);

        assertThat(discount.calculateDayOfWeekDiscount(order)).isEqualTo(4046);
    }
}
