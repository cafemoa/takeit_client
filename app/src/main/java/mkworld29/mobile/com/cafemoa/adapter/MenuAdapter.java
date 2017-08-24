package mkworld29.mobile.com.cafemoa.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
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

import mkworld29.mobile.com.cafemoa.CoffeeOptionActivity;
import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.entity.Menu;

/**
 * Created by chmod777 on 2017. 6. 24..
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    Context context;
    List<Menu> noticeList;

    public MenuAdapter(Context context, List<Menu> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //recycler view에 반복될 아이템 레이아웃 연결
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu,null);
        return new ViewHolder(v);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    /** 정보 및 이벤트 처리는 이 메소드에서 구현 **/
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tv_title.setText(noticeList.get(position).getName());
        holder.tv_price.setText(String.valueOf(noticeList.get(position).getPrice()));
        ImageView target=holder.iv_profile;
        Glide.with(context)
                .load(noticeList.get(position).getImage())
                .placeholder(R.mipmap.ic_launcher)
                .override(50,50)
                .error(R.mipmap.ic_launcher)
                .into(target);
        holder._v.setTag(noticeList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.noticeList.size();
    }
    /** item layout 불러오기 **/
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_price;
        ImageView iv_profile;
        CardView cv;
        View _v;

        public ViewHolder(View v) {
            super(v);
            _v = v;
            tv_title = (TextView) v.findViewById(R.id.tv_name);
            iv_profile = (ImageView) v.findViewById(R.id.iv_image);
            tv_price = (TextView) v.findViewById(R.id.tv_price);
            cv = (CardView) v.findViewById(R.id.cv);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, String.valueOf(v.getTag()) + "을(를) 주문하시겠습니까?", 10000).setAction("예", new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            //할거
                            Intent intent = new Intent(v.getContext(), CoffeeOptionActivity.class);
                            intent.putExtra("menu_name",tv_title.getText());
                            intent.putExtra("menu_price",tv_price.getText());
                            v.getContext().startActivity(intent);

                        }
                    }).show();
                }
            });
        }
    }
}

