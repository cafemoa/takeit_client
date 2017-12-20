package mkworld29.mobile.com.cafemoa;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import mkworld29.mobile.com.cafemoa.adapter.MainCafeAdapter;
import mkworld29.mobile.com.cafemoa.item.MainCafeItem;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import mkworld29.mobile.com.cafemoa.retrofit.SharedPreference;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SettingActivity extends AppCompatActivity {
    private EditText et_email;
    private EditText et_password;
    private Button bt_delete_user;
    private Button bt_logout;
    private ImageView iv_back;
    private Button bt_save;
    private TextView tv_name;
    private Retrofit retrofit;
    private Switch fcm_deny;
    private Button bt_password_change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setting);

        et_email           = (EditText)findViewById(R.id.et_profile_email);
        et_password        = (EditText)findViewById(R.id.tv_profile_password);
        bt_delete_user     = (Button)findViewById(R.id.bt_delete_user);
        bt_logout          = (Button)findViewById(R.id.bt_logout);
        tv_name            = (TextView) findViewById(R.id.tv_profile_name);
        bt_save            = (Button)findViewById(R.id.bt_save);
        iv_back            = (ImageView)findViewById(R.id.iv_back);
        fcm_deny           = (Switch)findViewById(R.id.switch_push);
        bt_password_change = (Button)findViewById(R.id.bt_password_change);


        fcm_deny.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean b) {
                RetrofitConnection.fcm_setActive service = retrofit.create(RetrofitConnection.fcm_setActive.class);
                Call<ResponseBody> repos = service.repoContributors(b);
                repos.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code()==200){

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

        bt_password_change.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i=new Intent(SettingActivity.this,PasswordChangeActivity.class);
                startActivity(i);
            }
        });

        retrofit = RetrofitInstance.getInstance(this);

        RetrofitConnection.get_profile service = retrofit.create(RetrofitConnection.get_profile.class);
        final Call<RetrofitConnection.Profile> repos = service.repoContributors();
        repos.enqueue(new Callback<RetrofitConnection.Profile>() {
            @Override
            public void onResponse(Call<RetrofitConnection.Profile> call, Response<RetrofitConnection.Profile> response) {
                if(response.code()==200){
                    et_email.setText(response.body().username);
                    tv_name.setText(response.body().name);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error : "+ response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RetrofitConnection.Profile> call, Throwable t) {
                Log.d("TAG",t.getLocalizedMessage());
            }
        });

        bt_logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreference.getInstance().remove("Authorization");
                Intent i=new Intent(SettingActivity.this, ReadyActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

        bt_delete_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
                alert.setTitle("정말로 탈퇴하시겠습니까?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RetrofitConnection.delete_user service = retrofit.create(RetrofitConnection.delete_user.class);
                        final Call<ResponseBody> repos = service.repoContributors();
                        repos.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if(response.code()==200){
                                    SharedPreference.getInstance().remove("Authorization");
                                    Intent i=new Intent(SettingActivity.this, ReadyActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(et_password.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    return ;
                }
                /*
                RetrofitConnection.save_profile service = retrofit.create(RetrofitConnection.save_profile.class);
                final Call<ResponseBody> repos = service.repoContributors(et_password.getText().toString());
                repos.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code()==200){
                            Toast.makeText(getApplicationContext(), "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
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
                */
            }
        });

    }
}

