package mkworld29.mobile.com.cafemoa;

import android.content.Context;
import android.graphics.Color;

/**
 * Created by chmod777 on 2017. 6. 25..
 */

public class Values {

    private static Values instance = new Values();

    public int[] colors;

    public Values()
    {
        colors = new int[5];
        colors[0] = Color.argb(255,255,67,67);      //레드
        colors[1] = Color.argb(255,123,193,72);
        colors[2] = Color.argb(255,72,86,193);
        colors[3] = Color.argb(255,197,39,203);
        colors[4] = Color.argb(255,39,203,143);     //민트
    }

    public static Values getInstance()
    {
        if(instance == null) {
            instance = new Values();
            return instance;
        }
        else
            return instance;
    }


}
