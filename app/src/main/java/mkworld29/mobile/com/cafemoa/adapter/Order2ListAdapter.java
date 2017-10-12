package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
import mkworld29.mobile.com.cafemoa.item.PaymentListViewItem;

import static android.graphics.Typeface.BOLD;

/**
 * Created by chmod777 on 2017. 6. 25..
 */

public class Order2ListAdapter extends BaseAdapter{

    private ArrayList<OrderListItem> listViewItemList = new ArrayList<>();
    private RelativeLayout rv_content;
    private LinearLayout lv_option;
    private TextView tv_content;
    private ImageView iv_content,iv_best;
    private TextView tv_amount_minus, tv_amount, tv_amount_plus;
    private TextView tv_size_s, tv_size_m,tv_size_l;
    private TextView tv_shots_minus, tv_shots, tv_shots_plus;
    private TextView tv_hot, tv_ice;
    private TextView tv_whipping_true, tv_whipping_false;
    private TextView[] arr_amount;
    private TextView[] arr_size;
    private TextView[] arr_shots;
    private TextView[] arr_cold;
    private TextView[] arr_whipping;

    private boolean is_showed;

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
            convertView = inflater.inflate(R.layout.order_list_item, parent, false);
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


    public void addItem(String content, String image, boolean is_best)
    {
        OrderListItem item = new OrderListItem(content,image,is_best);

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

    public void setFontDefaults(TextView[] arr)
    {
        for(TextView t : arr)
        {
            setFontStyle(t,false);
        }
    }
}
