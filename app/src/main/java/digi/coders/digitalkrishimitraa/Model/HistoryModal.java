package digi.coders.digitalkrishimitraa.Model;

public class HistoryModal {

    String amount, title, discription;

    public HistoryModal(String amount, String title, String discription) {
        this.amount = amount;
        this.title = title;
        this.discription = discription;
    }

    public HistoryModal() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
