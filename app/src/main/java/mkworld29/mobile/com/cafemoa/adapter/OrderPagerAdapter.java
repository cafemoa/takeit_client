package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import mkworld29.mobile.com.cafemoa.R;

/**
 * Created by parkjaemin on 2017. 9. 26..
 */

public class OrderPagerAdapter extends PagerAdapter {

    private LayoutInflater mInflater;

    public OrderPagerAdapter(Context c)
    {
        super();
        mInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object instantiateItem(View pager, int position) {
        View v = null;
        if(position==0){
            v = mInflater.inflate(R.layout.order_inflate_one, null);
            TextView tv_test = (TextView) v.findViewById(R.id.tv_test);
            tv_test.setText("INflate One");
        }
        else if(position==1){
            v = mInflater.inflate(R.layout.order_inflate_one, null);
            TextView tv_test = (TextView) v.findViewById(R.id.tv_test);
            tv_test.setText("INflate Two");
        }else if(position==2){
            v = mInflater.inflate(R.layout.order_inflate_one, null);
            TextView tv_test = (TextView) v.findViewById(R.id.tv_test);
            tv_test.setText("INflate Three");
        }else if(position==3){
            v = mInflater.inflate(R.layout.order_inflate_one, null);
            TextView tv_test = (TextView) v.findViewById(R.id.tv_test);
            tv_test.setText("INflate Four");
        }else if(position==4){
            v = mInflater.inflate(R.layout.order_inflate_one, null);
            TextView tv_test = (TextView) v.findViewById(R.id.tv_test);
            tv_test.setText("INflate Five");
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
