package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mkworld29.mobile.com.cafemoa.prefs.SignupPref;

public class SecondSignUpActivity extends AppCompatActivity implements View.OnClickListener{
    SignupPref pref;
    private Button btn_next;
    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_sign_up);

        pref=SignupPref.getInstance(getApplicationContext());

        btn_next = (Button) findViewById(R.id.btn_next);
        name=(EditText)findViewById(R.id.sign_name);
        btn_next.setOnClickListener(this);

        if(!pref.getString("name").equals("")){
            name.setText(pref.getString("name"));
        }
    }

    @Override
    public void onClick(View view) {
        pref.addInfo("name", name.getText().toString());
        if(name.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "이름을 입력 해 주세요", Toast.LENGTH_SHORT).show();
            return ;
        }
        Intent intent = new Intent(SecondSignUpActivity.this, ThirdSignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
