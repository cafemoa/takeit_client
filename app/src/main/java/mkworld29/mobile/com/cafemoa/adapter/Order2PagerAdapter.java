package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import mkworld29.mobile.com.cafemoa.CoffeOption2Activity;
import mkworld29.mobile.com.cafemoa.Option2Acitivity;
import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.item.OrderListItem2;

/**
 * Created by parkjaemin on 2017. 9. 26..
 */

public class Order2PagerAdapter extends PagerAdapter {

    private LayoutInflater mInflater;

    private Context mContext;
    ListView lv_order = null;
    Order2ListAdapter adapter = null;
    View v = null;

    public Order2PagerAdapter(Context c)
    {
        super();
        mContext = c;
        mInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object instantiateItem(View pager, int position) {

        if(position==0){
            v = mInflater.inflate(R.layout.order_inflate_one, null);
            lv_order = (ListView) v.findViewById(R.id.lv_order);
            adapter = new Order2ListAdapter();
            lv_order.setAdapter(adapter);
            adapter.addItem("아메리카노",null,false);
            adapter.addItem("카페라떼",null,true);
            adapter.addItem("카푸치노",null,true);
            adapter.addItem("오리엔탈버거",null,false);
            adapter.notifyDataSetChanged();

            lv_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    OrderListItem2 item= (OrderListItem2) adapter.getItem(i);
                    Intent intent = new Intent(v.getContext(), CoffeOption2Activity.class);
                    String iv_content = item.getImg();
                    String content = item.getContent();
                    intent.putExtra("iv_content", iv_content);
                    intent.putExtra("content",content);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(intent);
                }
            });
        }
        else if(position==1){
            v = mInflater.inflate(R.layout.order_inflate_one, null);
            lv_order = (ListView) v.findViewById(R.id.lv_order);
            adapter = new Order2ListAdapter();
            lv_order.setAdapter(adapter);
            adapter.addItem(null,null,false);
            adapter.notifyDataSetChanged();
        }
        else if(position==2){
            v = mInflater.inflate(R.layout.order_inflate_one, null);
            lv_order = (ListView) v.findViewById(R.id.lv_order);
            adapter = new Order2ListAdapter();
            lv_order.setAdapter(adapter);
            adapter.addItem(null,null,false);
            adapter.notifyDataSetChanged();
        }
        else if(position==3){
            v = mInflater.inflate(R.layout.order_inflate_one, null);
            lv_order = (ListView) v.findViewById(R.id.lv_order);
            adapter = new Order2ListAdapter();
            lv_order.setAdapter(adapter);
            adapter.addItem(null,null,false);
            adapter.notifyDataSetChanged();
        }
        else if(position==4){
            v = mInflater.inflate(R.layout.order_inflate_one, null);
            lv_order = (ListView) v.findViewById(R.id.lv_order);
            adapter = new Order2ListAdapter();
            lv_order.setAdapter(adapter);
            adapter.addItem(null,null,false);
            adapter.notifyDataSetChanged();
        }


        ((ViewPager)pager).addView(v, 0);

        return v;
    }

    @Override
    public void destroyItem(View pager, int position, Object view) {
        ((ViewPager)pager).removeView((View)view);
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }
    @Override public void restoreState(Parcelable arg0, ClassLoader arg1) {}
    @Override public Parcelable saveState() { return null; }
    @Override public void startUpdate(View arg0) {}
    @Override public void finishUpdate(View arg0) {}
}
