package mkworld29.mobile.com.cafemoa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import mkworld29.mobile.com.cafemoa.prefs.BasketPref;

/**
 * Created by parkjaemin on 2017. 12. 26..
 */

public class StoreCloseDialog extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.baskit_delete_dialog);
    }

    //확인 버튼 클릭
    public void onOkClick(View v){
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