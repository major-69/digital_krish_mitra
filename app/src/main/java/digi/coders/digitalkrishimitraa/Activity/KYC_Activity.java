package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ActivityKycBinding;


public class KYC_Activity extends AppCompatActivity {

    ActivityKycBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityKycBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.identityProofVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KYC_Activity.this,Activity_Identity_Verification.class));
            }
        });

        binding.bankVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KYC_Activity.this,BankVerificationActivity.class));
            }
        });



    }
}