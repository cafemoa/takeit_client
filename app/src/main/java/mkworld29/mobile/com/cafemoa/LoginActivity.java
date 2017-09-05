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
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.iid.FirebaseInstanceId;
import com.tsengvn.typekit.TypekitContextWrapper;
import com.zoyi.channel.plugin.android.ChannelException;
import com.zoyi.channel.plugin.android.ChannelPlugin;
import com.zoyi.channel.plugin.android.CheckIn;
import com.zoyi.channel.plugin.android.OnCheckInListener;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import mkworld29.mobile.com.cafemoa.retrofit.SharedPreference;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private CallbackManager callbackManager;

    Retrofit retrofit;
    EditText id;
    EditText pw;
    Button login;
    TextView tv_sign_up;
    SharedPreference sp;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseInstanceId.getInstance().getToken();

        String token = FirebaseInstanceId.getInstance().getToken();

        FacebookSdk.setAutoLogAppEventsEnabled(true);
        FacebookSdk.setApplicationId("1224243417701535");
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_login);

        tv_sign_up = (TextView)findViewById(R.id.tv_sign_up);
        tv_sign_up.setOnClickListener(this);

        id=(EditText)findViewById(R.id.login_id);
        pw=(EditText)findViewById(R.id.login_pwd);
        login=(Button)findViewById(R.id.btn_login);
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
                                    Intent i=new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                                @Override
                                public void onFailed(ChannelException exception) {
                                    Toast.makeText(LoginActivity.this,"Check In Failed", Toast.LENGTH_SHORT).show();
                                }
                            });

                            send_fcmtoken();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                        }
                        pd.dismiss();
                    }

                    @Override
                    public void onFailure(Call<RetrofitConnection.Token> call, Throwable t) {
                        Log.d("TAG", t.getLocalizedMessage());
                    }
                });
            }
        });

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v("result",object.toString());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();


            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("LoginErr",error.toString());
            }
        });

        retrofit= RetrofitInstance.getInstance(getApplicationContext());
        sp=SharedPreference.getInstance(getApplicationContext());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {

        Intent intent = null;

        if(view.getId()==tv_sign_up.getId())
        {
            intent = new Intent(this, AccessTermsActivity.class);
        }
        startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
    public void send_fcmtoken(){
        RetrofitConnection.fcm_register service = retrofit.create(RetrofitConnection.fcm_register.class);
        final String deviceID = android.provider.Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        final String fcm_token=sp.get("FCM_TOKEN");
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
