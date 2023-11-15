package christmas.DiscountTest;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.EventBadge;
import org.junit.jupiter.api.Test;

public class EventBadgeTest {
    @Test
    void 이벤트_배지_테스트() {
        int totalBenefitAmount1 = 5000;
        EventBadge badge1 = new EventBadge(totalBenefitAmount1);
        assertThat(badge1.getBadge()).isEqualTo("별");

        int totalBenefitAmount2 = 12000;
        EventBadge badge2 = new EventBadge(totalBenefitAmount2);
        assertThat(badge2.getBadge()).isEqualTo("트리");

        int totalBenefitAmount3 = 25000;
        EventBadge badge3 = new EventBadge(totalBenefitAmount3);
        assertThat(badge3.getBadge()).isEqualTo("산타");

        int totalBenefitAmount4 = 4000;
        EventBadge badge4 = new EventBadge(totalBenefitAmount4);
        assertThat(badge4.getBadge()).isEqualTo("없음");
    }
}
