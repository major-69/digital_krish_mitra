package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

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

        badgeStatusList.add(new BadgeModal("","F.p.o.Advicer",""));
        badgeStatusList.add(new BadgeModal("","F.p.o.orgniger",""));
        badgeStatusList.add(new BadgeModal("","F.p.o. Superviser",""));
        badgeStatusList.add(new BadgeModal("","F.p.o. Bdm",""));
        badgeStatusList.add(new BadgeModal("","F.p.o.Manager",""));
        badgeStatusList.add(new BadgeModal("","F.p.o. Regional manager",""));
        badgeStatusList.add(new BadgeModal("","F.p.o. State head Manager",""));


        binding.badgeStatusRecycler.setAdapter(new Badge_status_adapter(badgeStatusList,Badge_status_Activity.this));

        binding.badgeStatusBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}