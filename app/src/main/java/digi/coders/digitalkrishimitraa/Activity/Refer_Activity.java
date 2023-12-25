package digi.coders.digitalkrishimitraa.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import digi.coders.digitalkrishimitraa.Fragment.Home_Fragment;
import digi.coders.digitalkrishimitraa.databinding.ActivityReferBinding;

public class Refer_Activity extends AppCompatActivity {

    ActivityReferBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityReferBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details" +System.lineSeparator()+ "referral=");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
        binding.whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details" +System.lineSeparator()+ "referral=");
                try {
                    getApplicationContext().startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                }
            }
        });
        binding.telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent telegramIntent = new Intent(Intent.ACTION_SEND);
                telegramIntent.setType("text/plain");
                telegramIntent.setPackage("org.telegram.messenger");
                telegramIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                telegramIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details" +System.lineSeparator()+ "referral=");
                try {
                    getApplicationContext().startActivity(telegramIntent);

                }catch (android.content.ActivityNotFoundException e){

                }
            }
        });
        binding.facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent appIntent = new Intent(Intent.ACTION_SEND);
                appIntent.setType("text/plain");
                appIntent.setPackage("com.facebook");
                appIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                appIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details" +System.lineSeparator()+ "referral=");
                try {
                    getApplicationContext().startActivity(appIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                }
            }
        });
        binding.copy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getApplicationContext().getSystemService(CLIPBOARD_SERVICE);
                clipboard.setText(binding.refer.getText().toString());
                Toast.makeText(getApplicationContext(), "Referral copied...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void back(View view) {finish();
    }
}