package mkworld29.mobile.com.cafemoa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import mkworld29.mobile.com.cafemoa.iamportsdk.InicisWebViewClient;

public class PaymentActivity extends AppCompatActivity {
    private WebView mainWebView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_payment);

        mainWebView = (WebView) findViewById(R.id.mainWebview);
        mainWebView.setWebViewClient(new InicisWebViewClient(this));
        WebSettings settings = mainWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        Intent intent = getIntent();
        String payurl=intent.getStringExtra("payurl");
        //Uri intentData = intent.getData();

        mainWebView.loadUrl(payurl);
    }
}