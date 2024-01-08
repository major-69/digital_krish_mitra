package digi.coders.digitalkrishimitraa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import digi.coders.digitalkrishimitraa.Model.MoneyModal;
import digi.coders.digitalkrishimitraa.databinding.MoneyLayoutBinding;

public class Add_Money_Adapter extends RecyclerView.Adapter<Add_Money_Adapter.MoneyVH> {

    Context context;
    List<MoneyModal> moneyModalList;

    TextView textView;

    public Add_Money_Adapter(Context context, List<MoneyModal> moneyModalList, TextView amountNeedToAdd) {
        this.context = context;
        this.moneyModalList = moneyModalList;
        this.textView = amountNeedToAdd;
    }

    MoneyLayoutBinding binding;

    @NonNull
    @Override
    public MoneyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MoneyLayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        return new MoneyVH(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MoneyVH holder, int position) {
        MoneyModal model = moneyModalList.get(position);
        binding.plan.setText(model.getMonth() +" ₹"+model.getAmount());
        textView.setText("₹"+model.getAmount());
    }

    @Override
    public int getItemCount() {
        return moneyModalList.size();
    }

    public class MoneyVH extends RecyclerView.ViewHolder {
        public MoneyVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
