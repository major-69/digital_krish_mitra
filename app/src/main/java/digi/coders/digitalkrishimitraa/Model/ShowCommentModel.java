package digi.coders.digitalkrishimitraa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowCommentModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("receiver_id")
    @Expose
    private String receiverId;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("user_id_image")
    @Expose
    private String userIdImage;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("receiver_id_image")
    @Expose
    private String receiverIdImage;
    @SerializedName("receivername")
    @Expose
    private String receivername;

    /**
     * No args constructor for use in serialization
     *
     */
    public ShowCommentModel() {
    }

    /**
     *
     * @param receivername
     * @param datetime
     * @param receiverId
     * @param userIdImage
     * @param receiverIdImage
     * @param comment
     * @param id
     * @param postId
     * @param userId
     * @param username
     */
    public ShowCommentModel(String id, String userId, String receiverId, String comment, String postId, String datetime, String userIdImage, String username, String receiverIdImage, String receivername) {
        super();
        this.id = id;
        this.userId = userId;
        this.receiverId = receiverId;
        this.comment = comment;
        this.postId = postId;
        this.datetime = datetime;
        this.userIdImage = userIdImage;
        this.username = username;
        this.receiverIdImage = receiverIdImage;
        this.receivername = receivername;
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

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getUserIdImage() {
        return userIdImage;
    }

    public void setUserIdImage(String userIdImage) {
        this.userIdImage = userIdImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReceiverIdImage() {
        return receiverIdImage;
    }

    public void setReceiverIdImage(String receiverIdImage) {
        this.receiverIdImage = receiverIdImage;
    }

    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }

}