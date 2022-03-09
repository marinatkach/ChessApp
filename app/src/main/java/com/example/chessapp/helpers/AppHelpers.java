package com.example.chessapp.helpers;

import android.util.Log;
import android.widget.ImageView;

import com.example.chessapp.R;

/**
 * Help functions
 */
public class AppHelpers {

    /**
     * set image to imageView from resource id or set defaut image if resource not found
     * @param image
     * @param resourceId
     */
    public static void setImageOrDefault(ImageView image, String resourceId){
       setImageOrDefault(image, resourceId, "com.example.chessapp");
    }



    private static void setImageOrDefault(ImageView image, String resourceId, String packageName ){

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

    /**
     * replace strings characted with a '*'
     * @param sourceString
     * @return
     */
    public static String hideString(String sourceString){
       return hideString(sourceString.length());
    }


    /**
     * get string with a '*'
     * @param cnt number of '*'
     * @return
     */
    public static String hideString(int cnt){
        StringBuilder res= new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            res.append("*");
        }
        return res.toString();
    }

}
