package digi.coders.digitalkrishimitraa.Adapter;


import static android.content.Context.INPUT_METHOD_SERVICE;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import digi.coders.digitalkrishimitraa.Activity.Comment_Activity;
import digi.coders.digitalkrishimitraa.Activity.Post_Profile_Activity;
import digi.coders.digitalkrishimitraa.Activity.Post_fullview_Activity;
import digi.coders.digitalkrishimitraa.Fragment.Foryou_Fragment;
import digi.coders.digitalkrishimitraa.Helper.Constants;
import digi.coders.digitalkrishimitraa.Helper.Refresh;
import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.Model.PostModel;
import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Post_Adapter extends RecyclerView.Adapter<Post_Adapter.TaskDataHolder> {

    Context context;
    List<PostModel> postModelList;
    Refresh refresh;

    String postid,userid;


    public Post_Adapter(Context context, List<PostModel> postModelList, Refresh refresh) {
        this.context = context;
        this.postModelList=postModelList;
        this.refresh = refresh;
    }

    @NonNull
    @Override
    public TaskDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout,parent,false);
        return new TaskDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskDataHolder holder, int position) {
        PostModel model=postModelList.get(position);


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        String userdata = pref.getString("userdata", "{}");
        UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);
        userid=usermodel.getId();

        holder.name.setText(model.getName());
        holder.time.setText(model.getDays());
        holder.citystate.setText(model.getCity()+", "+model.getState());
        holder.liketxt.setText(model.getTotalLike()+" "+context.getString(R.string.like));
        holder.comments.setText(model.getTotalCumment()+" "+context.getString(R.string.comment));
        holder.text.setText(model.getDescritpion());

//        Picasso.get().load(Constants.IMAGE_URL+model.getImage()).placeholder(R.drawable.icon)
//                .into(holder.imageicon);
//
//        Picasso.get().load(Constants.IMAGE_URL+model.getUserImage()).placeholder(R.drawable.icon)
//                .into(holder.image);

        holder.likefull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                like(holder, model.getPostId());
            }
        });

        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showBottomSheetDialog();
                Intent intent = new Intent(context, Comment_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", model.getPostId());
                context.startActivity(intent);

            }
        });

        holder.image .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Post_fullview_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", model.getPostId());
                intent.putExtra("name", model.getName());
                intent.putExtra("time", model.getDays());
                intent.putExtra("citystate",model.getCity()+", "+model.getState());
                intent.putExtra("like", model.getTotalLike());
                intent.putExtra("comments", model.getTotalCumment());
                intent.putExtra("text", model.getDescritpion());
                intent.putExtra("imageicon",Constants.IMAGE_URL+model.getImage());
                intent.putExtra("image",Constants.IMAGE_URL+model.getUserImage());
                context.startActivity(intent);
            }
        });

        holder.option.setOnClickListener(view -> {

            PopupMenu popupMenu = new PopupMenu(context,holder.option);
            popupMenu.getMenuInflater().inflate(R.menu.post_option_menu,popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {

                if (menuItem.getItemId() == R.id.abuseReport){
                    Toast.makeText(context, "Report abuse", Toast.LENGTH_SHORT).show();
                }else if (menuItem.getItemId() == R.id.block){
                    Toast.makeText(context, "Block User", Toast.LENGTH_SHORT).show();
                }

                return true;
            });

            popupMenu.show();

        });


        holder.userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Post_Profile_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    shareImageAndTextToWhatsApp(context,holder.image,holder.text.getText().toString(),holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
    @Override
    public int getItemCount() { return postModelList.size(); }

    public class TaskDataHolder extends RecyclerView.ViewHolder {
        MaterialCardView card;
        TextView liketxt,text,name,time,citystate,like,comments;
        ImageView likeimg,image,imageicon,option;
        LinearLayout likefull,comment,userprofile,share;

        public TaskDataHolder(@NonNull View itemView) {
            super(itemView);
            likeimg=itemView.findViewById(R.id.likebtn);
            liketxt=itemView.findViewById(R.id.likepost);
            likefull=itemView.findViewById(R.id.likefull);
            comment=itemView.findViewById(R.id.comment);
            userprofile=itemView.findViewById(R.id.userprofile);
            share=itemView.findViewById(R.id.share);
            image=itemView.findViewById(R.id.image);
            text=itemView.findViewById(R.id.text);
            name=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
            option=itemView.findViewById(R.id.option);
            citystate=itemView.findViewById(R.id.citystate);
            like=itemView.findViewById(R.id.like);
            comments=itemView.findViewById(R.id.comments);
            imageicon=itemView.findViewById(R.id.imageicon);
        }
    }

    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogStyle);
        bottomSheetDialog.setContentView(R.layout.comment_layout);

        bottomSheetDialog.findViewById(R.id.comment).requestFocus();

        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(bottomSheetDialog.findViewById(R.id.comment), InputMethodManager.SHOW_IMPLICIT);

        bottomSheetDialog.show();
    }

    @SuppressLint("IntentReset")
    public  void shareImageAndTextToWhatsApp(Context context, ImageView zoomageView, String message, Post_Adapter.TaskDataHolder holder) throws IOException {

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

    public void like(Post_Adapter.TaskDataHolder holder,String postid){
        ProgressDialog progressDialog=new ProgressDialog(holder.itemView.getContext());
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
                            holder.likeimg.setImageTintList(ColorStateList.valueOf(Color.BLUE));
                            holder.liketxt.setTextColor(Color.BLUE);
                        }
                        else {
                            holder.likeimg.setImageTintList(ColorStateList.valueOf(Color.GRAY));
                            holder.liketxt.setTextColor(Color.GRAY);
                        }

                        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
                        refresh.refresh();
                    }
                    else{
                        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.dismiss();
                }
                else {
                    Toast.makeText(context,"API called failed",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

}
