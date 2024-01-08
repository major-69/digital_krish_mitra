package digi.coders.digitalkrishimitraa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import digi.coders.digitalkrishimitraa.Activity.Add_Fundraise_Activity;
import digi.coders.digitalkrishimitraa.databinding.ActivityCreateFarmerFpoBinding;
import digi.coders.digitalkrishimitraa.databinding.VerfiyOtpBtsBinding;

public class Create_Farmer_FPO extends AppCompatActivity {

    ActivityCreateFarmerFpoBinding binding;

    VerfiyOtpBtsBinding btsBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateFarmerFpoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btsBinding = VerfiyOtpBtsBinding.inflate(LayoutInflater.from(Create_Farmer_FPO.this),null,false);

        binding.sendOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               handelBottomSheet();
            }
        });
        List<String> fundraiseList = new ArrayList<>();
        fundraiseList.add(getResources().getString(R.string.choose));
        fundraiseList.add(getResources().getString(R.string.trackter));
        fundraiseList.add(getResources().getString(R.string.Rotavator));
        fundraiseList.add(getResources().getString(R.string.Troli));
        fundraiseList.add(getResources().getString(R.string.Solar_Pump));
        fundraiseList.add(getResources().getString(R.string.Spray_Machine));
        fundraiseList.add(getResources().getString(R.string.Land_purchase));
        fundraiseList.add(getResources().getString(R.string.Medical_treatment));


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Create_Farmer_FPO.this,R.layout.spinner_layout,fundraiseList);
        binding.equipmentName.setAdapter(arrayAdapter);







    }

    private void handelBottomSheet() {

        View view = btsBinding.getRoot();
        BottomSheetDialog dialog = new BottomSheetDialog(Create_Farmer_FPO.this);
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }

        dialog.setContentView(view);
        dialog.show();

    }
}