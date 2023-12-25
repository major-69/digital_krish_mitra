package digi.coders.digitalkrishimitraa.Fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import digi.coders.digitalkrishimitraa.Activity.Video_Activity;
import digi.coders.digitalkrishimitraa.Adapter.Video_Adapter;
import digi.coders.digitalkrishimitraa.Helper.Refresh;
import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.Model.VideoModel;
import digi.coders.digitalkrishimitraa.databinding.FragmentVideoBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoFragment extends Fragment {

    private List<VideoModel> videoModelList = new ArrayList<>();


    FragmentVideoBinding binding;
    String userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVideoBinding.inflate(inflater, container, false);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userdata = pref.getString("userdata", "{}");
        UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);
        userid = usermodel.getId();
        allvideo();

        return binding.getRoot();
    }

    public void allvideo(){
        videoModelList.clear();
        ProgressDialog progressDialog = new ProgressDialog(getContext());
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
                        Video_Adapter videoAdapter=new Video_Adapter(getContext(), videoModelList, (Refresh) getContext());
                        binding.viewPager2.setAdapter(videoAdapter);
                        progressDialog.dismiss();

                    } else {
                        Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
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