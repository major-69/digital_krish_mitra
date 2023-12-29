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
import digi.coders.digitalkrishimitraa.databinding.ActivityRegistrationBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration_Activity extends AppCompatActivity {

    ActivityRegistrationBinding binding;
    String userid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateRegistrationform()){
                    register();
                }
            }
        });


    }
    private void showBottomSheetDialog(String userid) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Registration_Activity.this, R.style.DialogStyle);
        bottomSheetDialog.setContentView(R.layout.verfiy_otp_bts);

        PinView pinView= bottomSheetDialog.findViewById(R.id.pinview);



        bottomSheetDialog.findViewById(R.id.confirmotp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOTP(userid,pinView.getText().toString());

            }
        });

        bottomSheetDialog.show();
    }

    private boolean validateRegistrationform()
    {
        boolean status=true;
        if(TextUtils.isEmpty(binding.name.getText())){
            binding.name.setError("Name required");
            binding.name.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.email.getText())){
            binding.email.setError("Email required");
            binding.email.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.mobile.getText())){
            binding.mobile.setError("Mobile no. required");
            binding.mobile.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.password.getText())){
            binding.password.setError("Password required");
            binding.password.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.passwordcon.getText())){
            binding.passwordcon.setError("Confirm Password required");
            binding.passwordcon.requestFocus();
            status=false;
        }
        else if (!binding.passwordcon.getText().toString().equalsIgnoreCase(binding.password.getText().toString())){
            binding.passwordcon.setError("Password are not same");
            binding.password.setError("Password are not same");
            binding.passwordcon.requestFocus();
            binding.password.requestFocus();
            status=false;
        }
        return status;
    }

    public void register(){
        ProgressDialog progressDialog=new ProgressDialog(Registration_Activity.this);
        progressDialog.setMessage("Adding message......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().Registration(binding.name.getText().toString(),binding.email.getText().toString(),binding.mobile.getText().toString(),binding.password.getText().toString()).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.isSuccessful()){
                    JsonArray jsonArray= response.body().getAsJsonArray();
                    JsonObject jsonObject=jsonArray.get(0).getAsJsonObject();

                    String result= jsonObject.get("status").getAsString();
                    String message= jsonObject.get("message").getAsString();

                    if (result.equalsIgnoreCase("success")){

                        Toast.makeText(Registration_Activity.this, message, Toast.LENGTH_SHORT).show();
                        JsonArray jsonArray1 = jsonObject.get("data").getAsJsonArray();
                        JsonObject jsonObject1 = jsonArray1.get(0).getAsJsonObject();
                        userid= jsonObject1.get("id").getAsString();

                        showBottomSheetDialog(userid);

                    }
                    else{
                        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.dismiss();
                }
                else {
                    Toast.makeText(getApplicationContext(),"API called failed",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }
    public void verifyOTP(String userid,String otp) {
        ProgressDialog progressDialog = new ProgressDialog(Registration_Activity.this);
        progressDialog.setMessage("Verifying otp......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().Verifyotp(userid,otp).enqueue(new Callback<JsonArray>() {
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

                            Intent intent = new Intent(Registration_Activity.this, Login_Activity.class);
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