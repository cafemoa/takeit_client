package mkworld29.mobile.com.cafemoa;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import mkworld29.mobile.com.cafemoa.adapter.AlertAdapter;
import mkworld29.mobile.com.cafemoa.item.AlertItem;

/**
 * Created by ABCla on 2017-10-08.
 */

public class NoticeDialog extends Dialog {
    ArrayList<AlertItem> data;
    AlertAdapter alertAdapter;
    ListView listView;
    Button back;
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

        ArrayList<AlertItem> data= new ArrayList<>();

        data.add(new AlertItem("형남공학과 2층 에비수", "3시~5시까지 전제품 10퍼센트 할인행사중!"));
        data.add(new AlertItem("학생회관 4층 아름다운세상", "신메뉴 요거바라 출시! 많은 관심 부탁드려요~"));

        //WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        //lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        //lpWindow.dimAmount = 0.5f;
        //getWindow().setAttributes(lpWindow);

        alertAdapter=new AlertAdapter(getContext(),R.layout.item_alert,data);
        listView.setAdapter(alertAdapter);
        listView.setHeaderDividersEnabled(false);
        listView.setFooterDividersEnabled(false);

        getWindow().setBackgroundDrawableResource(R.color.translucent_white);
        getWindow().setLayout(dpToPx(getContext(),380), dpToPx(getContext(),600));

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
