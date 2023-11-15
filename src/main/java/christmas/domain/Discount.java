package christmas.domain;

import java.util.Map;

public class Discount {
    public static void calculateDiscount(int orderDate, Map<Menu, Integer> order) {
        int christmasDiscount = calculateChristmasDiscount(orderDate);
    }

    private static int calculateChristmasDiscount(int orderDate) {
        int christmasStart = 1;
        int christmasEnd = 25;
        int discountAmount = 0;

        if (orderDate >= christmasStart && orderDate <= christmasEnd) {
            discountAmount = 1000 + (orderDate - 1) * 100;
        }

        return discountAmount;
    }

}
