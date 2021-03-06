package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mkworld29.mobile.com.cafemoa.adapter.CoffeeOptionAdapter;
import mkworld29.mobile.com.cafemoa.adapter.CoffeeOptionListAdapter;
import mkworld29.mobile.com.cafemoa.entity.CoffeeOption;
import mkworld29.mobile.com.cafemoa.entity.MenuOption;
import mkworld29.mobile.com.cafemoa.entity.MenuOptionList;
import mkworld29.mobile.com.cafemoa.item.BasketItem;
import mkworld29.mobile.com.cafemoa.item.OptionItem;
import mkworld29.mobile.com.cafemoa.prefs.BasketPref;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CoffeeOptionActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lv_add_option_list;

    private LinearLayout ly_back;

    private ImageView iv_amount_minus,
                        iv_amount_plus;

    private ImageView iv_shot_minus,
                        iv_shot_plus;

    private TextView tv_amount,
                        tv_shot;

    private TextView tv_price;

    private Button btn_small, btn_medium,
                                    btn_large, btn_take, btn_order;

    private TextView tv_add_shot;
    private View add_shot_divider;
    private CheckBox cb_coupon_sale;
    private TextView tv_sale_price;

    private int amount;
    private int shot;
    private int size = 0;
    private boolean is_cold;
    private String src_iv_content, src_content, src_cafe_name;
    private int beverage_pk;
    private int cafe_pk;
    private String price;
    private boolean have_shot;
    private int add_shot_price;
    private int amount_price;
    private int cafe_coupon_num;
    private int cafe_coupon_price;
    private CoffeeOptionListAdapter add_option_list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_option);

        amount=shot=1;
        amount_price=0;

        lv_add_option_list = (ListView) findViewById(R.id.add_option_list);

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
            have_shot=intent.getBooleanExtra("have_shot", true);
            add_shot_price=intent.getIntExtra("add_shot_price", 0);
            cafe_coupon_num=intent.getIntExtra("cafe_coupon_num", 0);
            cafe_coupon_price=intent.getIntExtra("cafe_coupon_price", 0);
        }

        ly_back         = (LinearLayout)    findViewById(R.id.ly_back);
        iv_amount_minus = (ImageView)       findViewById(R.id.iv_amount_minus);
        iv_amount_plus  = (ImageView)       findViewById(R.id.iv_amount_plus);
        iv_shot_minus   = (ImageView)       findViewById(R.id.iv_shot_minus);
        iv_shot_plus    = (ImageView)       findViewById(R.id.iv_shot_plus);
        tv_amount       = (TextView)        findViewById(R.id.tv_amount);
        tv_shot         = (TextView)        findViewById(R.id.tv_shot);
        btn_medium      = (Button)          findViewById(R.id.btn_medium);
        btn_small       = (Button)          findViewById(R.id.btn_small);
        btn_large       = (Button)          findViewById(R.id.btn_large);
        btn_take        = (Button)          findViewById(R.id.btn_take);
        btn_order       = (Button)          findViewById(R.id.btn_order);
        tv_price        = (TextView)        findViewById(R.id.tv_price);
        tv_add_shot     = (TextView)        findViewById(R.id.tv_add_shot);
        add_shot_divider= (View)            findViewById(R.id.add_shot_divider);
        cb_coupon_sale  = (CheckBox)        findViewById(R.id.cb_coupon_sale);
        tv_sale_price   = (TextView)        findViewById(R.id.tv_sale_price);

        if(cafe_coupon_num<10 || cafe_coupon_price<=0)
            cb_coupon_sale.setEnabled(false);
        tv_sale_price.setText("-"+cafe_coupon_price+"원");

        cb_coupon_sale.setOnClickListener(this);
        if(!have_shot){
            iv_shot_minus.setVisibility(View.GONE);
            iv_shot_plus.setVisibility(View.GONE);
            tv_shot.setVisibility(View.GONE);
            tv_add_shot.setVisibility(View.GONE);
            add_shot_divider.setVisibility(View.GONE);
        }

        if(price.split(" ").length>=1) {
            btn_small.setOnClickListener(this);
            btn_small.setText(price.split(" ")[0].split(":")[0]);
        }
        else
            btn_small.setVisibility(View.GONE);

        if(price.split(" ").length>=2) {
            btn_medium.setOnClickListener(this);
            btn_medium.setText(price.split(" ")[1].split(":")[0]);
        }
        else
            btn_medium.setVisibility(View.GONE);

        if(price.split(" ").length>=3) {
            btn_large.setOnClickListener(this);
            btn_large.setText(price.split(" ")[2].split(":")[0]);
        }
        else
            btn_large.setVisibility(View.GONE);


        Retrofit retrofit = RetrofitInstance.getInstance(getApplicationContext());
        RetrofitConnection.get_beverage_option service = retrofit.create(RetrofitConnection.get_beverage_option.class);

        final Call<List<OptionItem>> repos = service.repoContributors(beverage_pk);
        repos.enqueue(new Callback<List<OptionItem>>() {
            @Override
            public void onResponse(Call<List<OptionItem>> call, Response<List<OptionItem>> response) {
                if(response.code()==200){
                    add_option_list_adapter = new CoffeeOptionListAdapter();
                    for(int i=0; i<response.body().size(); i++){
                        OptionItem item=response.body().get(i);
                        String content = item.getContent();
                        List<OptionItem.Selection> selections=item.getSelections();
                        CoffeeOptionAdapter tmp = new CoffeeOptionAdapter();

                        for(int j=0; j<selections.size(); j++){
                            OptionItem.Selection selection=selections.get(j);
                            tmp.addItem(selection.getContent(),selection.getAdd_price(), selection.getPk());
                        }
                        tmp.setOne_selector(item.getOne_selector());
                        add_option_list_adapter.addItem(content,tmp);
                    }
                    lv_add_option_list.setAdapter(add_option_list_adapter);

                    lv_add_option_list.setDivider(null);
                    lv_add_option_list.setDividerHeight(30);

                    setListViewHeightBasedOnChildren(lv_add_option_list);

                }else{
                    Toast.makeText(getApplicationContext(), "Error : "+ response.code(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<OptionItem>> call, Throwable t) {
                Log.d("TAG",t.getLocalizedMessage());
            }
        });

        tv_price.setText(price+"원");
        ly_back.setOnClickListener(this);
        iv_amount_plus.setOnClickListener(this);
        iv_amount_minus.setOnClickListener(this);
        iv_shot_minus.setOnClickListener(this);
        iv_shot_plus.setOnClickListener(this);
        btn_medium.setOnClickListener(this);
        btn_small.setOnClickListener(this);
        btn_large.setOnClickListener(this);
        btn_take.setOnClickListener(this);
        btn_order.setOnClickListener(this);

        tv_shot.setText(String.valueOf(shot));

        //setListViewHeightBasedOnChildren(lv_add_option_list);

        ly_back.setFocusable(true);
        setAmount_price();
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            //listItem.measure(0, 0);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();

        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public void onClick(View v) {
        saveBasketItem();
        if(v.getId() == ly_back.getId())
        {
            finish();
        }
        else if(v.getId() == iv_amount_minus.getId())
        {
            amount = (amount<=1)?amount:amount-1;
            tv_amount.setText(String.valueOf(amount));
            setAmount_price();
        }
        else if(v.getId() == iv_amount_plus.getId())
        {
            amount = (amount>=5)?amount:amount+1;
            tv_amount.setText(String.valueOf(amount));
            setAmount_price();
        }
        else if(v.getId() == iv_shot_minus.getId())
        {
            shot = (shot<=1)?shot:shot-1;
            tv_shot.setText(String.valueOf(shot));
            setAmount_price();
        }
        else if(v.getId() == iv_shot_plus.getId())
        {
            shot = (shot>=5)?shot:shot+1;
            tv_shot.setText(String.valueOf(shot));
            setAmount_price();
        }
        else if(v.getId() == btn_small.getId())
        {
            if(size != 0) size = 0;
            setDrawableRed(btn_small);
            setDrawableDefault(btn_medium);
            setDrawableDefault(btn_large);
            setAmount_price();
        }
        else if(v.getId() == btn_medium.getId())
        {
            if(size != 1) size = 1;
            setDrawableRed(btn_medium);
            setDrawableDefault(btn_small);
            setDrawableDefault(btn_large);
            setAmount_price();
        }
        else if(v.getId() == btn_large.getId())
        {
            if(size != 2) size = 2;
            setDrawableRed(btn_large);
            setDrawableDefault(btn_small);
            setDrawableDefault(btn_medium);
            setAmount_price();
        }
        else if(v.getId() == btn_take.getId())
        {
            Toast.makeText(this, "장바구니에 상품을 성공적으로 담았습니다.",Toast.LENGTH_SHORT).show();
            finish();
        }
        else if(v.getId() == btn_order.getId())
        {
            Intent intent = new Intent(this, BaskitActivity.class);
            intent.putExtra("cafe_pk",cafe_pk);
            startActivity(intent);
            finish();
        }
        else if(v.getId() == cb_coupon_sale.getId()){
            if(amount_price!=0 && amount_price<3000){
                Toast.makeText(getApplicationContext(),"3000원 이상 결제 가능합니다.", Toast.LENGTH_SHORT).show();
                cb_coupon_sale.setChecked(false);
            }

            setAmount_price();
        }
    }

    public void setDrawableRed(View view)
    {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable( getResources().getDrawable(R.drawable.red_round_button) );
        } else {
            view.setBackground( getResources().getDrawable(R.drawable.red_round_button));
        }
    }

    public void setDrawableDefault(View view)
    {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable( getResources().getDrawable(R.drawable.gray_round_button) );
        } else {
            view.setBackground( getResources().getDrawable(R.drawable.gray_round_button));
        }
    }

    private void saveBasketItem()
    {
        String content;
        String cafeName;
        String price;
        CoffeeOption option = null;
        int shots, amount;

        shots = Integer.parseInt(tv_shot.getText().toString());
        amount = Integer.parseInt(tv_amount.getText().toString());

        content = src_content;
        cafeName = src_cafe_name;
        price = tv_price.getText().toString().substring(0,tv_price.length()-1);
        ArrayList<Integer> selections = new ArrayList<>();
        try {
            ArrayList<MenuOptionList> optionList = add_option_list_adapter.getOptionList();
            for (int i = 0; i < optionList.size(); i++) {
                ArrayList<MenuOption> optionList1 = optionList.get(i).getOptions().getOptionList();
                for (int j = 0; j < optionList1.size(); j++) {
                    if(optionList1.get(j).isIs_check())
                        selections.add(optionList1.get(j).getPk());
                }
            }
        }catch (NullPointerException e){}
        option = new CoffeeOption(shots, size, amount, beverage_pk,selections);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        BasketItem item = new BasketItem(src_iv_content, cafeName, content, price, dateFormat.format(date), amount, option);
        BasketPref.getInstance(this).addBasket(item);
    }


//    private void saveBasketItem()
//    {
//        String content;
//        String cafeName;
//        String price;
//        CoffeeOption option = null;
//        int shots, amount;
//
//        shots = Integer.parseInt(tv_shot.getText().toString());
//
//        amount = Integer.parseInt(tv_amount.getText().toString());
//
//        cafeName = ;
//        price = tv_price.getText().toString();
//
//        ArrayList<Integer> selections=new ArrayList<>();
//        option = new CoffeeOption(shots, size, amount, beverage_pk,selections);
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date = new Date();
//        BasketItem item = new BasketItem(src_iv_content, cafeName, content, price, dateFormat.format(date), amount, Integer.parseInt(edt_predict_arrive.getText().toString()), option);
//        BasketPref.getInstance(this).addBasket(item);
//
//
//    }


    public void setAmount_price(){
        amount_price=0;
        try {
            ArrayList<MenuOptionList> optionList = add_option_list_adapter.getOptionList();
            for (int i = 0; i < optionList.size(); i++) {
                ArrayList<MenuOption> optionList1 = optionList.get(i).getOptions().getOptionList();
                for (int j = 0; j < optionList1.size(); j++) {
                    amount_price+=optionList1.get(j).isIs_check()?
                            optionList1.get(j).getPrice() : 0;
                }
            }
        }catch (NullPointerException e){}
        amount_price+=Integer.parseInt(price.split(" ")[size].split(":")[1]);
        amount_price+=add_shot_price*(shot-1);
        amount_price*=amount;
        if(cb_coupon_sale.isChecked()){
            amount_price-=cafe_coupon_price;
        }
        tv_price.setText(amount_price+"원");
    }
    public int getAmount_price(){ return amount_price; }
}
