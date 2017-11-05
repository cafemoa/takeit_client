package mkworld29.mobile.com.cafemoa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;
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
import com.felipecsl.gifimageview.library.GifImageView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.tsengvn.typekit.TypekitContextWrapper;
import com.zoyi.channel.plugin.android.ChannelException;
import com.zoyi.channel.plugin.android.ChannelPlugin;
import com.zoyi.channel.plugin.android.CheckIn;
import com.zoyi.channel.plugin.android.OnCheckInListener;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import mkworld29.mobile.com.cafemoa.prefs.BasketPref;
import mkworld29.mobile.com.cafemoa.prefs.SignupPref;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import mkworld29.mobile.com.cafemoa.retrofit.SharedPreference;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReadyActivity extends AppCompatActivity implements View.OnClickListener{
    private CallbackManager callbackManager;
    SignupPref pref;
    GifImageView gifImageView;
    TextView tv_sign_up;
    TextView tv_login;
    Retrofit retrofit;
    ProgressDialog pd;
    SharedPreference sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);

        pref=SignupPref.getInstance(getApplicationContext());
        retrofit=RetrofitInstance.getInstance(getApplicationContext());
        sp=SharedPreference.getInstance();

        FacebookSdk.setAutoLogAppEventsEnabled(true);
        FacebookSdk.setApplicationId("1224243417701535");
        FacebookSdk.sdkInitialize(this.getApplicationContext());

        BasketPref.getInstance(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(final JSONObject object, GraphResponse response) {
                        String access_token=loginResult.getAccessToken().getToken();
                        //Log.d("TAG", access_token);
                        pd = ProgressDialog.show(ReadyActivity.this, "로그인중", "로그인중 입니다.");
                        RetrofitConnection.social_auth service = retrofit.create(RetrofitConnection.social_auth.class);
                        final Call<RetrofitConnection.Token> repos = service.repoContributors(access_token);
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
                                            Toast.makeText(ReadyActivity.this,"Check In Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                                else if(response.code()==201){
                                    try {
                                        String uid=object.getString("id");
                                        pref.addInfo("username", uid);
                                    } catch (JSONException e) {}

                                    try {
                                        String uid=object.getString("id");
                                        pref.addInfo("password", uid);
                                    } catch (JSONException e) {}

                                    try {
                                        String name=object.getString("name");
                                        pref.addInfo("name", name);
                                    } catch (JSONException e) {}

                                    try {
                                        String gender=object.getString("gender");
                                        pref.addInfo("gender", gender);
                                    } catch (JSONException e) {}

                                    try {
                                        String email=object.getString("email");
                                        pref.addInfo("email", email);
                                    } catch (JSONException e) {}

                                    Intent i=new Intent(ReadyActivity.this, SecondSignUpActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                                }
                                pd.dismiss();
                            }

                            @Override
                            public void onFailure(Call<RetrofitConnection.Token> call, Throwable t) {
                                Log.d("TAG", t.getLocalizedMessage());
                            }
                        });


                        //Intent intent = new Intent(ReadyActivity.this, MainActivity.class);
                        //startActivity(intent);
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

        gifImageView = (GifImageView) findViewById(R.id.gifImageView);

        try{
            InputStream inputStream = getAssets().open("gifImage.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }
        catch(IOException ex)
        {
            Toast.makeText(this, "IO Exception", Toast.LENGTH_SHORT).show();
        }

        tv_sign_up = (TextView)findViewById(R.id.tv_sign_up);
        tv_sign_up.setOnClickListener(this);

        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_login.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        if(view.getId() == tv_sign_up.getId())
        {
            SignupPref.getInstance(getApplicationContext()).removeAllInfo();
            intent = new Intent(this, AccessTermsActivity.class);

        }
        else if(view.getId() == tv_login.getId())
        {
            intent = new Intent(this, LoginActivity.class);
        }
        if(intent !=null) startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    public void send_fcmtoken(){
        RetrofitConnection.fcm_register service = retrofit.create(RetrofitConnection.fcm_register.class);
        final String deviceID = android.provider.Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        final String fcm_token= BasketPref.getInstance(getApplicationContext()).getString("FCM_TOKEN");
        Log.d("TAG", deviceID+","+fcm_token);
        final Call<ResponseBody> repos=service.repoContributors(deviceID,fcm_token,true);
        repos.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Intent i=new Intent(ReadyActivity.this, MainActivity.class);
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
