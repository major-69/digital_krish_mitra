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
import digi.coders.digitalkrishimitraa.Fragment.MyPost_Fragment;
import digi.coders.digitalkrishimitraa.Fragment.My_Fundraise_Fragment;
import digi.coders.digitalkrishimitraa.Fragment.My_Jobpost_Fragment;


public class Profile_Viewpager extends  FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    public Profile_Viewpager(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                MyPost_Fragment upcomingMatches = new MyPost_Fragment();
                return upcomingMatches;
            case 1:
                My_Jobpost_Fragment liveMatches = new My_Jobpost_Fragment();
                return liveMatches;
            case 2:
                My_Fundraise_Fragment completedMatches = new My_Fundraise_Fragment();
                return completedMatches;

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