package christmas.DiscountTest;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Discount;
import org.junit.jupiter.api.Test;

public class SpecialDiscountTest {
    @Test
    void 특별_할인_테스트() {
        Discount discount = new Discount();

        assertThat(discount.calculateSpecialDiscount(3)).isEqualTo(1000);
        assertThat(discount.calculateSpecialDiscount(4)).isEqualTo(0);
    }
}
