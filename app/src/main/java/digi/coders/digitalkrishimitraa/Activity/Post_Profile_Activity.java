package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import digi.coders.digitalkrishimitraa.Adapter.My_Post_Adapter;
import digi.coders.digitalkrishimitraa.databinding.ActivityPostProfileBinding;

public class Post_Profile_Activity extends AppCompatActivity {

    ActivityPostProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityPostProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        My_Post_Adapter adapter2 = new My_Post_Adapter(getApplicationContext());
//        binding.recycler.setAdapter(adapter2);
//        binding.recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),3, LinearLayoutManager.VERTICAL,false));

        binding.followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Followers_Activity.class));
            }
        });


        binding.followings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Followings_Activity.class));
            }
        });


    }

    public void back(View view) {
        finish();
    }
}