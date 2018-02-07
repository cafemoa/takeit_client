package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import mkworld29.mobile.com.cafemoa.R;

public class OrderCompleteActivity extends AppCompatActivity {
    private TextView tv_order_num;
    private TextView tv_payment_okay_date;
    private Button recent_order_button;
    private TextView tv_wait_time;
    private ImageView iv_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_complete);

        Intent i=getIntent();
        int order_num=i.getIntExtra("order_num",0);
        String payment_okay_date=i.getStringExtra("payment_okay_date");
        int remain_order=i.getIntExtra("remain_order",0);

        tv_payment_okay_date  = (TextView)findViewById(R.id.tv_payment_okay_date);
        tv_order_num          = (TextView)findViewById(R.id.tv_order_number);
        recent_order_button   = (Button)findViewById(R.id.recent_order_button);
        tv_wait_time          = (TextView)findViewById(R.id.tv_wait_time);
        iv_home               = (ImageView)findViewById(R.id.iv_home);

        tv_payment_okay_date.setText(payment_okay_date);
        tv_order_num.setText(""+order_num);

        tv_wait_time.setText(remain_order+"분");

        recent_order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderCompleteActivity.this,OrderListActivity.class);
                startActivity(i);
            }
        });
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderCompleteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
