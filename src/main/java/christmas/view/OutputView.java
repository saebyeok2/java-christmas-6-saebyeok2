package christmas.view;

public class OutputView {
    public void printMenu() {
        System.out.println("<주문 메뉴>");
        // ...
    }

    public void printInputDateError() {
        System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    public void printInputMenuError() {
        System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
