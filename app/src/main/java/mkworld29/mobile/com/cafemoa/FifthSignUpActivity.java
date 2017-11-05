package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mkworld29.mobile.com.cafemoa.prefs.SignupPref;

public class FifthSignUpActivity extends AppCompatActivity implements View.OnClickListener{
    SignupPref pref;
    private Button btn_next;
    private Button btn_man, btn_woman;
    private boolean is_man, is_woman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_sign_up);

        pref = SignupPref.getInstance(getApplicationContext());
        is_man = is_woman = false;

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        btn_man = (Button) findViewById(R.id.btn_man);
        btn_man.setOnClickListener(this);

        btn_woman = (Button) findViewById(R.id.btn_woman);
        btn_woman.setOnClickListener(this);

        btn_man.setBackgroundColor(0xFFA9E6F2);
        is_man=true;

        if(!pref.getString("gender").equals("")){
            String gender=pref.getString("gender");
            if(gender.equals("male")){
                is_man=true;
                is_woman=false;
                btn_man.setBackgroundColor(0xFFA9E6F2);
                btn_woman.setBackgroundColor(0xFFE5E5E5);
            }
            else {
                is_man=false;
                is_woman=true;
                btn_woman.setBackgroundColor(0xFFA9E6F2);
                btn_man.setBackgroundColor(0xFFE5E5E5);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btn_next.getId()) {
            if(is_man) pref.addInfo("gender", "male");
            else pref.addInfo("gender", "female");
            Intent intent = new Intent(FifthSignUpActivity.this, SixthSignUpActivity.class);
            startActivity(intent);
            finish();
        }
        else if(view.getId() == btn_man.getId())
        {
            if(is_man) {    // 체크되어있으면
                is_man = !is_man;   // 체크 해제 후
                btn_man.setBackgroundColor(0xFFE5E5E5);   // 회색으로 바꿈
            }
            else{
                is_man = !is_man;   // 체크 선택 후
                btn_man.setBackgroundColor(0xFFA9E6F2);   // 표시하는 색으로 바꾸고
                if(is_woman)        // 여자 선택되어있으면
                {
                    is_woman = !is_woman;   // 체크 해제 후
                    btn_woman.setBackgroundColor(0xFFE5E5E5); // 회색으로 바꿈
                }
            }
        }
        else if(view.getId() == btn_woman.getId())
        {
            if(is_woman) {
                is_woman = !is_woman;
                btn_woman.setBackgroundColor(0xFFE5E5);
            }
            else{
                is_woman = !is_woman;
                btn_woman.setBackgroundColor(0xFFFFDC55);
                if(is_man)
                {
                    is_man = !is_man;
                    btn_man.setBackgroundColor(0xFFE5E5E5);
                }
            }

        }
    }
}

