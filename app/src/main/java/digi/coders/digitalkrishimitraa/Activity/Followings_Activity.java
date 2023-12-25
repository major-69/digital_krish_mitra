package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import digi.coders.digitalkrishimitraa.databinding.ActivityFollowersBinding;
import digi.coders.digitalkrishimitraa.databinding.ActivityFollowingsBinding;

public class Followings_Activity extends AppCompatActivity {

    ActivityFollowingsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityFollowingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

    public void back(View view) {finish();
    }
}