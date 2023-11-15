package christmas.domain;

import java.util.Map;

public class Order {
    private Map<Menu, Integer> order;

    public Order(Map<Menu, Integer> order) {
        this.order = order;
    }

    public int calculateTotalAmount(Map<Menu, Integer> order) {
        return order.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
}
