package digi.coders.digitalkrishimitraa.Adapter;


import static android.content.Context.INPUT_METHOD_SERVICE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
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
import digi.coders.digitalkrishimitraa.Model.PostModel;
import digi.coders.digitalkrishimitraa.Model.ShowCommentModel;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ShowCommentLayoutBinding;

public class ShowComment_Adapter extends RecyclerView.Adapter<ShowComment_Adapter.TaskDataHolder> {

    Context context;
    List<ShowCommentModel> postModelList;
    Refresh refresh;

    ShowCommentLayoutBinding binding;

    public ShowComment_Adapter(Context context, List<ShowCommentModel> postModelList, Refresh refresh) {
        this.context = context;
        this.postModelList=postModelList;
        this.refresh = refresh;
    }

    @NonNull
    @Override
    public TaskDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.show_comment_layout,parent,false);
        return new TaskDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskDataHolder holder, int position) {
        ShowCommentModel model=postModelList.get(position);

        binding.comment.setText(model.getComment());
        binding.name.setText(model.getUsername());

        Picasso.get().load(Constants.IMAGE_URL+model.getUserIdImage()).placeholder(R.drawable.icon)
                .into(binding.image);


    }
    @Override
    public int getItemCount() { return postModelList.size(); }

    public class TaskDataHolder extends RecyclerView.ViewHolder {
        MaterialCardView card;
        TextView liketxt,text,name,time,citystate,like,comments;
        ImageView likeimg,image,imageicon;
        LinearLayout likefull,comment,userprofile,share;

        public TaskDataHolder(@NonNull View itemView) {
            super(itemView);


        }
    }



}
