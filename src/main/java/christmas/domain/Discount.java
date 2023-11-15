package christmas.domain;

import java.util.Map;

public class Discount {
    public static int calculateDiscount(int orderDate, Map<Menu, Integer> order) {
        int christmasDiscount = calculateChristmasDiscount(orderDate);
        int dayOfWeekDiscount = calculateDayOfWeekDiscount(orderDate, order);
        int specialDiscount = calculateSpecialDiscount(orderDate);
        
        return 0;
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

    private static int calculateDayOfWeekDiscount(int orderDay, Map<Menu, Integer> order) {
        int dayOfWeek = orderDay % 7;
        int discountAmount = 0;

        if (dayOfWeek == 2 || dayOfWeek == 3) {
            discountAmount = getWeekendDiscountAmount(order);
        }
        if (dayOfWeek != 2 && dayOfWeek != 3) {
            discountAmount = getWeekdayDiscountAmount(order);
        }
        return discountAmount;
    }

    private static int getWeekendDiscountAmount(Map<Menu, Integer> order) {
        int discountAmount;
        discountAmount = order.entrySet().stream()
                .filter(entry -> entry.getKey().getType().equals("Main"))
                .mapToInt(entry -> entry.getValue() * 2023)
                .sum();
        return discountAmount;
    }

    private static int getWeekdayDiscountAmount(Map<Menu, Integer> order) {
        int discountAmount;
        discountAmount = order.entrySet().stream()
                .filter(entry -> entry.getKey().getType().equals("Dessert"))
                .mapToInt(entry -> entry.getValue() * 2023)
                .sum();
        return discountAmount;
    }

    private static int calculateSpecialDiscount(int orderDate) {
        if (isStarEventDay(orderDate)) {
            return 1000;
        }

        return 0;
    }

    private static boolean isStarEventDay(int orderDate) {
        return orderDate % 7 == 3 || orderDate == 25;
    }

}
