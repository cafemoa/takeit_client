package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    public TextView tv_cafe_name;
    public Order2PagerAdapter adapter;
    private int cafe_pk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option2_acitivity);

        Intent intent=getIntent();
        cafe_pk=intent.getIntExtra("cafe_pk",0);
        ArrayList<OrderListItem2> beverages=intent.getParcelableArrayListExtra("beverages");

        tv_cafe_name = (TextView) findViewById(R.id.tv_cafe_name);
        mPager = (ViewPager) findViewById(R.id.vp_order);

        adapter = new Order2PagerAdapter(getApplicationContext(),cafe_pk);
        adapter.setCafeName(tv_cafe_name.getText().toString());

        for(int i=0; i<beverages.size(); i++){
            adapter.addItemPage0(beverages.get(i));
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

        Retrofit retrofit= RetrofitInstance.getInstance(getApplicationContext());
        RetrofitConnection.get_cafe_beverage service = retrofit.create(RetrofitConnection.get_cafe_beverage.class);

        final Call<List<RetrofitConnection.Beverage>> repos = service.repoContributors(cafe_pk);

        repos.enqueue(new Callback<List<RetrofitConnection.Beverage>>() {
            @Override
            public void onResponse(Call<List<RetrofitConnection.Beverage>> call, Response<List<RetrofitConnection.Beverage>> response) {
                if(response.code()==200){

                    for(int i=0; i<response.body().size(); i++){
                        RetrofitConnection.Beverage beverage=response.body().get(i);
                        OrderListItem2 item=new OrderListItem2(beverage.name,"http://rest.takeitnow.kr"+beverage.image,false,beverage.pk);
                        adapter.addItemPage1(item);
                    }
                    adapter.mAdapterRefresh1();
                    adapter.notifyDataSetChanged();

                }else{
                    Toast.makeText(getApplicationContext(), "Error : "+ response.code(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<RetrofitConnection.Beverage>> call, Throwable t) {
                Log.d("TAG",t.getLocalizedMessage());
            }
        });
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
