package mkworld29.mobile.com.cafemoa;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mkworld29.mobile.com.cafemoa.item.OrderListItem;

public class CoffeOption2Activity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<OrderListItem> listViewItemList = new ArrayList<>();
    private RelativeLayout rv_content;
    private LinearLayout lv_option;
    private TextView tv_content;
    private ImageView iv_content,iv_best;
    private TextView tv_amount_minus, tv_amount, tv_amount_plus;
    private TextView tv_size_s, tv_size_m,tv_size_l;
    private TextView tv_shots_minus, tv_shots, tv_shots_plus;
    private TextView tv_hot, tv_ice;
    private TextView tv_whipping_true, tv_whipping_false;
    private TextView[] arr_amount;
    private TextView[] arr_size;
    private TextView[] arr_shots;
    private TextView[] arr_cold;
    private TextView[] arr_whipping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffe_option2);

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
    }

    @Override
    public void onClick(View view) {
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
        }
        else if(view.getId() == tv_size_m.getId())
        {
            setFontDefaults(arr_size);
            setFontStyle(tv_size_m, true);
        }
        else if(view.getId() == tv_size_l.getId())
        {
            setFontDefaults(arr_size);
            setFontStyle(tv_size_l, true);
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
        else if(view.getId() == tv_hot.getId())
        {
            setFontDefaults(arr_cold);
            setFontStyle(tv_hot, true);
        }
        else if(view.getId() == tv_ice.getId())
        {
            setFontDefaults(arr_cold);
            setFontStyle(tv_ice, true);
        }
        else if(view.getId() == tv_whipping_true.getId())
        {
            setFontDefaults(arr_whipping);
            setFontStyle(tv_whipping_true, true);
        }
        else if(view.getId() == tv_whipping_false.getId())
        {
            setFontDefaults(arr_whipping);
            setFontStyle(tv_whipping_false, true);
        }
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
}
