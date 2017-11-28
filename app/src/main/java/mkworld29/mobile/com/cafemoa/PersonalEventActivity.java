package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

public class PersonalEventActivity extends AppCompatActivity {

    private ImageView iv_personal_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_personal_event);

        String url = null;
        Intent intent = null;

        intent = getIntent();

        if(intent != null)
        {
            url = intent.getStringExtra("url");
        }

        // 이제 요기에 url 넣으면됨

        //this.getWindow().getDecorView().setBackgroundResource(R.drawable.client_splash);

        iv_personal_event = (ImageView) findViewById(R.id.iv_personal_event);
    }
}
