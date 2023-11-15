package christmas.domain;

import java.util.Map;

public class Discount {
    private int christmasDiscount;
    private int dayOfWeekDiscount;
    private int specialDiscount;
    private boolean isPresentationEvent;

    public Discount(Order order) {
        calculateDiscount(order);
    }

    public void calculateDiscount(Order order) {
        christmasDiscount = calculateChristmasDiscount(order);
        dayOfWeekDiscount = calculateDayOfWeekDiscount(order);
        specialDiscount = calculateSpecialDiscount(order);
        isPresentationEvent = checkPresentationEvent(order);
    }

    private static int calculateChristmasDiscount(Order order) {
        int christmasStart = 1;
        int christmasEnd = 25;
        int discountAmount = 0;
        int orderDate = order.getDate();

        if (orderDate >= christmasStart && orderDate <= christmasEnd) {
            discountAmount = 1000 + (orderDate - 1) * 100;
        }

        return discountAmount;
    }

    private static int calculateDayOfWeekDiscount(Order order) {
        int dayOfWeek = order.getDate() % 7;
        int discountAmount = 0;

        if (dayOfWeek == 2 || dayOfWeek == 3) {
            discountAmount = getWeekendDiscountAmount(order.getOrderList());
        }
        if (dayOfWeek != 2 && dayOfWeek != 3) {
            discountAmount = getWeekdayDiscountAmount(order.getOrderList());
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

    private static int calculateSpecialDiscount(Order order) {
        if (isStarEventDay(order.getDate())) {
            return 1000;
        }

        return 0;
    }

    private static boolean isStarEventDay(int orderDate) {
        return orderDate % 7 == 3 || orderDate == 25;
    }

    private static boolean checkPresentationEvent(Order order) {
        return order.getTotalAmount() >= 120000;
    }

    public boolean isPresentationEvent() {
        return isPresentationEvent;
    }

    public int getTotalDiscountAmount() {
        return christmasDiscount + dayOfWeekDiscount + specialDiscount;
    }
}
