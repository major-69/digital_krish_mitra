package digi.coders.digitalkrishimitraa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("jabtitle")
    @Expose
    private String jabtitle;
    @SerializedName("companyname")
    @Expose
    private String companyname;
    @SerializedName("Experition")
    @Expose
    private String experition;
    @SerializedName("Qualification")
    @Expose
    private String qualification;
    @SerializedName("packge")
    @Expose
    private String packge;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("jobtype")
    @Expose
    private String jobtype;
    @SerializedName("jabdescription")
    @Expose
    private String jabdescription;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("posted_time")
    @Expose
    private String postedTime;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("user_image")
    @Expose
    private String userImage;

    /**
     * No args constructor for use in serialization
     *
     */
    public JobModel() {
    }

    /**
     *
     * @param jabdescription
     * @param jabtitle
     * @param postedTime
     * @param userId
     * @param jobtype
     * @param qualification
     * @param datetime
     * @param packge
     * @param userImage
     * @param companyname
     * @param location
     * @param id
     * @param experition
     * @param username
     */
    public JobModel(String id, String userId, String jabtitle, String companyname, String experition, String qualification, String packge, String location, String jobtype, String jabdescription, String datetime, String postedTime, String username, String userImage) {
        super();
        this.id = id;
        this.userId = userId;
        this.jabtitle = jabtitle;
        this.companyname = companyname;
        this.experition = experition;
        this.qualification = qualification;
        this.packge = packge;
        this.location = location;
        this.jobtype = jobtype;
        this.jabdescription = jabdescription;
        this.datetime = datetime;
        this.postedTime = postedTime;
        this.username = username;
        this.userImage = userImage;
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

    public String getJabtitle() {
        return jabtitle;
    }

    public void setJabtitle(String jabtitle) {
        this.jabtitle = jabtitle;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getExperition() {
        return experition;
    }

    public void setExperition(String experition) {
        this.experition = experition;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPackge() {
        return packge;
    }

    public void setPackge(String packge) {
        this.packge = packge;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getJabdescription() {
        return jabdescription;
    }

    public void setJabdescription(String jabdescription) {
        this.jabdescription = jabdescription;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(String postedTime) {
        this.postedTime = postedTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

}