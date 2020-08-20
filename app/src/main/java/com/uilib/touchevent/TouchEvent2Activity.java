package com.uilib.touchevent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.uilib.R;

public class TouchEvent2Activity extends AppCompatActivity {

    private ListView lv;
    private ListView lv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event2);

        lv = findViewById(R.id.lv);
        lv2 = findViewById(R.id.lv2);

        lv.setAdapter(new MyAdapter());
        lv2.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater from = LayoutInflater.from(TouchEvent2Activity.this);
            View inflate = from.inflate(R.layout.include_touch_event_text, null);
            return inflate;
        }
    }
}