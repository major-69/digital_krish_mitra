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

import java.util.ArrayList;
import java.util.List;

import digi.coders.digitalkrishimitraa.Adapter.Add_Money_Adapter;
import digi.coders.digitalkrishimitraa.Model.MoneyModal;
import digi.coders.digitalkrishimitraa.databinding.ActivityAddAmountBinding;

import digi.coders.digitalkrishimitraa.R;

public class Add_Amount_Activity extends AppCompatActivity {

    private ActivityAddAmountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddAmountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<MoneyModal> modalList = new ArrayList<>();

        modalList.add(new MoneyModal("1 "+getResources().getString(R.string.month),"300"));
        modalList.add(new MoneyModal("2 "+getResources().getString(R.string.month),"600"));
        modalList.add(new MoneyModal("4 "+getResources().getString(R.string.month),"1200"));
        modalList.add(new MoneyModal("5 "+getResources().getString(R.string.month),"1500"));
        modalList.add(new MoneyModal("6 "+getResources().getString(R.string.month),"1800"));
        modalList.add(new MoneyModal("12 "+getResources().getString(R.string.month),"3600"));
        modalList.add(new MoneyModal("24 "+getResources().getString(R.string.month),"7200"));
        modalList.add(new MoneyModal("36 "+getResources().getString(R.string.month),"10800"));
        modalList.add(new MoneyModal("48 "+getResources().getString(R.string.month),"14400"));
        modalList.add(new MoneyModal("60 "+getResources().getString(R.string.month),"18000"));

        binding.moneyRecycler.setAdapter(new Add_Money_Adapter(Add_Amount_Activity.this,modalList));

        binding.addMoneyToolbar.setNavigationOnClickListener(view -> {
            finish();
        });


    }
}