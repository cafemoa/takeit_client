package mkworld29.mobile.com.cafemoa.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.CouponDetailActivity;
import mkworld29.mobile.com.cafemoa.Values;
import mkworld29.mobile.com.cafemoa.entity.Coupon;

/**
 * Created by chmod777 on 2017. 6. 25..
 */

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {
    Context context;
    List<Coupon> couponList; //공지사항 정보 담겨있음

    public CouponAdapter(Context context, List<Coupon> couponList) {
        this.context = context;
        this.couponList = couponList;
    }

    @Override
    public CouponAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //recycler view에 반복될 아이템 레이아웃 연결
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coupon_detail,null);
        return new CouponAdapter.ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    /** 정보 및 이벤트 처리는 이 메소드에서 구현 **/

    @Override
    public void onBindViewHolder(final CouponAdapter.ViewHolder holder, int position) {
        StampAdapter stampAdapter = new StampAdapter(
                holder._v.getContext(),
                R.layout.item_stamp_on, couponList.get(position).getSum(), false);

        holder.tv_cafe_address.setText(couponList.get(position).getCafe_address());
        holder.tv_cafe_name.setText(String.valueOf(couponList.get(position).getCafe_name()));
        //holder.iv_cafe_logo.setImageResource(couponList.get(position).getCafe_logo());
        holder.gridView.setAdapter(stampAdapter);
        holder.sum = couponList.get(position).getSum();
        holder.pk = couponList.get(position).getPk();
    }

    @Override
    public int getItemCount() {
        return this.couponList.size();
    }
    /** item layout 불러오기 **/
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cafe_address;
        TextView tv_cafe_name;
        ImageView iv_cafe_logo;
        GridView gridView;
        int sum;
        int pk;
        CardView cv;
        View _v;

        public ViewHolder(View v) {
            super(v);
            _v = v;
            tv_cafe_address = (TextView) v.findViewById(R.id.tv_cafe_address);
            tv_cafe_name = (TextView) v.findViewById(R.id.tv_cafe_name);
            //iv_cafe_logo = (ImageView) v.findViewById(R.id.iv_cafe_logo);

            tv_cafe_address.setTextColor(Color.WHITE);
            tv_cafe_name.setTextColor(Color.WHITE);

            gridView = (GridView) v.findViewById(R.id.gv);
            gridView.setNumColumns(5);
            cv = (CardView) v.findViewById(R.id.cv);

            double random = Math.random();
            v.setBackgroundColor(Values.getInstance().colors[(int)(random * 4)]);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CouponDetailActivity.class);
                    intent.putExtra("cafe_name", tv_cafe_name.getText());
                    intent.putExtra("cafe_address", tv_cafe_address.getText());
                    intent.putExtra("sum", pk);

                    v.getContext().startActivity(intent);
                }
            });


        }
    }
}
