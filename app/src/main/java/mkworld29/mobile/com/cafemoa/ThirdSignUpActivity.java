package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mkworld29.mobile.com.cafemoa.prefs.SignupPref;

public class ThirdSignUpActivity extends AppCompatActivity implements View.OnClickListener{
    SignupPref pref;
    private Button btn_next;
    private EditText birth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_third_sign_up);

        pref=SignupPref.getInstance(getApplicationContext());
        birth=(EditText)findViewById(R.id.sign_birth);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        if(!pref.getString("birth").equals("")){
            birth.setText(pref.getString("birth"));
        }
    }

    @Override
    public void onClick(View view) {
        pref.addInfo("birth",birth.getText().toString());
        String birth_str=birth.getText().toString();
        if(birth_str.equals("")){
            Toast.makeText(getApplicationContext(), "생년월일을 입력해주세요", Toast.LENGTH_SHORT).show();
            return ;
        }
        if(birth_str.length()!=6){
            Toast.makeText(getApplicationContext(), "생년월일을 6자리로 입력해주세요", Toast.LENGTH_SHORT).show();
            return ;
        }
        if((birth_str.charAt(2)=='1' && birth_str.charAt(3)>='3')||
                (birth_str.charAt(4)>='3' && birth_str.charAt(5)>='2')) {
            Toast.makeText(getApplicationContext(), "생년월일을 형식이 옳지 않습니다.", Toast.LENGTH_SHORT).show();
            return ;
        }
        Intent intent = new Intent(ThirdSignUpActivity.this, FourthSignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
