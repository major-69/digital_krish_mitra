package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.databinding.ActivityAddJobBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Job_Activity extends AppCompatActivity {

    ActivityAddJobBinding binding;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddJobBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Add_Job_Activity.this);
        String userdata = pref.getString("userdata", "{}");
        UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);
        userid=usermodel.getId();


    }

    public void back(View view) {finish();
    }

    public void finish(View view) {
        if (validateRegistrationform()){
            addjob();
        }

    }

    private boolean validateRegistrationform() {
        boolean status=true;
        if(TextUtils.isEmpty(binding.jobtitle.getText())){
            binding.jobtitle.setError("Job Title required");
            binding.jobtitle.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.companyname.getText())){
            binding.companyname.setError("Company Name required");
            binding.companyname.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.experience.getText())){
            binding.experience.setError("Experience required");
            binding.experience.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.qualification.getText())){
            binding.qualification.setError("Qualification required");
            binding.qualification.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.location.getText())){
            binding.location.setError("Location required");
            binding.location.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.jobdesc.getText())){
            binding.jobdesc.setError("Job Description required");
            binding.jobdesc.requestFocus();
            status=false;
        }
        return status;
    }

    public void addjob(){
        ProgressDialog progressDialog=new ProgressDialog(Add_Job_Activity.this);
        progressDialog.setMessage("Adding message......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().addjob(userid,binding.jobtitle.getText().toString(),binding.companyname.getText().toString(),binding.experience.getText().toString(),binding.qualification.getText().toString(),
                binding.packages.getText().toString(),binding.location.getText().toString(),binding.jobtype.getText().toString(),binding.jobdesc.getText().toString()).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.isSuccessful()){
                    JsonArray jsonArray= response.body().getAsJsonArray();
                    JsonObject jsonObject=jsonArray.get(0).getAsJsonObject();

                    String result= jsonObject.get("status").getAsString();
                    String message= jsonObject.get("message").getAsString();

                    if (result.equalsIgnoreCase("success")){

                        Toast.makeText(Add_Job_Activity.this, message, Toast.LENGTH_SHORT).show();

                        finish();
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

}