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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import mkworld29.mobile.com.cafemoa.R;

public class SignUpAcitivity extends AppCompatActivity implements View.OnClickListener{

    Retrofit retrofit;
    EditText sign_id;
    EditText sign_pwd;
    EditText sign_pwd_again;
    EditText sign_name;
    EditText sign_email;
    EditText sign_tel;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        
        sign_id=(EditText) findViewById(R.id.sign_id);
        sign_pwd=(EditText) findViewById(R.id.sign_pwd);
        sign_pwd_again=(EditText) findViewById(R.id.sign_pwd_again);
        sign_name=(EditText) findViewById(R.id.sign_name);
        sign_email=(EditText) findViewById(R.id.sign_email);
        sign_tel=(EditText) findViewById(R.id.sign_tel);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        retrofit=RetrofitInstance.getInstance(getApplicationContext());
        btn_submit.setOnClickListener(this);

    }

    public void onClick(View view) {
        String id=sign_id.getText().toString();
        String pw=sign_pwd.getText().toString();
        String pw_again=sign_pwd_again.getText().toString();
        String name=sign_name.getText().toString();
        String email=sign_email.getText().toString();
        String tel=sign_tel.getText().toString();

        if(!pw.equals(pw_again)){
            Toast.makeText(getApplicationContext(), "비밀번호를 확인해주세요", Toast.LENGTH_LONG).show();
            sign_pwd.requestFocus();
        }
        RetrofitConnection.signup service = retrofit.create(RetrofitConnection.signup.class);

        final Call<ResponseBody> repos = service.repoContributors(id,pw,name,tel,"980818",true);
        repos.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {
                    Toast.makeText(getApplicationContext(), "가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "가입시 에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
    }
}
