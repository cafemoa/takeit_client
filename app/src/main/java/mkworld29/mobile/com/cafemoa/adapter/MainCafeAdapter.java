package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.item.MainCafeItem;

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
        Drawable drawable = ContextCompat.getDrawable(context, item.getImage());
        holder.image.setBackground(drawable);
        //holder.image.setImageResource(item.getImage());
        holder.name.setText(item.getName());
        holder.tag.setText(item.getTag());
        holder.location.setText(item.getLocation());
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
}
