package mkworld29.mobile.com.cafemoa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.felipecsl.gifimageview.library.GifImageView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.tsengvn.typekit.TypekitContextWrapper;
import com.zoyi.channel.plugin.android.ChannelException;
import com.zoyi.channel.plugin.android.ChannelPlugin;
import com.zoyi.channel.plugin.android.CheckIn;
import com.zoyi.channel.plugin.android.OnCheckInListener;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import mkworld29.mobile.com.cafemoa.prefs.BasketPref;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import mkworld29.mobile.com.cafemoa.retrofit.SharedPreference;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    Retrofit retrofit;
    EditText id;
    EditText pw;
    Button login;
    CheckBox auto_login;
    ImageView img_arrow;
    ProgressDialog pd;
    SharedPreference sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        FirebaseInstanceId.getInstance().getToken();

        String token = FirebaseInstanceId.getInstance().getToken();

        final String fcm_token= BasketPref.getInstance(getApplicationContext()).getString("FCM_TOKEN");

        id=(EditText)findViewById(R.id.login_id);
        pw=(EditText)findViewById(R.id.login_pwd);
        login=(Button)findViewById(R.id.btn_login);
        img_arrow = (ImageView) findViewById(R.id.imageView2);


        auto_login=(CheckBox) findViewById(R.id.chk_auto_login);
        if(SharedPreference.getInstance(getApplicationContext()).get("AUTO").equals("Y"))
            auto_login.setChecked(true);

        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreference.getInstance(getApplicationContext()).put("AUTO", b? "Y" : "N");
            }
        });

        img_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = ProgressDialog.show(LoginActivity.this, "로그인중", "로그인중 입니다.");
                RetrofitConnection.login service = retrofit.create(RetrofitConnection.login.class);
                String id_str=id.getText().toString();
                String pw_str=pw.getText().toString();
                final Call<RetrofitConnection.Token> repos = service.repoContributors(id_str,pw_str);
                repos.enqueue(new Callback<RetrofitConnection.Token>() {
                    @Override
                    public void onResponse(Call<RetrofitConnection.Token> call, Response<RetrofitConnection.Token> response) {
                        if (response.code() == 200){
                            sp.put("Authorization", response.body().token);

                            CheckIn checkIn = CheckIn.create();

                            ChannelPlugin.checkIn(checkIn, new OnCheckInListener() {
                                @Override
                                public void onSuccessed() {
                                    send_fcmtoken();
//                                    Intent i=new Intent(LoginActivity.this, MainActivity.class);
//                                    startActivity(i);
//                                    finish();
                                }
                                @Override
                                public void onFailed(ChannelException exception) {
                                    Toast.makeText(LoginActivity.this,"Check In Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                        }
                        pd.dismiss();
                    }

                    @Override
                    public void onFailure(Call<RetrofitConnection.Token> call, Throwable t) {
                        Log.d("TAG", t.getLocalizedMessage());
                        pd.dismiss();
                    }
                });
            }
        });

        retrofit= RetrofitInstance.getInstance(getApplicationContext());
        sp=SharedPreference.getInstance();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
    public void send_fcmtoken(){
        RetrofitConnection.fcm_register service = retrofit.create(RetrofitConnection.fcm_register.class);
        final String deviceID = android.provider.Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        final String fcm_token= BasketPref.getInstance(getApplicationContext()).getString("FCM_TOKEN");
        final Call<ResponseBody> repos=service.repoContributors(deviceID,fcm_token,true);
        repos.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Intent i=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
    }
}
