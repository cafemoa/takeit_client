package mkworld29.mobile.com.cafemoa;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import mkworld29.mobile.com.cafemoa.item.CardItem;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.zoyi.channel.plugin.android.ChannelPlugin;
import com.zoyi.channel.plugin.android.OnChannelPluginChangedListener;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa.retrofit.RetrofitInstance;
import mkworld29.mobile.com.cafemoa.adapter.CardAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnTouchListener ,
        View.OnClickListener,
        OnChannelPluginChangedListener,
        ViewFlipperAction.ViewFlipperCallback{

    private ViewFlipper flipper;
    private float initialX;
    private Retrofit retrofit;
    private Button imb_prev, imb_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = RetrofitInstance.getInstance(getApplicationContext());
        establishView();
        //startRecyclerView();
        startFlipper();
        startNavigation();

    }

    private void establishView()
    {
        imb_next = (Button) findViewById(R.id.imb_next);
        imb_prev = (Button) findViewById(R.id.imb_prev);

        imb_next.setOnClickListener(this);
        imb_prev.setOnClickListener(this);
    }

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
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void startFlipper()
    {
        flipper = (ViewFlipper)findViewById(R.id.main_flipper);

        // add ImageView to ViewFlipper

        for(int i=0;i<7;i++)
        {
            ImageView img = new ImageView(this);
            img.setImageResource(R.mipmap.ic_launcher);
            flipper.addView(img);
        }

        Animation showIn = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        flipper.setInAnimation(showIn);

        flipper.setOutAnimation(this,android.R.anim.slide_out_right);

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
            return true;
        }
        else if(id==R.id.action_basket){
            Intent intent = new Intent(this, BaskitActivity.class);
            startActivity(intent);
            return true;
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
            // Handle the camera action
            intent = new Intent(this, ReservationActivity.class);
        } else if (id == R.id.nav_coupon) {
            intent = new Intent(this, CouponActivity.class);
        } else if (id == R.id.nav_event) {
            intent = new Intent(this, ReservationActivity.class);
        } else if (id == R.id.nav_settings) {
            intent = new Intent(this, ReservationActivity.class);
        } else if (id == R.id.nav_talk){
            checkInVeil();
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
                }
                else
                {
                    if(flipper.getDisplayedChild() == 0)
                        break;
                    flipper.setInAnimation(this,R.anim.in_left);
                    flipper.setOutAnimation(this,R.anim.out_right);

                    Log.d("TOUCH TAGGG!!!!","show,Prev");
                    flipper.showPrevious();

                }
                break;
        }
        return true;
    }

    private void checkInVeil() {
                Intent intent = new Intent(MainActivity.this, VeilActivity.class);
                startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == imb_next.getId())
        {
            flipper.showNext();
        }
        else if(v.getId() == imb_prev.getId())
        {
            flipper.showPrevious();
        }

    }

    @Override
    protected void onDestroy() {
        ChannelPlugin.removeOnChannelPluginChangedListener(this);
        ChannelPlugin.checkOut();
        super.onDestroy();
    }

    @Override
    public void badgeChanged(int count) {
        Log.i("Badge Changed", count + "");
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
}
