package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import digi.coders.digitalkrishimitraa.Adapter.RecyclerProductAdapter;
import digi.coders.digitalkrishimitraa.Model.ProductModal;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ActivityProductListBinding;

public class Product_list_Activity extends AppCompatActivity {

    ActivityProductListBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<ProductModal> productModalList = new ArrayList<>();
        productModalList.add(new ProductModal());
        productModalList.add(new ProductModal());
        productModalList.add(new ProductModal());
        productModalList.add(new ProductModal());
        productModalList.add(new ProductModal());
        productModalList.add(new ProductModal());
        productModalList.add(new ProductModal());
        productModalList.add(new ProductModal());
        productModalList.add(new ProductModal());

        binding.productRecyclerView.setAdapter(new RecyclerProductAdapter(Product_list_Activity.this,productModalList));

    }
}