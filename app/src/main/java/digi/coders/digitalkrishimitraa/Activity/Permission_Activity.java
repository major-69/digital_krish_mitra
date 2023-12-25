package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ActivityPermissionBinding;

public class Permission_Activity extends AppCompatActivity {

    ActivityPermissionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityPermissionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




    }

    public void back(View view) {finish();
    }
}