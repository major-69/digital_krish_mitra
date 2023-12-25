package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import digi.coders.digitalkrishimitraa.databinding.ActivitySearchBinding;
import digi.coders.digitalkrishimitraa.databinding.ActivitySplashBinding;

public class Search_Activity extends AppCompatActivity {

    ActivitySearchBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }

    public void back(View view) {finish();
    }
}