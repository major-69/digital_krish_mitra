package digi.coders.digitalkrishimitraa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("discription")
    @Expose
    private String discription;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("image")
    @Expose
    private String image;

    /**
     * No args constructor for use in serialization
     *
     */
    public VideoModel() {
    }

    /**
     *
     * @param image
     * @param datetime
     * @param discription
     * @param id
     * @param video
     * @param userName
     * @param userId
     */
    public VideoModel(String id, String userId, String discription, String video, String datetime, String userName, String image) {
        super();
        this.id = id;
        this.userId = userId;
        this.discription = discription;
        this.video = video;
        this.datetime = datetime;
        this.userName = userName;
        this.image = image;
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
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

}