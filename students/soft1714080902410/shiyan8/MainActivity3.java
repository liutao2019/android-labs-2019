package com.example.mymusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {
    private Button c = null;
    private Button d=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        c = (Button) findViewById(R.id.next);
        c.setOnClickListener(new MainActivity3.OPQ());
        d = (Button) findViewById(R.id.last);
        d.setOnClickListener(new MainActivity3.SRT());
    }
    private class OPQ implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity3.this, MainActivity.class);
            startActivity(intent);
        }
    }
    private class SRT implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
            startActivity(intent);
        }
    }
}
