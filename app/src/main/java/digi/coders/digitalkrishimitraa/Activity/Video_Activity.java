package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import digi.coders.digitalkrishimitraa.Adapter.Post_Adapter;
import digi.coders.digitalkrishimitraa.Adapter.Video_Adapter;
import digi.coders.digitalkrishimitraa.Fragment.Foryou_Fragment;
import digi.coders.digitalkrishimitraa.Helper.Refresh;
import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.Model.PostModel;
import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.Model.VideoModel;
import digi.coders.digitalkrishimitraa.databinding.ActivityVideoBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Video_Activity extends AppCompatActivity implements Refresh {

    ActivityVideoBinding binding;
    private Video_Adapter videoAdapter;
    String userid;

    private List<VideoModel> videoModelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userdata = pref.getString("userdata", "{}");
        UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);
        userid=usermodel.getId();

//        videoModelList.add(new VideoModel("Amit Kumar", "https://erp.digicoders.in/uploads/message/sample-1.mp4"));
//        videoModelList.add(new VideoModel("Sumit Singh", "http://erp.digicoders.in/uploads/message/sample-video.mp4"));
//        videoModelList.add(new VideoModel("Ram Saxena", "https://erp.digicoders.in/uploads/message/sample-1.mp4"));
//        videoModelList.add(new VideoModel("Shyam Verma", "https://erp.digicoders.in/uploads/message/sample-1.mp4"));
//        videoModelList.add(new VideoModel("Ramesh Sahu", "https://erp.digicoders.in/uploads/message/sample-1.mp4"));
//        videoModelList.add(new VideoModel("Suresh Patel", "https://erp.digicoders.in/uploads/message/sample-1.mp4"));

        allvideo();
//        Video_Adapter videoAdapter=new Video_Adapter(Video_Activity.this, videoModelList,Video_Activity.this);
//        binding.viewPager2.setAdapter(videoAdapter);

    }

    @Override
    public void refresh() {
        allvideo();
    }
    public void allvideo(){
        videoModelList.clear();
        ProgressDialog progressDialog = new ProgressDialog(Video_Activity.this);
        progressDialog.setMessage("Loading data...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().allvideo(userid).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                try {

                    JsonArray jsonArray = response.body().getAsJsonArray();
                    JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();


                    String result = jsonObject.get("status").getAsString();
                    String message = jsonObject.get("message").getAsString();

                    if (result.equalsIgnoreCase("success")) {

                        JsonArray jsonArray1 = jsonObject.getAsJsonArray("data");

                        for (int i = 0; i < jsonArray1.size(); i++) {
                            VideoModel model=new Gson().fromJson(jsonArray1.get(i).getAsJsonObject(),VideoModel.class);
                            videoModelList.add(model);
                        }
                        Video_Adapter videoAdapter=new Video_Adapter(Video_Activity.this, videoModelList,Video_Activity.this);
                        binding.viewPager2.setAdapter(videoAdapter);
                        progressDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), "" + message, Toast.LENGTH_SHORT).show();
                        binding.viewPager2.setVisibility(View.GONE);
                        progressDialog.dismiss();
                    }
                }
                catch(Exception e) {
                    progressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("response t", t.getMessage());
                progressDialog.dismiss();
            }
        });

    }


}