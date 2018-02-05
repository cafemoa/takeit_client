package mkworld29.mobile.com.cafemoa.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mkworld29.mobile.com.cafemoa.BaskitActivity;
import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.entity.CoffeeOption;
import mkworld29.mobile.com.cafemoa.item.BasketItem;
import mkworld29.mobile.com.cafemoa.BaskitDeleteDialog;

/**
 * Created by chmod777 on 2017. 6. 24..
 */

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {
    private Context context;
    private BaskitActivity activity;
    public List<BasketItem> basketList; //공지사항 정보 담겨있음

    private static int CONTENT_TAG_ID = 1231241232;


    public BasketAdapter(Context context, List<BasketItem> basketList, BaskitActivity activity) {
        this.context = context;
        this.basketList = basketList;
        this.activity=activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //recycler view에 반복될 아이템 레이아웃 연결
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_basket,null);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    /** 정보 및 이벤트 처리는 이 메소드에서 구현 **/

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(basketList.get(position) == null)
            return;

        CoffeeOption option = basketList.get(position).getOption();

        holder.tv_cafe_name.setText(basketList.get(position).getCafe_name());
        holder.tv_content.setText(basketList.get(position).getContent());
        holder.tv_price.setText(String.valueOf(basketList.get(position).getPrice()));

        if(basketList.get(position).getAmount() == 1)
            holder.tv_amount.setVisibility(View.GONE);
        else
            holder.tv_amount.setText("수량 " + basketList.get(position).getAmount() + "잔");

        if(option.getShots() == 0)
            holder.tv_shots.setText("샷 추가 없음");
        else holder.tv_shots.setText(String.valueOf(option.getShots())+"샷");

        switch(option.getSize())
        {
            case 0:
                holder.tv_size.setText("( R )");
                break;
            case 1:
                holder.tv_size.setText("( L )");
                break;
            case 2:
                holder.tv_size.setText("( XL )");
                break;

        }

        holder.iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BaskitDeleteDialog.class);
                intent.putExtra("ID", basketList.get(position).getId());
                intent.putExtra("Position",position);
                activity.startActivityForResult(intent,0);
            }
        });

    }


    @Override
    public int getItemCount() {
        return this.basketList.size();
    }
    /** item layout 불러오기 **/
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cafe_name,
                tv_content,
                tv_shots,
                tv_is_whipping,
                tv_is_cold,
                tv_price,
                tv_size,
                tv_time,
                tv_amount;
        ImageView iv_content;
        ImageView iv_cancel;
        View _v;
        CardView cv;
        String image_url;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            tv_cafe_name    = (TextView) v.findViewById(R.id.tv_cafe_name);
            tv_content      = (TextView) v.findViewById(R.id.tv_content);
            tv_price        = (TextView) v.findViewById(R.id.tv_price);
            tv_is_cold      = (TextView) v.findViewById(R.id.tv_is_cold);
            tv_is_whipping  = (TextView) v.findViewById(R.id.tv_is_whipping);
            tv_shots        = (TextView) v.findViewById(R.id.tv_shots);
            tv_size         = (TextView) v.findViewById(R.id.tv_size);
            tv_amount       = (TextView) v.findViewById(R.id.tv_amount);
            cv              = (CardView) v.findViewById(R.id.cv);
            iv_content      = (ImageView) v.findViewById(R.id.iv_content);
            iv_cancel       = (ImageView) v.findViewById(R.id.iv_cancel);
            _v = v;

        }
    }
}