package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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

import digi.coders.digitalkrishimitraa.Adapter.Chat_Adapter;
import digi.coders.digitalkrishimitraa.Adapter.MyDonation_Adapter;
import digi.coders.digitalkrishimitraa.Adapter.My_Post_Adapter;
import digi.coders.digitalkrishimitraa.Fragment.MyPost_Fragment;
import digi.coders.digitalkrishimitraa.Helper.Refresh;
import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.Model.DonateModel;
import digi.coders.digitalkrishimitraa.Model.DonationModel;
import digi.coders.digitalkrishimitraa.Model.MyPostModel;
import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.databinding.ActivityMyDonationsBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyDonations_Activity extends AppCompatActivity implements Refresh {

    ActivityMyDonationsBinding binding;
    List<DonationModel> donateModelList=new ArrayList<>();

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding=ActivityMyDonationsBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userdata = pref.getString("userdata", "{}");
        UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);
        userid=usermodel.getId();

        donation();

    }

    public void back(View view) {finish();
    }

    @Override
    public void refresh() {
        donation();
    }

    public void donation(){
        donateModelList.clear();
        ProgressDialog progressDialog = new ProgressDialog(MyDonations_Activity.this);
        progressDialog.setMessage("Loading data...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().mydonations(userid).enqueue(new Callback<JsonArray>() {
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
                            DonationModel model=new Gson().fromJson(jsonArray1.get(i).getAsJsonObject(),DonationModel.class);
                            donateModelList.add(model);
                        }
                        MyDonation_Adapter adapter = new MyDonation_Adapter(getApplicationContext(),donateModelList,MyDonations_Activity.this);
                        binding.recycler.setAdapter(adapter);
                        binding.recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                        progressDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), "" + message, Toast.LENGTH_SHORT).show();
                        binding.recycler.setVisibility(View.GONE);
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