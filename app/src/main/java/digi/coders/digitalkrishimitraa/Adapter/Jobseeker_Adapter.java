package digi.coders.digitalkrishimitraa.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
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

import java.util.List;

import digi.coders.digitalkrishimitraa.Activity.Job_fullview_Activity;
import digi.coders.digitalkrishimitraa.Activity.MainActivity;
import digi.coders.digitalkrishimitraa.Activity.Post_fullview_Activity;
import digi.coders.digitalkrishimitraa.Activity.Registration_Activity;
import digi.coders.digitalkrishimitraa.Helper.Refresh;
import digi.coders.digitalkrishimitraa.Model.JobModel;
import digi.coders.digitalkrishimitraa.Model.MyPostModel;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.JobDesignBinding;
import digi.coders.digitalkrishimitraa.databinding.MyPostLayoutBinding;

public class Jobseeker_Adapter extends RecyclerView.Adapter<Jobseeker_Adapter.TaskDataHolder> {

    Context context;
    JobDesignBinding binding;
    List<JobModel> postModelList;
    Refresh refresh;

    public Jobseeker_Adapter(Context context, List<JobModel> postModelList, Refresh refresh) {
        this.context = context;
        this.postModelList=postModelList;
        this.refresh = refresh;

    }

    @NonNull
    @Override
    public TaskDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = JobDesignBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TaskDataHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull TaskDataHolder holder, int position) {

        JobModel model=postModelList.get(position);

        try {

        binding.title.setText(model.getJabtitle().toString());
        binding.location.setText(model.getLocation().toString());
        binding.qualification.setText(model.getQualification().toString());
        binding.experience.setText(model.getExperition().toString());
        binding.packages.setText(model.getPackge().toString());
        binding.name.setText(model.getUsername().toString());
        }catch (Exception e){
            Log.d("jobsssss", "onResponse: "+e.toString());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, Job_fullview_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("title", model.getJabtitle().toString());
                intent.putExtra("location", model.getLocation().toString());
                intent.putExtra("qualification", model.getQualification().toString());
                intent.putExtra("experience", model.getExperition().toString());
                intent.putExtra("packages", model.getPackge().toString());
                intent.putExtra("name", model.getUsername().toString());
                intent.putExtra("jobtype", model.getJobtype().toString());
                intent.putExtra("desc", model.getJabdescription().toString());
                intent.putExtra("company", model.getCompanyname().toString());
                intent.putExtra("id", model.getId().toString());
                intent.putExtra("time", model.getPostedTime().toString());
                context.startActivity(intent);
            }
        });


    }
    @Override
    public int getItemCount() { return postModelList.size(); }

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
