package mkworld29.mobile.com.cafemoa;

import android.app.Application;

import com.tsengvn.typekit.Typekit;
import com.zoyi.channel.plugin.android.ChannelPlugin;

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
        ChannelPlugin.initialize(this, "4af61d65-9361-4b37-9130-0de3a492caa8");
    }
}
