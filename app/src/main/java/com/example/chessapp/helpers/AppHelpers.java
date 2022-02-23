package com.example.chessapp.helpers;

import android.util.Log;
import android.widget.ImageView;

import com.example.chessapp.R;

public class AppHelpers {


    public static void setImageOrDefault(ImageView image, String resourceId){
       setImageOrDefault(image, resourceId, "com.example.chessapp");
    }


    public static void setImageOrDefault(ImageView image, String resourceId, String packageName ){

        String resourceName = resourceId;
        if(resourceName.contains(".")){
            resourceName = resourceName.split("\\.")[0];
        }

        int drawableResourceId = image.getContext().getResources().getIdentifier(resourceName, "drawable", packageName);

        if(drawableResourceId == 0)
        {
            Log.w("Place Info Card Adapter", "cannot parse resource image id " + resourceId);
            drawableResourceId = R.drawable.ic_launcher_background;
        }
        image.setImageResource(drawableResourceId);

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

    public static String createPlayerText(String player1, String player2, int year){
        return String.format("%s - %s, %s", player1, player2, year);
    }
}
