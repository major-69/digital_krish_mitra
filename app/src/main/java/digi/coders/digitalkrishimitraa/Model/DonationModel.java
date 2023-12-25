package digi.coders.digitalkrishimitraa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonationModel {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("date_field")
    @Expose
    private String dateField;

    /**
     * No args constructor for use in serialization
     *
     */
    public DonationModel() {
    }

    /**
     *
     * @param image
     * @param amount
     * @param datetime
     * @param dateField
     * @param userName
     * @param userId
     */
    public DonationModel(String userId, String amount, String datetime, String userName, String image, String dateField) {
        super();
        this.userId = userId;
        this.amount = amount;
        this.datetime = datetime;
        this.userName = userName;
        this.image = image;
        this.dateField = dateField;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateField() {
        return dateField;
    }

    public void setDateField(String dateField) {
        this.dateField = dateField;
    }

}