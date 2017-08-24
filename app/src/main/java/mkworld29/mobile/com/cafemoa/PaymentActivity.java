package mkworld29.mobile.com.cafemoa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import mkworld29.mobile.com.cafemoa.iamportsdk.InicisWebViewClient;

public class PaymentActivity extends AppCompatActivity {
    private WebView mainWebView;
    private static final String APP_SCHEME = "CAFEMOAAPP";

    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mainWebView = (WebView) findViewById(R.id.mainWebview);
        mainWebView.setWebViewClient(new InicisWebViewClient(this));
        WebSettings settings = mainWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.setAcceptThirdPartyCookies(mainWebView, true);
        }

        Intent intent = getIntent();
        Uri intentData = intent.getData();

        if ( intentData == null ) {
            mainWebView.loadUrl("http://61.100.180.146/payment/");
        } else {
            //isp 인증 후 복귀했을 때 결제 후속조치
            String url = intentData.toString();
            if ( url.startsWith(APP_SCHEME) ) {
                String redirectURL = url.substring(APP_SCHEME.length()+3);
                mainWebView.loadUrl(redirectURL);
            }
        }

        try {
            if(intent != null)
            {
                String menu_name = intent.getStringExtra("menu_name");
                String price = intent.getStringExtra("price");
                String str = null;
                if(menu_name != null && price != null)
                    str = "name=" + URLEncoder.encode(menu_name, "UTF-8") + "&price=" + URLEncoder.encode(price, "UTF-8");
                if(str !=null)
                    mainWebView.postUrl("http://61.100.180.146/payment/", str.getBytes());
            }
        }
        catch(UnsupportedEncodingException i){
            i.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        String url = intent.toString();
        if ( url.startsWith(APP_SCHEME) ) {
            String redirectURL = url.substring(APP_SCHEME.length()+3);
            mainWebView.loadUrl(redirectURL);
        }
    }
}