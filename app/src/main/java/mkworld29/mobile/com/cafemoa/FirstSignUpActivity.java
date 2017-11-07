package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private Button btn_next;
    private EditText email;
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
                    if(!pref.getString("password").equals("")){
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
}
