package mkworld29.mobile.com.cafemoa;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mkworld29.mobile.com.cafemoa.adapter.OrderPagerAdapter;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mPager;
    private TextView tv_coffe, tv_smootie, tv_tea, tv_dessert, tv_etc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mPager = (ViewPager) findViewById(R.id.vp_order);
        mPager.setAdapter(new OrderPagerAdapter(getApplicationContext()));

        tv_coffe = (TextView) findViewById(R.id.tv_coffe_espresso);
        tv_smootie= (TextView) findViewById(R.id.tv_ade_smootie);
        tv_tea = (TextView) findViewById(R.id.tv_tea);
        tv_dessert = (TextView) findViewById(R.id.tv_dessert);
        tv_etc = (TextView) findViewById(R.id.tv_etc);

        tv_coffe.setOnClickListener(this);
        tv_smootie.setOnClickListener(this);
        tv_tea.setOnClickListener(this);
        tv_dessert.setOnClickListener(this);
        tv_etc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == tv_coffe.getId())
        {
            mPager.setCurrentItem(0);
        }
        else if(view.getId() == tv_smootie.getId())
        {
            mPager.setCurrentItem(1);
        }
        else if(view.getId() == tv_tea.getId())
        {
            mPager.setCurrentItem(2);

        }
        else if(view.getId() == tv_dessert.getId())
        {
            mPager.setCurrentItem(3);
        }
        else if(view.getId() == tv_etc.getId())
        {
            mPager.setCurrentItem(4);
        }
    }
}
