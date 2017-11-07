package mkworld29.mobile.com.cafemoa;

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

        iv_personal_event = (ImageView) findViewById(R.id.iv_personal_event);
    }
}
