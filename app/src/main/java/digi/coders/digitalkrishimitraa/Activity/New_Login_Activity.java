package digi.coders.digitalkrishimitraa.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.databinding.ActivityNewLoginBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class New_Login_Activity extends AppCompatActivity {

    ActivityNewLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.loginMobile.getEditText().getText().toString().isEmpty()) {
                    Toast.makeText(New_Login_Activity.this, "mobile not empty", Toast.LENGTH_SHORT).show();
                } else if (!(binding.loginMobile.getEditText().getText().toString().length() == 10)) {
                    Toast.makeText(New_Login_Activity.this, "mobile no must be 10-digits", Toast.LENGTH_SHORT).show();
                } else {
                   // sendOtp();
                    startActivity(new Intent(New_Login_Activity.this, OTP_Activity.class).putExtra("mobile",binding.loginMobile.getEditText().getText().toString()));

                }

            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(New_Login_Activity.this, New_Register_Activity.class));
            }
        });

    }

    private void sendOtp() {

        Call<JsonObject> call = RetrofitClient.getClient().sendOtp(binding.loginMobile.getEditText().toString());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {



                } else {

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }
}