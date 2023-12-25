package digi.coders.digitalkrishimitraa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class DonateModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("patientName")
    @Expose
    private String patientName;
    @SerializedName("disease")
    @Expose
    private String disease;
    @SerializedName("totaldonation")
    @Expose
    private String totaldonation;
    @SerializedName("patientage")
    @Expose
    private String patientage;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("patientageimage")
    @Expose
    private String patientageimage;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("raised")
    @Expose
    private String raised;
    @SerializedName("percentage")
    @Expose
    private String percentage;

    /**
     * No args constructor for use in serialization
     *
     */
    public DonateModel() {
    }

    /**
     *
     * @param patientName
     * @param totaldonation
     * @param datetime
     * @param disease
     * @param patientageimage
     * @param percentage
     * @param raised
     * @param patientage
     * @param description
     * @param id
     * @param userId
     */
    public DonateModel(String id, String userId, String patientName, String disease, String totaldonation, String patientage, String description, String patientageimage, String datetime, String raised, String percentage) {
        super();
        this.id = id;
        this.userId = userId;
        this.patientName = patientName;
        this.disease = disease;
        this.totaldonation = totaldonation;
        this.patientage = patientage;
        this.description = description;
        this.patientageimage = patientageimage;
        this.datetime = datetime;
        this.raised = raised;
        this.percentage = percentage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getTotaldonation() {
        return totaldonation;
    }

    public void setTotaldonation(String totaldonation) {
        this.totaldonation = totaldonation;
    }

    public String getPatientage() {
        return patientage;
    }

    public void setPatientage(String patientage) {
        this.patientage = patientage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPatientageimage() {
        return patientageimage;
    }

    public void setPatientageimage(String patientageimage) {
        this.patientageimage = patientageimage;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getRaised() {
        return raised;
    }

    public void setRaised(String raised) {
        this.raised = raised;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

}