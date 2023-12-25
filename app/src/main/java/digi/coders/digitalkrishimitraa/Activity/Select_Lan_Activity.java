package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.nio.channels.SelectionKey;
import java.util.Locale;

import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ActivitySelectLanBinding;

public class Select_Lan_Activity extends AppCompatActivity {

    ActivitySelectLanBinding binding;
    String myStringValue = "en";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivitySelectLanBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String value=  sharedPreferences.getString("my_string_key", "");

        if (value.equalsIgnoreCase("en")){
            myStringValue="en";
            binding.eng.setChecked(true);
            binding.hindi.setChecked(false);
        }
        else if (value.equalsIgnoreCase("hi")){
            myStringValue="hi";
            binding.hindi.setChecked(true);
            binding.eng.setChecked(false);
        }
        else {
            myStringValue="en";
            binding.eng.setChecked(true);
            binding.hindi.setChecked(false);
        }

        binding.eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myStringValue="en";
                binding.eng.setChecked(true);
                binding.hindi.setChecked(false);

            }
        });

        binding.hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myStringValue="hi";
                binding.hindi.setChecked(true);
                binding.eng.setChecked(false);
            }
        });

       binding.submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               LocaleHelper.setLocale(Select_Lan_Activity.this, myStringValue);

               SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.putString("my_string_key", myStringValue);
               editor.commit();

               Intent intent = new Intent(Select_Lan_Activity.this, MainActivity.class);
               startActivity(intent);
           }
       });

    }

    public void back(View view) {finish();
    }

}