package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.item.OrderListItem;
import mkworld29.mobile.com.cafemoa.item.OrderListItem2;
import mkworld29.mobile.com.cafemoa.item.PaymentListViewItem;

import static android.graphics.Typeface.BOLD;

/**
 * Created by chmod777 on 2017. 6. 25..
 */

public class Order2ListAdapter extends BaseAdapter{

    private ArrayList<OrderListItem2> listViewItemList ;

    private TextView tv_content;
    private ImageView iv_content,iv_best;

    public Order2ListAdapter()
    {
        listViewItemList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_list_item2, parent, false);
        }

        tv_content          =   (TextView)convertView.findViewById(R.id.tv_content);
        iv_content          =   (ImageView)convertView.findViewById(R.id.iv_content);
        iv_best             =   (ImageView)convertView.findViewById(R.id.iv_best);

        tv_content.setText(listViewItemList.get(position).getContent());
        Glide.with(context)
                .load(listViewItemList.get(position).getImg())
                .placeholder(R.drawable.option_americano)
                .error(R.drawable.option_americano)
                .into(iv_content);
        if(listViewItemList.get(position).is_best())
            iv_best.setVisibility(View.VISIBLE);
        else
            iv_best.setVisibility(View.GONE);

        return convertView;
    }

    public ArrayList<OrderListItem2> getListViewItemList()
    {
        return listViewItemList;
    }

    public void addItem(String content, String image, boolean is_best,int pk)
    {
        OrderListItem2 item = new OrderListItem2(content,image,is_best,pk);
        listViewItemList.add(item);
    }

    public void setFontStyle(TextView view, boolean is_bold)
    {
        if(is_bold) {
            view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            view.setTextColor(Color.rgb(12,12,12));
        }
        else {
            view.setTypeface(Typeface.DEFAULT);
            view.setTextColor(Color.rgb(172,172,172));
        }
    }

}
