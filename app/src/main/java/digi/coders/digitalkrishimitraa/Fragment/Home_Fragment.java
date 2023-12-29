package digi.coders.digitalkrishimitraa.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import digi.coders.digitalkrishimitraa.Activity.Add_Fundraise_Activity;
import digi.coders.digitalkrishimitraa.Activity.Add_Job_Activity;
import digi.coders.digitalkrishimitraa.Activity.Add_Post_Activity;
import digi.coders.digitalkrishimitraa.Activity.Add_Reel_Activity;
import digi.coders.digitalkrishimitraa.Adapter.Viewpager_Adapter;
import digi.coders.digitalkrishimitraa.Create_Farmer_FPO;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.FragmentHomeBinding;


public class Home_Fragment extends Fragment {


    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    binding= FragmentHomeBinding.inflate(inflater,container,false);

        binding.tabLayout.setTabTextColors(getResources().getColor(R.color.grey), getResources().getColor(R.color.purple_700));
        binding.tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.purple_700));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.foryou));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.jobseeker));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.Donatee));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.bottomtab_video));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        Viewpager_Adapter myMatchesAdapter = new Viewpager_Adapter(getActivity(),getChildFragmentManager(), binding.tabLayout.getTabCount());
        binding.viewPager.setAdapter(myMatchesAdapter);

        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        Viewpager_Adapter viewpagerAdapter = new Viewpager_Adapter(getActivity().getSupportFragmentManager());
//        binding.viewPager.setAdapter(viewpagerAdapter);
//
//        binding.tabLayout.setupWithViewPager(binding.viewPager);
//        binding.tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#7faa40"));
//        binding.tabLayout.setSelectedTabIndicatorHeight((int) (1.5 * getResources().getDisplayMetrics().density));
//        binding.tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#7faa40"));

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomdotmenu();
            }
        });

    return binding.getRoot();

    }
    private void showBottomdotmenu() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(R.layout.addpost_layout);
        bottomSheetDialog.findViewById(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();

                startActivity(new Intent(getActivity(), Add_Post_Activity.class));
            }
        });
        bottomSheetDialog.findViewById(R.id.job).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();

                startActivity(new Intent(getActivity(), Add_Job_Activity.class));

            }
        });
        bottomSheetDialog.findViewById(R.id.donation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                startActivity(new Intent(getActivity(), Create_Farmer_FPO.class));

            }
        });

        bottomSheetDialog.findViewById(R.id.reel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                startActivity(new Intent(getActivity(), Add_Reel_Activity.class));

            }
        });

        bottomSheetDialog.show();
    }



}