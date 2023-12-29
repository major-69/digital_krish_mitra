package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ActivityForgetPassBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPass_Activity extends AppCompatActivity {

    ActivityForgetPassBinding binding;
    String mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding=ActivityForgetPassBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());


      binding.sendotp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if (validateRegistrationform()){
                  mobile=binding.forgotMobiletext.getText().toString();
                  verifymobile();

              }

          }
      });


    }

    public void back(View view) {finish();
    }
    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ForgetPass_Activity.this, R.style.DialogStyle);
        bottomSheetDialog.setContentView(R.layout.verfiy_otp_bts);

        PinView pinView= bottomSheetDialog.findViewById(R.id.pinview);

        bottomSheetDialog.findViewById(R.id.confirmotp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyotp(mobile,pinView.getText().toString());
            }
        });

        bottomSheetDialog.show();
    }

    private boolean validateRegistrationform() {
        boolean status=true;
        if(TextUtils.isEmpty(binding.forgotMobiletext.getText())){
            binding.forgotMobiletext.setError("Mobile required");
            binding.forgotMobiletext.requestFocus();
            status=false;
        }
        return status;
    }

    public void verifymobile() {
        ProgressDialog progressDialog = new ProgressDialog(ForgetPass_Activity.this);
        progressDialog.setMessage("Verifying number......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().forgetpassword1("verify_mobile",mobile).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                try {
                    if (response.isSuccessful()) {
                        JsonArray jsonArray = response.body().getAsJsonArray();
                        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

                        String result = jsonObject.get("status").getAsString();
                        String message = jsonObject.get("message").getAsString();

                        if (result.equalsIgnoreCase("success")) {

                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                            showBottomSheetDialog();

                            progressDialog.dismiss();

                        } else {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(getApplicationContext(), "API called failed", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                } catch (Exception e) {
//                    Toast.makeText(Myprofile_Activity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
    public void verifyotp(String mobile,String otp) {
        ProgressDialog progressDialog = new ProgressDialog(ForgetPass_Activity.this);
        progressDialog.setMessage("Verifying number......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().forgetpassword2("otp",mobile,otp).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                try {
                    if (response.isSuccessful()) {
                        JsonArray jsonArray = response.body().getAsJsonArray();
                        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

                        String result = jsonObject.get("status").getAsString();
                        String message = jsonObject.get("message").getAsString();

                        if (result.equalsIgnoreCase("success")) {

                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ForgetPass_Activity.this, ChangePass_Activity.class);
                            intent.putExtra("otp", otp);
                            intent.putExtra("mobile", mobile);
                            startActivity(intent);
                            finish();

                            progressDialog.dismiss();

                        } else {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(getApplicationContext(), "API called failed", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                } catch (Exception e) {
//                    Toast.makeText(Myprofile_Activity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }


}