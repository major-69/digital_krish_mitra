package digi.coders.digitalkrishimitraa.Fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import digi.coders.digitalkrishimitraa.Adapter.My_Post_Adapter;
import digi.coders.digitalkrishimitraa.Adapter.Post_Adapter;
import digi.coders.digitalkrishimitraa.Helper.Refresh;
import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.Model.MyPostModel;
import digi.coders.digitalkrishimitraa.Model.PostModel;
import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.FragmentMyJobpostBinding;
import digi.coders.digitalkrishimitraa.databinding.FragmentMyPostBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyPost_Fragment extends Fragment implements Refresh {

 FragmentMyPostBinding binding;
    List<MyPostModel> postModelArrayList=new ArrayList<>();
    String userid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=  FragmentMyPostBinding.inflate(inflater,container,false);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userdata = pref.getString("userdata", "{}");
        UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);
        userid=usermodel.getId();


        post();

        return binding.getRoot();
    }
    public void post(){
        postModelArrayList.clear();
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading data...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().mypost(userid,"mypost").enqueue(new Callback<JsonArray>() {
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
                            MyPostModel model=new Gson().fromJson(jsonArray1.get(i).getAsJsonObject(),MyPostModel.class);
                            postModelArrayList.add(model);
                        }
                        My_Post_Adapter adapter2 = new My_Post_Adapter(getContext(),postModelArrayList,MyPost_Fragment.this);
                        binding.recycler.setAdapter(adapter2);
                        binding.recycler.setLayoutManager(new GridLayoutManager(getContext(),3, LinearLayoutManager.VERTICAL,false));
                        progressDialog.dismiss();

                    } else {
                        Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
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


    @Override
    public void refresh() {
        post();
    }
}