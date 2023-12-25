package digi.coders.digitalkrishimitraa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import digi.coders.digitalkrishimitraa.Model.BadgeModal;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.RecyclerBadgeLayoutBinding;

public class Badge_status_adapter extends RecyclerView.Adapter<Badge_status_adapter.Badge_VH> {

    List<BadgeModal> badgeModalList;

    Context context;

    public Badge_status_adapter(List<BadgeModal> badgeModalList, Context context) {
        this.badgeModalList = badgeModalList;
        this.context = context;
    }

    RecyclerBadgeLayoutBinding binding;

    @Override
    public Badge_VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = RecyclerBadgeLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Badge_VH(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull Badge_VH holder, int position) {

        BadgeModal modal = badgeModalList.get(position);
        binding.badgeRankNumber.setText((holder.getAdapterPosition() + 1) + "");
        binding.badgeRankName.setText(modal.getBadgeRankName());
        binding.badgeRankNumberText.setText(context.getString(R.string.Rank) + " " + (holder.getAdapterPosition() + 1));

        if (holder.getAdapterPosition() == 0) {
            binding.topViewDesign.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return badgeModalList.size();
    }

    public class Badge_VH extends RecyclerView.ViewHolder {
        public Badge_VH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
