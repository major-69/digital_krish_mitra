package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import digi.coders.digitalkrishimitraa.databinding.ActivityMessageBinding;

public class Message_Activity extends AppCompatActivity {

    ActivityMessageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}