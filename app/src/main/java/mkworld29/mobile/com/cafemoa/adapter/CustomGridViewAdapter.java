package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import mkworld29.mobile.com.cafemoa.R;

/**
 * Created by chmod777 on 2017. 6. 25..
 */

public class CustomGridViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inf;

    int layout;
    int sum;
    public CustomGridViewAdapter(Context context, int layout, int sum)
    {
        this.context = context;
        this.layout = layout;
        this.sum = sum;
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
            iv.setImageResource(R.drawable.stamp_on);            //  쿠폰 찍힌 이미지
        } else {
            iv = (ImageView) convertView.findViewById(R.id.iv_stamp_on);
            iv.setImageResource(R.drawable.stamp_off);            //  쿠폰 안 찍힌 이미지
        }

        return convertView;
    }
}

