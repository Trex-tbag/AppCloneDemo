package com.example.tbag.toclone;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText cloneid;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        cloneid=(EditText)findViewById(R.id.token);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String token = cloneid.getText().toString();
                Log.d(TAG, "onClick: "+token);
                final ComponentName componentName = new ComponentName(
                        "com.example.tbag.appclone",
                        "com.example.tbag.appclone.SuccessActivity");
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setComponent(componentName);
                String tocken = "user1";
                
                bundle.putString("URL", "http://192.168.86.225:8080/getinfo.jsp?tocken="+token);
                //bundle.putString("URL", "http://baidu.com/");
                bundle.putString("token",token);

                intent.putExtra("bundle", bundle);
                startActivity(intent);

            }

        });

    }
}
