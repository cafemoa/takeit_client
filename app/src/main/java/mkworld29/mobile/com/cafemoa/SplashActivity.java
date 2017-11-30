package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import mkworld29.mobile.com.cafemoa.retrofit.SharedPreference;

public class SplashActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().getDecorView().setBackgroundResource(R.drawable.client_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if(!SharedPreference.getInstance(getApplicationContext()).get("Authorization").equals("")){
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(SplashActivity.this, ReadyActivity.class);
                    startActivity(i);
                }


                // close this activity
                finish();
            }
        }, 2000);
    }
}
