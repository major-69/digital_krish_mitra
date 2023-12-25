package digi.coders.digitalkrishimitraa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostModel {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("total_like")
    @Expose
    private String totalLike;
    @SerializedName("total_Cumment")
    @Expose
    private String totalCumment;
    @SerializedName("User_image")
    @Expose
    private String userImage;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("days")
    @Expose
    private String days;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("descritpion")
    @Expose
    private String descritpion;
    @SerializedName("image")
    @Expose
    private String image;

    /**
     * No args constructor for use in serialization
     *
     */
    public PostModel() {
    }

    /**
     *
     * @param image
     * @param userImage
     * @param city
     * @param totalLike
     * @param name
     * @param descritpion
     * @param days
     * @param postId
     * @param state
     * @param userId
     * @param totalCumment
     */
    public PostModel(String userId, String postId, String totalLike, String totalCumment, String userImage, String name, String days, String city, String state, String descritpion, String image) {
        super();
        this.userId = userId;
        this.postId = postId;
        this.totalLike = totalLike;
        this.totalCumment = totalCumment;
        this.userImage = userImage;
        this.name = name;
        this.days = days;
        this.city = city;
        this.state = state;
        this.descritpion = descritpion;
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(String totalLike) {
        this.totalLike = totalLike;
    }

    public String getTotalCumment() {
        return totalCumment;
    }

    public void setTotalCumment(String totalCumment) {
        this.totalCumment = totalCumment;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}