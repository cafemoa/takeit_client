package mkworld29.mobile.com.cafemoa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.entity.MenuOptionList;

/**
 * Created by parkjaemin on 2018. 1. 10..
 */

public class CoffeeOptionListAdapter extends BaseAdapter{
    private ArrayList<MenuOptionList> optionList = new ArrayList<MenuOptionList>();

    public CoffeeOptionListAdapter(){

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
            convertView = inf.inflate(R.layout.item_coffee_option_list, parent, false);
        }

        TextView option_name = (TextView) convertView.findViewById(R.id.option_name);
        ListView options = (ListView) convertView.findViewById(R.id.add_options);
        MenuOptionList opt = optionList.get(position);
        options.setAdapter(opt.getOptions());
        option_name.setText(opt.getContent());

        setListViewHeightBasedOnChildren(options);
        options.setDivider(null);
        return convertView;
    }

    public ArrayList<MenuOptionList> getOptionList(){
        return optionList;
    }

    public void addItem(String content, CoffeeOptionAdapter optionAdapter)
    {
        MenuOptionList opt = new MenuOptionList(content,optionAdapter);
        optionList.add(opt);
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            //listItem.measure(0, 0);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight;
        listView.setLayoutParams(params);

        listView.requestLayout();
    }
}
