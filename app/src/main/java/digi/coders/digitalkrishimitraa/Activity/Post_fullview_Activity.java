package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import digi.coders.digitalkrishimitraa.Adapter.Post_Adapter;
import digi.coders.digitalkrishimitraa.Helper.Constants;
import digi.coders.digitalkrishimitraa.Helper.Refresh;
import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ActivityPostFullviewBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Post_fullview_Activity extends AppCompatActivity implements Refresh {

    ActivityPostFullviewBinding binding;
    String comment,userid,postid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPostFullviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Post_fullview_Activity.this);
        String userdata = pref.getString("userdata", "{}");
        UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);
        userid=usermodel.getId();

        try {
            binding.name.setText( getIntent().getStringExtra("name").toString());
            binding.time.setText( getIntent().getStringExtra("time").toString());
            binding.citystate.setText( getIntent().getStringExtra("citystate").toString());
            binding.text.setText( getIntent().getStringExtra("text").toString());
            binding.like.setText( getIntent().getStringExtra("like").toString());
            binding.comments.setText( getIntent().getStringExtra("comments").toString());
            postid= getIntent().getStringExtra("id").toString();

            Picasso.get().load(getIntent().getStringExtra("imageicon").toString()).placeholder(R.drawable.icon)
                    .into(binding.imageicon);

            Picasso.get().load(getIntent().getStringExtra("image").toString()).placeholder(R.drawable.icon)
                    .into(binding.image);
        }
        catch (Exception e){

        }

        binding.head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Post_Profile_Activity.class);
                startActivity(intent);
            }
        });

        binding.likefull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                like();
            }
        });

        binding.reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.commententer.requestFocus();

                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput( binding.commententer, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    shareImageAndTextToWhatsApp(getApplicationContext(),binding.image,binding.text.getText().toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        binding.sendcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              comment = binding.commententer.getText().toString();
              if (comment==null||comment.equalsIgnoreCase("")){
                  Toast.makeText(Post_fullview_Activity.this, "Type comment", Toast.LENGTH_SHORT).show();
              }
              else {
                  addjob();
              }
            }
        });

    }

    public void back(View view) {
        finish();
    }

    public  void shareImageAndTextToWhatsApp(Context context, ImageView zoomageView, String message) throws IOException {

        Bitmap bitmap = ((BitmapDrawable) zoomageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, ""+"filename", null);
        Uri imageUri = Uri.parse(path);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
//            Intent shareIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:" + "" + "+91"+Message_Activity.number+ "?body=" + ""));
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
//        shareIntent.putExtra("jid", "+91"+Message_Activity.number+ "@s.whatsapp.net"); // "jid" is WhatsApp's ID for a contact
        shareIntent.setPackage("com.whatsapp");
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/*");
        context.startActivity(shareIntent);

    }

    public void addjob(){
        ProgressDialog progressDialog=new ProgressDialog(Post_fullview_Activity.this);
        progressDialog.setMessage("Adding comment......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().addcomment(userid,postid,binding.commententer.getText().toString()).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.isSuccessful()){
                    JsonArray jsonArray= response.body().getAsJsonArray();
                    JsonObject jsonObject=jsonArray.get(0).getAsJsonObject();

                    String result= jsonObject.get("status").getAsString();
                    String message= jsonObject.get("message").getAsString();

                    if (result.equalsIgnoreCase("success")){

                        Toast.makeText(Post_fullview_Activity.this, message, Toast.LENGTH_SHORT).show();

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

    public void like(){
        ProgressDialog progressDialog=new ProgressDialog(Post_fullview_Activity.this);
        progressDialog.setMessage("updating......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().like(userid,postid).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.isSuccessful()){
                    JsonArray jsonArray= response.body().getAsJsonArray();
                    JsonObject jsonObject=jsonArray.get(0).getAsJsonObject();

                    String result= jsonObject.get("status").getAsString();
                    String message= jsonObject.get("message").getAsString();

                    if (result.equalsIgnoreCase("success")){

                        if (message.equalsIgnoreCase("Liked")){
                            binding.likebtn.setImageTintList(ColorStateList.valueOf(Color.BLUE));
                            binding.likepost.setTextColor(Color.BLUE);
                        }
                        else {
                            binding.likebtn.setImageTintList(ColorStateList.valueOf(Color.GRAY));
                            binding.likepost.setTextColor(Color.GRAY);
                        }

                        Toast.makeText(getApplicationContext(), ""+message, Toast.LENGTH_SHORT).show();

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


    @Override
    public void refresh() {

    }
}