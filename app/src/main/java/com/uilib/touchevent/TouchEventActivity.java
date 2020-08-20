package com.uilib.touchevent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.uilib.R;

public class TouchEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);

        final ScrollView nsvScroll1 = findViewById(R.id.nsvScroll1);
        ScrollView nsvScroll2 = findViewById(R.id.nsvScroll2);

        nsvScroll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TouchEventActivity.this, "我被点击了", Toast.LENGTH_SHORT).show();
            }
        });

        nsvScroll2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                nsvScroll1.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }
}