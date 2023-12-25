package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.databinding.ActivitySplashBinding;

public class Splash_Activity extends AppCompatActivity {

    ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String userdata = pref.getString("userdata", "{}");
                UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);

                if (usermodel.getMobile()==null) {

                    Intent intent = new Intent(Splash_Activity.this, New_Login_Activity.class);
                    startActivity(intent);
                    finish();

                } else {

                    SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                    String value=  sharedPreferences.getString("my_string_key", "");

                    if (value.equalsIgnoreCase("en")){
                        LocaleHelper.setLocale(Splash_Activity.this, "en");
                        Intent intent = new Intent(Splash_Activity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else if (value.equalsIgnoreCase("hi")){
                        LocaleHelper.setLocale(Splash_Activity.this, "hi");
                        Intent intent = new Intent(Splash_Activity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        LocaleHelper.setLocale(Splash_Activity.this, "en");
                        Intent intent = new Intent(Splash_Activity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }



            }
        }, 3000);

    }
}