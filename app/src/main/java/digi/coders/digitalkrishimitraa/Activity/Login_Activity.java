package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.databinding.ActivityLoginBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity {

    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateRegistrationform()){
                    login();
                }
            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, Registration_Activity.class);
                startActivity(intent);
            }
        });

        binding.forgotPasText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, ForgetPass_Activity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateRegistrationform() {
        boolean status=true;
        if(TextUtils.isEmpty(binding.emailLogin.getText())){
            binding.emailLogin.setError("Email/Mobile required");
            binding.emailLogin.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.passwordLogin.getText())){
            binding.passwordLogin.setError("Password required");
            binding.passwordLogin.requestFocus();
            status=false;
        }
        return status;
    }

    public void login(){
        ProgressDialog progressDialog=new ProgressDialog(Login_Activity.this);
        progressDialog.setMessage("Fetching your detail......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().Login(binding.emailLogin.getText().toString(),binding.passwordLogin.getText().toString()).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                try {
                    if(response.isSuccessful()){
                        JsonArray jsonArray= response.body().getAsJsonArray();
                        JsonObject jsonObject=jsonArray.get(0).getAsJsonObject();

                        String result= jsonObject.get("status").getAsString();
                        String message= jsonObject.get("message").getAsString();

                        if (result.equalsIgnoreCase("success")){

                            JsonArray jsonArray1=jsonObject.get("data").getAsJsonArray();
                            JsonObject jsonObject1=jsonArray1.get(0).getAsJsonObject();

                            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("userdata", jsonObject1.toString());
                            editor.apply();
                            editor.commit();
                            Intent i=new Intent(Login_Activity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                        progressDialog.dismiss();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"API called failed",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
                catch (Exception e){}

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

}