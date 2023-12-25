package digi.coders.digitalkrishimitraa.Activity;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import digi.coders.digitalkrishimitraa.databinding.ActivityAddAmountBinding;

import digi.coders.digitalkrishimitraa.R;

public class Add_Amount_Activity extends AppCompatActivity {

    private ActivityAddAmountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddAmountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}