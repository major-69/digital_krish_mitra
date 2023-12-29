package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ActivityProductFullDetailBinding;

public class Product_Full_Detail_Activity extends AppCompatActivity {

    ActivityProductFullDetailBinding binding;


    int qty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductFullDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backBtn.setNavigationOnClickListener(view -> {
            finish();
        });







    }
}