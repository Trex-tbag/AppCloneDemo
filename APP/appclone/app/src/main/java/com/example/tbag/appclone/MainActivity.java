package com.example.tbag.appclone;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;


import android.os.Handler;
import android.os.Message;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;



public class MainActivity extends Activity {

    private EditText username;

    private EditText password;

    private Button button;

    private Handler handler;

    private String result="";

    private TextView resultTV;

    public  static  final String Intent_key="token";

    public  static  final String Intent_url="URL";

    private SharedPreferences preferences;

    //private  String urlInfo ="http://www.androidserver.com:8080/ad/myinfo.jsp?token=";

    // 获取用户信息的URL
    private  String urlInfo ="http://192.168.86.225:8080/getinfo.jsp?tocken=";

    //private  String urlInfo ="http://192.168.31.246:8080/getinfo.jsp?tocken=";

    private SharedPreferences.Editor editor;

    String TAG = "MainActivity";



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        //获取preferences和editor对象

        preferences = getSharedPreferences("loginState",MODE_PRIVATE);


        // 获取一个SharedPreferences.Editor 对象
        editor = preferences.edit();


        String token =preferences.getString("token","fail"); //获取数据 如果不存在则返回缺省值(第二个参数)

        Log.d(TAG, "onCreate: "+ token);

        Intent intent = new Intent(this,SuccessActivity.class);

        Bundle bundle = new Bundle();

        if(token.equals("user3_login_success")){

            bundle.putString(Intent_key, token);

            bundle.putString(Intent_url, urlInfo + token);

            intent.putExtra("bundle", bundle);

            startActivityForResult(intent,0);

        }else if(token.equals("user4_login_success")){

            bundle.putString(Intent_key, token);

            bundle.putString(Intent_url, urlInfo + token);

            intent.putExtra("bundle", bundle);

            startActivityForResult(intent,0);

        }

        username=(EditText)findViewById(R.id.username);

        password=(EditText)findViewById(R.id.password);

        resultTV=(TextView)findViewById(R.id.result);

        button=(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if("".equals(username.getText().toString())){

                    Toast.makeText(MainActivity.this, "请登录",

                            Toast.LENGTH_SHORT).show();
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        login();
                        Message m = handler.obtainMessage();
                        handler.sendMessage(m);
                    }

                }).start();

            }

        });

        handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {

                if(result!=null){

                    resultTV.setText(result);

                    username.setText("");

                    password.setText("");

                }

                super.handleMessage(msg);

            }

        };

    }


    //当从secondActivity中返回时调用此函数,清空token
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0 && resultCode==RESULT_OK){

            Bundle bundle = data.getExtras();

            String text =null;

            if(bundle!=null)

                text=bundle.getString("return");

            Log.d("text",text);

            editor.remove("token");

            editor.commit();

        }

    }

    public void login() {

        //String target="http://www.androidserver.com:8080/ad/login.jsp";

        //String target = "http://192.168.86.225:8080/gettocken.jsp";

        String target = "http://192.168.31.246:8080/gettocken.jsp";

        URL url;

        try {

            url= new URL(target);

            HttpURLConnection urlConn=(HttpURLConnection) url.openConnection();

            urlConn.setRequestMethod("POST");

            urlConn.setDoInput(true);

            urlConn.setDoOutput(true);

            urlConn.setUseCaches(false);

            urlConn.setInstanceFollowRedirects(true);

            urlConn.setRequestProperty("Content-Type",

                    "application/x-www-form-urlencoded");

            DataOutputStream out=new DataOutputStream(urlConn.getOutputStream());

            String param="username="+ URLEncoder.encode(username.getText().toString(),"utf-8")

                    +"&password="+URLEncoder.encode(password.getText().toString(),"utf-8");

            System.out.println(username);

            out.writeBytes(param);

            out.flush();

            out.close();

            if(urlConn.getResponseCode()==HttpURLConnection.HTTP_OK){

                InputStreamReader in=new InputStreamReader(urlConn.getInputStream());

                BufferedReader buffer=new BufferedReader(in);

                String inputLine=null;

                String token = "";

                result = "";

                while((inputLine=buffer.readLine())!=null){

                    result+=inputLine;

                }

                Log.d(TAG, "login: "+result);

                in.close();

                Intent intent = new Intent(this,SuccessActivity.class);

                Bundle bundle = new Bundle();

                //嘀嗒嘀嗒嘀
                if(result.indexOf("user3_login_success")!=-1){
                    Log.d(TAG, "login: 登录成功");
                    token ="user3_login_success";

                }else if(result.indexOf("user4_login_success")!=-1){

                    token ="user4_login_success";

                }else{

                    return;

                }

                editor.putString("token",token);

                bundle.putString(Intent_key,token);

                bundle.putString(Intent_url,urlInfo + token);

                editor.commit();

                intent.putExtra("bundle", bundle);

                startActivityForResult(intent,0);

            }

            urlConn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
