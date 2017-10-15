package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mkworld29.mobile.com.cafemoa.prefs.SignupPref;

public class FirstSignUpActivity extends AppCompatActivity implements View.OnClickListener{
    SignupPref pref;
    private Button btn_next;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_sign_up);

        pref=SignupPref.getInstance(getApplicationContext());

        btn_next = (Button) findViewById(R.id.btn_next);
        email = (EditText)findViewById(R.id.sign_email);
        btn_next.setOnClickListener(this);
        if(!pref.getString("email").equals("")){
            email.setText(pref.getString("email"));
        }
    }

    @Override
    public void onClick(View view) {
        pref.addInfo("email", email.getText().toString());
        Intent intent = new Intent(FirstSignUpActivity.this, SecondSignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
