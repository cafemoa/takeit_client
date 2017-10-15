package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mkworld29.mobile.com.cafemoa.prefs.SignupPref;

public class ThirdSignUpActivity extends AppCompatActivity implements View.OnClickListener{
    SignupPref pref;
    private Button btn_next;
    private EditText birth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Intent intent = new Intent(ThirdSignUpActivity.this, FourthSignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
