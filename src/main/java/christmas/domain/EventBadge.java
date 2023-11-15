package christmas.domain;

public class EventBadge {
    private int totalBenefits;

    public EventBadge(int totalBenefits) {
        this.totalBenefits = totalBenefits;
    }

    public String getBadge() {
        if (totalBenefits >= 20000) {
            return "산타";
        }
        if (totalBenefits >= 10000) {
            return "트리";
        }
        if (totalBenefits >= 5000) {
            return "별";
        }
        return "없음";
    }
}
