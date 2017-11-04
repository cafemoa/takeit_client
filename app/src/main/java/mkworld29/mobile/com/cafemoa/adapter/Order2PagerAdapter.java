package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.CoffeOption2Activity;
import mkworld29.mobile.com.cafemoa.CouponActivity;
import mkworld29.mobile.com.cafemoa.Option2Acitivity;
import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.entity.Coupon;
import mkworld29.mobile.com.cafemoa.item.OrderListItem2;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by parkjaemin on 2017. 9. 26..
 */

public class Order2PagerAdapter extends PagerAdapter{

    private LayoutInflater mInflater;

    private Context mContext;
    ListView lv_order = null;
    String cafe_name;
    Order2ListAdapter mAdapter0 = null;
    Order2ListAdapter mAdapter1 = null;
    Order2ListAdapter mAdapter2 = null;
    Order2ListAdapter mAdapter3 = null;
    Order2ListAdapter mAdapter4 = null;

    private ArrayList<OrderListItem2> page0=new ArrayList<>();
    private ArrayList<OrderListItem2> page1=new ArrayList<>();
    private ArrayList<OrderListItem2> page2=new ArrayList<>();
    private ArrayList<OrderListItem2> page3=new ArrayList<>();
    private ArrayList<OrderListItem2> page4=new ArrayList<>();
    View v = null;
    int cafe_pk;


    public void addItemPage0(OrderListItem2 item){page0.add(item);}
    public void addItemPage1(OrderListItem2 item){page1.add(item);}
    public void addItemPage2(OrderListItem2 item){page2.add(item);}
    public void addItemPage3(OrderListItem2 item){page3.add(item);}
    public void addItemPage4(OrderListItem2 item){page4.add(item);}

    public Order2PagerAdapter(Context c, int cafe_pk)
    {
        super();
        mContext = c;
        mInflater = LayoutInflater.from(c);
        this.cafe_pk=cafe_pk;
    }
    public void setCafeName(String s)
    {
        this.cafe_name = s;
    }

    @Override
    public int getCount() {
        return 5;
    }


    @Override
    public Object instantiateItem(View pager, int position) {

        if(position==0 && mAdapter0==null){
            mAdapterRefresh0();
        }
        else if(position==1&& mAdapter1==null){
            mAdapterRefresh1();
        }
        else if(position==2&& mAdapter2==null){
            mAdapterRefresh2();
        }
        else if(position==3&& mAdapter3==null){
            mAdapterRefresh3();
        }
        else if(position==4&& mAdapter4==null){
            mAdapterRefresh4();
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

    public void setClickListener(final Order2ListAdapter mAdapter){
        lv_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OrderListItem2 item= (OrderListItem2) mAdapter.getItem(i);
                Intent intent = new Intent(v.getContext(), CoffeOption2Activity.class);
                String iv_content = item.getImg();
                String content = item.getContent();
                intent.putExtra("iv_content", iv_content);
                intent.putExtra("content",content);
                intent.putExtra("cafe_name", cafe_name);
                intent.putExtra("cafe_pk", cafe_pk);
                intent.putExtra("beverage_pk", ((OrderListItem2) mAdapter.getItem(i)).getPk());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });
    }
    public void mAdapterRefresh0(){
        v = mInflater.inflate(R.layout.order_inflate_one, null);
        lv_order = (ListView) v.findViewById(R.id.lv_order_1);
        mAdapter0 = new Order2ListAdapter();
        lv_order.setAdapter(mAdapter0);
        for(int i=0; i<page0.size(); i++){
            mAdapter0.addItem(page0.get(i));
        }
        setClickListener(mAdapter0);
        mAdapter0.notifyDataSetChanged();
    }
    public void mAdapterRefresh1(){
        v = mInflater.inflate(R.layout.order_inflate_two, null);
        lv_order = (ListView) v.findViewById(R.id.lv_order_2);
        mAdapter1 = new Order2ListAdapter();
        lv_order.setAdapter(mAdapter1);
        for(int i=0; i<page1.size(); i++){
            mAdapter1.addItem(page1.get(i));
        }
        setClickListener(mAdapter1);
        mAdapter1.notifyDataSetChanged();
    }
    public void mAdapterRefresh2(){
        v = mInflater.inflate(R.layout.order_inflate_three, null);
        lv_order = (ListView) v.findViewById(R.id.lv_order_3);
        mAdapter2 = new Order2ListAdapter();
        lv_order.setAdapter(mAdapter2);
        for(int i=0; i<page2.size(); i++){
            mAdapter2.addItem(page2.get(i));
        }
        mAdapter2.notifyDataSetChanged();
        setClickListener(mAdapter2);
    }
    public void mAdapterRefresh3(){
        v = mInflater.inflate(R.layout.order_inflate_four, null);
        lv_order = (ListView) v.findViewById(R.id.lv_order_4);
        mAdapter3 = new Order2ListAdapter();
        lv_order.setAdapter(mAdapter3);
        for(int i=0; i<page3.size(); i++){
            mAdapter3.addItem(page3.get(i));
        }
        mAdapter3.notifyDataSetChanged();
        setClickListener(mAdapter3);
    }public void mAdapterRefresh4(){
        v = mInflater.inflate(R.layout.order_inflate_five, null);
        lv_order = (ListView) v.findViewById(R.id.lv_order_5);
        mAdapter4 = new Order2ListAdapter();
        lv_order.setAdapter(mAdapter4);
        for(int i=0; i<page4.size(); i++){
            mAdapter4.addItem(page4.get(i));
        }
        mAdapter4.notifyDataSetChanged();
        setClickListener(mAdapter4);
    }
}
