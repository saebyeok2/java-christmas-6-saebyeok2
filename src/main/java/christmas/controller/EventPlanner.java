package christmas.controller;

import christmas.domain.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;
import java.util.Map;

public class EventPlanner {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {
        int date = inputDate();
        Map<Menu, Integer> order = inputMenu();
        
    }

    private int inputDate() {
        try {
            String input = inputView.readDate();
            return validateDate(input);
        } catch (IllegalArgumentException e) {
            outputView.printInputDateError();
            return inputDate();
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

    private Map<Menu, Integer> inputMenu() {
        String input = inputView.readMenu();
        Map<Menu, Integer> order = processOrder(input);

        return order;
    }

    private Map<Menu, Integer> processOrder(String input) {
        Map<Menu, Integer> order = new HashMap<>();

        for (String item : input.split(",")) {
            String[] parts = item.split("-");
            if (parts.length == 2) {
                Menu menu = findMenu(parts[0]);
                if (menu != null) {
                    order.put(menu, Integer.parseInt(parts[1]));
                }
            }
        }

        return order;
    }

    private Menu findMenu(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getLabel().equals(menuName)) {
                return menu;
            }
        }
        return null;
    }

}
