package mkworld29.mobile.com.cafemoa;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by parkjaemin on 2017. 7. 18..
 */

public class ApplicationBase extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this,"NanumBarunGothic.otf"))
                .addBold(Typekit.createFromAsset(this,"NanumBarunGothicBold.otf"));
    }
}
