package digi.coders.digitalkrishimitraa.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import digi.coders.digitalkrishimitraa.Activity.Product_Full_Detail_Activity;
import digi.coders.digitalkrishimitraa.Model.ProductModal;
import digi.coders.digitalkrishimitraa.databinding.RecyclerProductLayoutBinding;

public class RecyclerProductAdapter extends RecyclerView.Adapter<RecyclerProductAdapter.ProductVH> {

    RecyclerProductLayoutBinding binding;
    Context context;
    List<ProductModal> productModalList;
    int qty = 0;

    public RecyclerProductAdapter(Context context, List<ProductModal> productModalList) {
        this.context = context;
        this.productModalList = productModalList;
    }

    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = RecyclerProductLayoutBinding.inflate(LayoutInflater.from(context), parent, false);

        return new ProductVH(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVH holder, int position) {
        ProductModal modal = productModalList.get(position);



        binding.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Product_Full_Detail_Activity.class));
            }
        });


        binding.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty = qty + 1;

                Log.d("setUpTheProductQuantityLayout","plusButton click qty: "+qty);
                binding.quantityText.setText(String.valueOf(qty));
                Log.d("setUpTheProductQuantityLayout","plusButton click qty from text: "+binding.quantityText.getText().toString());
                if (qty > 0) {
                    Log.d("setUpTheProductQuantityLayout","plus button click qty: qty above 0");
                    binding.quantityText.setVisibility(View.VISIBLE);
                    binding.minusButton.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty = qty - 1;
                Log.d("setUpTheProductQuantityLayout","minusButton click qty: "+qty);
                binding.quantityText.setText(String.valueOf(qty));
                Log.d("setUpTheProductQuantityLayout","minusButton click qty from text: "+binding.quantityText.getText().toString());
                if (qty <= 0) {
                    Log.d("setUpTheProductQuantityLayout","minusButton click qty: qty below 0");
                    binding.quantityText.setVisibility(View.GONE);
                    binding.minusButton.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return productModalList.size();
    }

    public class ProductVH extends RecyclerView.ViewHolder {
        public ProductVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
