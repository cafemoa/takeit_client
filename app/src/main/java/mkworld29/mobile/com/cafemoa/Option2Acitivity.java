package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import mkworld29.mobile.com.cafemoa.adapter.Order2PagerAdapter;
import mkworld29.mobile.com.cafemoa.adapter.OrderListAdapter;
import mkworld29.mobile.com.cafemoa.adapter.OrderPagerAdapter;

public class Option2Acitivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mPager;
    private TextView tv_coffe, tv_smootie, tv_tea, tv_dessert, tv_etc;
    public TextView tv_cafe_name;
    private int cafe_pk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option2_acitivity);

        Intent i=getIntent();
        cafe_pk=i.getIntExtra("cafe_pk",0);

        tv_cafe_name = (TextView) findViewById(R.id.tv_cafe_name);
        mPager = (ViewPager) findViewById(R.id.vp_order);

        Order2PagerAdapter adapter = new Order2PagerAdapter(getApplicationContext(),cafe_pk);
        adapter.setCafeName(tv_cafe_name.getText().toString());

        mPager.setAdapter(adapter);

        tv_cafe_name = (TextView) findViewById(R.id.tv_cafe_name);
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
