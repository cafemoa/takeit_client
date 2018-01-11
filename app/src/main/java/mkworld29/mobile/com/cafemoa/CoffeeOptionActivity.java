package mkworld29.mobile.com.cafemoa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import mkworld29.mobile.com.cafemoa.adapter.CoffeeOptionListAdapter;

public class CoffeeOptionActivity extends AppCompatActivity {

    private ListView lv_add_menu;
    private ListView lv_add_option;

    private CoffeeOptionListAdapter add_menu_adapter;
    private CoffeeOptionListAdapter add_option_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_option);

        lv_add_menu = (ListView) findViewById(R.id.lv_add_menu);
        lv_add_option = (ListView) findViewById(R.id.lv_add_option);

        add_menu_adapter = new CoffeeOptionListAdapter();
        add_option_adapter = new CoffeeOptionListAdapter();

        lv_add_menu.setAdapter(add_menu_adapter);
        lv_add_option.setAdapter(add_option_adapter);

        add_menu_adapter.addItem("휘핑크림",500);
        add_option_adapter.addItem("딸기",0);
        add_option_adapter.addItem("사과",0);
        add_option_adapter.addItem("오렌지",0);

        lv_add_menu.setDivider(null);
        lv_add_option.setDivider(null);
    }
}
