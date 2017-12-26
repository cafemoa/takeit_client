package mkworld29.mobile.com.cafemoa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tsengvn.typekit.TypekitContextWrapper;

import mkworld29.mobile.com.cafemoa.prefs.SignupPref;

public class SecondSignUpActivity extends AppCompatActivity implements View.OnClickListener{
    SignupPref pref;
    private ImageView iv_next;
    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second_sign_up);

        pref=SignupPref.getInstance(getApplicationContext());

        iv_next = (ImageView) findViewById(R.id.iv_next);
        name=(EditText)findViewById(R.id.sign_name);
        iv_next.setOnClickListener(this);

        if(!pref.getString("name").equals("")){
            name.setText(pref.getString("name"));
        }


        name.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event != null && (keyCode == KeyEvent.KEYCODE_ENTER)) || (keyCode == EditorInfo.IME_ACTION_DONE)) {
                    // Perform action on key press
                    onClick(name);
                    return true;
                }
                return false;
            }
        });
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
