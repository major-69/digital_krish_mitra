package digi.coders.digitalkrishimitraa.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.databinding.ActivityOtpBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTP_Activity extends AppCompatActivity {

    ActivityOtpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String mobileNo = getIntent().getStringExtra("mobile");

        binding.mobileNo.setText("+91" + mobileNo);



        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp = binding.firstPinView.getText().toString();
                Toast.makeText(OTP_Activity.this, ""+otp, Toast.LENGTH_SHORT).show();

                if (otp.isEmpty()) {
                    Toast.makeText(OTP_Activity.this, "Please Fill OTP", Toast.LENGTH_SHORT).show();
                } else if (!(otp.length() == 4)) {
                    Toast.makeText(OTP_Activity.this, "OTP must be 4 digits", Toast.LENGTH_SHORT).show();
                } else {
                    performLogin(otp,mobileNo);
                }
            }
        });

    }

    private void performLogin(String otp, String mobileNo) {
        ProgressDialog progressDialog = new ProgressDialog(OTP_Activity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Log.d("performLogin", "function call performLogin  otp: " + otp + "mobile No: " + mobileNo);

        Call<JsonObject> call = RetrofitClient.getClient().newLogin(mobileNo, otp);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                try {
                    Log.d("performLogin", "performLogin onResponse call");
                    if (response.isSuccessful()) {
                        Log.d("performLogin", "performLogin onResponse isSuccessful");
                        Log.d("performLogin", "performLogin onResponse response: "+response.body());
                        JsonObject jsonObject = response.body();
                        String status = jsonObject.get("status").getAsString();
                        String message = jsonObject.get("message").getAsString();

                        if (status.equalsIgnoreCase("success")) {
                            JsonArray dataArray = jsonObject.get("data").getAsJsonArray();
                            JsonObject dataObject = dataArray.get(0).getAsJsonObject();

                            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(OTP_Activity.this);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("userdata", dataObject.toString());
                            editor.apply();
                            editor.commit();

                            progressDialog.dismiss();
                            Intent i=new Intent(OTP_Activity.this,MainActivity.class);
                            startActivity(i);
                            finish();

                        } else {

                            if (message.trim().equalsIgnoreCase("Mobile Not Correct!")) {
                                startActivity(new Intent(OTP_Activity.this, New_Register_Activity.class));
                            }

                        }


                    } else {
                        Log.d("performLogin", "performLogin onResponse is not Successful");
                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }
}