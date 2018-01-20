package mkworld29.mobile.com.cafemoa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tsengvn.typekit.TypekitContextWrapper;

import mkworld29.mobile.com.cafemoa.prefs.SignupPref;

public class ThirdSignUpActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    SignupPref pref;
    private ImageView iv_next;
    private ImageView iv_back;
    private EditText edt_number_1, edt_number_2, edt_number_3, edt_number_4,
            edt_number_5, edt_number_6;
    private int now_input_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_third_sign_up);

        pref = SignupPref.getInstance(getApplicationContext());
        iv_next = (ImageView) findViewById(R.id.iv_next);
        iv_next.setOnClickListener(this);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);

        edt_number_1 = (EditText) findViewById(R.id.edt_number_1);
        edt_number_2 = (EditText) findViewById(R.id.edt_number_2);
        edt_number_3 = (EditText) findViewById(R.id.edt_number_3);
        edt_number_4 = (EditText) findViewById(R.id.edt_number_4);
        edt_number_5 = (EditText) findViewById(R.id.edt_number_5);
        edt_number_6 = (EditText) findViewById(R.id.edt_number_6);

        edt_number_1.addTextChangedListener(this);
        edt_number_2.addTextChangedListener(this);
        edt_number_3.addTextChangedListener(this);
        edt_number_4.addTextChangedListener(this);
        edt_number_5.addTextChangedListener(this);
        edt_number_6.addTextChangedListener(this);

        if (!pref.getString("birth").equals("")) {
            String birth_num = pref.getString("birth");
            edt_number_1.setText(birth_num.charAt(0));
            edt_number_2.setText(birth_num.charAt(1));
            edt_number_3.setText(birth_num.charAt(2));
            edt_number_4.setText(birth_num.charAt(3));
            edt_number_5.setText(birth_num.charAt(4));
            edt_number_6.setText(birth_num.charAt(5));
        }

        edt_number_1.setFocusable(true);
    }

    @Override
    public void onClick (View view){
        if(view.getId()==iv_back.getId()){
            Intent intent = new Intent(ThirdSignUpActivity.this, SecondSignUpActivity.class);
            System.out.println("backButton clicked");
            startActivity(intent);
            finish();
        }
        else{
            if (edt_number_1.getText().toString().length() == 0 ||
                    edt_number_2.getText().toString().length() == 0 ||
                    edt_number_3.getText().toString().length() == 0 ||
                    edt_number_4.getText().toString().length() == 0 ||
                    edt_number_5.getText().toString().length() == 0 ||
                    edt_number_6.getText().toString().length() == 0) {
                Toast.makeText(getApplicationContext(), "생년월일 형식이 옳지 않습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            if ((edt_number_3.getText().equals("1") && Integer.parseInt(edt_number_4.getText().toString()) >= 3) ||
                    edt_number_5.getText().equals("1") && Integer.parseInt(edt_number_6.getText().toString()) >= 3) {
                Toast.makeText(getApplicationContext(), "생년월일을 형식이 옳지 않습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            String birth = edt_number_1.getText().toString() +
                    edt_number_2.getText().toString() +
                    edt_number_3.getText().toString() +
                    edt_number_4.getText().toString() +
                    edt_number_5.getText().toString() +
                    edt_number_6.getText().toString();

            pref.addInfo("birth", birth);
            Intent intent = new Intent(ThirdSignUpActivity.this, FourthSignUpActivity.class);
            startActivity(intent);
            finish();
        }

    }


    @Override
    protected void attachBaseContext (Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (edt_number_1.getText().toString().length() == 1) {
            edt_number_2.requestFocus();
            now_input_num = 2;
        }
        if (edt_number_2.getText().toString().length() == 1) {
            edt_number_3.requestFocus();
            now_input_num = 3;
        }
        if (edt_number_3.getText().toString().length() == 1) {
            edt_number_4.requestFocus();
            now_input_num = 4;
        }
        if (edt_number_4.getText().toString().length() == 1) {
            edt_number_5.requestFocus();
            now_input_num = 5;
        }
        if (edt_number_5.getText().toString().length() == 1) {
            edt_number_6.requestFocus();
            now_input_num = 6;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}