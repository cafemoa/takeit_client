package mkworld29.mobile.com.cafemoa;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.adapter.AlertAdapter;
import mkworld29.mobile.com.cafemoa.adapter.MainCafeAdapter;
import mkworld29.mobile.com.cafemoa.item.AlertItem;
import mkworld29.mobile.com.cafemoa.item.MainCafeItem;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ABCla on 2017-10-08.
 */

public class NoticeDialog extends Dialog {
    ArrayList<AlertItem> data;
    AlertAdapter alertAdapter;
    ListView listView;
    Button back;
    Retrofit retrofit;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.notice_dialog);

        back=(Button)findViewById(R.id.alert_back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        listView=(ListView)findViewById(R.id.listview);

       data= new ArrayList<>();

        retrofit= RetrofitInstance.getInstance(getContext());

        RetrofitConnection.get_alerts service = retrofit.create(RetrofitConnection.get_alerts.class);
        final Call<List<RetrofitConnection.Alert>> repos = service.repoContributors();
        repos.enqueue(new Callback<List<RetrofitConnection.Alert>>() {
            @Override
            public void onResponse(Call<List<RetrofitConnection.Alert>> call, Response<List<RetrofitConnection.Alert>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++) {
                        RetrofitConnection.Alert item=response.body().get(i);
                        data.add(new AlertItem(item.cafe_name, item.content,item.is_event));
                    }
                    alertAdapter=new AlertAdapter(getContext(),R.layout.item_alert,data);
                    listView.setAdapter(alertAdapter);
                }
                else{
                    Toast.makeText(context, "Error : "+ response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitConnection.Alert>> call, Throwable t) {
                Log.d("TAG",t.getLocalizedMessage());
            }
        });



        //WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        //lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        //lpWindow.dimAmount = 0.5f;
        //getWindow().setAttributes(lpWindow);

        listView.setHeaderDividersEnabled(false);
        listView.setFooterDividersEnabled(false);

        getWindow().setBackgroundDrawableResource(R.color.translucent_white);

        //getWindow().setLayout(dpToPx(getContext(),380), dpToPx(getContext(),600));

    }
    public NoticeDialog(Context context) {
        super(context);
    }
    public int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

}
