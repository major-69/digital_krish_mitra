package digi.coders.digitalkrishimitraa.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import digi.coders.digitalkrishimitraa.Adapter.Badge_status_adapter;
import digi.coders.digitalkrishimitraa.Model.BadgeModal;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ActivityBadgeStatusBinding;

public class Badge_status_Activity extends AppCompatActivity {

    ActivityBadgeStatusBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBadgeStatusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<BadgeModal> badgeStatusList = new ArrayList<>();

        badgeStatusList.add(new BadgeModal("1", getString(R.string.fpo_nirman_officer), getString(R.string.desc_nirman_officer), "174000", "1,2,3"));
        badgeStatusList.add(new BadgeModal("2", getString(R.string.fpo_prashikhan_officer), getString(R.string.desc_prashikhan_officer), "186000", "4"));
        badgeStatusList.add(new BadgeModal("3", getString(R.string.fpo_nirikhan_officer), getString(R.string.desc_nirikhan_officer), "264000", "5"));
        badgeStatusList.add(new BadgeModal("4", getString(R.string.fpo_vistar_officer), getString(R.string.desc_vistar_officer), "420000", "6"));
        badgeStatusList.add(new BadgeModal("5", getString(R.string.fpo_supervisior), getString(R.string.desc_supervisior), "600000", "7"));
        badgeStatusList.add(new BadgeModal("6", getString(R.string.fpo_manager), getString(R.string.desc_manager), "840000", "8"));
        badgeStatusList.add(new BadgeModal("7", getString(R.string.fpo_area_manager), getString(R.string.desc_area_manager), "1260000", "9"));
        badgeStatusList.add(new BadgeModal("8", getString(R.string.fpo_jonal_manager), getString(R.string.desc_jonal_manager), "1500000", "10"));
        badgeStatusList.add(new BadgeModal("9", getString(R.string.fpo_regional_manager), getString(R.string.desc_regional_manager), "2000000", "11"));
        badgeStatusList.add(new BadgeModal("10", getString(R.string.fpo_state_head_manager), getString(R.string.desc_state_head_manager), "2500000", "12"));
        badgeStatusList.add(new BadgeModal("11", getString(R.string.fpo_country_head_manager), getString(R.string.desc_country_head_manager), "4000000", "13"));
        badgeStatusList.add(new BadgeModal("12", getString(R.string.fpo_directet), getString(R.string.desc_directet), "6000000", "14"));
        badgeStatusList.add(new BadgeModal("13", getString(R.string.fpo_cmd), getString(R.string.desc_cmd), "10000000", "15"));

        binding.badgeStatusRecycler.setAdapter(new Badge_status_adapter(badgeStatusList, Badge_status_Activity.this));
        binding.badgeStatusBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}