package com.example.mymusic;

import android.annotation.SuppressLint;
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

public class MainActivity5 extends AppCompatActivity {
    private LinearLayout layout;
    private SensorManager manager;
    private Sensor sensor;
    private int what=0;
    private Vibrator vibrator;
    private Button last;
    private Button next1;
    Handler handler=new Handler(){
        @SuppressLint("ResourceType")
        @Override
        public void handleMessage(Message msg){
            if (msg.what==0){
                layout.setBackgroundResource(R.layout.activity_main);
            }else if (msg.what==1){
                layout.setBackgroundResource(R.layout.activity_main2);
            }else if (msg.what==2){
                layout.setBackgroundResource(R.layout.activity_main3);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        layout=(LinearLayout)findViewById(R.id.next);
        last=(Button)findViewById(R.id.last);
        last.setOnClickListener(new MainActivity5.Liu());
        next1=(Button)findViewById(R.id.next1);
        next1.setOnClickListener(new MainActivity5.Tao());
        manager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(listener,sensor,manager.SENSOR_DELAY_NORMAL);
        vibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

    }
    SensorEventListener listener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values=sensorEvent.values;
            float x=values[0];
            float y=values[1];
            float z=values[2];
            int liutao=18;
            if (Math.abs(x)>liutao||Math.abs(y)>liutao||Math.abs(z)>liutao){
                long[]pattern={300,500};
                vibrator.vibrate(pattern,-1);
                what++;
                if (what>3){
                    what=0;
                }
                handler.sendEmptyMessage(what);
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor,int accuracy){
        }
    };
    @Override
    protected void onPause(){
        super.onPause();
        manager.unregisterListener(listener);
    }
    private class Liu implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Intent intent = new Intent(MainActivity5.this, MainActivity2.class);
            startActivity(intent);
        }
    }
    private class Tao implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Intent intent = new Intent(MainActivity5.this, MainActivity3.class);
            startActivity(intent);
        }
    }
}

