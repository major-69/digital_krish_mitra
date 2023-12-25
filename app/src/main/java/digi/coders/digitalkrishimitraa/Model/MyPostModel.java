package digi.coders.digitalkrishimitraa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyPostModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("discription")
    @Expose
    private String discription;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("datetime")
    @Expose
    private String datetime;

    /**
     * No args constructor for use in serialization
     *
     */
    public MyPostModel() {
    }

    /**
     *
     * @param img
     * @param datetime
     * @param discription
     * @param id
     * @param userId
     */
    public MyPostModel(String id, String userId, String discription, String img, String datetime) {
        super();
        this.id = id;
        this.userId = userId;
        this.discription = discription;
        this.img = img;
        this.datetime = datetime;
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

}