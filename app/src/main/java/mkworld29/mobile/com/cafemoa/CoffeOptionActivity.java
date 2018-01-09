package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mkworld29.mobile.com.cafemoa.entity.CoffeeOption;
import mkworld29.mobile.com.cafemoa.item.BasketItem;
import mkworld29.mobile.com.cafemoa.item.OptionItem;
import mkworld29.mobile.com.cafemoa.item.OrderListItem;
import mkworld29.mobile.com.cafemoa.prefs.BasketPref;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CoffeOptionActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_order, btn_get;
    private ImageView iv_back;
    private EditText edt_predict_arrive;
    private TextView tv_size_s, tv_size_m,tv_size_l;
    private TextView tv_shots_minus, tv_shots, tv_shots_plus;
    private TextView tv_hot, tv_ice;
    private TextView tv_whipping_true, tv_whipping_false;
    private TextView tv_price;
    private TextView tv_content;
    private TextView tv_amount_minus, tv_amount, tv_amount_plus;
    private TextView[] arr_amount;
    private TextView[] arr_size;
    private TextView[] arr_shots;
    private TextView[] arr_cold;
    private TextView[] arr_whipping;

    private int size;
    private boolean is_cold, is_whipping;
    private String src_iv_content, src_content, src_cafe_name;
    private int beverage_pk;
    private int cafe_pk;
    private String price;
    private int cafe_min_time;
    private int defaultPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_coffe_option2);

        Intent intent = getIntent();

        size = 0;
        is_cold = false;

        if(intent != null) {
            src_iv_content = intent.getStringExtra("iv_content");
            src_content = intent.getStringExtra("content");
            src_cafe_name = intent.getStringExtra("cafe_name");
            beverage_pk = intent.getIntExtra("beverage_pk", 0);
            cafe_pk = intent.getIntExtra("cafe_pk", 0);
            price = intent.getStringExtra("price");
            cafe_min_time=intent.getIntExtra("cafe_min_time",0);
        }

        Retrofit retrofit= RetrofitInstance.getInstance(getApplicationContext());
        RetrofitConnection.get_beverage_option service = retrofit.create(RetrofitConnection.get_beverage_option.class);

        final Call<List<OptionItem>> repos = service.repoContributors(beverage_pk);
        repos.enqueue(new Callback<List<OptionItem>>() {
            @Override
            public void onResponse(Call<List<OptionItem>> call, Response<List<OptionItem>> response) {
                if(response.code()==200){
                    Log.d("SIBAL", "SIBAL");
                }else{
                    Toast.makeText(getApplicationContext(), "Error : "+ response.code(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<OptionItem>> call, Throwable t) {
                Log.d("TAG",t.getLocalizedMessage());
            }
        });

        tv_price            =   (TextView) findViewById(R.id.tv_order_price);
        iv_back             =   (ImageView)findViewById(R.id.iv_back);
        tv_content          =   (TextView) findViewById(R.id.tv_content);

        tv_content.setText(src_content);

        //defaultPrice = Integer.parseInt(price.split(" ")[0].split(":")[1]);
        setDefaultPrice(0);


        btn_get             =   (Button)findViewById(R.id.btn_get);
        btn_order             =   (Button)findViewById(R.id.btn_order);

        edt_predict_arrive  =   (EditText)findViewById(R.id.edt_predict_arrive);

        tv_amount_minus     =   (TextView)findViewById(R.id.tv_order_amount_minus);
        tv_amount           =   (TextView)findViewById(R.id.tv_order_amount);
        tv_amount_plus      =   (TextView)findViewById(R.id.tv_order_amount_plus);

        tv_size_s           =   (TextView)findViewById(R.id.tv_order_size_s);
        tv_size_m           =   (TextView)findViewById(R.id.tv_order_size_m);
        tv_size_l           =   (TextView)findViewById(R.id.tv_order_size_l);

        tv_shots_minus      =   (TextView)findViewById(R.id.tv_order_shots_minus);
        tv_shots            =   (TextView)findViewById(R.id.tv_order_shots);
        tv_shots_plus       =   (TextView)findViewById(R.id.tv_order_shots_plus);

        tv_hot              =   (TextView)findViewById(R.id.tv_order_hot);
        tv_ice              =   (TextView)findViewById(R.id.tv_order_ice);

        tv_whipping_true    =   (TextView)findViewById(R.id.tv_order_whipping_true);
        tv_whipping_false   =   (TextView)findViewById(R.id.tv_order_whipping_false);


        arr_amount = new TextView[2];
        arr_amount[0] = tv_amount_minus;
        arr_amount[1] = tv_amount_plus;

        arr_size = new TextView[3];
        arr_size[0] = tv_size_s;
        arr_size[1] = tv_size_m;
        arr_size[2] = tv_size_l;

        arr_shots = new TextView[2];
        arr_shots[0] = tv_shots_minus;
        arr_shots[1] = tv_shots_plus;

        arr_cold = new TextView[2];
        arr_cold[0] = tv_hot;
        arr_cold[1] = tv_ice;

        arr_whipping = new TextView [2];
        arr_whipping[0] = tv_whipping_true;
        arr_whipping[1] = tv_whipping_false;

        edt_predict_arrive.setText(""+cafe_min_time);


        btn_get.setOnClickListener(this);
        btn_order.setOnClickListener(this);
        tv_amount_minus.setOnClickListener(this);
        tv_amount_plus.setOnClickListener(this);

        if(price.split(" ").length>=1) {
            tv_size_s.setOnClickListener(this);
            tv_size_s.setText(price.split(" ")[0].split(":")[0]);
        }
        else
            tv_size_s.setVisibility(View.GONE);

        if(price.split(" ").length>=2) {
            tv_size_m.setOnClickListener(this);
            tv_size_m.setText(price.split(" ")[1].split(":")[0]);
        }
        else
            tv_size_m.setVisibility(View.GONE);

        if(price.split(" ").length>=3) {
            tv_size_l.setOnClickListener(this);
            tv_size_l.setText(price.split(" ")[2].split(":")[0]);
        }
        else
            tv_size_l.setVisibility(View.GONE);

        tv_shots_minus.setOnClickListener(this);
        tv_shots_plus.setOnClickListener(this);
        tv_ice.setOnClickListener(this);
        tv_hot.setOnClickListener(this);
        tv_whipping_true.setOnClickListener(this);
        tv_whipping_false.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        onClick(tv_size_s);
        onClick(tv_ice);
        onClick(tv_whipping_false);
    }

    @Override
    public void onClick(View view) {
        if(Integer.parseInt(edt_predict_arrive.getText().toString())<
                cafe_min_time) {
            Toast.makeText(getApplicationContext(), "최소 시간은" +cafe_min_time+"분 입니다.", Toast.LENGTH_SHORT).show();
            edt_predict_arrive.setText(""+cafe_min_time);
        }
        if(view.getId() == tv_amount_minus.getId())
        {
            int amount = Integer.parseInt(tv_amount.getText().toString());
            if(amount>1)
                amount--;
            tv_amount.setText(""+amount);
        }
        else if(view.getId() == tv_amount_plus.getId())
        {
            int amount = Integer.parseInt(tv_amount.getText().toString());
            if(amount<20)
                amount++;
            tv_amount.setText(""+amount);

        }
        else if(view.getId() == tv_size_s.getId())
        {
            setFontDefaults(arr_size);
            setFontStyle(tv_size_s, true);
            size = 0;
            setDefaultPrice(0);
        }
        else if(view.getId() == tv_size_m.getId())
        {
            setFontDefaults(arr_size);
            setFontStyle(tv_size_m, true);
            size = 1;
            setDefaultPrice(1);
        }
        else if(view.getId() == tv_size_l.getId())
        {
            setFontDefaults(arr_size);
            setFontStyle(tv_size_l, true);
            size = 2;
            setDefaultPrice(2);
        }
        else if(view.getId() == tv_shots_minus.getId())
        {
            int amount = Integer.parseInt(tv_shots.getText().toString());
            if(amount>1)
                amount--;
            tv_shots.setText(""+amount);
        }
        else if(view.getId() == tv_shots_plus.getId())
        {
            int amount = Integer.parseInt(tv_shots.getText().toString());
            if(amount<5)
                amount++;
            tv_shots.setText(""+amount);
        }
        else if(view.getId() == btn_get.getId())
        {
            saveBasketItem();
            Toast.makeText(this, "장바구니에 상품을 성공적으로 담았습니다.",Toast.LENGTH_SHORT).show();
            finish();
        }
        else if(view.getId() == btn_order.getId())
        {
            saveBasketItem();
            Intent intent = new Intent(this, BaskitActivity.class);
            intent.putExtra("cafe_pk",cafe_pk);
            startActivity(intent);
            finish();
        }
        else if(view.getId() == iv_back.getId())
        {
            finish();
        }
        tv_price.setText(String.valueOf(Integer.parseInt(tv_amount.getText().toString()) * defaultPrice) + "원");
    }

    private void saveBasketItem()
    {
        String content;
        String cafeName;
        String price;
        CoffeeOption option = null;
        int shots, amount;

        shots = Integer.parseInt(tv_shots.getText().toString());

        amount = Integer.parseInt(tv_amount.getText().toString());

        content = src_content;
        cafeName = src_cafe_name;
        price = tv_price.getText().toString();

        ArrayList<Integer> selections=new ArrayList<>();
        option = new CoffeeOption(shots, size, amount, beverage_pk,selections);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        BasketItem item = new BasketItem(src_iv_content, cafeName, content, price, dateFormat.format(date), amount, Integer.parseInt(edt_predict_arrive.getText().toString()), option);
        BasketPref.getInstance(this).addBasket(item);


    }

    public void setFontStyle(TextView view, boolean is_bold)
    {
        if(is_bold) {
            view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            view.setTextColor(Color.rgb(12,12,12));
        }
        else {
            view.setTypeface(Typeface.DEFAULT);
            view.setTextColor(Color.rgb(172,172,172));
        }
    }

    public void setFontDefaults(TextView[] arr)
    {
        for(TextView t : arr)
        {
            setFontStyle(t,false);
        }
    }

    public void setDefaultPrice(int index){
        defaultPrice = Integer.parseInt(price.split(" ")[index].split(":")[1]);
    }

    public boolean is_checked(TextView view)
    {
        return view.getTypeface() == Typeface.DEFAULT_BOLD;
    }
}
