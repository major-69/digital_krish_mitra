package digi.coders.digitalkrishimitraa.Model;

public class MoneyModal {

    String Month , amount;

    public MoneyModal(String month, String amount) {
        Month = month;
        this.amount = amount;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
