package digi.coders.digitalkrishimitraa.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import digi.coders.digitalkrishimitraa.Activity.Product_list_Activity;
import digi.coders.digitalkrishimitraa.Model.BazaarModal;
import digi.coders.digitalkrishimitraa.databinding.RecyclerKrisiBazaarLayBinding;

public class RecyclerKrishiBazaarAdapter extends RecyclerView.Adapter<RecyclerKrishiBazaarAdapter.BazaarVH> {

    RecyclerKrisiBazaarLayBinding binding;

    Context context;

    List<BazaarModal> bazaarModalList;

    public RecyclerKrishiBazaarAdapter(Context context, List<BazaarModal> bazaarModalList) {
        this.context = context;
        this.bazaarModalList = bazaarModalList;
    }

    @NonNull
    @Override
    public BazaarVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = RecyclerKrisiBazaarLayBinding.inflate(LayoutInflater.from(context),parent,false);
        return new BazaarVH(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BazaarVH holder, int position) {
        BazaarModal modal = bazaarModalList.get(position);
        holder.itemView.setOnClickListener(v->{
            context.startActivity(new Intent(context, Product_list_Activity.class));
        });

    }

    @Override
    public int getItemCount() {
        return bazaarModalList.size();
    }

    public class BazaarVH extends RecyclerView.ViewHolder {
        public BazaarVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
