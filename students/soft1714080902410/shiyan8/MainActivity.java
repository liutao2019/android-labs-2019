package com.example.mymusic;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private TextView liu;
    private Button liu_tao;
    private Button e = null;
    private Button f = null;
    private Button g = null;
    private Button h = null;

    TextView textView=null;
    String text=null;
    JSONObject object;
    String URL="https://raw.githubusercontent.com/liutao2019/android-labs-2019/master/students/soft1714080902410/6.photo/songer";
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.liu);
        liu_tao=findViewById(R.id.liu_tao);
        Button liutao=(Button)findViewById(R.id.liu_tao);
        liutao.setOnClickListener(new View.OnClickListener() {

            public void getjson(){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            java.net.URL url =new URL(URL);
                            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(5000);
                            connection.setReadTimeout(5000);
                            connection.setUseCaches(false);
                            connection.connect();
                            InputStream in=connection.getInputStream();
                            InputStreamReader input=new InputStreamReader(in);
                            BufferedReader reader=new BufferedReader(input);
                            if (connection.getResponseCode()==200){
                                StringBuilder response=new StringBuilder();
                                String Line;
                                while ((Line=reader.readLine())!=null){
                                    response.append(Line);
                                }
                                text=response.toString();
                                System.out.println(text);
                                object=new JSONObject(text);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @SuppressLint("SetText")
                            @Override
                            public void run(){
                                try {
                                    liu_tao.setText(object.getString("songer"));
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }).start();
            }
            @Override
            public void onClick(View v) {
                getjson();
            }
        });

        e = (Button) findViewById(R.id.next);
        e.setOnClickListener(new MainActivity.ABC());
        f = (Button) findViewById(R.id.last);
        f.setOnClickListener(new MainActivity.EFG());
        g=(Button)findViewById(R.id.tao_liu);
        g.setOnClickListener(new  MainActivity.GDD());
        h = (Button) findViewById(R.id.D_liu);
        h.setOnClickListener(new MainActivity.LTT());
}
    private class ABC implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }
    }
    private class EFG implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, MainActivity3.class);
            startActivity(intent);
        }
    }
    private class GDD implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, MainActivity4.class);
            startActivity(intent);
        }
    }
    private class LTT implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, MainActivity5.class);
            startActivity(intent);
        }
    }

}
