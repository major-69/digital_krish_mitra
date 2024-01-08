package digi.coders.digitalkrishimitraa.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codebyashish.autoimageslider.Enums.ImageScaleType;
import com.codebyashish.autoimageslider.ExceptionsClass;
import com.codebyashish.autoimageslider.Models.ImageSlidesModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import digi.coders.digitalkrishimitraa.Activity.Add_Reel_Activity;
import digi.coders.digitalkrishimitraa.Adapter.Post_Adapter;
import digi.coders.digitalkrishimitraa.Helper.Refresh;
import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.Model.PostModel;
import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.FragmentForyouBinding;
import digi.coders.digitalkrishimitraa.databinding.FragmentHomeBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Foryou_Fragment extends Fragment implements Refresh {

    FragmentForyouBinding binding;
    List<PostModel> postModelArrayList=new ArrayList<>();
    String userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentForyouBinding.inflate(inflater,container,false);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userdata = pref.getString("userdata", "{}");
        UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);
        userid=usermodel.getId();

        List<ImageSlidesModel> imageSlidesModelList = new ArrayList<>();

        try {
            imageSlidesModelList.add(new ImageSlidesModel("https://img.freepik.com/free-psd/banner-template-concept-nature-environs_23-2148403961.jpg?w=1060&t=st=1704370511~exp=1704371111~hmac=65b4109538bf063c278c8558951131826388d2a1595570593560803a9526813c"));
            imageSlidesModelList.add(new ImageSlidesModel("https://img.freepik.com/free-psd/banner-template-concept-green-nature_23-2148403960.jpg?w=826&t=st=1704370647~exp=1704371247~hmac=52452ca8a8657226055f16ac6dcf34e6e298859207fe37434c393ae4414387c5"));
            imageSlidesModelList.add(new ImageSlidesModel("https://img.freepik.com/free-vector/hand-drawn-gardening-facebook-ad-template_23-2149704786.jpg?w=1060&t=st=1704370674~exp=1704371274~hmac=b65e01d743172cb83dfc6623140fd50b39c4a65fd8333f91b186fc3d620d4fc0"));
        } catch (ExceptionsClass e) {
            throw new RuntimeException(e);
        }

        binding.autoImageSlider.setImageList((ArrayList<ImageSlidesModel>) imageSlidesModelList, ImageScaleType.FIT);

//        Post_Adapter adapter = new Post_Adapter(getContext());
//        binding.recycler.setAdapter(adapter);
//        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        post();
        return binding.getRoot();

    }
//    @Override
//    public void setUserVisibleHint(boolean isFragmentVisible_) {
//        super.setUserVisibleHint(true);
//        if (this.isVisible()){
//            // we check that the fragment is becoming visible
//            if (isFragmentVisible_) {
//
//                Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
//                Post_Adapter adapter = new Post_Adapter(getContext());
//                binding.recycler.setAdapter(adapter);
//                binding.recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));                //           _hasLoadedOnce = true;
//            }
//        }
//    }

    public void post(){
        postModelArrayList.clear();
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading data...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().allpost(userid).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                try {
                    JsonArray jsonArray = response.body().getAsJsonArray();
                    JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

                    String result = jsonObject.get("res").getAsString();
                    String message = jsonObject.get("msg").getAsString();

                    if (result.equalsIgnoreCase("success")) {

                        JsonArray jsonArray1 = jsonObject.getAsJsonArray("data");

                        for (int i = 0; i < jsonArray1.size(); i++) {
                            PostModel model=new Gson().fromJson(jsonArray1.get(i).getAsJsonObject(),PostModel.class);
                            Log.d("post88",""+model.getImage());
                            postModelArrayList.add(model);
                        }
                        Post_Adapter adapter = new Post_Adapter(getContext(),postModelArrayList,Foryou_Fragment.this);

                        binding.recycler.setAdapter(adapter);
                        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
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