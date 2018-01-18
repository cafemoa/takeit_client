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

import java.util.ArrayList;

import mkworld29.mobile.com.cafemoa.CoffeOptionActivity;
import mkworld29.mobile.com.cafemoa.CoffeeOptionActivity;
import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.item.OrderListItem;

/**
 * Created by parkjaemin on 2017. 9. 26..
 */

public class OrderPagerAdapter extends PagerAdapter{

    private LayoutInflater mInflater;

    private Context mContext;
    ListView lv_order = null;
    String cafe_name;
    OrderListAdapter mAdapter0 = null;
    OrderListAdapter mAdapter1 = null;
    OrderListAdapter mAdapter2 = null;
    OrderListAdapter mAdapter3 = null;
    OrderListAdapter mAdapter4 = null;

    private ArrayList<OrderListItem> page0=new ArrayList<>();
    private ArrayList<OrderListItem> page1=new ArrayList<>();
    private ArrayList<OrderListItem> page2=new ArrayList<>();
    private ArrayList<OrderListItem> page3=new ArrayList<>();
    private ArrayList<OrderListItem> page4=new ArrayList<>();
    View v = null;
    private int cafe_pk;
    private int cafe_min_time;


    public void addItemPage0(OrderListItem item){page0.add(item);}
    public void addItemPage1(OrderListItem item){page1.add(item);}
    public void addItemPage2(OrderListItem item){page2.add(item);}
    public void addItemPage3(OrderListItem item){page3.add(item);}
    public void addItemPage4(OrderListItem item){page4.add(item);}

    public OrderPagerAdapter(Context c, int cafe_pk, int cafe_min_time)
    {
        super();
        mContext = c;
        mInflater = LayoutInflater.from(c);
        this.cafe_pk=cafe_pk;
        this.cafe_min_time=cafe_min_time;
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

        if(position==0){
            if(mAdapter0==null){
                mAdapterRefresh0();
            }
            else{
                v = mInflater.inflate(R.layout.order_inflate_one, null);
                lv_order = (ListView) v.findViewById(R.id.lv_order_1);
                lv_order.setAdapter(mAdapter0);
                setClickListener(mAdapter0);
            }
            ((ViewPager)pager).addView(v, 0);
        }
        else if(position==1){
            if(mAdapter1==null){
                mAdapterRefresh1();
            }
            else{
                v = mInflater.inflate(R.layout.order_inflate_two, null);
                lv_order = (ListView) v.findViewById(R.id.lv_order_2);
                lv_order.setAdapter(mAdapter1);
                setClickListener(mAdapter1);
            }
            ((ViewPager)pager).addView(v, 0);
        }
        else if(position==2){
            if(mAdapter2==null){
                mAdapterRefresh2();
            }
            else{
                v = mInflater.inflate(R.layout.order_inflate_three, null);
                lv_order = (ListView) v.findViewById(R.id.lv_order_3);
                lv_order.setAdapter(mAdapter2);
                setClickListener(mAdapter2);
            }
            ((ViewPager)pager).addView(v, 0);
        }
        else if(position==3){
            if(mAdapter3==null){
                mAdapterRefresh3();
            }
            else{
                v = mInflater.inflate(R.layout.order_inflate_four, null);
                lv_order = (ListView) v.findViewById(R.id.lv_order_4);
                lv_order.setAdapter(mAdapter3);
                setClickListener(mAdapter3);
            }
            ((ViewPager)pager).addView(v, 0);
        }
        else if(position==4){
            if(mAdapter4==null){
                mAdapterRefresh4();
            }
            else{
                v = mInflater.inflate(R.layout.order_inflate_five, null);
                lv_order = (ListView) v.findViewById(R.id.lv_order_5);
                lv_order.setAdapter(mAdapter4);
                setClickListener(mAdapter4);
            }
            ((ViewPager)pager).addView(v, 0);
        }

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

    public void setClickListener(final OrderListAdapter mAdapter){
        lv_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OrderListItem item= (OrderListItem) mAdapter.getItem(i);
                Intent intent = new Intent(v.getContext(), CoffeeOptionActivity.class);
                String iv_content = item.getImg();
                String content = item.getContent();
                intent.putExtra("iv_content", iv_content);
                intent.putExtra("content",content);
                intent.putExtra("cafe_name", cafe_name);
                intent.putExtra("cafe_pk", cafe_pk);
                intent.putExtra("beverage_pk", ((OrderListItem) mAdapter.getItem(i)).getPk());
                intent.putExtra("cafe_min_time",cafe_min_time);
                intent.putExtra("price", item.getPrice());
                intent.putExtra("have_shot", item.getHave_shot());
                intent.putExtra("add_shot_price", item.getAdd_shot_price());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });
    }
    public void mAdapterRefresh0(){

        v = mInflater.inflate(R.layout.order_inflate_one, null);
        lv_order = (ListView) v.findViewById(R.id.lv_order_1);
        mAdapter0 = new OrderListAdapter();
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
        mAdapter1 = new OrderListAdapter();
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
        mAdapter2 = new OrderListAdapter();
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
        mAdapter3 = new OrderListAdapter();
        lv_order.setAdapter(mAdapter3);
        for(int i=0; i<page3.size(); i++){
            mAdapter3.addItem(page3.get(i));
        }
        mAdapter3.notifyDataSetChanged();
        setClickListener(mAdapter3);
    }
    public void mAdapterRefresh4(){
        v = mInflater.inflate(R.layout.order_inflate_five, null);
        lv_order = (ListView) v.findViewById(R.id.lv_order_5);
        mAdapter4 = new OrderListAdapter();
        lv_order.setAdapter(mAdapter4);
        for(int i=0; i<page4.size(); i++){
            mAdapter4.addItem(page4.get(i));
        }
        mAdapter4.notifyDataSetChanged();
        setClickListener(mAdapter4);
    }
}
