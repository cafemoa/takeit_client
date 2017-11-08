package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.adapter.Order2PagerAdapter;
import mkworld29.mobile.com.cafemoa.adapter.OrderListAdapter;
import mkworld29.mobile.com.cafemoa.adapter.OrderPagerAdapter;
import mkworld29.mobile.com.cafemoa.item.OrderListItem;
import mkworld29.mobile.com.cafemoa.item.OrderListItem2;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Option2Acitivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mPager;
    private TextView tv_coffe, tv_smootie, tv_tea, tv_dessert, tv_etc;
    public TextView tv_cafe_name,tv_cafe_location;
    public ImageView iv_cafe_image;
    public Order2PagerAdapter adapter;
    private int cafe_pk;
    private String cafe_location,cafe_name,cafe_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_option2_acitivity);

        Intent intent=getIntent();
        cafe_pk=intent.getIntExtra("cafe_pk",0);
        cafe_name=intent.getStringExtra("cafe_name");
        cafe_location=intent.getStringExtra("cafe_location");
        cafe_image=intent.getStringExtra("cafe_image");

        ArrayList<OrderListItem2> beverages=intent.getParcelableArrayListExtra("beverages");

        tv_cafe_name = (TextView) findViewById(R.id.tv_cafe_name);
        tv_cafe_name.setText(cafe_name);

        tv_cafe_location = (TextView) findViewById(R.id.tv_cafe_location);
        tv_cafe_location.setText(cafe_location);

        iv_cafe_image=(ImageView)findViewById(R.id.iv_order);
        Glide.with(getApplicationContext())
                .load(cafe_image)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(iv_cafe_image);

        mPager = (ViewPager) findViewById(R.id.vp_order);

        adapter = new Order2PagerAdapter(getApplicationContext(),cafe_pk);
        adapter.setCafeName(tv_cafe_name.getText().toString());

        for(int i=0; i<beverages.size(); i++){
            OrderListItem2 item=beverages.get(i);

            if(item.getType()==0) adapter.addItemPage0(item);
            if(item.getType()==1) adapter.addItemPage1(item);
            if(item.getType()==2) adapter.addItemPage2(item);
            if(item.getType()==3) adapter.addItemPage3(item);
            if(item.getType()==4) adapter.addItemPage4(item);
        }

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
