package mkworld29.mobile.com.cafemoa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import mkworld29.mobile.com.cafemoa.adapter.MainCafeAdapter;
import mkworld29.mobile.com.cafemoa.item.MainCafeItem;
import mkworld29.mobile.com.cafemoa.prefs.SignupPref;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SixthSignUpActivity extends AppCompatActivity {
    Retrofit retrofit;
    SignupPref pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth_sign_up);

        retrofit= RetrofitInstance.getInstance(getApplicationContext());
        RetrofitConnection.signup service = retrofit.create(RetrofitConnection.signup.class);

        pref=SignupPref.getInstance(getApplicationContext());


        final String email=pref.getInfo("email");
        final String name=pref.getInfo("name");
        final String birth=pref.getInfo("birth");
        final String phone_number=pref.getInfo("phone_number");
        final boolean gender;

        if(pref.getInfo("gender").equals("male")) gender=true;
        else gender=false;

        pref.removeAllInfo();

        final Call<ResponseBody> repos = service.repoContributors(
                "username",
                "password",
                email,
                name,
                birth,
                phone_number,
                gender
        );
        repos.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code()==200) {
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
