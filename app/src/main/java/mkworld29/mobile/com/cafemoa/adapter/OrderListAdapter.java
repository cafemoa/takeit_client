package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.item.OptionItem;
import mkworld29.mobile.com.cafemoa.item.OrderListItem;

/**
 * Created by chmod777 on 2017. 6. 25..
 */

public class OrderListAdapter extends BaseAdapter{

    private ArrayList<OrderListItem> listViewItemList ;

    private TextView tv_content,tv_price;
    private ImageView iv_best;

    public OrderListAdapter()
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
        iv_best             =   (ImageView)convertView.findViewById(R.id.iv_best);
        tv_price            =   (TextView)convertView.findViewById(R.id.beverage_price);

        tv_content.setText(listViewItemList.get(position).getContent());
        tv_price.setText(listViewItemList.get(position).getPrice());

        if(listViewItemList.get(position).is_best())
            iv_best.setVisibility(View.VISIBLE);
        else
            iv_best.setVisibility(View.GONE);

        return convertView;
    }

    public ArrayList<OrderListItem> getListViewItemList()
    {
        return listViewItemList;
    }

    public void addItem(String content, String image, String price,boolean is_best,int type,int pk, ArrayList<OptionItem> options)
    {
        OrderListItem item = new OrderListItem(content,image,price,is_best,type,pk,options);
        listViewItemList.add(item);
    }

    public void addItem(OrderListItem item)
    {
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
