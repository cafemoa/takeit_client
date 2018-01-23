package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import mkworld29.mobile.com.cafemoa.prefs.SignupPref;


/*
* 여기 iv_back 써야함 //
* */

/**
 * Created by ABCla on 2017-11-05.
 */

public class SignupPasswordActivity extends AppCompatActivity implements View.OnClickListener{
    SignupPref pref;
    private ImageView iv_next;
    private ImageView iv_back;



    private EditText password;
    private EditText password_check;
    private TextView tv_password1;
    private TextView tv_password2;
    private TextView tv_password_check1;
    private TextView tv_password_check2;
    private TextView tv_isnot_password_equal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup_password);

        pref=SignupPref.getInstance(getApplicationContext());

        iv_next                  =   (ImageView) findViewById(R.id.iv_next);
        iv_back                  =   (ImageView) findViewById(R.id.iv_back);
        password                  =   (EditText)findViewById(R.id.sign_password);
        password_check            =   (EditText)findViewById(R.id.sign_password_check);

        tv_password1              =   (TextView)findViewById(R.id.tv_password1);
        tv_password2              =   (TextView)findViewById(R.id.tv_password2);
        tv_password_check1        =   (TextView)findViewById(R.id.tv_password_check1);
        tv_password_check2        =   (TextView)findViewById(R.id.tv_password_check2);
        tv_isnot_password_equal   =   (TextView)findViewById(R.id.tv_isnot_password_equal);

        iv_next.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        password.setFocusable(true);
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==iv_back.getId()){
            Intent intent = new Intent(SignupPasswordActivity.this, FirstSignUpActivity.class);
            startActivity(intent);
            System.out.println("backButton clicked");
            finish();
        }
        else{
            if(!password.getText().toString().equals(password_check.getText().toString())){
                tv_password1.setTextColor(Color.parseColor("#ef4136"));
                tv_password2.setTextColor(Color.parseColor("#ef4136"));

                tv_password_check1.setTextColor(Color.parseColor("#ef4136"));
                tv_password_check2.setTextColor(Color.parseColor("#ef4136"));

                tv_isnot_password_equal.setVisibility(View.VISIBLE);
                return ;
            }
            pref.addInfo("password", password.getText().toString());
            Intent intent = new Intent(SignupPasswordActivity.this, SecondSignUpActivity.class);
            startActivity(intent);
            finish();
        }


    }
}