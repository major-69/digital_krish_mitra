package digi.coders.digitalkrishimitraa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import digi.coders.digitalkrishimitraa.Model.HistoryModal;
import digi.coders.digitalkrishimitraa.databinding.HistoryRecyclerLayoutBinding;

public class RecyclerHistoryAdapter extends RecyclerView.Adapter<RecyclerHistoryAdapter.HistoryVH> {

    HistoryRecyclerLayoutBinding binding;

    Context context;
    List<HistoryModal> historyModalList;

    public RecyclerHistoryAdapter(Context context, List<HistoryModal> historyModalList) {
        this.context = context;
        this.historyModalList = historyModalList;
    }

    @NonNull
    @Override
    public HistoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = HistoryRecyclerLayoutBinding.inflate(LayoutInflater.from(context),parent,false);

        return new HistoryVH(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return historyModalList.size();
    }

    public class HistoryVH extends RecyclerView.ViewHolder {
        public HistoryVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
