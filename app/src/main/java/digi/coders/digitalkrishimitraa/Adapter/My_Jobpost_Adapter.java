package digi.coders.digitalkrishimitraa.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import digi.coders.digitalkrishimitraa.R;

public class My_Jobpost_Adapter extends RecyclerView.Adapter<My_Jobpost_Adapter.TaskDataHolder> {

    Context context;

    public My_Jobpost_Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TaskDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_post_layout,parent,false);
        return new TaskDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskDataHolder holder, int position) {




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
