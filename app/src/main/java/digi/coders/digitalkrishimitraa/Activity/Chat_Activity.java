package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import digi.coders.digitalkrishimitraa.Adapter.Chat_Adapter;
import digi.coders.digitalkrishimitraa.Adapter.Donation_Adapter;
import digi.coders.digitalkrishimitraa.databinding.ActivityChatBinding;
import digi.coders.digitalkrishimitraa.databinding.ActivityVideoBinding;

public class Chat_Activity extends AppCompatActivity {

    ActivityChatBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Chat_Adapter adapter = new Chat_Adapter(getApplicationContext());
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));


    }

    public void back(View view) {finish();
    }
}