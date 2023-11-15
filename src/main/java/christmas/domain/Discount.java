package christmas.domain;

import java.util.Map;

public class Discount {
    private int christmasDiscount;
    private int dayOfWeekDiscount;
    private int specialDiscount;
    private boolean isPresentationEvent;

    public Discount() {
    }

    public Discount(Order order) {
        calculateDiscount(order);
    }

    public void calculateDiscount(Order order) {
        christmasDiscount = calculateChristmasDiscount(order.getDate());
        dayOfWeekDiscount = calculateDayOfWeekDiscount(order);
        specialDiscount = calculateSpecialDiscount(order.getDate());
        isPresentationEvent = checkPresentationEvent(order.getTotalAmount());
    }

    public int calculateChristmasDiscount(int date) {
        int christmasStart = 1;
        int christmasEnd = 25;
        int discountAmount = 0;

        if (date >= christmasStart && date <= christmasEnd) {
            discountAmount = 1000 + (date - 1) * 100;
        }

        return discountAmount;
    }

    public int calculateDayOfWeekDiscount(Order order) {
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

    public int getWeekendDiscountAmount(Map<Menu, Integer> order) {
        int discountAmount;
        discountAmount = order.entrySet().stream()
                .filter(entry -> entry.getKey().getType().equals("Main"))
                .mapToInt(entry -> entry.getValue() * 2023)
                .sum();
        return discountAmount;
    }

    public int getWeekdayDiscountAmount(Map<Menu, Integer> order) {
        int discountAmount;
        discountAmount = order.entrySet().stream()
                .filter(entry -> entry.getKey().getType().equals("Dessert"))
                .mapToInt(entry -> entry.getValue() * 2023)
                .sum();
        return discountAmount;
    }

    public int calculateSpecialDiscount(int date) {
        if (isStarEventDay(date)) {
            return 1000;
        }

        return 0;
    }

    public boolean isStarEventDay(int orderDate) {
        return orderDate % 7 == 3 || orderDate == 25;
    }

    public boolean checkPresentationEvent(int totalAmount) {
        return totalAmount >= 120000;
    }

    public boolean isPresentationEvent() {
        return isPresentationEvent;
    }

    public int getTotalBenefits() {
        int totalDiscountAmount = christmasDiscount + dayOfWeekDiscount + specialDiscount;
        if (isPresentationEvent) {
            return totalDiscountAmount + 25000;
        }
        return totalDiscountAmount;
    }

    public int getChristmasDiscount() {
        return christmasDiscount;
    }

    public int getDayOfWeekDiscount() {
        return dayOfWeekDiscount;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }
}
