package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import mkworld29.mobile.com.cafemoa.OrderCompleteActivity;
import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.item.PaymentListViewItem;

/**
 * Created by chmod777 on 2017. 6. 25..
 */

public class PaymentListViewAdapter extends BaseAdapter {

    private ArrayList<PaymentListViewItem> listViewItemList = new ArrayList<>();

    public PaymentListViewAdapter()
    {

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
        ViewHolder holder;
        final PaymentListViewItem item = (PaymentListViewItem) getItem(position);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item_payment, parent, false);

            holder= new ViewHolder();

            holder.tv_content         = (TextView)convertView.findViewById(R.id.tv_content);
            holder.tv_price           =  (TextView) convertView.findViewById(R.id.tv_price);
            holder.tv_time            = (TextView) convertView.findViewById(R.id.tv_order_date);
            holder.tv_cafe_name       = (TextView)convertView.findViewById(R.id.tv_cafe_name);
            holder.tv_cafe_address    = (TextView)convertView.findViewById(R.id.tv_cafe_address);
            holder.ly_all             = (LinearLayout)convertView.findViewById(R.id.ly_all);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }



        holder.tv_content.setText(item.getContent());
        holder.tv_price.setText(String.valueOf(item.getPrice()) + "원");
        holder.tv_time.setText(item.getTime());
        holder.tv_cafe_name.setText(item.getCafe_name());
        holder.tv_cafe_address.setText(item.getCafe_address());

        holder.ly_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderCompleteActivity.class);
                intent.putExtra("content",item.getContent());
                intent.putExtra("order_num",String.valueOf(item.getOrder_num()));
                intent.putExtra("payment_okay_date",item.getTime());
                context.startActivity(intent);
            }
        });

        return convertView;

    }

    public void addItem(String content, String cafe_name, String cafe_address, String price, String time, int order_num)
    {
        PaymentListViewItem item = new PaymentListViewItem(content, cafe_name, cafe_address, price,time, order_num);

        listViewItemList.add(item);
    }
    public void clearItems(){
        listViewItemList.clear();
    }

    private class ViewHolder {
        private TextView tv_content, tv_price, tv_time, tv_cafe_name, tv_cafe_address;
        private LinearLayout ly_all;
    }

}
