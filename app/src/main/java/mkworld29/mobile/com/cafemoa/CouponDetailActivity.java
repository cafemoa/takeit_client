package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import mkworld29.mobile.com.cafemoa.adapter.PaymentListViewAdapter;
import mkworld29.mobile.com.cafemoa.adapter.StampAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CouponDetailActivity extends AppCompatActivity {

    private ListView listView;
    PaymentListViewAdapter adapter;
    private GridView gv;

    private TextView tv_cafe_name;
    private TextView tv_cafe_address;
    private ImageView iv_cafe_logo;

    Retrofit retrofit;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_detail);

        intent = getIntent();

        int pk = intent.getIntExtra("sum",0);

        tv_cafe_name = (TextView)findViewById(R.id.tv_cafe_name);
        tv_cafe_address = (TextView)findViewById(R.id.tv_cafe_address);
        iv_cafe_logo = (ImageView)findViewById(R.id.iv_cafe_logo);


        tv_cafe_address.setText(intent.getStringExtra("cafe_address"));
        tv_cafe_name.setText(intent.getStringExtra("cafe_name"));
        retrofit= RetrofitInstance.getInstance(getApplicationContext());
        RetrofitConnection.one_coupon service = retrofit.create(RetrofitConnection.one_coupon.class);

        final Call<RetrofitConnection.Coupon_info> repos = service.repoContributors("get_one_coupon/"+pk+"/");

        repos.enqueue(new Callback<RetrofitConnection.Coupon_info>() {
            @Override
            public void onResponse(Call<RetrofitConnection.Coupon_info> call, Response<RetrofitConnection.Coupon_info> response) {
                if(response.code()==200){
                    RetrofitConnection.Coupon_info info=response.body();
                    tv_cafe_address.setText(info.cafe.locationString);
                    tv_cafe_name.setText(info.cafe.name);
                }else{
                    Toast.makeText(getApplicationContext(), "Error : "+ response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RetrofitConnection.Coupon_info> call, Throwable t) {
                Log.d("TAG",t.getLocalizedMessage());
            }
        });

        gv = (GridView)findViewById(R.id.gv);
        gv.setNumColumns(5);
        StampAdapter stampAdapter = new StampAdapter(
                getApplicationContext(),
                R.layout.item_stamp_on, pk, true);

        gv.setAdapter(stampAdapter);

        adapter = new PaymentListViewAdapter();

        listView = (ListView)findViewById(R.id.payment_listview);


        // AddItem

        RetrofitConnection.recent_payments service2 = retrofit.create(RetrofitConnection.recent_payments.class);

        final Call<List<RetrofitConnection.Recent_payment>> repos2 = service2.repoContributors("recent_payment_list_by_order/"+pk+"/");

        repos2.enqueue(new Callback<List<RetrofitConnection.Recent_payment>>() {
            @Override
            public void onResponse(Call<List<RetrofitConnection.Recent_payment>> call, Response<List<RetrofitConnection.Recent_payment>> response) {
                if(response.code()==200){
                    List<RetrofitConnection.Recent_payment> info=response.body();
                    for(int i=0; i<info.size(); i++){
                        RetrofitConnection.Recent_payment now_payment=info.get(i);
                        String title="["+now_payment.cafe_name+"] "+now_payment.menu_name;
                        adapter.addItem(title, now_payment.price, now_payment.order_time);
                    }
                    listView.setAdapter(adapter);
                }else{
                    Toast.makeText(getApplicationContext(), "Error : "+ response.code(), Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<List<RetrofitConnection.Recent_payment>> call, Throwable t) {
                Log.d("TAG",t.getLocalizedMessage());
            }
        });
    }
}
