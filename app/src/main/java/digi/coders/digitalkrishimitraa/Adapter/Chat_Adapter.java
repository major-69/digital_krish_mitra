package digi.coders.digitalkrishimitraa.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;

import digi.coders.digitalkrishimitraa.Activity.MainActivity;
import digi.coders.digitalkrishimitraa.Activity.Message_Activity;
import digi.coders.digitalkrishimitraa.Activity.Post_fullview_Activity;
import digi.coders.digitalkrishimitraa.Activity.Registration_Activity;
import digi.coders.digitalkrishimitraa.R;

public class Chat_Adapter extends RecyclerView.Adapter<Chat_Adapter.TaskDataHolder> {

    Context context;

    public Chat_Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TaskDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_layout,parent,false);
        return new TaskDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskDataHolder holder, int position) {


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Message_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }
    @Override
    public int getItemCount() { return 10; }

    public class TaskDataHolder extends RecyclerView.ViewHolder {
        MaterialCardView card;
        TextView liketxt;
        ImageView likeimg;
        LinearLayout likefull,comment;
        public TaskDataHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
