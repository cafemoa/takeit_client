package mkworld29.mobile.com.cafemoa.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.item.CardItem;

/**
 * Created by chmod777 on 2017. 6. 24..
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    Context context;
    List<CardItem> noticeList; //공지사항 정보 담겨있음

    public CardAdapter(Context context, List<CardItem> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //recycler view에 반복될 아이템 레이아웃 연결
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,null);
        return new ViewHolder(v);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    /** 정보 및 이벤트 처리는 이 메소드에서 구현 **/

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tv_cafe_name.setText(noticeList.get(position).getCafe_name()); //?쒕ぉ
        holder.tv_content.setText(noticeList.get(position).getContent());
        holder.tv_price.setText(String.valueOf(noticeList.get(position).getPrice()) + "원");

        ImageView target=holder.iv_profile;
        Glide.with(context)
                .load(noticeList.get(position).getImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(target);
    }


    @Override
    public int getItemCount() {
        return this.noticeList.size();
    }
    /** item layout 불러오기 **/
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cafe_name;
        TextView tv_content;
        ImageView iv_profile;
        TextView tv_price;
        CardView cv;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "주문하시겠습니까?", 10000).setAction("예", new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            //할거
                        }
                    }).show();
                }
            });
            tv_cafe_name = (TextView) v.findViewById(R.id.tv_cafe_name);
            tv_content = (TextView) v.findViewById(R.id.tv_content);
            tv_price = (TextView) v.findViewById(R.id.tv_price);
            iv_profile = (ImageView) v.findViewById(R.id.iv_image);
            cv = (CardView) v.findViewById(R.id.cv);
        }
    }
}

