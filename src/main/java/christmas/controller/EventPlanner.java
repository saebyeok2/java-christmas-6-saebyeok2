package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlanner {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {
        int date = inputDate();
    }

    private int inputDate() {
        try {
            String input = inputView.readDate();
            return validate(input);
        } catch (IllegalArgumentException e) {
            outputView.printInputDateError();
            return inputDate();
        }
    }

    private int validate(String input) {
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

}
