package digi.coders.digitalkrishimitraa.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import digi.coders.digitalkrishimitraa.Activity.Post_fullview_Activity;
import digi.coders.digitalkrishimitraa.Helper.Constants;
import digi.coders.digitalkrishimitraa.Helper.Refresh;
import digi.coders.digitalkrishimitraa.Model.MyPostModel;
import digi.coders.digitalkrishimitraa.Model.PostModel;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.MyPostLayoutBinding;

public class My_Post_Adapter extends RecyclerView.Adapter<My_Post_Adapter.TaskDataHolder> {

    Context context;
    MyPostLayoutBinding binding;
    List<MyPostModel> postModelList;
    Refresh refresh;
    public My_Post_Adapter(Context context,List<MyPostModel> postModelList, Refresh refresh) {
        this.context = context;
        this.postModelList=postModelList;
        this.refresh = refresh;
    }

    @NonNull
    @Override
    public TaskDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MyPostLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TaskDataHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull TaskDataHolder holder, int position) {
        MyPostModel model=postModelList.get(position);

        Picasso.get().load(Constants.IMAGE_URL+model.getImg()).placeholder(R.drawable.icon)
                .into(binding.fullview);


    }
    @Override
    public int getItemCount() { return postModelList.size(); }

    public class TaskDataHolder extends RecyclerView.ViewHolder {

        public TaskDataHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
