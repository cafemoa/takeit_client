package mkworld29.mobile.com.cafemoa;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.zoyi.channel.plugin.android.ChannelPlugin;
import com.zoyi.channel.plugin.android.OnChannelPluginChangedListener;
import com.zoyi.channel.plugin.android.push.ChannelPushClient;

public class VeilActivity extends AppCompatActivity implements OnChannelPluginChangedListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veil);

        ChannelPlugin.addOnChannelPluginChangedListener(this);

        ChannelPushClient.handlePushMessage(this);
    }

    @Override
    public void badgeChanged(int count) {
        Log.i("Badge Changed", count + "");
    }
}