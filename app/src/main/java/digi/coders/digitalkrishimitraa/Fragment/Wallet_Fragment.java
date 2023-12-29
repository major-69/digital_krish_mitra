package digi.coders.digitalkrishimitraa.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import digi.coders.digitalkrishimitraa.Activity.Add_Amount_Activity;
import digi.coders.digitalkrishimitraa.Activity.KYC_Activity;
import digi.coders.digitalkrishimitraa.Adapter.RecyclerHistoryAdapter;
import digi.coders.digitalkrishimitraa.Model.HistoryModal;
import digi.coders.digitalkrishimitraa.databinding.FragmentWalletBinding;

public class Wallet_Fragment extends Fragment {

    FragmentWalletBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentWalletBinding.inflate(inflater, container, false);

        List<HistoryModal> historyModalList = new ArrayList<>();

        historyModalList.add(new HistoryModal("","",""));
        historyModalList.add(new HistoryModal("","",""));
        historyModalList.add(new HistoryModal("","",""));
        historyModalList.add(new HistoryModal("","",""));
        historyModalList.add(new HistoryModal("","",""));
        historyModalList.add(new HistoryModal("","",""));
        historyModalList.add(new HistoryModal("","",""));

        binding.transactionRecycler.setAdapter(new RecyclerHistoryAdapter(getContext(),historyModalList));

        binding.completeKyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), KYC_Activity.class));
            }
        });


        binding.addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Add_Amount_Activity.class));
            }
        });

        return binding.getRoot();
    }
}