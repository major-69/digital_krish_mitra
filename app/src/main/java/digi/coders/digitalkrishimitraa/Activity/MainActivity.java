package digi.coders.digitalkrishimitraa.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import digi.coders.digitalkrishimitraa.Fragment.ChannelPartnerFragment;
import digi.coders.digitalkrishimitraa.Fragment.Home_Fragment;
import digi.coders.digitalkrishimitraa.Fragment.KrishiMitraBazaarFragment;
import digi.coders.digitalkrishimitraa.Fragment.Notifications_Fragment;
import digi.coders.digitalkrishimitraa.Fragment.Profile_Fragment;
import digi.coders.digitalkrishimitraa.Fragment.Wallet_Fragment;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        fragmentManager.beginTransaction().replace(R.id.fragment1, new Home_Fragment()).commit();

        binding.ChipNavigationBar.setItemSelected(R.id.item0, true);

        binding.ChipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.item0:
                        fragmentManager.beginTransaction().replace(R.id.fragment1, new Home_Fragment()).commit();
                        break;
                    case R.id.item1:
                        fragmentManager.beginTransaction().replace(R.id.fragment1, new KrishiMitraBazaarFragment()).commit();
                        break;
                    case R.id.item2:
                        fragmentManager.beginTransaction().replace(R.id.fragment1, new ChannelPartnerFragment()).commit();
                        break;
                    case R.id.item3:
                        fragmentManager.beginTransaction().replace(R.id.fragment1, new Wallet_Fragment()).commit();
                        break;
                }
            }
        });

        binding.profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.fragment1, new Profile_Fragment()).commit();
            }
        });

        binding.setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawer.openDrawer(GravityCompat.END);

            }
        });

        binding.videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Video_Activity.class));
            }
        });

        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, Search_Activity.class);
                startActivity(i);
            }
        });

        binding.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, Chat_Activity.class);
                startActivity(i);
            }
        });

        binding.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.fragment1, new Notifications_Fragment()).commit();
            }
        });


        binding.homeNavDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:

                        break;
                    case R.id.donation:
                        Intent browserIntent = new Intent(getApplicationContext(), MyDonations_Activity.class);
                        startActivity(browserIntent);
                        break;
                    case R.id.language:
                        Intent browser = new Intent(getApplicationContext(), Select_Lan_Activity.class);
                        startActivity(browser);
                        break;
                    case R.id.refer:
                        Intent browsere = new Intent(getApplicationContext(), Refer_Activity.class);
                        startActivity(browsere);
                        break;
//                    case R.id.drawer_message:
//                        Intent in=new Intent(Home_Activity.this,Message_Activity.class);
//                        in.putExtra("status","false");
//                        in.putExtra("number","");
//                        startActivity(in);
//                        break;
                    case R.id.shareapp:
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this awesome app!");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "Download the amazing MyApp from the Play Store: https://play.google.com/store/apps/details?id=digi.coders.digitalkrishimitraa");
                        startActivity(Intent.createChooser(shareIntent, "Share via"));
                        break;
                    case R.id.logout:
                        deleteuserdata();
                        break;

                }
                return true;
            }
        });

    }

    private void deleteuserdata() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(R.string.Youwantlogout);
        builder.setTitle(R.string.Confirm);
        builder.setPositiveButton(R.string.Logout, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.apply();

//                SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
//                String value=  sharedPreferences.getString("my_string_key", "");
//                SharedPreferences.Editor editor1 = value.edit();
//                editor1.clear();
//                editor1.apply();

                SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor1.remove("my_string_key");
                editor1.apply();

                Intent i = new Intent(MainActivity.this, New_Login_Activity.class);
                startActivity(i);
                finish();
            }
        });
        builder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    @Override
    public void onBackPressed() {
        fragmentManager.beginTransaction().replace(R.id.fragment1, new Home_Fragment()).commit();
    }
}