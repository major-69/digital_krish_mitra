package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ActivityAddFundraiseBinding;
import digi.coders.digitalkrishimitraa.databinding.ActivityCommentBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Comment_Activity extends AppCompatActivity {

    ActivityCommentBinding binding;
    String comment,userid,postid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toast.makeText(Comment_Activity.this, "Created", Toast.LENGTH_SHORT).show();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Comment_Activity.this);
        String userdata = pref.getString("userdata", "{}");
        UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);
        userid=usermodel.getId();

        postid= getIntent().getStringExtra("id").toString();

        binding.reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.comment.requestFocus();

                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput( binding.comment, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        binding.comment.requestFocus();

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput( binding.comment, InputMethodManager.SHOW_IMPLICIT);

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comment = binding.comment.getText().toString();
                if (comment==null||comment.equalsIgnoreCase("")){
                    Toast.makeText(Comment_Activity.this, "Type comment", Toast.LENGTH_SHORT).show();
                }
                else {
                    addjob();
                }
            }
        });

    }
    public void addjob(){
        ProgressDialog progressDialog=new ProgressDialog(Comment_Activity.this);
        progressDialog.setMessage("Adding comment......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().addcomment(userid,postid,binding.comment.getText().toString()).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.isSuccessful()){
                    JsonArray jsonArray= response.body().getAsJsonArray();
                    JsonObject jsonObject=jsonArray.get(0).getAsJsonObject();

                    String result= jsonObject.get("status").getAsString();
                    String message= jsonObject.get("message").getAsString();

                    if (result.equalsIgnoreCase("success")){

                        Toast.makeText(Comment_Activity.this, message, Toast.LENGTH_SHORT).show();

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