package digi.coders.digitalkrishimitraa.Helper;

import android.content.Intent;

public class Constants {
    public static String BASE_URL="http://digitalkrishi.digicommunique.com/api/v1/";
    public static String IMAGE_URL="http://digitalkrishi.digicommunique.com/api/v1/img/";
    public static String VIDEO_URL="http://digitalkrishi.digicommunique.com/krishimirta/video/";

    public static final int PICK_IMAGE = 1;

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            //TODO: action
        }
    }
}
