package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import mkworld29.mobile.com.cafemoa.R;

/**
 * Created by chmod777 on 2017. 6. 25..
 */

public class StampAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inf;

    boolean scale;
    int layout;
    int sum;
    int complete_coupon;

    public StampAdapter(Context context, int layout, int sum, boolean scale)
    {
        this.context = context;
        this.layout = layout;
        this.sum = sum;
        this.scale = scale;
        inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inf.inflate(layout, null);
            ImageView iv = null;
        if (position + 1 <= sum) {
            iv = (ImageView) convertView.findViewById(R.id.iv_stamp_on);
            iv.setImageResource(R.drawable.item_stamp_on);            //  쿠폰 찍힌 이미지
        } else {
            iv = (ImageView) convertView.findViewById(R.id.iv_stamp_on);
            iv.setImageResource(R.drawable.stamp_off);            //  쿠폰 안 찍힌 이미지
        }

        if(scale) {
            Log.d("SCALESCALE!!!!", "SCALESCALE COMPLETE");
            iv.getLayoutParams().height = (int)convertDpToPixel(50,context);
            iv.requestLayout();
        }
        else {
            iv.getLayoutParams().height = (int)convertDpToPixel(25,context);
            iv.requestLayout();
        }

        return convertView;
    }

    public static float convertPixelsToDp(float px, Context context){

        Resources resources = context.getResources();

        DisplayMetrics metrics = resources.getDisplayMetrics();

        float dp = px / (metrics.densityDpi / 160f);

        return dp;

    }

    public static float convertDpToPixel(float dp, Context context){

        Resources resources = context.getResources();

        DisplayMetrics metrics = resources.getDisplayMetrics();

        float px = dp * (metrics.densityDpi / 160f);

        return px;

    }



}
