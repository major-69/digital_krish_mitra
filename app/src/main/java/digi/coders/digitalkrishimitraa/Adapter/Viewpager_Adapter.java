package digi.coders.digitalkrishimitraa.Adapter;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import digi.coders.digitalkrishimitraa.Fragment.BlankFragment;
import digi.coders.digitalkrishimitraa.Fragment.Donation_Fragment;
import digi.coders.digitalkrishimitraa.Fragment.Foryou_Fragment;
import digi.coders.digitalkrishimitraa.Fragment.Jobseeker_Fragment;
import digi.coders.digitalkrishimitraa.Fragment.VideoFragment;


public class Viewpager_Adapter extends  FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    public Viewpager_Adapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                Foryou_Fragment upcomingMatches = new Foryou_Fragment();
                return upcomingMatches;
            case 1:
                Jobseeker_Fragment liveMatches = new Jobseeker_Fragment();
                return liveMatches;
            case 2:
                Donation_Fragment completedMatches = new Donation_Fragment();
                return completedMatches;
            case 3:
                VideoFragment videoFragment = new VideoFragment();
                return videoFragment;

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return totalTabs;
    }
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        if (position == 0) {
//            return "For you";
//        } else if (position == 1) {
//            return "Job Seeker";
//        }
//        else if (position == 2) {
//            return "Donate";
//        } else if (position == 3) {
//            return "Loan";
//        } else {
//            return "For you";
//        }
//
//    }


}