package christmas.domain;

public enum Menu {
    PINEMUSHROOMSOUP("양송이수프", 6000, "Appetizer"),
    TAPAS("타파스", 5500, "Appetizer"),
    CAESARSALAD("시저샐러드", 8000, "Appetizer"),

    T_BONESTEAK("티본스테이크", 55000, "Main"),
    BARBECUERIBS("바비큐립", 54000, "Main"),
    SEAFOODPASTA("해산물파스타", 35000, "Main"),
    CHRISTMASPASTA("크리스마스파스타", 25000, "Main"),

    CHOCOLATECAKE("초코케이크", 15000, "Dessert"),
    ICECREAM("아이스크림", 5000, "Dessert"),
    ZEROCOLA("제로콜라", 3000, "Drink"),
    REDWINE("레드와인", 60000, "Drink"),
    CHAMPAGNE("샴페인", 25000, "Drink");

    private final String label;
    private final int price;
    private final String type;

    Menu(String label, int price, String type) {
        this.label = label;
        this.price = price;
        this.type = type;
    }
}
