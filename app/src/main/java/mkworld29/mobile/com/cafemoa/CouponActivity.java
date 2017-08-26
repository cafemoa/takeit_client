package mkworld29.mobile.com.cafemoa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import mkworld29.mobile.com.cafemoa.adapter.CardAdapter;
import mkworld29.mobile.com.cafemoa.adapter.CouponAdapter;
import mkworld29.mobile.com.cafemoa.adapter.StampAdapter;
import mkworld29.mobile.com.cafemoa.entity.Coupon;
import mkworld29.mobile.com.cafemoa.iamportsdk.InicisWebViewClient;
import mkworld29.mobile.com.cafemoa.item.CardItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CouponActivity extends AppCompatActivity {

    int ITEM_SIZE = 5;

    private TextView tv_cafe_name;
    private TextView tv_cafe_address;
    private ImageView iv_cafe_logo;
    private GridView gv;
    Retrofit retrofit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        tv_cafe_address = (TextView)findViewById(R.id.tv_cafe_address);
        tv_cafe_name = (TextView)findViewById(R.id.tv_cafe_name);
        iv_cafe_logo = (ImageView) findViewById(R.id.iv_cafe_logo);
        gv = (GridView)findViewById(R.id.gv);
        gv.setNumColumns(5);

        retrofit= RetrofitInstance.getInstance(getApplicationContext());
        RetrofitConnection.all_coupon service = retrofit.create(RetrofitConnection.all_coupon.class);

        final Call<List<RetrofitConnection.Coupon_info>> repos = service.repoContributors();

        repos.enqueue(new Callback<List<RetrofitConnection.Coupon_info>>() {
            @Override
            public void onResponse(Call<List<RetrofitConnection.Coupon_info>> call, Response<List<RetrofitConnection.Coupon_info>> response) {
                if(response.code()==200){
                    List<RetrofitConnection.Coupon_info> info=response.body();
                    int coupon_num=info.size();
                    List<Coupon> items = new ArrayList<>();
                    Coupon[] item = new Coupon[coupon_num];
                    for(int i=0; i<coupon_num; i++){
                        item[i] = new Coupon(info.get(i).cafe.name,info.get(i).cafe.locationString,R.mipmap.ic_launcher_round, info.get(i).coupon_progress,info.get(i).pk);
                    }

                    RecyclerView rv = (RecyclerView)findViewById(R.id.coupon_rv);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    rv.setHasFixedSize(true);
                    rv.setLayoutManager(mLayoutManager);
                    rv.addItemDecoration(new SpacesItemDecoration(50));


                    tv_cafe_address.setText(item[0].getCafe_address());
                    tv_cafe_name.setText(item[0].getCafe_name());
                    iv_cafe_logo.setImageResource(item[0].getCafe_logo());

                    StampAdapter stampAdapter = new StampAdapter(
                            getApplicationContext(),
                            R.layout.item_stamp_on, item[0].getSum(),true);

                    gv.setAdapter(stampAdapter);

                    for(int i=1;i<coupon_num;i++)
                        items.add(item[i]);


                    rv.setAdapter(new CouponAdapter(getApplicationContext(), items));
                }else{
                    Toast.makeText(getApplicationContext(), "Error : "+ response.code(), Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<List<RetrofitConnection.Coupon_info>> call, Throwable t) {
                Log.d("TAG",t.getLocalizedMessage());
            }
        });

    }


    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parasent, RecyclerView.State state) {
            outRect.right = space;
            outRect.bottom = space;
//
//            // Add top margin only for the first item to avoid double space between items
//            if (parent.getChildLayoutPosition(view) == 0) {
//                outRect.top = space;
//            } else {
//                outRect.top = 0;
//            }
        }
    }


}
