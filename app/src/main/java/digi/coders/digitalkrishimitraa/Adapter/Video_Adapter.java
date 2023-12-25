package digi.coders.digitalkrishimitraa.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.List;

import digi.coders.digitalkrishimitraa.Activity.Comment_Activity;
import digi.coders.digitalkrishimitraa.Activity.Post_Profile_Activity;
import digi.coders.digitalkrishimitraa.Helper.Constants;
import digi.coders.digitalkrishimitraa.Helper.Refresh;
import digi.coders.digitalkrishimitraa.Model.VideoModel;
import digi.coders.digitalkrishimitraa.R;

public class Video_Adapter extends RecyclerView.Adapter<Video_Adapter.MyViewHolder> {

    Context context;
    List<VideoModel> videoModelList;

    Refresh refresh;
    public Video_Adapter(Context context, List<VideoModel> videoModelList,Refresh refresh) {
        this.context = context;
        this.videoModelList = videoModelList;
        this.refresh = refresh;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.video_layout, parent, false);
        MyViewHolder vh=new MyViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        VideoModel videoItem=videoModelList.get(position);

        holder.username.setText(videoItem.getUserName());
        holder.desc.setText(videoItem.getDiscription());
        holder.videoView.setVideoPath(Constants.VIDEO_URL +videoItem.getVideo());

        Picasso.get().load(Constants.IMAGE_URL+videoItem.getImage()).placeholder(R.drawable.icon)
                .into(holder.image);

        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                holder.progressBar.setVisibility(View.GONE);
                mp.start();
            }
        });

        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });

        holder.likeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.likeimg.setImageTintList(ColorStateList.valueOf(Color.BLUE));
                holder.liketxt.setTextColor(Color.BLUE);
            }
        });

        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Comment_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

        holder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Post_Profile_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return videoModelList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView username,desc;
        VideoView videoView;
        ProgressBar progressBar;
        ImageView comment;
        TextView liketxt;
        ImageView likeimg,image;
        MaterialCardView profile;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            username=itemView.findViewById(R.id.username);
            videoView=itemView.findViewById(R.id.videoview);
            progressBar=itemView.findViewById(R.id.progressbar);
            comment=itemView.findViewById(R.id.comment);
            likeimg=itemView.findViewById(R.id.likebtn);
            liketxt=itemView.findViewById(R.id.likepost);
            profile=itemView.findViewById(R.id.profile);
            desc=itemView.findViewById(R.id.description);
            image=itemView.findViewById(R.id.image);

        }


    }

}
