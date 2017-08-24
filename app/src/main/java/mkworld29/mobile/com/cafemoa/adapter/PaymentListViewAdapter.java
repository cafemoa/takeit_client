package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item_payment, parent, false);
        }


        TextView tv_content = (TextView)convertView.findViewById(R.id.tv_content);
        TextView tv_price =  (TextView) convertView.findViewById(R.id.tv_price);
        TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);

        PaymentListViewItem listViewItem = listViewItemList.get(position);

        tv_content.setText(listViewItem.getContent());
        tv_price.setText(String.valueOf(listViewItem.getPrice()) + "원");
        tv_time.setText(listViewItem.getTime());

        return convertView;

    }

    public void addItem(String content, int price, String time)
    {
        PaymentListViewItem item = new PaymentListViewItem(content,price,time);

        listViewItemList.add(item);
    }
}
