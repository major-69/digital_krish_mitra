package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.databinding.ActivityChangePassBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePass_Activity extends AppCompatActivity {

    ActivityChangePassBinding binding;
    String otp,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChangePassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       otp =  getIntent().getStringExtra("otp");
       mobile = getIntent().getStringExtra("mobile");


        binding.Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateRegistrationform()){

                    changepass();
                }
            }
        });

    }

    private boolean validateRegistrationform() {
        boolean status=true;
//        if(TextUtils.isEmpty(binding.oldpass.getText())){
//            binding.oldpass.setError("Old Password required");
//            binding.oldpass.requestFocus();
//            status=false;
//        }
         if (TextUtils.isEmpty(binding.newpass.getText())){
            binding.newpass.setError("New Password required");
            binding.newpass.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.confirmpass.getText())){
            binding.confirmpass.setError("Confirm Password required");
            binding.confirmpass.requestFocus();
            status=false;
        }
        return status;
    }
    public void changepass() {
        ProgressDialog progressDialog = new ProgressDialog(ChangePass_Activity.this);
        progressDialog.setMessage("Changing password......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().forgetpassword3("password",mobile,otp,binding.newpass.getText().toString(),
                binding.confirmpass.getText().toString()).enqueue(new Callback<JsonArray>() {
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

                            Intent intent = new Intent(ChangePass_Activity.this, Login_Activity.class);
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