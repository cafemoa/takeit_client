package mkworld29.mobile.com.cafemoa;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

/**
 * Created by parkjaemin on 2017. 9. 7..
 */

public class ViewFlipperAction {

    Context context;

    //전체화면개수
    int countIndexes;
    //현재화면인덱스
    int currentIndex;
    //터치시작 x좌표
    float downX;
    //터치끝 x좌표
    float upX;
    ViewFlipper flipper;

    //
    ViewFlipperCallback indexCallback;


    //인터페이스 - 탭 클릭시 이미지 변경하기 위한 인터페이스
    //여러 액티비티가 fragment를 호출하여도 동일한 인터페이스를 구현하도록 한다
    public static interface ViewFlipperCallback{
        public void onFlipperActionCallback(int position);
    }

    public ViewFlipperAction(Context context, ViewFlipper flipper){
        this.context = context;
        this.flipper = flipper;

        if(context instanceof ViewFlipperCallback){
            indexCallback = (ViewFlipperCallback)context;
        }

        currentIndex = 0;
        downX = 0;
        upX = 0;
        countIndexes = flipper.getChildCount();

        //인덱스 업데이트
        indexCallback.onFlipperActionCallback(currentIndex);
    }


}
