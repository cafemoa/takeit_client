package mkworld29.mobile.com.cafemoa;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import mkworld29.mobile.com.cafemoa.adapter.MainCafeAdapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.zoyi.channel.plugin.android.ChannelException;
import com.zoyi.channel.plugin.android.ChannelPlugin;
import com.zoyi.channel.plugin.android.CheckIn;
import com.zoyi.channel.plugin.android.OnChannelPluginChangedListener;
import com.zoyi.channel.plugin.android.OnCheckInListener;
import com.zoyi.channel.plugin.android.activity.settings.SettingsActivity;
import com.zoyi.channel.plugin.android.push.ChannelPushClient;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.item.MainCafeItem;
import mkworld29.mobile.com.cafemoa.prefs.BasketPref;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//2018-01-03 이걸 롤백 버전으로


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnTouchListener ,
        View.OnClickListener,
        ViewFlipperAction.ViewFlipperCallback{

    private ViewFlipper flipper;
    private float initialX;
    private Retrofit retrofit;
    private Button imb_prev, imb_next;
    private RecyclerView recyclerView;
    private ArrayList<MainCafeItem> data=new ArrayList<>();
    private TextView drawer_name;
    private View viewToLoad;
    private NavigationView nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        nav_view=(NavigationView) findViewById(R.id.nav_view);

        retrofit = RetrofitInstance.getInstance(getApplicationContext());
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewToLoad =inflater.inflate(R.layout.nav_header_main,null);
        drawer_name=(TextView) viewToLoad.findViewById(R.id.drawer_name);

        establishView();
        //startRecyclerView();
        startFlipper();
        startNavigation();
        setCafeList();
        ChannelPlugin.initialize(this.getApplication(),"4af61d65-9361-4b37-9130-0de3a492caa8");
        //ChannelPlugin.addOnChannelPluginChangedListener(this);
        ChannelPushClient.handlePushMessage(this);

        RetrofitConnection.get_profile service = retrofit.create(RetrofitConnection.get_profile.class);
        final Call<RetrofitConnection.Profile> repos = service.repoContributors();
        repos.enqueue(new Callback<RetrofitConnection.Profile>() {
            @Override
            public void onResponse(Call<RetrofitConnection.Profile> call, Response<RetrofitConnection.Profile> response) {
                if (response.code() == 200){
                    drawer_name.setText(response.body().name);
                    nav_view.addHeaderView(viewToLoad);
                }
                else{
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RetrofitConnection.Profile> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
    }




    private void setCafeList(){
        recyclerView = (RecyclerView)findViewById(R.id.main_recyclerView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        RetrofitConnection.get_cafes service = retrofit.create(RetrofitConnection.get_cafes.class);
        final Call<List<RetrofitConnection.Cafe>> repos = service.repoContributors();
        repos.enqueue(new Callback<List<RetrofitConnection.Cafe>>() {
            @Override
            public void onResponse(Call<List<RetrofitConnection.Cafe>> call, Response<List<RetrofitConnection.Cafe>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++) {
                        RetrofitConnection.Cafe cafe=response.body().get(i);
                        //Log.d("TAG",cafe.cafe_image);
                        data.add(new MainCafeItem(cafe.pk,cafe.name, cafe.locationString, cafe.tag, cafe.cafe_image, cafe.is_open,cafe.min_time,cafe.coupon_num,cafe.coupon_price));
                    }
                    recyclerView.setAdapter(new MainCafeAdapter(getApplicationContext(), data, R.layout.activity_main));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error : "+ response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitConnection.Cafe>> call, Throwable t) {
                Log.d("TAG",t.getLocalizedMessage());
            }
        });

    }

    private void establishView()
    {
        imb_next = (Button) findViewById(R.id.imb_next);
        imb_prev = (Button) findViewById(R.id.imb_prev);

        imb_next.setOnClickListener(this);
        imb_prev.setOnClickListener(this);
    }


    private void startNavigation()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.getMenu().close();
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setDisplayShowCustomEnabled(false);
        ab.setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.menu_bar,getTheme()));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

//        ImageView iv_set_up = (ImageView) hView.findViewById(R.id.iv_set_up);
//        iv_set_up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
//                startActivity(intent);
//            }
//        });

    }


    private void startFlipper()
    {
        flipper = (ViewFlipper)findViewById(R.id.main_flipper);

        // add ImageView to ViewFlipper

        for(int i=0;i<3;i++)
        {
            ImageView img = new ImageView(this);
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.main_banner);
            img.setBackground(drawable);
            flipper.addView(img);
        }

        Animation showIn = AnimationUtils.loadAnimation(this,R.anim.out_left);

        flipper.setInAnimation(showIn);
        flipper.setOutAnimation(this,R.anim.in_right);

        flipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PersonalEventActivity.class);
                intent.putExtra("url","여기에 Url 넣으면됨 ");
                startActivity(intent);

            }
        });

        flipper.setFlipInterval(6000);
        flipper.startFlipping();
        flipper.setFocusable(true);
        flipper.setAutoStart(true);

        flipper.setOnTouchListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_cart);
        Log.d("====BASKET TAG===", "" + BasketPref.getInstance(this.getApplicationContext()).getSize());
        if(BasketPref.getInstance(this.getApplicationContext()).getSize() > 0) item.setVisible(true);
        else item.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_alarm) {
            // Display Alarm Layout
            //Intent intent = new Intent(getApplicationContext(), NoticeDialog.class);
            //startActivity(intent);
            /*
            Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.notice_dialog);
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();


            ArrayList<AlertItem> data= new ArrayList<>();

            data.add(new AlertItem("형남공학과 2층 에비수", "3시~5시까지 전제품 10퍼센트 할인행사중!"));
            data.add(new AlertItem("학생회관 4층 아름다운세상", "신메뉴 요거바라 출시! 많은 관심 부탁드려요~"));

            AlertAdapter adapter=new AlertAdapter(getApplicationContext(),R.layout.item_alert,data);

            dialog.getWindow().setAttributes(params);
            dialog.show();
            */
            //Intent intent = new Intent(this, NoticeDialog.class);
            //startActivity(intent);
            NoticeDialog nd=new NoticeDialog(this);
            WindowManager.LayoutParams wm = new WindowManager.LayoutParams();
            wm.copyFrom(nd.getWindow().getAttributes());
            nd.show();
        }
        else if(id == R.id.action_cart)
        {
            Intent intent = new Intent(this, BaskitActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;
        if (id == R.id.nav_reservation) {
            intent = new Intent(this, SettingActivity.class);
        } else if (id == R.id.nav_coupon) {
            intent = new Intent(this, CouponActivity.class);
        } else if (id == R.id.nav_event) {
            intent = new Intent(this, OrderListActivity.class);
        } else if (id == R.id.nav_talk){
            //checkInVeil();
            ChannelPlugin.show(this);
        }

        if(intent !=null)
            startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                Log.d("TOUCHEVENT","TOUCH DOWB");
                initialX = event.getX();
                break;

            case MotionEvent.ACTION_UP:
                Log.d("TOUCHEVENT","TOUCH UPUPUP");
                float finalX = event.getX();
                if(initialX > finalX)
                {
                    if(flipper.getDisplayedChild() == 1)
                        break;

                    flipper.setInAnimation(this, R.anim.in_right);
                    flipper.setOutAnimation(this,R.anim.out_left);

                    Log.d("TOUCH TAGGG!!!!","show,next");
                    flipper.showNext();
                    flipper.setFlipInterval(6000);
                }
                else
                {
                    if(flipper.getDisplayedChild() == 0)
                        break;
                    flipper.setInAnimation(this,R.anim.in_left);
                    flipper.setOutAnimation(this,R.anim.out_right);

                    Log.d("TOUCH TAGGG!!!!","show,Prev");
                    flipper.showPrevious();
                    flipper.setFlipInterval(6000);

                }
                break;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == imb_next.getId())
        {
            flipper.setInAnimation(this, R.anim.in_right);
            flipper.setOutAnimation(this,R.anim.out_left);
            flipper.showNext();
            flipper.setFlipInterval(6000);
        }
        else if(v.getId() == imb_prev.getId())
        {
            flipper.setInAnimation(this,R.anim.in_left);
            flipper.setOutAnimation(this,R.anim.out_right);
            flipper.showPrevious();
            flipper.setFlipInterval(6000);
        }
    }

    @Override
    protected void onDestroy() {
        //ChannelPlugin.removeOnChannelPluginChangedListener(this);
        ChannelPlugin.checkOut();
        super.onDestroy();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void onFlipperActionCallback(int position)
    {

    }


    /*
    private void startRecyclerView()
    {
        RetrofitConnection.recent_payment_list_by_id service = retrofit.create(RetrofitConnection.recent_payment_list_by_id.class);

        final Call<List<RetrofitConnection.Recent_payment>> repos = service.repoContributors();

        repos.enqueue(new Callback<List<RetrofitConnection.Recent_payment>>() {
            @Override
            public void onResponse(Call<List<RetrofitConnection.Recent_payment>> call, Response<List<RetrofitConnection.Recent_payment>> response) {
                if(response.code()==200){
                    RecyclerView rv = (RecyclerView)findViewById(R.id.recyclerView);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                    rv.setHasFixedSize(true);
                    rv.setLayoutManager(mLayoutManager);
                    List<RetrofitConnection.Recent_payment> info=response.body();
                    if(info.size() == 0)
                        return;
                    List<CardItem> items = new ArrayList<CardItem>();
                    CardItem[] item = new CardItem[info.size()];
                    for(int i=0; i<info.size(); i++){
                        RetrofitConnection.Recent_payment now_payment=info.get(i);
                        item[i] = new CardItem(RetrofitInstance.getApiUrl()+info.get(i).image_url,info.get(i).cafe_name,info.get(i).menu_name,info.get(i).price);
                        items.add(item[i]);
                    }
                    rv.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                    rv.setAdapter(new CardAdapter(getApplicationContext(), items));
                }else{
                    Toast.makeText(getApplicationContext(), "Error : "+ response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitConnection.Recent_payment>> call, Throwable t) {
                Log.d("TAG",t.getLocalizedMessage());
            }
        });
    }*/

}
