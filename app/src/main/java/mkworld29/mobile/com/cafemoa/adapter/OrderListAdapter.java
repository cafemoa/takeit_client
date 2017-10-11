package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.item.OrderListItem;
import mkworld29.mobile.com.cafemoa.item.PaymentListViewItem;

import static android.graphics.Typeface.BOLD;

/**
 * Created by chmod777 on 2017. 6. 25..
 */

public class OrderListAdapter extends BaseAdapter{

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

    private boolean is_showed;

    public OrderListAdapter()
    {

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        View.OnClickListener listener;
        is_showed = false;



        listener = new View.OnClickListener() {
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
                else if(view.getId() == rv_content.getId())
                {

                }
            }
        };

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_list_item, parent, false);
        }

        View row = convertView;

        row.setOnClickListener(listener);

        rv_content          =   (RelativeLayout)convertView.findViewById(R.id.rv_content);
        lv_option           =   (LinearLayout)convertView.findViewById(R.id.lv_order_option);

        tv_content          =   (TextView)convertView.findViewById(R.id.tv_content);
        iv_content          =   (ImageView)convertView.findViewById(R.id.iv_content);
        iv_best             =   (ImageView)convertView.findViewById(R.id.iv_best);

        tv_amount_minus     =   (TextView)convertView.findViewById(R.id.tv_order_amount_minus);
        tv_amount           =   (TextView)convertView.findViewById(R.id.tv_order_amount);
        tv_amount_plus      =   (TextView)convertView.findViewById(R.id.tv_order_amount_plus);

        tv_size_s           =   (TextView)convertView.findViewById(R.id.tv_order_size_s);
        tv_size_m           =   (TextView)convertView.findViewById(R.id.tv_order_size_m);
        tv_size_l           =   (TextView)convertView.findViewById(R.id.tv_order_size_l);

        tv_shots_minus      =   (TextView)convertView.findViewById(R.id.tv_order_shots_minus);
        tv_shots            =   (TextView)convertView.findViewById(R.id.tv_order_shots);
        tv_shots_plus       =   (TextView)convertView.findViewById(R.id.tv_order_shots_plus);

        tv_hot              =   (TextView)convertView.findViewById(R.id.tv_order_hot);
        tv_ice              =   (TextView)convertView.findViewById(R.id.tv_order_ice);

        tv_whipping_true    =   (TextView)convertView.findViewById(R.id.tv_order_whipping_true);
        tv_whipping_false   =   (TextView)convertView.findViewById(R.id.tv_order_whipping_false);

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

        rv_content.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              if(!is_showed) {
                                                  lv_option.setVisibility(View.VISIBLE);
                                                  is_showed = !is_showed;
                                              }
                                              else {
                                                  lv_option.setVisibility(View.GONE);
                                                  is_showed = !is_showed;
                                              }
                                          }
                                      });
        tv_amount_minus.setOnClickListener(listener);
        tv_amount_plus.setOnClickListener(listener);
        tv_size_s.setOnClickListener(listener);
        tv_size_m.setOnClickListener(listener);
        tv_size_l.setOnClickListener(listener);
        tv_shots_minus.setOnClickListener(listener);
        tv_shots_plus.setOnClickListener(listener);
        tv_ice.setOnClickListener(listener);
        tv_hot.setOnClickListener(listener);
        tv_whipping_true.setOnClickListener(listener);
        tv_whipping_false.setOnClickListener(listener);


        tv_content.setText(listViewItemList.get(position).getContent());
        Glide.with(context)
                .load(listViewItemList.get(position).getImg())
                .placeholder(R.drawable.option_americano)
                .error(R.drawable.option_americano)
                .into(iv_content);
        if(listViewItemList.get(position).is_best())
            iv_best.setVisibility(View.VISIBLE);
        else
            iv_best.setVisibility(View.GONE);

        return convertView;
    }


    public void addItem(String content, String image, boolean is_best)
    {
        OrderListItem item = new OrderListItem(content,image,is_best);

        listViewItemList.add(item);

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
