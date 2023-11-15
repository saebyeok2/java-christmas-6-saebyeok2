package christmas.controller;

import christmas.domain.Menu;
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
        int date = dateInput();
        Map<Menu, Integer> order = MenuInput();

    }

    private int dateInput() {
        try {
            String input = inputView.readDate();
            return validateDate(input);
        } catch (IllegalArgumentException e) {
            outputView.printInputDateError();
            return dateInput();
        }
    }

    private int validateDate(String input) {
        try {
            int date = Integer.parseInt(input);

            if (date < 0 || 31 < date) {
                throw new IllegalArgumentException();
            }

            return date;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    private Map<Menu, Integer> MenuInput() {
        try {
            String input = inputView.readMenu();
            return validateMenuInput(input);
        } catch (IllegalArgumentException e) {
            outputView.printInputMenuError();
            return MenuInput();
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
        validateMenuFormat(input);
        Set<String> uniqueMenus = new HashSet<>();

        for (String item : input.split(",")) {
            String[] parts = item.split("-");
            Menu menu = findMenu(parts[0]);
            validateExistMenu(menu);
            validateDuplicateMenu(uniqueMenus, parts[0]);
            int quantity = validateQuantity(parts[1]);
            order.put(menu, quantity);
        }
        validateOnlyDrinkOrder(order);

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
}
