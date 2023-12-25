package digi.coders.digitalkrishimitraa.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import digi.coders.digitalkrishimitraa.Adapter.RecyclerKrishiBazaarAdapter;
import digi.coders.digitalkrishimitraa.Model.BazaarModal;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.FragmentKrishiMitraBazaarBinding;

public class KrishiMitraBazaarFragment extends Fragment {

   FragmentKrishiMitraBazaarBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentKrishiMitraBazaarBinding.inflate(inflater,container,false);

        List<BazaarModal> bazaarModalList = new ArrayList<>();
        bazaarModalList.add(new BazaarModal());
        bazaarModalList.add(new BazaarModal());
        bazaarModalList.add(new BazaarModal());
        bazaarModalList.add(new BazaarModal());
        bazaarModalList.add(new BazaarModal());
        bazaarModalList.add(new BazaarModal());
        bazaarModalList.add(new BazaarModal());
        bazaarModalList.add(new BazaarModal());
        bazaarModalList.add(new BazaarModal());

        binding.krishiBazaarRecycler.setAdapter(new RecyclerKrishiBazaarAdapter(getContext(),bazaarModalList));


        return binding.getRoot();
    }
}