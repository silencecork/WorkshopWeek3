package com.android.demo.nasa;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.demo.nasa.feed.NasaDaily;

public class DailyNasaActivity extends Activity {
    
	NasaDaily daily;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView title = (TextView) findViewById(R.id.title);
        TextView date = (TextView) findViewById(R.id.date);
        TextView desc = (TextView) findViewById(R.id.description);
        ImageView image = (ImageView) findViewById(R.id.image);
        
        daily = new NasaDaily(DailyNasaActivity.this);
        daily.processDialyImage(title, date, desc, image);
        
    }
	
}