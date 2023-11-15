package christmas.DiscountTest;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Discount;
import org.junit.jupiter.api.Test;


public class ChristmasDiscountTest {
    @Test
    void 크리스마스_디데이_할인_테스트() {
        Discount discount = new Discount();
        
        assertThat(discount.calculateChristmasDiscount(1)).isEqualTo(1000);
        assertThat(discount.calculateChristmasDiscount(10)).isEqualTo(1900);
        assertThat(discount.calculateChristmasDiscount(25)).isEqualTo(3400);
    }
}
