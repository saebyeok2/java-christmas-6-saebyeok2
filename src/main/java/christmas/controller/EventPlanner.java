package christmas.controller;

import christmas.view.InputView;

public class EventPlanner {
    InputView inputView = new InputView();

    public void run() {
        int date = inputView.readDate();
    }
}
