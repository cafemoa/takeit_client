package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.Option2Acitivity;
import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.item.MainCafeItem;
import mkworld29.mobile.com.cafemoa.item.OrderListItem2;
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
                .placeholder(R.mipmap.ic_launcher)

                .error(R.mipmap.ic_launcher)
                .into(holder.image);
        //holder.image.setImageResource(item.getImage());
        holder.name.setText(item.getName());
        holder.tag.setText(item.getTag());
        holder.location.setText(item.getLocation());
        final ArrayList<OrderListItem2> beverages=beverage_community(item.getPk());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Option2Acitivity.class);
                i.putExtra("cafe_pk",item.getPk());
                i.putParcelableArrayListExtra("beverages", beverages);
                v.getContext().startActivity(i);
            }
        });
        if(!item.getEvent()) holder.event.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView tag;
        TextView event;
        TextView location;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.main_cafe_image);
            name = (TextView) itemView.findViewById(R.id.main_cafe_name);
            tag = (TextView) itemView.findViewById(R.id.main_cafe_tag);
            event = (TextView) itemView.findViewById(R.id.main_is_event);
            location = (TextView) itemView.findViewById(R.id.main_cafe_location);
            cardview = (CardView) itemView.findViewById(R.id.main_cafe_cardview);
        }
    }

    ArrayList<OrderListItem2> beverage_community(int cafe_pk){
        final ArrayList<OrderListItem2> beverages=new ArrayList<>();
        Retrofit retrofit= RetrofitInstance.getInstance(context);
        RetrofitConnection.get_cafe_beverage service = retrofit.create(RetrofitConnection.get_cafe_beverage.class);

        final Call<List<RetrofitConnection.Beverage>> repos = service.repoContributors(cafe_pk);

        repos.enqueue(new Callback<List<RetrofitConnection.Beverage>>() {
            @Override
            public void onResponse(Call<List<RetrofitConnection.Beverage>> call, Response<List<RetrofitConnection.Beverage>> response) {
                if(response.code()==200){

                    for(int i=0; i<response.body().size(); i++){
                        RetrofitConnection.Beverage beverage=response.body().get(i);
                        OrderListItem2 item=new OrderListItem2(beverage.name,"http://rest.takeitnow.kr"+beverage.image,false,beverage.pk);
                        beverages.add(item);
                    }
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
