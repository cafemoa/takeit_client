package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.Option2Acitivity;
import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.item.MainCafeItem;
import mkworld29.mobile.com.cafemoa.item.OrderListItem;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ABCla on 2017-10-07.
 */

public class MainCafeAdapter extends RecyclerView.Adapter<MainCafeAdapter.ViewHolder> {
    Context context;
    ArrayList<MainCafeItem> items;
    int item_layout;
    public MainCafeAdapter(Context context, ArrayList<MainCafeItem> items, int item_layout){
        this.context=context;
        this.items=items;
        this.item_layout=item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_cafe, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainCafeAdapter.ViewHolder holder, int position) {
        final MainCafeItem item = items.get(position);
        //Log.d("TAG",item.getImage());
        Glide.with(context)
                .load(item.getImage())
                .placeholder(R.drawable.decoration)
                .error(R.drawable.decoration)
                .into(holder.image);
        //holder.image.setImageResource(item.getImage());
        holder.name.setText(item.getName());
        holder.tag.setText(item.getTag());
        holder.location.setText(item.getLocation());

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getOpen()) beverage_community(v,item);
                else Toast.makeText(context,"점주의 사정으로 인해 주문이 불가합니다.", Toast.LENGTH_SHORT).show();
            }
        });
        if(item.getOpen())            // 영업 중일때의 조건식 적으시오..
        {
            holder.iv_wall.setVisibility(View.GONE);
            holder.ly_wall.setVisibility(View.GONE);
        }
        else                // 영업중이 아니면
        {
            holder.iv_wall.setVisibility(View.VISIBLE);
            holder.ly_wall.setVisibility(View.VISIBLE);
        }
        if(item.getOpen()) holder.is_open.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView tag;
        TextView is_open;
        TextView location;
        CardView cardview;
        ImageView iv_wall;
        LinearLayout ly_wall;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.main_cafe_image);
            name = (TextView) itemView.findViewById(R.id.main_cafe_name);
            tag = (TextView) itemView.findViewById(R.id.main_cafe_tag);
            is_open = (TextView) itemView.findViewById(R.id.main_is_open);
            location = (TextView) itemView.findViewById(R.id.main_cafe_location);
            cardview = (CardView) itemView.findViewById(R.id.main_cafe_cardview);

            iv_wall     =   (ImageView) itemView.findViewById(R.id.iv_wall) ;
            ly_wall     =   (LinearLayout) itemView.findViewById(R.id.ly_wall);
        }
    }

    ArrayList<OrderListItem> beverage_community(final View v, final MainCafeItem item){
        final ArrayList<OrderListItem> beverages=new ArrayList<>();
        Retrofit retrofit= RetrofitInstance.getInstance(context);
        RetrofitConnection.get_cafe_beverage service = retrofit.create(RetrofitConnection.get_cafe_beverage.class);

        final Call<List<RetrofitConnection.Beverage>> repos = service.repoContributors(item.getPk());

        repos.enqueue(new Callback<List<RetrofitConnection.Beverage>>() {
            @Override
            public void onResponse(Call<List<RetrofitConnection.Beverage>> call, Response<List<RetrofitConnection.Beverage>> response) {
                if(response.code()==200){

                    for(int i=0; i<response.body().size(); i++){
                        RetrofitConnection.Beverage beverage=response.body().get(i);
                        OrderListItem item=new OrderListItem(beverage.name,"http://rest.takeitnow.kr"+beverage.image,beverage.price,beverage.is_best,beverage.type,beverage.pk);
                        beverages.add(item);
                    }
                    Intent i = new Intent(v.getContext(), Option2Acitivity.class);
                    i.putExtra("cafe_pk",item.getPk());
                    i.putExtra("cafe_name", item.getName());
                    i.putExtra("cafe_image", item.getImage());
                    i.putExtra("cafe_location", item.getLocation());
                    i.putExtra("cafe_min_time", item.getMin_time());

                    i.putParcelableArrayListExtra("beverages", beverages);
                    v.getContext().startActivity(i);
                }else{
                    Toast.makeText(context, "Error : "+ response.code(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<RetrofitConnection.Beverage>> call, Throwable t) {
                Log.d("TAG",t.getLocalizedMessage());
            }
        });
        return beverages;
    }
}
