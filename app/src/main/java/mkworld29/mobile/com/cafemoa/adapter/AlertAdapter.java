package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.item.AlertItem;

/**
 * Created by ABCla on 2017-10-07.
 */

public class AlertAdapter extends BaseAdapter{
    private ArrayList<AlertItem> data;

    private LayoutInflater inflater;
    private int layout;
    public AlertAdapter(Context context, int layout, ArrayList<AlertItem> data){
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout=layout;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) convertView=inflater.inflate(layout,parent,false);

        AlertItem item=data.get(position);

        TextView cafe_name=(TextView) convertView.findViewById(R.id.alert_cafe_name);
        cafe_name.setText(item.getCafe_name());

        TextView content=(TextView)convertView.findViewById(R.id.alert_content);
        content.setText(item.getContent());

        ImageView alert_image=(ImageView) convertView.findViewById(R.id.alert_event_image);
        if(item.getIs_event()) alert_image.setImageResource(R.drawable.alarm_event1);
        else alert_image.setImageResource(R.drawable.alarm_megaphone);


        return convertView;
    }
}
