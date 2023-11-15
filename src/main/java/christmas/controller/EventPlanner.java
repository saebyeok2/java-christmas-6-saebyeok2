package christmas.controller;

import christmas.domain.Discount;
import christmas.domain.EventBadge;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventPlanner {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {
        int date = getDateInput();
        Map<Menu, Integer> orderList = getMenuInput();

        Order order = new Order(date, orderList);
        Discount discount = getDiscount(order);
        EventBadge badge = new EventBadge(discount.getTotalBenefits());

        printResults(order, discount, badge);
    }

    private int getDateInput() {
        while (true) {
            try {
                String input = inputView.readDate();
                return validateDate(input);
            } catch (IllegalArgumentException e) {
                outputView.printInputDateError();
            }
        }
    }

    public int validateDate(String input) {
        try {
            int date = Integer.parseInt(input);

            if (date <= 0 || 31 < date) {
                throw new IllegalArgumentException();
            }

            return date;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private Map<Menu, Integer> getMenuInput() {
        while (true) {
            try {
                String input = inputView.readMenu();
                validateMenuFormat(input);

                return validateMenuInput(input);
            } catch (IllegalArgumentException e) {
                outputView.printInputMenuError();
            }
        }
    }

    private Menu findMenu(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getLabel().equals(menuName)) {
                return menu;
            }
        }

        return null;
    }

    public Map<Menu, Integer> validateMenuInput(String input) {
        Map<Menu, Integer> order = new HashMap<>();
        Set<String> uniqueMenus = new HashSet<>();
        int totalQuantity = 0;

        for (String item : input.split(",")) {
            String[] parts = item.split("-");
            Menu menu = findMenu(parts[0]);
            validateExistMenu(menu);
            validateDuplicateMenu(uniqueMenus, parts[0]);
            totalQuantity += validateQuantity(parts[1]);
            order.put(menu, Integer.parseInt(parts[1]));
        }
        validateOnlyDrinkOrder(order);
        validateTotalQuantity(totalQuantity);

        return order;
    }

    private void validateMenuFormat(String input) {
        if (!input.matches("([가-힣]+-[1-9][0-9]*)+(,([가-힣]+-[1-9][0-9]*))*")) {
            throw new IllegalArgumentException();
        }
    }

    private void validateExistMenu(Menu menu) {
        if (menu == null) {
            throw new IllegalArgumentException();
        }
    }

    private int validateQuantity(String quantityStr) {
        int quantity;

        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }

        return quantity;
    }

    private void validateDuplicateMenu(Set<String> uniqueMenus, String menuName) {
        if (!uniqueMenus.add(menuName)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateOnlyDrinkOrder(Map<Menu, Integer> order) {
        boolean hasDrink = order.keySet().stream()
                .anyMatch(menu -> !menu.getType().equals("Drink"));

        if (!hasDrink) {
            throw new IllegalArgumentException();
        }
    }

    private void validateTotalQuantity(int totalQuantity) {
        if (totalQuantity > 20) {
            throw new IllegalArgumentException();
        }
    }

    private Discount getDiscount(Order order) {
        if (order.getTotalAmount() > 10000) {
            return new Discount(order);
        }
        
        return new Discount();
    }

    private void printResults(Order order, Discount discount, EventBadge badge) {
        outputView.printEventPreviewMessage();
        outputView.printMenu(order.getOrderList());
        outputView.printTotalOrderAmount(order.getTotalAmount());
        outputView.printPresentationEvent(discount.isPresentationEvent());
        outputView.printBenefitDetails(discount);
        outputView.printTotalBenefitAmount(discount);
        outputView.printAfterDiscountAmount(discount, order.getTotalAmount());
        outputView.printEventBadge(badge);
    }
}
