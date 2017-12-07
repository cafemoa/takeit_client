package mkworld29.mobile.com.cafemoa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zoyi.channel.plugin.android.push.ChannelPushClient;

import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ABCla on 2017-11-28.
 */

public class PasswordChangeActivity extends AppCompatActivity {
    EditText et_now_password;
    EditText et_change_password;
    EditText et_change_passwordCheck;
    ImageView iv_back;
    ImageView iv_password_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_password);

        et_now_password         = (EditText)findViewById(R.id.et_now_password);
        et_change_password      = (EditText)findViewById(R.id.et_change_password);
        et_change_passwordCheck = (EditText)findViewById(R.id.et_change_passwordCheck);
        iv_password_confirm     = (ImageView)findViewById(R.id.iv_password_confirm);
        iv_back                 = (ImageView)findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_password_confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!et_change_password.getText().toString().equals(et_change_password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "바뀔 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                }
                Retrofit retrofit=RetrofitInstance.getInstance(getApplicationContext());
                RetrofitConnection.save_profile service = retrofit.create(RetrofitConnection.save_profile.class);
                final Call<ResponseBody> repos = service.repoContributors(et_now_password.getText().toString(),et_change_password.getText().toString());
                repos.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code()==200){
                            Toast.makeText(getApplicationContext(), "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else if(response.code()==201){
                            Toast.makeText(getApplicationContext(), "현재 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Error : "+ response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("TAG",t.getLocalizedMessage());
                    }
                });
            }
        });
    }

}
