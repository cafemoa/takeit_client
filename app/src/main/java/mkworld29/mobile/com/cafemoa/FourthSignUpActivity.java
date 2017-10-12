package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mkworld29.mobile.com.cafemoa.prefs.SignupPref;

public class FourthSignUpActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher{

    SignupPref pref;
    private Button btn_next;


    private EditText edt_number_1, edt_number_2, edt_number_3, edt_number_4,
            edt_number_5, edt_number_6, edt_number_7, edt_number_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_sign_up);

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        pref=SignupPref.getInstance(getApplicationContext());

        edt_number_1 = (EditText) findViewById(R.id.edt_number_1);
        edt_number_2 = (EditText) findViewById(R.id.edt_number_2);
        edt_number_3 = (EditText) findViewById(R.id.edt_number_3);
        edt_number_4 = (EditText) findViewById(R.id.edt_number_4);
        edt_number_5 = (EditText) findViewById(R.id.edt_number_5);
        edt_number_6 = (EditText) findViewById(R.id.edt_number_6);
        edt_number_7 = (EditText) findViewById(R.id.edt_number_7);
        edt_number_8 = (EditText) findViewById(R.id.edt_number_8);

        edt_number_1.addTextChangedListener(this);
        edt_number_2.addTextChangedListener(this);
        edt_number_3.addTextChangedListener(this);
        edt_number_4.addTextChangedListener(this);
        edt_number_5.addTextChangedListener(this);
        edt_number_6.addTextChangedListener(this);
        edt_number_7.addTextChangedListener(this);
        edt_number_8.addTextChangedListener(this);

    }

    @Override
    public void onClick(View view) {
        String phone_number=edt_number_1.getText().toString()+
                edt_number_2.getText().toString()+
                edt_number_3.getText().toString()+
                edt_number_4.getText().toString()+
                edt_number_5.getText().toString()+
                edt_number_6.getText().toString()+
                edt_number_7.getText().toString()+
                edt_number_8.getText().toString();

        pref.addInfo("phone_number",phone_number);
        Intent intent = new Intent(FourthSignUpActivity.this, FifthSignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if(edt_number_1.getText().toString().length() == 1)
            edt_number_2.requestFocus();
        if(edt_number_2.getText().toString().length() == 1)
            edt_number_3.requestFocus();
        if(edt_number_3.getText().toString().length() == 1)
            edt_number_4.requestFocus();
        if(edt_number_4.getText().toString().length() == 1)
            edt_number_5.requestFocus();
        if(edt_number_5.getText().toString().length() == 1)
            edt_number_6.requestFocus();
        if(edt_number_6.getText().toString().length() == 1)
            edt_number_7.requestFocus();
        if(edt_number_7.getText().toString().length() == 1)
            edt_number_8.requestFocus();
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if(edt_number_1.getText().toString().length() == 1)
            edt_number_2.requestFocus();
        if(edt_number_2.getText().toString().length() == 1)
            edt_number_3.requestFocus();
        if(edt_number_3.getText().toString().length() == 1)
            edt_number_4.requestFocus();
        if(edt_number_4.getText().toString().length() == 1)
            edt_number_5.requestFocus();
        if(edt_number_5.getText().toString().length() == 1)
            edt_number_6.requestFocus();
        if(edt_number_6.getText().toString().length() == 1)
            edt_number_7.requestFocus();
        if(edt_number_7.getText().toString().length() == 1)
            edt_number_8.requestFocus();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
