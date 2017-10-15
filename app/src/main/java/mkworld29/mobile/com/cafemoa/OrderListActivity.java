package mkworld29.mobile.com.cafemoa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import mkworld29.mobile.com.cafemoa.adapter.PaymentListViewAdapter;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderListActivity extends AppCompatActivity {

    private ListView listView;
    private PaymentListViewAdapter adapter;
    private Spinner spinner_order_list;

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        spinner_order_list = (Spinner) findViewById(R.id.spinner_order_list);

        /**
         여기서 spinner 처리하셍
         */
        spinner_order_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(

        ) {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adapter = new PaymentListViewAdapter();

        listView = (ListView)findViewById(R.id.payment_listview);


        // AddItem

        RetrofitConnection.recent_payments service2 = retrofit.create(RetrofitConnection.recent_payments.class);

        final Call<List<RetrofitConnection.Recent_payment>> repos2 = service2.repoContributors("recent_payment_list_by_order/"+0+"/");

        repos2.enqueue(new Callback<List<RetrofitConnection.Recent_payment>>() {
            @Override
            public void onResponse(Call<List<RetrofitConnection.Recent_payment>> call, Response<List<RetrofitConnection.Recent_payment>> response) {
                if(response.code()==200){
                    List<RetrofitConnection.Recent_payment> info=response.body();
                    for(int i=0; i<info.size(); i++){
                        RetrofitConnection.Recent_payment now_payment=info.get(i);
                        String title="["+now_payment.cafe_name+"] "+now_payment.menu_name;
                        //adapter.addItem(title, cafe_name, cafe_address, now_payment.price, now_payment.order_time); //이부분 고쳐줘
                    }
                    listView.setAdapter(adapter);
                }else{
                    Toast.makeText(getApplicationContext(), "Error : "+ response.code(), Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<List<RetrofitConnection.Recent_payment>> call, Throwable t) {
                Log.d("TAG",t.getLocalizedMessage());
            }
        });
    }
}
