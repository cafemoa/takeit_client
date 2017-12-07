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
        colors = new int[4];
        colors[0] = Color.argb(255,255,214,80);      //레드
        colors[1] = Color.argb(255,205,205,205);
        colors[2] = Color.argb(255,88, 208, 196);
        colors[3] = Color.argb(255,188, 186, 225);
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
