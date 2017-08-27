package mkworld29.mobile.com.cafemoa.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mkworld29.mobile.com.cafemoa.BaskitActivity;
import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.entity.CoffeeOption;
import mkworld29.mobile.com.cafemoa.item.BasketItem;
import mkworld29.mobile.com.cafemoa.item.CardItem;
import mkworld29.mobile.com.cafemoa.prefs.BasketPref;

/**
 * Created by chmod777 on 2017. 6. 24..
 */

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {
    Context context;
    List<BasketItem> basketList; //공지사항 정보 담겨있음

    private static int CONTENT_TAG_ID = 1231241232;


    public BasketAdapter(Context context, List<BasketItem> basketList) {
        this.context = context;
        this.basketList = basketList;
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
        holder.tv_content.setTag(CONTENT_TAG_ID,basketList.get(position).getId());
        holder.tv_price.setText(String.valueOf(basketList.get(position).getPrice()) + "원");
        holder.tv_shots.setText(String.valueOf(option.getShots()));
        holder.tv_is_cold.setText(String.valueOf(option.is_cold()));
        holder.tv_is_whipping.setText(String.valueOf(option.is_whipping()));
        holder.tv_size.setText(String.valueOf(option.getSize()));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(holder._v.getContext(),
                R.array.basket_option_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(adapter);
        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, final View selectedView, int i, long l) {
                switch (i)
                {
                    /** 수정 선택 시 */
                    case 0:
                        break;
                    /** 삭제 선택 시 */
                    case 1:

                        LayoutInflater inflater = (LayoutInflater) adapterView.getContext()
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                        final View layout = inflater.inflate(R.layout.popup_delete,
                                (ViewGroup) adapterView.findViewById(R.id.ly_popup_all));
                        final PopupWindow pwindo = new PopupWindow(layout, BaskitActivity.mWidthPixels-100, BaskitActivity.mHeightPixels-500, true);
                        pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

                        Button btn_yes  = (Button) layout.findViewById(R.id.btn_yes);
                        Button btn_no   = (Button) layout.findViewById(R.id.btn_no);

                        View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(view.getId() == layout.findViewById(R.id.btn_yes).getId())
                                {
                                    BasketPref.getInstance(
                                            adapterView.getContext()).deleteBasket(
                                            basketList.get(position).getId()
                                    );
                                    ((View)adapterView.getParent()).setVisibility(View.GONE);
                                    pwindo.dismiss();
                                }
                                else
                                {
                                    pwindo.dismiss();
                                }
                            }
                        };
                        btn_yes.setOnClickListener(cancel_button_click_listener);
                        btn_no.setOnClickListener(cancel_button_click_listener);

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                tv_size;
        ImageView iv_content;
        View _v;
        Spinner spinner;
        CardView cv;

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
            spinner         = (Spinner)  v.findViewById(R.id.spinner_option);
            cv              = (CardView) v.findViewById(R.id.cv);

            _v = v;

        }
    }
}

