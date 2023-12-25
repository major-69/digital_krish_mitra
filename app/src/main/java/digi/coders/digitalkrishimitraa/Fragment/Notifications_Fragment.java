package digi.coders.digitalkrishimitraa.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.FragmentNotificationsBinding;


public class Notifications_Fragment extends Fragment {


    FragmentNotificationsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentNotificationsBinding.inflate(inflater,container,false);


        return binding.getRoot();
    }
}