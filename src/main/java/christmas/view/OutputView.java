package christmas.view;

import christmas.domain.Discount;
import christmas.domain.Menu;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    public void printEventPreviewMessage() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printMenu(Map<Menu, Integer> orderList) {
        StringBuilder menuList = new StringBuilder();

        menuList.append("\n<주문 메뉴>\n");

        for (Map.Entry<Menu, Integer> entry : orderList.entrySet()) {
            menuList.append(entry.getKey().getLabel())
                    .append(" ")
                    .append(entry.getValue())
                    .append("개\n");
        }

        System.out.println(menuList);
    }

    public void printTotalOrderAmount(int totalAmount) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###원");
        System.out.println("<할인 전 총주문 금액>\n" + decimalFormat.format(totalAmount));
    }

    public void printPresentationEvent(boolean isPresentationEvent) {
        System.out.println("\n<증정 메뉴>");
        if (isPresentationEvent) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println("없음");
    }

    public void printBenefitDetails(Discount discount) {
        System.out.println("\n<혜택 내역>");
        System.out.printf("크리스마스 디데이 할인: -%,d원%n", discount.getChristmasDiscount());
        System.out.printf("평일 할인: -%,d원%n", discount.getDayOfWeekDiscount());
        System.out.printf("특별 할인: -%,d원%n", discount.getSpecialDiscount());
        if (discount.isPresentationEvent()) {
            System.out.println("증정 이벤트: -25,000원");
        }
    }

    public void printTotalBenefitAmount(Discount discount) {
        int totalBenefits = discount.getTotalDiscountAmount();

        System.out.printf("\n<총혜택 금액>%n%,d원%n", totalBenefits);
    }

    public void printInputDateError() {
        System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    public void printInputMenuError() {
        System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
