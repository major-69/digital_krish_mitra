package digi.coders.digitalkrishimitraa.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import digi.coders.digitalkrishimitraa.Activity.Add_Product_Activity;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.FragmentChannelPartnerBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChannelPartnerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class
ChannelPartnerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChannelPartnerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChannelPartnerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChannelPartnerFragment newInstance(String param1, String param2) {
        ChannelPartnerFragment fragment = new ChannelPartnerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FragmentChannelPartnerBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChannelPartnerBinding.inflate(inflater, container, false);

        binding.registerChannelPartnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.registerChannelPartnerButton.setVisibility(View.GONE);
                binding.channelPartnerForm.setVisibility(View.VISIBLE);
            }
        });

        binding.submitFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.channelPartnerForm.setVisibility(View.GONE);
                binding.chanelPartnerData.setVisibility(View.VISIBLE);
            }
        });

        binding.addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Add_Product_Activity.class));
            }
        });

        return binding.getRoot();
    }
}