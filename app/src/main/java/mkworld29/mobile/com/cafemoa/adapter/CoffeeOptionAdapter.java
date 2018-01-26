package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import mkworld29.mobile.com.cafemoa.CoffeeOptionActivity;
import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.Values;
import mkworld29.mobile.com.cafemoa.entity.CoffeeOption;
import mkworld29.mobile.com.cafemoa.entity.MenuOption;

/**
 * Created by parkjaemin on 2018. 1. 10..
 */

public class CoffeeOptionAdapter extends BaseAdapter{

    private ArrayList<MenuOption> optionList = new ArrayList<MenuOption>();

    public static ArrayList<Integer> arr_pk;
    public HashMap<Integer,Boolean> keyMap;
    private boolean one_selector;

    public CoffeeOptionAdapter(){
        keyMap = new HashMap<Integer, Boolean>();
        arr_pk = new ArrayList<Integer>();
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
        final View _convertView;
        final ViewGroup _parent = parent;

        if(convertView == null)
        {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.item_coffee_option, parent, false);
        }

        _convertView = convertView;

        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tv_add_price = (TextView) convertView.findViewById(R.id.tv_add_price);
        final CheckBox is_checked = (CheckBox) convertView.findViewById(R.id.is_checked);
        final MenuOption opt = optionList.get(position);

        tv_title.setText(opt.getTitle());
        tv_add_price.setText("+" + opt.getPrice() + "원  ");
        is_checked.setTag(String.valueOf(opt.getPk()));

        is_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyMap == null) return;
                CheckBox box = (CheckBox) v;

                if(!one_selector){
                    opt.setIs_check(box.isChecked());
                    return ;
                }

                for(int i=0; i<optionList.size(); i++)
                    optionList.get(i).setIs_check(false);

                opt.setIs_check(box.isChecked());
                Iterator<Map.Entry<Integer,Boolean>> iterator = keyMap.entrySet().iterator();

                while(iterator.hasNext())
                {
                    Map.Entry<Integer, Boolean> entry = (Map.Entry<Integer,Boolean>) iterator.next();
                    if(entry.getKey() != opt.getPk()) {
                        keyMap.put(entry.getKey(), false);
                        CheckBox tmp = (CheckBox) _parent.findViewWithTag(String.valueOf(entry.getKey()));
                        if(tmp != null) {
                            tmp.setChecked(false); // 여기서부터 고치자
                        }
                    }
                }

                ((CoffeeOptionActivity)context).setAmount_price();

            }
        });

        return convertView;
    }

    public void addItem(String title, int price,int pk)
    {
        MenuOption opt = new MenuOption(title, price, pk,false);
        optionList.add(opt);
        if(arr_pk != null && keyMap != null)
        {
            arr_pk.add(pk);
            keyMap.put(pk, false);
        }

    }
    public ArrayList<MenuOption> getOptionList(){ return optionList; }
    public void setOne_selector(boolean one_selector){ this.one_selector=one_selector; }
}
