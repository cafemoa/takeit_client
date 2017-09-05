package mkworld29.mobile.com.cafemoa;

import android.app.Application;

import com.zoyi.channel.plugin.android.ChannelPlugin;

/**
 * Created by parkjaemin on 2017. 8. 30..
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ChannelPlugin.initialize(this, "4af61d65-9361-4b37-9130-0de3a492caa8");
        // ChannelPlugin.initialize(this, pluginKey, true); // for debug
    }
}