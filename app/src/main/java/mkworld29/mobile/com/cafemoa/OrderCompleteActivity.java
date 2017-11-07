package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import mkworld29.mobile.com.cafemoa.R;

public class OrderCompleteActivity extends AppCompatActivity {
    private TextView tv_order_num;
    private TextView tv_payment_okay_date;
    private Button recent_order_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_complete);

        Intent i=getIntent();
        int order_num=i.getIntExtra("order_num",0);
        String payment_okay_date=i.getStringExtra("payment_okay_date");

        tv_payment_okay_date  = (TextView)findViewById(R.id.tv_payment_okay_date);
        tv_order_num          = (TextView)findViewById(R.id.tv_order_number);
        recent_order_button   = (Button)findViewById(R.id.recent_order_button);

        tv_payment_okay_date.setText(payment_okay_date);
        tv_order_num.setText(""+order_num);

        recent_order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderCompleteActivity.this,OrderListActivity.class);
                startActivity(i);
            }
        });
    }

}
