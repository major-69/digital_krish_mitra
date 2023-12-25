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
import com.squareup.picasso.Picasso;

import java.util.List;

import digi.coders.digitalkrishimitraa.Activity.MainActivity;
import digi.coders.digitalkrishimitraa.Activity.Post_fullview_Activity;
import digi.coders.digitalkrishimitraa.Activity.Registration_Activity;
import digi.coders.digitalkrishimitraa.Helper.Constants;
import digi.coders.digitalkrishimitraa.Helper.Refresh;
import digi.coders.digitalkrishimitraa.Model.DonateModel;
import digi.coders.digitalkrishimitraa.Model.DonationModel;
import digi.coders.digitalkrishimitraa.Model.MyPostModel;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.MyPostLayoutBinding;
import digi.coders.digitalkrishimitraa.databinding.MydonationLayoutBinding;

public class MyDonation_Adapter extends RecyclerView.Adapter<MyDonation_Adapter.TaskDataHolder> {

    Context context;
    MydonationLayoutBinding binding;
    List<DonationModel> donateModelList;
    Refresh refresh;
    public MyDonation_Adapter(Context context,List<DonationModel> postModelList, Refresh refresh) {
        this.context = context;
        this.donateModelList=postModelList;
        this.refresh = refresh;
    }

    @NonNull
    @Override
    public TaskDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MydonationLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TaskDataHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull TaskDataHolder holder, int position) {

        DonationModel model=donateModelList.get(position);

        binding.name.setText(model.getUserName());
        binding.price.setText("₹ "+model.getAmount());
        binding.time.setText("On : "+model.getDateField());

        Picasso.get().load(Constants.IMAGE_URL+model.getImage()).placeholder(R.drawable.icon)
                .into(binding.icon);

    }
    @Override
    public int getItemCount() { return donateModelList.size(); }

    public class TaskDataHolder extends RecyclerView.ViewHolder {

        public TaskDataHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
