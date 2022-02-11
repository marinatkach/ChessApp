package com.example.chessapp.helpers;

import android.util.Log;
import android.widget.ImageView;

import com.example.chessapp.R;

public class AppHelpers {

    public static void setImageOrDefault(ImageView image, String resourceId){
        try {
            int resourceIdNum = Integer.parseInt(resourceId);
            image.setImageResource(resourceIdNum);
        }catch (NumberFormatException exception){
            Log.w("Place Info Card Adapter", "cannot parse resource image id " + resourceId);
            image.setImageResource(R.drawable.ic_launcher_background);
        }
    }

    public static String hideString(String sourceString){
       return hideString(sourceString.length());
    }

    public static String hideString(int cnt){
        StringBuilder res= new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            res.append("*");
        }
        return res.toString();
    }

}
