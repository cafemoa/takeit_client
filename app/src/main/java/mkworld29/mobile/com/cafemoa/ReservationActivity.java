package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.entity.CoffeeOption;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import mkworld29.mobile.com.cafemoa.fragment.CafeFragment;
import mkworld29.mobile.com.cafemoa.entity.Cafe;
import mkworld29.mobile.com.cafemoa.entity.Menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ReservationActivity extends AppCompatActivity implements View.OnClickListener {

    public static int CAFE_SIZE = 7;

    public static List<Cafe> cafes;

    public static String[] pageTitle;

    private Button btn_click;
    private Button btn_click2;
    private Button btn_reservation;
    private HorizontalScrollView sv;
    private HorizontalScrollView sv2;
    private ViewPager vp;

    Retrofit retrofit;

    private static boolean is_cafe_gone = false;
    private static boolean is_near_gone = false;

    private PagerSlidingTabStrip tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        retrofit= RetrofitInstance.getInstance(getApplicationContext());
        RetrofitConnection.reservation service = retrofit.create(RetrofitConnection.reservation.class);

        final Call<List<RetrofitConnection.Cafe>> repos = service.repoContributors();

        repos.enqueue(new Callback<List<RetrofitConnection.Cafe>>() {
            @Override
            public void onResponse(Call<List<RetrofitConnection.Cafe>> call, Response<List<RetrofitConnection.Cafe>> response) {
                if(response.code()==200){
                    ReservationActivity.CAFE_SIZE=response.body().size();
                    ReservationActivity.pageTitle = new String[ReservationActivity.CAFE_SIZE];

                    cafes = new ArrayList<>();

                    Cafe[] cafe = new Cafe[CAFE_SIZE];
                    for(int i=0; i<CAFE_SIZE; i++){
                        RetrofitConnection.Cafe now_cafe=response.body().get(i);
                        String name=now_cafe.name;
                        int menu_num=now_cafe.beverages.size();
                        int cafe_pk=now_cafe.pk;

                        List<Menu> menus = new ArrayList<>();
                        Menu[] menu = new Menu[menu_num];
                        for(int j=0; j<menu_num; j++){
                            RetrofitConnection.Beverage now_menu=now_cafe.beverages.get(j);
                            menu[j] = new Menu(now_menu.name, now_menu.price, RetrofitInstance.getApiUrl()+now_menu.image, 1000, now_menu.pk,cafe_pk);
                            menus.add(j, menu[j]);
                        }
                        cafe[i] = new Cafe(name, menu_num, menus, cafe_pk);
                    }

                    for (int i = 0; i < CAFE_SIZE; i++) {
                        ReservationActivity.pageTitle[i] = cafe[i].getCafe_name();
                        cafes.add(i, cafe[i]);
                    }
                    vp = (ViewPager) findViewById(R.id.viewPager);
                    vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
                    vp.setOffscreenPageLimit(5);
                    vp.setCurrentItem(0);

                    tabs = (PagerSlidingTabStrip) findViewById(R.id.pagerSlidingTab);
                    tabs.setViewPager(vp);




                }else{
                    Toast.makeText(getApplicationContext(), "Error : "+ response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitConnection.Cafe>> call, Throwable t) {
                Log.d("TAG",t.getLocalizedMessage());
            }
        });

        btn_click = (Button) findViewById(R.id.btn_click);
        btn_click.setOnClickListener(this);

        btn_click2 = (Button) findViewById(R.id.btn_near_cafe);
        btn_click2.setOnClickListener(this);

        btn_reservation = (Button) findViewById(R.id.btn_reservation);
        btn_reservation.setOnClickListener(this);

        sv = (HorizontalScrollView) findViewById(R.id.sv);
        sv2 = (HorizontalScrollView) findViewById(R.id.sv2);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == btn_click.getId()) {
            if (is_cafe_gone) {
                btn_click.setBackground(getResources().getDrawable(R.drawable.arrow_up));
                sv.setVisibility(View.VISIBLE);
                is_cafe_gone = !is_cafe_gone;

                Intent intent = new Intent(this, CoffeeOptionActivity.class);
                startActivity(intent);
            } else {
                btn_click.setBackground(getResources().getDrawable(R.drawable.arrow_down));
                sv.setVisibility(View.GONE);
                is_cafe_gone = !is_cafe_gone;
            }
        }
        else if(v.getId() == btn_click2.getId())
        {
            if(is_near_gone){
                btn_click2.setBackground(getResources().getDrawable(R.drawable.arrow_up));
                sv2.setVisibility(View.VISIBLE);
                is_near_gone = !is_near_gone;
            }else{
                btn_click2.setBackground(getResources().getDrawable(R.drawable.arrow_down));
                sv2.setVisibility(View.GONE);
                is_near_gone = !is_near_gone;
            }
        }
        else if(v.getId() == btn_reservation.getId())
        {
            Intent intent = new Intent(this, BaskitActivity.class);
            startActivity(intent);
        }
    }



    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            if (ReservationActivity.cafes != null) {
                CafeFragment frag = new CafeFragment();
                frag.setCafe(ReservationActivity.cafes.get(position));
                return frag;
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitle[position];
        }

        @Override
        public int getCount() {
            return ReservationActivity.CAFE_SIZE;
        }
    }
}
