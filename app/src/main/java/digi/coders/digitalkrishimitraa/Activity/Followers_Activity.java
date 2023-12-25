package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import digi.coders.digitalkrishimitraa.databinding.ActivityFollowersBinding;

public class Followers_Activity extends AppCompatActivity {

    ActivityFollowersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityFollowersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




    }

    public void back(View view) {finish();
    }
}