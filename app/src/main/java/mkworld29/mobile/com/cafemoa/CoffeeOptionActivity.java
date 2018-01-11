package mkworld29.mobile.com.cafemoa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mkworld29.mobile.com.cafemoa.adapter.CoffeeOptionListAdapter;
import mkworld29.mobile.com.cafemoa.entity.CoffeeOption;
import mkworld29.mobile.com.cafemoa.item.BasketItem;
import mkworld29.mobile.com.cafemoa.prefs.BasketPref;

public class CoffeeOptionActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lv_add_menu;
    private ListView lv_add_option;

    private EditText edt_predict_arrive;

    private LinearLayout ly_back;

    private ImageView iv_amount_minus,
                        iv_amount_plus;

    private ImageView iv_shot_minus,
                        iv_shot_plus;

    private TextView tv_amount,
                        tv_shot;

    private TextView tv_price;

    private Button btn_hot, btn_ice, btn_small, btn_medium,
                                    btn_large, btn_take, btn_order;

    private int amount;
    private int shot;
    boolean is_hot  = true;
    private int size = 0;

    private CoffeeOptionListAdapter add_menu_adapter;
    private CoffeeOptionListAdapter add_option_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_option);

        amount = shot = 1;

        lv_add_menu = (ListView) findViewById(R.id.lv_add_menu);
        lv_add_option = (ListView) findViewById(R.id.lv_add_option);

        edt_predict_arrive = (EditText) findViewById(R.id.edt_predict_arrive);

        add_menu_adapter = new CoffeeOptionListAdapter();
        add_option_adapter = new CoffeeOptionListAdapter();

        lv_add_menu.setAdapter(add_menu_adapter);
        lv_add_option.setAdapter(add_option_adapter);

        add_menu_adapter.addItem("휘핑크림",500);
        add_menu_adapter.addItem("악수",300);
        add_option_adapter.addItem("딸기",0);
        add_option_adapter.addItem("사과",0);
        add_option_adapter.addItem("오렌지",0);
        add_option_adapter.addItem("수박",0);

        ly_back         = (LinearLayout)    findViewById(R.id.ly_back);
        iv_amount_minus = (ImageView)       findViewById(R.id.iv_amount_minus);
        iv_amount_plus  = (ImageView)       findViewById(R.id.iv_amount_plus);
        iv_shot_minus   = (ImageView)       findViewById(R.id.iv_shot_minus);
        iv_shot_plus    = (ImageView)       findViewById(R.id.iv_shot_plus);
        tv_amount       = (TextView)        findViewById(R.id.tv_amount);
        tv_shot         = (TextView)        findViewById(R.id.tv_shot);
        btn_hot         = (Button)          findViewById(R.id.btn_hot);
        btn_ice         = (Button)          findViewById(R.id.btn_ice);
        btn_medium      = (Button)          findViewById(R.id.btn_medium);
        btn_small       = (Button)          findViewById(R.id.btn_small);
        btn_large       = (Button)          findViewById(R.id.btn_large);
        btn_take        = (Button)          findViewById(R.id.btn_take);
        btn_order       = (Button)          findViewById(R.id.btn_order);

        ly_back.setOnClickListener(this);
        iv_amount_plus.setOnClickListener(this);
        iv_amount_minus.setOnClickListener(this);
        iv_shot_minus.setOnClickListener(this);
        iv_shot_plus.setOnClickListener(this);
        btn_hot.setOnClickListener(this);
        btn_ice.setOnClickListener(this);
        btn_medium.setOnClickListener(this);
        btn_small.setOnClickListener(this);
        btn_large.setOnClickListener(this);
        btn_take.setOnClickListener(this);
        btn_order.setOnClickListener(this);

        tv_shot.setText(String.valueOf(shot));

        setListViewHeightBasedOnChildren(lv_add_menu);
        setListViewHeightBasedOnChildren(lv_add_option);

        lv_add_menu.setDivider(null);
        lv_add_option.setDivider(null);

        edt_predict_arrive.setFocusable(false);

        onClick(btn_hot);
        onClick(btn_small);
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

        if(v.getId() == ly_back.getId())
        {
            finish();
        }
        else if(v.getId() == iv_amount_minus.getId())
        {
            amount = (amount<=1)?amount:amount-1;
            tv_amount.setText(String.valueOf(amount));
        }
        else if(v.getId() == iv_amount_plus.getId())
        {
            amount = (amount>=5)?amount:amount+1;
            tv_amount.setText(String.valueOf(amount));
        }
        else if(v.getId() == iv_shot_minus.getId())
        {
            shot = (shot<=1)?shot:shot-1;
            tv_shot.setText(String.valueOf(shot));
        }
        else if(v.getId() == iv_shot_plus.getId())
        {
            shot = (shot>=5)?shot:shot+1;
            tv_shot.setText(String.valueOf(shot));
        }
        else if(v.getId() == btn_hot.getId())
        {
            if(is_hot == false) is_hot = true;
            setDrawableRed(btn_hot);
            setDrawableDefault(btn_ice);
        }
        else if(v.getId() == btn_ice.getId())
        {
            if(is_hot == true) is_hot = false;
            setDrawableRed(btn_ice);
            setDrawableDefault(btn_hot);
        }
        else if(v.getId() == btn_small.getId())
        {
            if(size != 0) size = 0;
            setDrawableRed(btn_small);
            setDrawableDefault(btn_medium);
            setDrawableDefault(btn_large);
        }
        else if(v.getId() == btn_medium.getId())
        {
            if(size != 1) size = 1;
            setDrawableRed(btn_medium);
            setDrawableDefault(btn_small);
            setDrawableDefault(btn_large);
        }
        else if(v.getId() == btn_large.getId())
        {
            if(size != 2) size = 2;
            setDrawableRed(btn_large);
            setDrawableDefault(btn_small);
            setDrawableDefault(btn_medium);
        }
        else if(v.getId() == btn_take.getId())
        {

        }
        else if(v.getId() == btn_order.getId())
        {

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
}
