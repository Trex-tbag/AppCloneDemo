package com.example.tbag.appclone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by tbag on 2018/3/7.
 */

public class SuccessActivity extends AppCompatActivity {
    private Button button=null;

    private TextView textView =null;

    private WebView webView;

    private String url = "";

    private String text = "";

    private String token;

    private final String TAG = "SuccessActivity";

    private class ButtonListener implements View.OnClickListener {

        @Override

        public void onClick(View v) {

            switch (v.getId()){

                case R.id.button:

                    Intent intent =getIntent();

                    Bundle bundle =new Bundle();

                    bundle.putString("return","return fromSuccessActivity!");

                    intent.putExtras(bundle);

                    setResult(RESULT_OK,intent);

                    finish();

                    break;

            }

        }

    }

    public void initView(){

        button= (Button) findViewById(R.id.button);

        textView= (TextView) findViewById(R.id.textView);

        button.setOnClickListener(

                new ButtonListener()

        );

        textView.setText(text);

    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_success);

        webView = findViewById(R.id.webView);

        webView.getSettings().setAllowFileAccess(true);

        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {

            }

        });

        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        //实际测试需要开启file域访问才行
        webView.getSettings().setAllowFileAccessFromFileURLs(true);  //必须设置开启file域可读取之后 webview里面的页面才可以调用file域了

        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.setDownloadListener(new MyWebViewDownLoadListener());


        Intent intent =getIntent();


         //用于通过链接进行克隆测试  本地克隆测试的时候删除这段
         //还不清楚如何子啊bundle填入到URI中
        String token = "test";
        Uri uri = intent.getData();
        if (uri != null) {
            url = uri.getQueryParameter("url");
            //String bundle = uri.getQueryParameter("budle");
            //textView.setText(articleId);
            token = uri.getQueryParameter("token");


        }else {
            Bundle bundle = intent.getBundleExtra("bundle");
            token = bundle.getString(MainActivity.Intent_key); //通过链接克隆测试的时候注释掉
            url = bundle.getString(MainActivity.Intent_url);  //链接克隆的时候注释掉
        }
         //end
        //Bundle bundle = intent.getBundleExtra("bundle");

        //String token = bundle.getString(MainActivity.Intent_key); //通过链接克隆测试的时候注释掉

        if(token.equals("user3_login_success")){

            text ="张三登录成功";

        }else if(token.equals("user4_login_success")){

            text ="李四登录成功";

        }

        //url = bundle.getString(MainActivity.Intent_url);  //链接克隆的时候注释掉


        initView();
        String baiduurl = "http://baidu.com/";
        String tokenurl = "file:///sdcard/Download/send.htm";  //这个是有执行的，获取到token  api16以上没有访问sd卡的权限
        String tokenurl2 = "http://192.168.86.225/sendTocken.htm";  //这个没有获取到token,
        //webView.loadUrl(url);
        webView.loadUrl(url);


    }
    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            Log.i(TAG, "url="+url);
            Log.i(TAG, "userAgent="+userAgent);
            Log.i(TAG, "contentDisposition="+contentDisposition);
            Log.i(TAG, "mimetype="+mimetype);
            Log.i(TAG, "contentLength="+contentLength);
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }
}
