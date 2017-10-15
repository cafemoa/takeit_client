package mkworld29.mobile.com.cafemoa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mkworld29.mobile.com.cafemoa.entity.CoffeeOption;
import mkworld29.mobile.com.cafemoa.item.BasketItem;
import mkworld29.mobile.com.cafemoa.prefs.BasketPref;

public class CoffeeOptionActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_submit, btn_add_basket;
    private RadioGroup rg_size, rg_whipping, rg_temp;
    private RadioButton rb_M,rb_L,rb_XL, rb_whipping_true,rb_whipping_false,rb_temp_cold,rb_temp_hot;
    private TextView tv_content, tv_price, tv_cafe_name;
    private ImageView iv_content;
    private Spinner spinner_shots;
    Activity activity;
    private int pk;
    private int cafe_pk;
    String menu_name = null;
    String price=null;
    String image_url=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_option);

        activity = this;

        Intent intent = getIntent();

        // 옵션 추가를 위해 온 경우
        if(intent != null)
        {
            menu_name = intent.getStringExtra("menu_name");
            price = intent.getStringExtra("menu_price");
            image_url = intent.getStringExtra("image_url");
            pk = intent.getIntExtra("menu_pk",0);
            cafe_pk = intent.getIntExtra("cafe_pk",0);
        }


        iv_content          = (ImageView)   findViewById(R.id.iv_content);

        tv_content          = (TextView)    findViewById(R.id.tv_content);
        tv_price            = (TextView)    findViewById(R.id.tv_price);
        tv_cafe_name        = (TextView)    findViewById(R.id.tv_cafe_name);

        btn_submit          = (Button)      findViewById(R.id.btn_submit);
        btn_add_basket      = (Button)      findViewById(R.id.btn_add_basket);

        rg_size             = (RadioGroup)  findViewById(R.id.rg_size);
        rg_temp             = (RadioGroup)  findViewById(R.id.rg_temp);
        rg_whipping         = (RadioGroup)  findViewById(R.id.rg_whipping);

        rb_M                = (RadioButton) findViewById(R.id.rb_sizeM);
        rb_L                = (RadioButton) findViewById(R.id.rb_sizeL);
        rb_XL               = (RadioButton) findViewById(R.id.rb_sizeXL);
        rb_temp_cold        = (RadioButton) findViewById(R.id.rb_ice);
        rb_temp_hot         = (RadioButton) findViewById(R.id.rb_hot);
        rb_whipping_true    = (RadioButton) findViewById(R.id.rb_whippingTrue);
        rb_whipping_false   = (RadioButton) findViewById(R.id.rb_whippingFalse);

        btn_submit.setOnClickListener(this);
        btn_add_basket.setOnClickListener(this);

        tv_content.setText(menu_name);
        tv_price.setText(price);
        Glide.with(getApplicationContext())
                .load(image_url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(iv_content);

        spinner_shots = (Spinner) findViewById(R.id.shots_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.shots_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_shots.setAdapter(adapter);



    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btn_submit.getId())
        {
            saveBasketItem();
            
            Intent intent = new Intent(this, BaskitActivity.class);
            intent.putExtra("cafe_pk", cafe_pk);
            startActivity(intent);
            finish();
            Animation anim = AnimationUtils.loadAnimation(this,R.anim.basket_enter_start);
        }
        else if(view.getId() == btn_add_basket.getId())
        {
            saveBasketItem();
            finish();
        }

    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.basket_end_start, R.anim.basket_end_exit);
    }

    private void saveBasketItem()
    {
        String content, image, cafe_name, price, time;
        int shots;
        int size;
        boolean is_whipping, is_cold;


        if(rg_size.getCheckedRadioButtonId()==rb_M.getId())
            size = 0;
        else if(rg_size.getCheckedRadioButtonId()==rb_L.getId())
            size = 1;
        else
            size = 2;

        if(rg_whipping.getCheckedRadioButtonId()==rb_whipping_true.getId())
            is_whipping = true;
        else is_whipping = false;

        if(rg_temp.getCheckedRadioButtonId() == rb_temp_cold.getId())
            is_cold = true;
        else is_cold = false;

        DateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
        Date date = new Date();
        time = dateFormat.format(date);


        shots = Integer.parseInt(""+String.valueOf(spinner_shots.getSelectedItem()).charAt(0));

        content = String.valueOf(tv_content.getText());
        image = "http://cfile208.uf.daum.net/image/2265AB49563AC29E183589";
        cafe_name = String.valueOf(tv_cafe_name.getText());
        price = String.valueOf(tv_price.getText());

        //CoffeeOption option = new CoffeeOption(shots,size,is_cold,is_whipping,pk);

        //BasketItem item = new BasketItem(image,cafe_name,content,price,time, 1,option);

        //BasketPref.getInstance(this).addBasket(item);
    }
    
}
