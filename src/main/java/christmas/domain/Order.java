package christmas.domain;

import java.util.Map;

public class Order {
    private int date;
    private Map<Menu, Integer> orderList;
    private int totalAmount;

    public Order(int date, Map<Menu, Integer> orderList) {
        this.date = date;
        this.orderList = orderList;
        totalAmount = calculateTotalAmount(orderList);
    }

    public int calculateTotalAmount(Map<Menu, Integer> order) {
        return order.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public int getDate() {
        return date;
    }

    public Map<Menu, Integer> getOrderList() {
        return orderList;
    }

    public int getTotalAmount() {
        return totalAmount;
    }
}
