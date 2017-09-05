package mkworld29.mobile.com.cafemoa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.adapter.BasketAdapter;
import mkworld29.mobile.com.cafemoa.adapter.CardAdapter;
import mkworld29.mobile.com.cafemoa.entity.CoffeeOption;
import mkworld29.mobile.com.cafemoa.item.BasketItem;
import mkworld29.mobile.com.cafemoa.item.CardItem;
import mkworld29.mobile.com.cafemoa.prefs.BasketPref;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BaskitActivity extends AppCompatActivity implements View.OnClickListener{

    public static int mWidthPixels, mHeightPixels;

    private Button btn_submit;
    private Retrofit retrofit;
    private int cafe_pk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baskit);

        Intent intent = getIntent();
        cafe_pk=intent.getIntExtra("cafe_pk", 0);
        retrofit=RetrofitInstance.getInstance(getApplicationContext());
        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        // since SDK_INT = 1;
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        // 상태바와 메뉴바의 크기를 포함해서 재계산
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try {
                mWidthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                mHeightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
            }
        // 상태바와 메뉴바의 크기를 포함
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                mWidthPixels = realSize.x;
                mHeightPixels = realSize.y;
            } catch (Exception ignored) {
            }
        }

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv_basket_card);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(BaskitActivity.this, 1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(mLayoutManager);

        String ids[] = BasketPref.getInstance(this).getSplitPrefsCurrentStorage();

        Log.d("mkWorld29.com.cafemoa", ""+ids.length);

        if(ids.length != 0) {
            BasketItem[] item = new BasketItem[ids.length];

            List<BasketItem> items = new ArrayList<BasketItem>();

            for (int i = 0; i < ids.length; i++) {
                item[i] = BasketPref.getInstance(this).getBasket(ids[i]);
                items.add(item[i]);
            }
            rv.addItemDecoration(new BaskitActivity.GridSpacingItemDecoration(2, dpToPx(10), true));
            rv.setAdapter(new BasketAdapter(getApplicationContext(), items));
        }
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == btn_submit.getId())
        {
            final ProgressDialog pd = ProgressDialog.show(BaskitActivity.this, "주문중", "주문중 입니다.");
            String ids[] = BasketPref.getInstance(this).getSplitPrefsCurrentStorage();
            RetrofitConnection.Order_option[] options = new RetrofitConnection.Order_option[ids.length];
            for(int i=0; i<ids.length; i++){
                BasketItem item=BasketPref.getInstance(this).getBasket(ids[i]);
                CoffeeOption option=item.getOption();

                int shots=option.getShots();
                int size=option.getSize();
                boolean is_ice=option.is_cold();
                boolean is_whipping=option.is_whipping();
                int beverage=option.getPk();
                options[i]=new RetrofitConnection.Order_option(beverage,is_whipping,is_ice,size,shots);
            }
            RetrofitConnection.Order_Info info=new RetrofitConnection.Order_Info(0,options);
            Gson gson = new Gson();
            String option_json = gson.toJson(info);

            RetrofitConnection.payment_beverages service = retrofit.create(RetrofitConnection.payment_beverages.class);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), option_json);
            final Call<ResponseBody> repos = service.repoContributors(cafe_pk,body);
            repos.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 201) {
                        Toast.makeText(getApplicationContext(), "주문이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        BasketPref.getInstance(getApplicationContext()).removeAllBasket();
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("TAG", t.getLocalizedMessage());
                }
            });
        }
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void finish() {
        this.overridePendingTransition(R.anim.basket_end_start, R.anim.basket_end_exit);
        super.finish();
    }
}
