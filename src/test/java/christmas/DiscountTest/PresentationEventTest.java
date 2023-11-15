package christmas.DiscountTest;

import christmas.domain.Discount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PresentationEventTest {
    @Test
    void 증정_이벤트_테스트() {
        Discount discount = new Discount();

        Assertions.assertThat(discount.checkPresentationEvent(120000)).isEqualTo(true);
        Assertions.assertThat(discount.checkPresentationEvent(50000)).isEqualTo(false);
    }
}
