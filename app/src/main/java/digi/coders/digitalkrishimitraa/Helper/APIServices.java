package digi.coders.digitalkrishimitraa.Helper;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIServices {

    @FormUrlEncoded
    @POST("registerapi.php")
    Call<JsonArray> Registration(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobileno,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("loginapi.php")
    Call<JsonArray> Login(
            @Field("mobile") String mobileno,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("loginapi.php")
    Call<JsonObject> newLogin(
            @Field("mobile") String mobileno,
            @Field("otp") String otp
    );


    @FormUrlEncoded
    @POST("verifiy_otp.php")
    Call<JsonArray> Verifyotp(
            @Field("userid") String mobileno,
            @Field("otp") String password
    );


    @FormUrlEncoded
    @POST("userjob.php")
    Call<JsonArray> addjob(
            @Field("user_id") String mobileno,
            @Field("jabtitle") String passwqord,
            @Field("companyname") String password,
            @Field("Experition") String passwfword,
            @Field("Qualification") String passwword,
            @Field("packge") String passweord,
            @Field("location") String pasdsword,
            @Field("jobtype") String jobtype,
            @Field("jabdescription") String passwodrd
    );

    @Multipart
    @POST("addfundraise.php")
    Call<JsonArray> addfund(
            @Part("user_id") RequestBody str_passwordidh,
            @Part("patientName") RequestBody str_passwordhid,
            @Part("disease") RequestBody str_passwodhid,
            @Part("totaldonation") RequestBody str_passodhid,
            @Part("patientage") RequestBody str_passohid,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part image1
    );


    @Multipart
    @POST("userpost.php")
    Call<JsonArray> addpost(
            @Part("user_id") RequestBody str_passwordidh,
            @Part("discription") RequestBody str_passwordhid,
            @Part MultipartBody.Part image1
    );

    @Multipart
    @POST("shortvideo.php")
    Call<JsonArray> addvideo(
            @Part("user_id") RequestBody str_passwordidh,
            @Part("description") RequestBody str_passwordhid,
            @Part MultipartBody.Part image1
    );


    @Multipart
    @POST("updateprofile.php")
    Call<JsonArray> editprofile(
            @Part("user_id") RequestBody user_id,
            @Part("name") RequestBody name,
            @Part("mobile") RequestBody mobile,
            @Part("email") RequestBody email,
            @Part("house") RequestBody house,
            @Part("street") RequestBody street,
            @Part("city") RequestBody city,
            @Part("states") RequestBody states,
            @Part("password") RequestBody pass,
            @Part("alt_mobile") RequestBody alt_mobile,
            @Part MultipartBody.Part image1
    );

    @FormUrlEncoded
    @POST("home.php")
    Call<JsonArray> allpost(
            @Field("userid") String mobileno
    );

    @FormUrlEncoded
    @POST("show_video.php")
    Call<JsonArray> allvideo(
            @Field("userid") String mobileno
    );

    @FormUrlEncoded
    @POST("show_job_seeker.php")
    Call<JsonArray> alljobs(
            @Field("userid") String mobileno
    );

    @FormUrlEncoded
    @POST("show_donate.php")
    Call<JsonArray> fundraise(
            @Field("userid") String mobileno
    );

    @FormUrlEncoded
    @POST("forget_password.php")
    Call<JsonArray> forgetpassword1(
            @Field("flag") String mobileno,
            @Field("mobile") String mobileeno
    );

    @FormUrlEncoded
    @POST("forget_password.php")
    Call<JsonArray> forgetpassword2(
            @Field("flag") String mobileno,
            @Field("mobile") String mobileeno,
            @Field("otp") String mobileen
    );

    @FormUrlEncoded
    @POST("forget_password.php")
    Call<JsonArray> forgetpassword3(
            @Field("flag") String mobileno,
            @Field("mobile") String mobileeno,
            @Field("otp") String otp,
            @Field("newpassword") String mobileenod,
            @Field("confirmpassword") String mobileedno
    );

    @FormUrlEncoded
    @POST("comment.php")
    Call<JsonArray> addcomment(
            @Field("user_id") String mobileno,
            @Field("post_id") String mobileeno,
            @Field("comment") String otp
    );

    @FormUrlEncoded
    @POST("show_comment.php")
    Call<JsonArray> showcomment(
            @Field("post_id") String mobileeno
    );

    @FormUrlEncoded
    @POST("mypost.php")
    Call<JsonArray> mypost(
            @Field("user_id") String mobileen,
            @Field("flag") String mobileeno
    );

    @FormUrlEncoded
    @POST("show_my_donated.php")
    Call<JsonArray> mydonations(
            @Field("user_id") String mobileen
    );

    @FormUrlEncoded
    @POST("like1.php")
    Call<JsonArray> like(
            @Field("user_id") String mobileen,
            @Field("post_id") String mobileeno
    );


    @FormUrlEncoded
    @POST("user_jab_apply.php")
    Call<JsonArray> jobapply(
            @Field("user_id") String str_passwordidh,
            @Field("job_id") String job_id,
            @Field("applicant_name") String applicant_name,
            @Field("mobile") String str_passwordhid,
            @Field("email") String str_passwodhid,
            @Field("profile_headline") String str_passodhid,
            @Field("experience") String str_passohid,
            @Field("qualification") String description,
            @Field("key_skills") String key_skills,
            @Field("location") String location,
            @Field("upload_resume") String locatio
    );

    @FormUrlEncoded
    @POST("sendOtp.php")
    Call<JsonObject> sendOtp(
            @Field("mobile") String mobile
    );
}
