package mkworld29.mobile.com.cafemoa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.tsengvn.typekit.TypekitContextWrapper;
import com.zoyi.channel.plugin.android.ChannelException;
import com.zoyi.channel.plugin.android.ChannelPlugin;
import com.zoyi.channel.plugin.android.CheckIn;
import com.zoyi.channel.plugin.android.OnCheckInListener;

import org.json.JSONException;

import java.util.List;

import mkworld29.mobile.com.cafemoa.adapter.MainCafeAdapter;
import mkworld29.mobile.com.cafemoa.item.MainCafeItem;
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

public class SixthSignUpActivity extends AppCompatActivity {
    Retrofit retrofit;
    SignupPref pref;
    ProgressDialog pd;
    SharedPreference sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sixth_sign_up);

        sp=SharedPreference.getInstance();
        retrofit= RetrofitInstance.getInstance(getApplicationContext());


        pref=SignupPref.getInstance(getApplicationContext());

        final String email         =  pref.getInfo("email");
        final String name          =  pref.getInfo("name");
        final String birth         =  pref.getInfo("birth");
        final String password      =  pref.getInfo("password");
        final String phone_number  =  pref.getInfo("phone_number");
        final String access_token  =  pref.getInfo("access_token");

        final boolean gender;

        if(pref.getInfo("gender").equals("male")) gender=true;
        else gender=false;

        pref.removeAllInfo();


        if(access_token.equals("")){
            RetrofitConnection.signup service = retrofit.create(RetrofitConnection.signup.class);
            final Call<ResponseBody> repos = service.repoContributors(
                    email,
                    password,
                    name,
                    phone_number,
                    birth,
                    gender
            );
            repos.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.code()==201) {
                        Toast.makeText(getApplicationContext(), "성공적으로 가입을 완료하였습니다.",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SixthSignUpActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
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
        else{
            RetrofitConnection.social_signup service = retrofit.create(RetrofitConnection.social_signup.class);
            final Call<ResponseBody> repos = service.repoContributors(
                    name,
                    phone_number,
                    birth,
                    gender,
                    access_token
            );
            repos.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.code()==201) {
                        Toast.makeText(getApplicationContext(), "성공적으로 가입을 완료하였습니다.",Toast.LENGTH_SHORT).show();
                        pd = ProgressDialog.show(SixthSignUpActivity.this, "로그인중", "로그인중 입니다.");
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
                                        }
                                        @Override
                                        public void onFailed(ChannelException exception) {
                                            Toast.makeText(SixthSignUpActivity.this,"Check In Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    });
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

                Intent i=new Intent(SixthSignUpActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
