package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.entity.CoffeeOption;
import mkworld29.mobile.com.cafemoa.entity.MenuOption;

/**
 * Created by parkjaemin on 2018. 1. 10..
 */

public class CoffeeOptionAdapter extends BaseAdapter{

    private ArrayList<MenuOption> optionList = new ArrayList<MenuOption>();

    public CoffeeOptionAdapter(){

    }

    @Override
    public int getCount() {
        return optionList.size();
    }

    @Override
    public Object getItem(int position) {
        return optionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null)
        {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.item_coffee_option, parent, false);
        }

        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tv_add_price = (TextView) convertView.findViewById(R.id.tv_add_price);
        final CheckBox is_checked = (CheckBox) convertView.findViewById(R.id.is_checked);
        final MenuOption opt = optionList.get(position);

        tv_title.setText(opt.getTitle());
        tv_add_price.setText("+" + opt.getPrice() + "원  ");

        is_checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                opt.setIs_check(isChecked);
            }
        });
        return convertView;
    }

    public void addItem(String title, int price,int pk)
    {
        MenuOption opt = new MenuOption(title, price, pk,false);
        optionList.add(opt);
    }
    public ArrayList<MenuOption> getOptionList(){ return optionList; }
}
