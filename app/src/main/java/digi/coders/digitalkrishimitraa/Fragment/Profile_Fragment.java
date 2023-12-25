package digi.coders.digitalkrishimitraa.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import digi.coders.digitalkrishimitraa.Activity.Badge_status_Activity;
import digi.coders.digitalkrishimitraa.Activity.Edit_Profile_Activity;
import digi.coders.digitalkrishimitraa.Activity.Followers_Activity;
import digi.coders.digitalkrishimitraa.Activity.Followings_Activity;
import digi.coders.digitalkrishimitraa.Activity.Login_Activity;
import digi.coders.digitalkrishimitraa.Activity.MainActivity;
import digi.coders.digitalkrishimitraa.Activity.Post_fullview_Activity;
import digi.coders.digitalkrishimitraa.Adapter.Profile_Viewpager;
import digi.coders.digitalkrishimitraa.Adapter.Viewpager_Adapter;
import digi.coders.digitalkrishimitraa.Helper.Constants;
import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.FragmentProfileBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Profile_Fragment extends Fragment {

    FragmentProfileBinding binding;
    String userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=  FragmentProfileBinding.inflate(inflater,container,false);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userdata = pref.getString("userdata", "{}");
        UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);
        userid=usermodel.getId();

        binding.badgeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Badge_status_Activity.class));
            }
        });

        topview();
        binding.name.setText(usermodel.getName().toString());

        Picasso.get().load(Constants.IMAGE_URL+usermodel.getImage()).placeholder(R.drawable.icon)
                .into(binding.imageicon);

        binding.tabLayout.setTabTextColors(getResources().getColor(R.color.grey), getResources().getColor(R.color.purple_700));
        binding.tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.purple_700));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.YourPost));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.YourJobPost));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.YourFundraise));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit_Profile_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        Profile_Viewpager myMatchesAdapter = new Profile_Viewpager(getActivity(),getChildFragmentManager(), binding.tabLayout.getTabCount());
        binding.viewPager.setAdapter(myMatchesAdapter);

        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        binding.fullview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                        Intent intent = new Intent(getActivity(), Post_fullview_Activity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//
//            }
//        });

        binding.followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        Intent intent = new Intent(getActivity(), Followers_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

            }
        });

        binding.followings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        Intent intent = new Intent(getActivity(), Followings_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

            }
        });

        return binding.getRoot();
    }

    public void topview(){
        ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Fetching your detail......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().mypost(userid,"topview").enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                try {
                    if(response.isSuccessful()){
                        JsonArray jsonArray= response.body().getAsJsonArray();
                        JsonObject jsonObject=jsonArray.get(0).getAsJsonObject();

                        String result= jsonObject.get("status").getAsString();
                        String message= jsonObject.get("message").getAsString();

                        if (result.equalsIgnoreCase("success")){
                            JsonArray jsonArray1=jsonObject.get("data").getAsJsonArray();
                            JsonObject jsonObject1=jsonArray1.get(0).getAsJsonObject();

                            binding.following.setText(jsonObject1.get("total_following").getAsString().toString());
                            binding.posts.setText(jsonObject1.get("total_post").getAsString().toString());
                            binding.follower.setText(jsonObject1.get("total_follower").getAsString().toString());

                            progressDialog.dismiss();
                        }
                        else{
                            Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                        progressDialog.dismiss();
                    }
                    else {
                        Toast.makeText(getContext(),"API called failed",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
                catch (Exception e){
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

}