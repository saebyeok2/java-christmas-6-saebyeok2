package christmas.controller;

import christmas.domain.Discount;
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
        Discount discount = new Discount(order);
        printResults(order, discount);
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

    private int validateDate(String input) {
        try {
            int date = Integer.parseInt(input);

            if (date < 0 || 31 < date) {
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

    private Map<Menu, Integer> validateMenuInput(String input) {
        Map<Menu, Integer> order = new HashMap<>();
        Set<String> uniqueMenus = new HashSet<>();
        int totalQuantity = 0;

        validateMenuFormat(input);
        for (String item : input.split(",")) {
            String[] parts = item.split("-");
            Menu menu = findMenu(parts[0]);
            validateExistMenu(menu);
            validateDuplicateMenu(uniqueMenus, parts[0]);
            int quantity = validateQuantity(parts[1]);

            totalQuantity += quantity;
            order.put(menu, quantity);
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

    private void splitMenuInput(String input) {

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

    private void printResults(Order order, Discount discount) {
        outputView.printEventPreviewMessage();
        outputView.printMenu(order.getOrderList());
        outputView.printTotalOrderAmount(order.getTotalAmount());
        outputView.printPresentationEvent(discount.isPresentationEvent());
        outputView.printBenefitDetails(discount);
        outputView.printTotalBenefitAmount(discount);
    }
}
