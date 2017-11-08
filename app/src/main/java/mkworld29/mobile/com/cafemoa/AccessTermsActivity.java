package mkworld29.mobile.com.cafemoa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tsengvn.typekit.TypekitContextWrapper;

public class AccessTermsActivity extends Activity implements View.OnClickListener, GestureDetector.OnGestureListener{

    private LinearLayout ly_all;
    private ImageView   iv_financial, iv_mobile, iv_alarm, iv_gps, iv_indiv;
    private ScrollView  sv_financial, sv_mobile, sv_alarm, sv_gps, sv_indiv;
    private TextView    tv_financial, tv_mobile, tv_alarm, tv_gps, tv_indiv, tv_cancel;
    private boolean     is_financial, is_mobile, is_alarm, is_gps, is_indiv;
    private CheckBox    cb_all, cb_financial, cb_mobile, cb_alarm, cb_gps, cb_indiv;
    private Button      btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_access_terms);

        initView();
        setContent();
    }

    protected void initView()
    {
        iv_financial    =   (ImageView) findViewById(R.id.iv_financial_access_term);
        iv_mobile       =   (ImageView) findViewById(R.id.iv_mobile_access_term);
        iv_alarm        =   (ImageView) findViewById(R.id.iv_alarm_access_term);
        iv_gps          =   (ImageView) findViewById(R.id.iv_gps_access_term);
        iv_indiv        =   (ImageView) findViewById(R.id.iv_indiv_access_term);

        sv_financial    =   (ScrollView) findViewById(R.id.sv_financial_access_term);
        sv_mobile       =   (ScrollView) findViewById(R.id.sv_mobile_access_term);
        sv_alarm        =   (ScrollView) findViewById(R.id.sv_alarm_access_term);
        sv_gps          =   (ScrollView) findViewById(R.id.sv_gps_access_term);
        sv_indiv        =   (ScrollView) findViewById(R.id.sv_indiv_access_term);

        tv_financial    =   (TextView) findViewById(R.id.tv_financial_access_term);
        tv_mobile       =   (TextView) findViewById(R.id.tv_mobile_access_term);
        tv_alarm        =   (TextView) findViewById(R.id.tv_alarm_access_term);
        tv_gps          =   (TextView) findViewById(R.id.tv_gps_access_term);
        tv_indiv        =   (TextView) findViewById(R.id.tv_individual_access_term);
        tv_cancel       =   (TextView) findViewById(R.id.tv_cancel);

        cb_all          =   (CheckBox) findViewById(R.id.cb_all);
        cb_financial    =   (CheckBox) findViewById(R.id.cb_checkbox2);
        cb_mobile       =   (CheckBox) findViewById(R.id.cb_checkbox3);
        cb_alarm        =   (CheckBox) findViewById(R.id.cb_checkbox4);
        cb_gps          =   (CheckBox) findViewById(R.id.cb_checkbox5);
        cb_indiv        =   (CheckBox) findViewById(R.id.cb_checkbox6);

        btn_next        =   (Button) findViewById(R.id.btn_next);

        ly_all          =   (LinearLayout)  findViewById(R.id.ly_all);

        iv_financial.setOnClickListener(this);
        iv_mobile.setOnClickListener(this);
        iv_alarm.setOnClickListener(this);
        iv_gps.setOnClickListener(this);
        iv_indiv.setOnClickListener(this);
        cb_all.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    protected void setContent()
    {
        is_financial = is_mobile = is_alarm = is_gps = is_indiv = false;

        tv_financial.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In fringilla ac felis id convallis. Aliquam vehicula enim bibendum commodo egestas. Sed nec augue aliquet, tincidunt leo sit amet, placerat lacus. In hac habitasse platea dictumst. Donec fermentum non arcu eu tristique. Nullam ut ipsum erat. Ut vulputate sem eu egestas accumsan. Integer non consectetur lacus, sed fringilla lacus. Quisque gravida tellus non mauris tempus auctor. Nullam sed velit vel lectus condimentum pellentesque. Phasellus iaculis lacus consequat ipsum tempus luctus. Mauris sed laoreet tellus, sed efficitur erat. Nulla tellus purus, blandit quis vehicula ut, malesuada bibendum augue.\n" +
                "\n" +
                "Cras hendrerit libero non nibh gravida, sit amet cursus nulla sagittis. Sed maximus tellus non sapien luctus fermentum sed fringilla tortor. Ut ut risus at dolor posuere auctor a ornare mi. Donec a dolor ut est sollicitudin blandit. Mauris at leo massa. Aenean est quam, posuere vel tortor at, suscipit sodales velit. Vivamus venenatis, nulla ac rutrum convallis, ligula magna bibendum est, eu semper orci orci in diam. Nulla blandit sapien sagittis, placerat nibh id, congue urna. Maecenas viverra mauris sed ligula consectetur tincidunt. Ut ut ex in est viverra maximus. Vestibulum sollicitudin ipsum non sodales placerat. Donec et efficitur tellus. Quisque ac ultrices tortor. Pellentesque scelerisque id lectus eu laoreet. Maecenas hendrerit eleifend arcu eu facilisis. Curabitur eleifend suscipit quam.\n" +
                "\n" +
                "In finibus elementum nulla sed tincidunt. Cras at sagittis tellus. Nunc mattis tortor non neque blandit gravida. Sed sed purus leo. Aliquam erat volutpat. Cras ultricies, lectus non consequat luctus, nibh dui malesuada libero, quis aliquet urna nibh et justo. Integer iaculis, sapien a ultricies ultrices, dolor diam tincidunt lorem, ut pharetra justo orci a urna.\n" +
                "\n" +
                "Suspendisse potenti. Integer mattis bibendum ante, id rhoncus mi tincidunt non. In mollis, lacus non finibus rhoncus, nulla neque porttitor nisl, a pulvinar libero turpis sed diam. Proin dictum lacinia nisl et tincidunt. In posuere, metus quis luctus feugiat, mi leo sollicitudin odio, ac condimentum tellus mi eget velit. Aliquam quis turpis nec neque vestibulum maximus. Cras dui nunc, mollis a arcu et, congue bibendum tortor. Vivamus luctus sit amet orci at posuere. Fusce a nunc lectus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce accumsan elementum diam ac molestie. Pellentesque et turpis sollicitudin, varius elit non, tincidunt leo. In sit amet risus id turpis accumsan suscipit nec non purus. Integer at arcu vestibulum, sagittis turpis at, dictum massa.\n" +
                "\n" +
                "Mauris scelerisque ullamcorper sapien, vel cursus ligula porta non. Sed mi erat, tempor et nibh in, molestie tempor metus. Donec in condimentum mauris, vel ornare arcu. Etiam tincidunt, tellus quis efficitur pellentesque, sem nunc pellentesque ex, dapibus elementum eros sem in ligula. Pellentesque fermentum arcu dui, a viverra justo fringilla sed. Curabitur pharetra odio ut ante convallis, non varius ante imperdiet. Donec quis elit mollis, congue erat vel, accumsan augue.");

        tv_mobile.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In fringilla ac felis id convallis. Aliquam vehicula enim bibendum commodo egestas. Sed nec augue aliquet, tincidunt leo sit amet, placerat lacus. In hac habitasse platea dictumst. Donec fermentum non arcu eu tristique. Nullam ut ipsum erat. Ut vulputate sem eu egestas accumsan. Integer non consectetur lacus, sed fringilla lacus. Quisque gravida tellus non mauris tempus auctor. Nullam sed velit vel lectus condimentum pellentesque. Phasellus iaculis lacus consequat ipsum tempus luctus. Mauris sed laoreet tellus, sed efficitur erat. Nulla tellus purus, blandit quis vehicula ut, malesuada bibendum augue.\n" +
                "\n" +
                "Cras hendrerit libero non nibh gravida, sit amet cursus nulla sagittis. Sed maximus tellus non sapien luctus fermentum sed fringilla tortor. Ut ut risus at dolor posuere auctor a ornare mi. Donec a dolor ut est sollicitudin blandit. Mauris at leo massa. Aenean est quam, posuere vel tortor at, suscipit sodales velit. Vivamus venenatis, nulla ac rutrum convallis, ligula magna bibendum est, eu semper orci orci in diam. Nulla blandit sapien sagittis, placerat nibh id, congue urna. Maecenas viverra mauris sed ligula consectetur tincidunt. Ut ut ex in est viverra maximus. Vestibulum sollicitudin ipsum non sodales placerat. Donec et efficitur tellus. Quisque ac ultrices tortor. Pellentesque scelerisque id lectus eu laoreet. Maecenas hendrerit eleifend arcu eu facilisis. Curabitur eleifend suscipit quam.\n" +
                "\n" +
                "In finibus elementum nulla sed tincidunt. Cras at sagittis tellus. Nunc mattis tortor non neque blandit gravida. Sed sed purus leo. Aliquam erat volutpat. Cras ultricies, lectus non consequat luctus, nibh dui malesuada libero, quis aliquet urna nibh et justo. Integer iaculis, sapien a ultricies ultrices, dolor diam tincidunt lorem, ut pharetra justo orci a urna.\n" +
                "\n" +
                "Suspendisse potenti. Integer mattis bibendum ante, id rhoncus mi tincidunt non. In mollis, lacus non finibus rhoncus, nulla neque porttitor nisl, a pulvinar libero turpis sed diam. Proin dictum lacinia nisl et tincidunt. In posuere, metus quis luctus feugiat, mi leo sollicitudin odio, ac condimentum tellus mi eget velit. Aliquam quis turpis nec neque vestibulum maximus. Cras dui nunc, mollis a arcu et, congue bibendum tortor. Vivamus luctus sit amet orci at posuere. Fusce a nunc lectus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce accumsan elementum diam ac molestie. Pellentesque et turpis sollicitudin, varius elit non, tincidunt leo. In sit amet risus id turpis accumsan suscipit nec non purus. Integer at arcu vestibulum, sagittis turpis at, dictum massa.\n" +
                "\n" +
                "Mauris scelerisque ullamcorper sapien, vel cursus ligula porta non. Sed mi erat, tempor et nibh in, molestie tempor metus. Donec in condimentum mauris, vel ornare arcu. Etiam tincidunt, tellus quis efficitur pellentesque, sem nunc pellentesque ex, dapibus elementum eros sem in ligula. Pellentesque fermentum arcu dui, a viverra justo fringilla sed. Curabitur pharetra odio ut ante convallis, non varius ante imperdiet. Donec quis elit mollis, congue erat vel, accumsan augue.");
        tv_alarm.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In fringilla ac felis id convallis. Aliquam vehicula enim bibendum commodo egestas. Sed nec augue aliquet, tincidunt leo sit amet, placerat lacus. In hac habitasse platea dictumst. Donec fermentum non arcu eu tristique. Nullam ut ipsum erat. Ut vulputate sem eu egestas accumsan. Integer non consectetur lacus, sed fringilla lacus. Quisque gravida tellus non mauris tempus auctor. Nullam sed velit vel lectus condimentum pellentesque. Phasellus iaculis lacus consequat ipsum tempus luctus. Mauris sed laoreet tellus, sed efficitur erat. Nulla tellus purus, blandit quis vehicula ut, malesuada bibendum augue.\n" +
                "\n" +
                "Cras hendrerit libero non nibh gravida, sit amet cursus nulla sagittis. Sed maximus tellus non sapien luctus fermentum sed fringilla tortor. Ut ut risus at dolor posuere auctor a ornare mi. Donec a dolor ut est sollicitudin blandit. Mauris at leo massa. Aenean est quam, posuere vel tortor at, suscipit sodales velit. Vivamus venenatis, nulla ac rutrum convallis, ligula magna bibendum est, eu semper orci orci in diam. Nulla blandit sapien sagittis, placerat nibh id, congue urna. Maecenas viverra mauris sed ligula consectetur tincidunt. Ut ut ex in est viverra maximus. Vestibulum sollicitudin ipsum non sodales placerat. Donec et efficitur tellus. Quisque ac ultrices tortor. Pellentesque scelerisque id lectus eu laoreet. Maecenas hendrerit eleifend arcu eu facilisis. Curabitur eleifend suscipit quam.\n" +
                "\n" +
                "In finibus elementum nulla sed tincidunt. Cras at sagittis tellus. Nunc mattis tortor non neque blandit gravida. Sed sed purus leo. Aliquam erat volutpat. Cras ultricies, lectus non consequat luctus, nibh dui malesuada libero, quis aliquet urna nibh et justo. Integer iaculis, sapien a ultricies ultrices, dolor diam tincidunt lorem, ut pharetra justo orci a urna.\n" +
                "\n" +
                "Suspendisse potenti. Integer mattis bibendum ante, id rhoncus mi tincidunt non. In mollis, lacus non finibus rhoncus, nulla neque porttitor nisl, a pulvinar libero turpis sed diam. Proin dictum lacinia nisl et tincidunt. In posuere, metus quis luctus feugiat, mi leo sollicitudin odio, ac condimentum tellus mi eget velit. Aliquam quis turpis nec neque vestibulum maximus. Cras dui nunc, mollis a arcu et, congue bibendum tortor. Vivamus luctus sit amet orci at posuere. Fusce a nunc lectus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce accumsan elementum diam ac molestie. Pellentesque et turpis sollicitudin, varius elit non, tincidunt leo. In sit amet risus id turpis accumsan suscipit nec non purus. Integer at arcu vestibulum, sagittis turpis at, dictum massa.\n" +
                "\n" +
                "Mauris scelerisque ullamcorper sapien, vel cursus ligula porta non. Sed mi erat, tempor et nibh in, molestie tempor metus. Donec in condimentum mauris, vel ornare arcu. Etiam tincidunt, tellus quis efficitur pellentesque, sem nunc pellentesque ex, dapibus elementum eros sem in ligula. Pellentesque fermentum arcu dui, a viverra justo fringilla sed. Curabitur pharetra odio ut ante convallis, non varius ante imperdiet. Donec quis elit mollis, congue erat vel, accumsan augue.");
        tv_indiv.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In fringilla ac felis id convallis. Aliquam vehicula enim bibendum commodo egestas. Sed nec augue aliquet, tincidunt leo sit amet, placerat lacus. In hac habitasse platea dictumst. Donec fermentum non arcu eu tristique. Nullam ut ipsum erat. Ut vulputate sem eu egestas accumsan. Integer non consectetur lacus, sed fringilla lacus. Quisque gravida tellus non mauris tempus auctor. Nullam sed velit vel lectus condimentum pellentesque. Phasellus iaculis lacus consequat ipsum tempus luctus. Mauris sed laoreet tellus, sed efficitur erat. Nulla tellus purus, blandit quis vehicula ut, malesuada bibendum augue.\n" +
                "\n" +
                "Cras hendrerit libero non nibh gravida, sit amet cursus nulla sagittis. Sed maximus tellus non sapien luctus fermentum sed fringilla tortor. Ut ut risus at dolor posuere auctor a ornare mi. Donec a dolor ut est sollicitudin blandit. Mauris at leo massa. Aenean est quam, posuere vel tortor at, suscipit sodales velit. Vivamus venenatis, nulla ac rutrum convallis, ligula magna bibendum est, eu semper orci orci in diam. Nulla blandit sapien sagittis, placerat nibh id, congue urna. Maecenas viverra mauris sed ligula consectetur tincidunt. Ut ut ex in est viverra maximus. Vestibulum sollicitudin ipsum non sodales placerat. Donec et efficitur tellus. Quisque ac ultrices tortor. Pellentesque scelerisque id lectus eu laoreet. Maecenas hendrerit eleifend arcu eu facilisis. Curabitur eleifend suscipit quam.\n" +
                "\n" +
                "In finibus elementum nulla sed tincidunt. Cras at sagittis tellus. Nunc mattis tortor non neque blandit gravida. Sed sed purus leo. Aliquam erat volutpat. Cras ultricies, lectus non consequat luctus, nibh dui malesuada libero, quis aliquet urna nibh et justo. Integer iaculis, sapien a ultricies ultrices, dolor diam tincidunt lorem, ut pharetra justo orci a urna.\n" +
                "\n" +
                "Suspendisse potenti. Integer mattis bibendum ante, id rhoncus mi tincidunt non. In mollis, lacus non finibus rhoncus, nulla neque porttitor nisl, a pulvinar libero turpis sed diam. Proin dictum lacinia nisl et tincidunt. In posuere, metus quis luctus feugiat, mi leo sollicitudin odio, ac condimentum tellus mi eget velit. Aliquam quis turpis nec neque vestibulum maximus. Cras dui nunc, mollis a arcu et, congue bibendum tortor. Vivamus luctus sit amet orci at posuere. Fusce a nunc lectus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce accumsan elementum diam ac molestie. Pellentesque et turpis sollicitudin, varius elit non, tincidunt leo. In sit amet risus id turpis accumsan suscipit nec non purus. Integer at arcu vestibulum, sagittis turpis at, dictum massa.\n" +
                "\n" +
                "Mauris scelerisque ullamcorper sapien, vel cursus ligula porta non. Sed mi erat, tempor et nibh in, molestie tempor metus. Donec in condimentum mauris, vel ornare arcu. Etiam tincidunt, tellus quis efficitur pellentesque, sem nunc pellentesque ex, dapibus elementum eros sem in ligula. Pellentesque fermentum arcu dui, a viverra justo fringilla sed. Curabitur pharetra odio ut ante convallis, non varius ante imperdiet. Donec quis elit mollis, congue erat vel, accumsan augue.");
        tv_gps.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In fringilla ac felis id convallis. Aliquam vehicula enim bibendum commodo egestas. Sed nec augue aliquet, tincidunt leo sit amet, placerat lacus. In hac habitasse platea dictumst. Donec fermentum non arcu eu tristique. Nullam ut ipsum erat. Ut vulputate sem eu egestas accumsan. Integer non consectetur lacus, sed fringilla lacus. Quisque gravida tellus non mauris tempus auctor. Nullam sed velit vel lectus condimentum pellentesque. Phasellus iaculis lacus consequat ipsum tempus luctus. Mauris sed laoreet tellus, sed efficitur erat. Nulla tellus purus, blandit quis vehicula ut, malesuada bibendum augue.\n" +
                "\n" +
                "Cras hendrerit libero non nibh gravida, sit amet cursus nulla sagittis. Sed maximus tellus non sapien luctus fermentum sed fringilla tortor. Ut ut risus at dolor posuere auctor a ornare mi. Donec a dolor ut est sollicitudin blandit. Mauris at leo massa. Aenean est quam, posuere vel tortor at, suscipit sodales velit. Vivamus venenatis, nulla ac rutrum convallis, ligula magna bibendum est, eu semper orci orci in diam. Nulla blandit sapien sagittis, placerat nibh id, congue urna. Maecenas viverra mauris sed ligula consectetur tincidunt. Ut ut ex in est viverra maximus. Vestibulum sollicitudin ipsum non sodales placerat. Donec et efficitur tellus. Quisque ac ultrices tortor. Pellentesque scelerisque id lectus eu laoreet. Maecenas hendrerit eleifend arcu eu facilisis. Curabitur eleifend suscipit quam.\n" +
                "\n" +
                "In finibus elementum nulla sed tincidunt. Cras at sagittis tellus. Nunc mattis tortor non neque blandit gravida. Sed sed purus leo. Aliquam erat volutpat. Cras ultricies, lectus non consequat luctus, nibh dui malesuada libero, quis aliquet urna nibh et justo. Integer iaculis, sapien a ultricies ultrices, dolor diam tincidunt lorem, ut pharetra justo orci a urna.\n" +
                "\n" +
                "Suspendisse potenti. Integer mattis bibendum ante, id rhoncus mi tincidunt non. In mollis, lacus non finibus rhoncus, nulla neque porttitor nisl, a pulvinar libero turpis sed diam. Proin dictum lacinia nisl et tincidunt. In posuere, metus quis luctus feugiat, mi leo sollicitudin odio, ac condimentum tellus mi eget velit. Aliquam quis turpis nec neque vestibulum maximus. Cras dui nunc, mollis a arcu et, congue bibendum tortor. Vivamus luctus sit amet orci at posuere. Fusce a nunc lectus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce accumsan elementum diam ac molestie. Pellentesque et turpis sollicitudin, varius elit non, tincidunt leo. In sit amet risus id turpis accumsan suscipit nec non purus. Integer at arcu vestibulum, sagittis turpis at, dictum massa.\n" +
                "\n" +
                "Mauris scelerisque ullamcorper sapien, vel cursus ligula porta non. Sed mi erat, tempor et nibh in, molestie tempor metus. Donec in condimentum mauris, vel ornare arcu. Etiam tincidunt, tellus quis efficitur pellentesque, sem nunc pellentesque ex, dapibus elementum eros sem in ligula. Pellentesque fermentum arcu dui, a viverra justo fringilla sed. Curabitur pharetra odio ut ante convallis, non varius ante imperdiet. Donec quis elit mollis, congue erat vel, accumsan augue.");

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == iv_financial.getId())
        {
            if(is_financial == false)
            {
                sv_financial.setVisibility(View.VISIBLE);
                is_financial = true;
            }
            else
            {
                sv_financial.setVisibility(View.GONE);
                is_financial = false;
            }
        }
        else if(view.getId() == iv_mobile.getId())
        {
            if(is_mobile == false)
            {
                sv_mobile.setVisibility(View.VISIBLE);
                is_mobile = true;
            }
            else
            {
                sv_mobile.setVisibility(View.GONE);
                is_financial = false;
            }
        }
        else if(view.getId() == iv_alarm.getId())
        {
            if(is_alarm == false)
            {
                sv_alarm.setVisibility(View.VISIBLE);
                is_alarm = true;
            }
            else
            {
                sv_alarm.setVisibility(View.GONE);
                is_alarm = false;
            }
        }
        else if(view.getId() == iv_gps.getId())
        {
            if(is_gps == false)
            {
                sv_gps.setVisibility(View.VISIBLE);
                is_gps = true;
            }
            else
            {
                sv_gps.setVisibility(View.GONE);
                is_gps = false;
            }
        }
        else if(view.getId() == iv_indiv.getId())
        {
            if(is_indiv == false)
            {
                sv_indiv.setVisibility(View.VISIBLE);
                is_indiv = true;
            }
            else
            {
                sv_indiv.setVisibility(View.GONE);
                is_indiv = false;
            }
        }
        else if(view.getId() == cb_all.getId())
        {
            if(cb_all.isChecked())
            {
                cb_financial.setChecked(true);
                cb_indiv.setChecked(true);
                cb_mobile.setChecked(true);
                cb_alarm.setChecked(true);
                cb_gps.setChecked(true);
            }
        }
        else if(view.getId() == tv_cancel.getId())
        {
            finish();
        }
        else if(view.getId() == btn_next.getId())
        {
            Intent intent = new Intent(this, FirstSignUpActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {

        float recentX, recentY;

        recentX = motionEvent.getX();
        recentY = motionEvent.getY();


        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
