package mkworld29.mobile.com.cafemoa;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.adapter.BasketAdapter;
import mkworld29.mobile.com.cafemoa.entity.CoffeeOption;
import mkworld29.mobile.com.cafemoa.item.BasketItem;
import mkworld29.mobile.com.cafemoa.item.OptionItem;
import mkworld29.mobile.com.cafemoa.prefs.BasketPref;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import mkworld29.mobile.com.cafemoa.xmlEntity.Pay_request;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static java.lang.Integer.parseInt;

public class BaskitActivity extends AppCompatActivity implements View.OnClickListener{

    public static int mWidthPixels, mHeightPixels;

    private TextView tv_title, tv_total;
    private Button btn_order, btn_add_order;
    private Retrofit retrofit;
    private ImageView iv_back;
    private int cafe_pk;
    private int total = 0;
    private BasketAdapter adapter;
    private String thepay_order_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        btn_order = (Button) findViewById(R.id.btn_order);
        //btn_add_order = (Button) findViewById(R.id.btn_add_order);
        btn_order.setOnClickListener(this);
        //btn_add_order.setOnClickListener(this);

        tv_total = (TextView) findViewById(R.id.tv_total);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("장바구니");

        iv_back=(ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);


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

        if(ids.length != 0)
        {
            BasketItem item = null;
            for(int i=0;i<ids.length;i++)
            {
                item = BasketPref.getInstance(this).getBasket(ids[i]);
                total += Integer.parseInt(item.getPrice());
            }
        }

        if(ids.length != 0) {
            BasketItem[] item = new BasketItem[ids.length];

            List<BasketItem> items = new ArrayList<BasketItem>();

            for (int i = 0; i < ids.length; i++) {
                item[i] = BasketPref.getInstance(this).getBasket(ids[i]);
                items.add(item[i]);
            }

            adapter=new BasketAdapter(getApplicationContext(), items,this);
            rv.addItemDecoration(new BaskitActivity.GridSpacingItemDecoration(2, dpToPx(10), true));
            rv.setAdapter(adapter);
        }

        tv_total.setText(String.valueOf(total));
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == iv_back.getId())
            finish();

        if(view.getId() == btn_order.getId())
        {
            final ProgressDialog pd = ProgressDialog.show(BaskitActivity.this, "주문중", "주문중 입니다.");
            String ids[] = BasketPref.getInstance(this).getSplitPrefsCurrentStorage();
            RetrofitConnection.Order_option[] options = new RetrofitConnection.Order_option[ids.length];
            int time=0;
            for(int i=0; i<ids.length; i++){
                BasketItem item=BasketPref.getInstance(this).getBasket(ids[i]);
                CoffeeOption option=item.getOption();
                int shots=option.getShots();
                int size=option.getSize();
                int beverage=option.getPk();
                int amount=option.getAmounts();
                ArrayList<Integer> selections=option.getSelections();

                time=item.getPredict_time();
                //Log.d("TAG", ""+shots+","+size+","+is_ice+","+is_whipping+","+beverage+","+amount+",");

                options[i]=new RetrofitConnection.Order_option(beverage,size,shots,amount,selections);
            }

            //String time=BasketPref.getInstance(this).getBasket(ids[0]).getTime();
            RetrofitConnection.Order_Info info=new RetrofitConnection.Order_Info(0,time,options);
            Gson gson = new Gson();
            String option_json = gson.toJson(info);
            Log.d("SIBAL", option_json);

            RetrofitConnection.ready_payment service = retrofit.create(RetrofitConnection.ready_payment.class);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), option_json);
            final Call<RetrofitConnection.Payment_ready> repos = service.repoContributors(cafe_pk,body);
            repos.enqueue(new Callback<RetrofitConnection.Payment_ready>() {
                @Override
                public void onResponse(Call<RetrofitConnection.Payment_ready> call, Response<RetrofitConnection.Payment_ready> response) {
                    if (response.code() == 200) {
                        //public String create_payment_xml(String orderno,String payusernm, String usernm, String payhpno, String goodsnm, String payrequestamt, String payclosedt, String telno){
                        RetrofitConnection.Payment_ready data = response.body();
                        thepay_order_num=data.order_num;
                        String xml=create_payment_xml(data.order_num, data.cafe_name, data.user_name, data.user_phonenumber, data.menu_name, ""+data.amount_price, data.pay_closetime, data.cafe_phonenumber);
                        thePay_pay_request(xml);
                    }
                    else if(response.code() == 202){
                        Toast.makeText(getApplicationContext(), "영업을 종료하였습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<RetrofitConnection.Payment_ready> call, Throwable t) {
                    Log.d("TAG", t.getLocalizedMessage());
                    pd.dismiss();
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
     * Converting dp to pixelasd
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if(resultCode == Activity.RESULT_OK){
               int position = data.getIntExtra("Position",0);
                adapter.basketList.remove(position);
                adapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
        if(requestCode==1){
            String xml=create_payment_check_xml(thepay_order_num);

            RetrofitConnection.thePay_pay_request service = retrofit.create(RetrofitConnection.thePay_pay_request.class);
            RequestBody body = RequestBody.create(MediaType.parse("application/xml"), xml);
            final Call<ResponseBody> repos = service.repoContributors(body);

            repos.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        try {
                            String source = response.body().string();
                            Serializer serializer = new Persister();
                            Pay_request pay_request = serializer.read(Pay_request.class, source);

                            if(!pay_request.resbody.response.data.statusnm.trim().equals("결제대기"))
                                order_request();
                            else{
                                Toast.makeText(getApplicationContext(), "결제가 취소되었습니다", Toast.LENGTH_SHORT).show();
                            }

                        }catch(Exception e){}
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("TAG", t.getLocalizedMessage());
                }
            });
        }
    }
    public void order_request(){
        final ProgressDialog pd = ProgressDialog.show(BaskitActivity.this, "주문중", "주문중 입니다.");
        String ids[] = BasketPref.getInstance(this).getSplitPrefsCurrentStorage();
        RetrofitConnection.Order_option[] options = new RetrofitConnection.Order_option[ids.length];
        int time=0;
        for(int i=0; i<ids.length; i++){
            BasketItem item=BasketPref.getInstance(this).getBasket(ids[i]);
            CoffeeOption option=item.getOption();
            int shots=option.getShots();
            int size=option.getSize();
            int beverage=option.getPk();
            int amount=option.getAmounts();
            ArrayList<Integer> selections=option.getSelections();
            time=item.getPredict_time();
            //Log.d("TAG", ""+shots+","+size+","+is_ice+","+is_whipping+","+beverage+","+amount+",");

            options[i]=new RetrofitConnection.Order_option(beverage,size,shots,amount,selections);
        }

        //String time=BasketPref.getInstance(this).getBasket(ids[0]).getTime();
        RetrofitConnection.Order_Info info=new RetrofitConnection.Order_Info(0,time,options);
        Gson gson = new Gson();
        String option_json = gson.toJson(info);

        RetrofitConnection.payment_beverages service = retrofit.create(RetrofitConnection.payment_beverages.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), option_json);
        final Call<RetrofitConnection.Payment_Complete> repos = service.repoContributors(cafe_pk,body);
        repos.enqueue(new Callback<RetrofitConnection.Payment_Complete>() {
            @Override
            public void onResponse(Call<RetrofitConnection.Payment_Complete> call, Response<RetrofitConnection.Payment_Complete> response) {
                if (response.code() == 201) {
                    BasketPref.getInstance(getApplicationContext()).removeAllBasket();
                    Intent i = new Intent(getApplicationContext(), OrderCompleteActivity.class);
                    i.putExtra("order_num", response.body().order_num);
                    i.putExtra("payment_okay_date", response.body().order_time);
                    i.putExtra("get_time", response.body().get_time);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                }
                pd.dismiss();
            }

            @Override
            public void onFailure(Call<RetrofitConnection.Payment_Complete> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
                pd.dismiss();
            }
        });
    }
    public void thePay_pay_request(String payment_xml){
        RetrofitConnection.thePay_pay_request service = retrofit.create(RetrofitConnection.thePay_pay_request.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/xml"), payment_xml);
        final Call<ResponseBody> repos = service.repoContributors(body);

        repos.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String source = response.body().string();
                        Serializer serializer = new Persister();
                        Pay_request pay_request=serializer.read(Pay_request.class, source);
                        if(pay_request.resbody.response.data.error==5000){
                            Toast.makeText(getApplicationContext(), "이미 존재하는 주문번호입니다 : "+pay_request.resbody.response.data.orderno, Toast.LENGTH_SHORT).show();
                            return ;
                        }
                        else {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pay_request.resbody.response.data.payurl));
                            startActivityForResult(intent,1);
                        }

                    }catch (Exception e){ Log.d("TAG", e.getLocalizedMessage());}
                }
                else {
                    Toast.makeText(getApplicationContext(), "에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
    }

    public String create_payment_xml(String orderno,String payusernm, String usernm, String payhpno, String goodsnm, String payrequestamt, String payclosedt, String telno){
        String payment_xml="<root>\n" +
                "  <reqhead>\n" +
                "    <userinfo userid=\"thepay1\" passwd=\"7110eda4d09e062aa5e4a390b0a572ac0d2c0220\"/>\n" +
                "  </reqhead>\n" +
                "  <reqbody>\n" +
                "     <request method=\"pay_request\">\n" +
                "       <data orderno        = \""+orderno+"\" \n" +
                "             payusernm      = \""+payusernm+"\" \n" +
                "             usernm         = \""+usernm+"\" \n" +
                "             payhpno        = \""+payhpno+"\" \n" +
                "             goodsnm        = \""+goodsnm+"\" \n" +
                "             payrequestamt  = \""+payrequestamt+"\" \n" +
                "             payclosedt     = \""+payclosedt+"\" \n" +
                "             birthdate      = \"\" \n" +
                "             smssendyn      = \"Y\" \n" +
                "             paymethod      = \"0001\" \n" +
                "             imsyn          = \"N\"\n" +
                "             payitemnm1     = \"\" \n" +
                "             payitemamt1    = \"\" \n" +
                "             payitemnm2     = \"\" \n" +
                "             payitemamt2    = \"\" \n" +
                "             payitemnm3     = \"\"  \n" +
                "             payitemamt3    = \"\" \n" +
                "             payitemnm4     = \"\" \n" +
                "             payitemamt4    = \"\" \n" +
                "             payitemnm5     = \"\"  \n" +
                "             payitemamt5    = \"\" \n" +
                "             payitemnm6     = \"\"  \n" +
                "             payitemamt6    = \"\" \n" +
                "             payitemnm7     = \"\"  \n" +
                "             payitemamt7    = \"\" \n" +
                "             payitemnm8     = \"\"  \n" +
                "             payitemamt8    = \"\" \n" +
                "             etcremark      = \"기타사항\"  \n" +
                "             base64yn       = \"\"\n" +
                "             useretc1       = \"\"  \n" +
                "             useretc2       = \"\"\n" +
                "             useretc3       = \"\"\n" +
                "             telno          = \""+telno+"\"\n" +
                "             mediatype      = \"MC02\"    \n" +
                "             imsurl         = \"http://www.thepay.kr\"/>\n" +
                "     </request>\n" +
                "  </reqbody>\n" +
                "</root>\n";
        return payment_xml;
    }

    public String create_payment_check_xml(String order_num){
        String payment_xml="<root>\n" +
                "  <reqhead>\n" +
                "    <userinfo userid=\"thepay1\" passwd=\"7110eda4d09e062aa5e4a390b0a572ac0d2c0220\"/>\n" +
                "  </reqhead>\n" +
                "  <reqbody>\n" +
                "     <request  method=\"pay_detail\">\n" +
                "       <data orderno=\""+order_num+"\"/>\n" +
                "     </request>\n" +
                "  </reqbody>\n" +
                "</root>";
        return payment_xml;
    }

}
