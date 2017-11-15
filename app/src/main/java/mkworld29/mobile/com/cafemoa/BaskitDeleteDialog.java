package mkworld29.mobile.com.cafemoa;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.adapter.BasketAdapter;
import mkworld29.mobile.com.cafemoa.prefs.BasketPref;

/**
 * Created by ABCla on 2017-11-14.
 */

public class BaskitDeleteDialog extends Activity {

    private String id;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.baskit_delete_dialog);

        id=getIntent().getStringExtra("ID");
        position=getIntent().getIntExtra("Position", 0);
    }

    //확인 버튼 클릭
    public void onOkClick(View v){
        //데이터 전달하기
        BasketPref.getInstance(this).deleteBasket(id);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("Position", position);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    public void onCancelClick(View v){
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
