package mkworld29.mobile.com.cafemoa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tsengvn.typekit.TypekitContextWrapper;

import mkworld29.mobile.com.cafemoa.prefs.SignupPref;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FirstSignUpActivity extends AppCompatActivity implements View.OnClickListener{
    SignupPref pref;
    private ImageView iv_next;
    private EditText email;
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_first_sign_up);

        pref=SignupPref.getInstance(getApplicationContext());

        iv_next = (ImageView) findViewById(R.id.iv_next);
        email = (EditText)findViewById(R.id.sign_email);
        iv_next.setOnClickListener(this);
        if(!pref.getString("email").equals("")){
            email.setText(pref.getString("email"));
        }

        email.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER) || (event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.FLAG_EDITOR_ACTION)) {
                    // Perform action on key press
                    onClick(email);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        pref.addInfo("email", email.getText().toString());
        if(!email.getText().toString().contains("@") ||  !email.getText().toString().contains(".")){
            Toast.makeText(getApplicationContext(), "이메일 형식이 옳지 않습니다.", Toast.LENGTH_SHORT).show();
            return ;
        }
        retrofit= RetrofitInstance.getInstance(getApplicationContext());
        RetrofitConnection.email_check service = retrofit.create(RetrofitConnection.email_check.class);

        final Call<ResponseBody> repos = service.repoContributors(email.getText().toString());
        repos.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code()==200) {
                    if(!pref.getString("password").equals("")) {
                        Intent intent = new Intent(FirstSignUpActivity.this, SecondSignUpActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent = new Intent(FirstSignUpActivity.this, SignupPasswordActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "이미 존재하는 이메일 입니다.", Toast.LENGTH_SHORT).show();
                    return ;
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG",t.getLocalizedMessage());
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}