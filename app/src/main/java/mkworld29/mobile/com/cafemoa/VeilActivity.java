package mkworld29.mobile.com.cafemoa;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.zoyi.channel.plugin.android.ChannelPlugin;
import com.zoyi.channel.plugin.android.OnChannelPluginChangedListener;
import com.zoyi.channel.plugin.android.push.ChannelPushClient;

public class VeilActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_veil);
        //ChannelPlugin.addOnChannelPluginChangedListener(this);
        ChannelPushClient.handlePushMessage(this);
    }

}