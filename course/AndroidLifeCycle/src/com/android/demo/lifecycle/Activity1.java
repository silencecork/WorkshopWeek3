package com.android.demo.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity1 extends Activity {
	
	private static final String TAG = "Activity1";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        
        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(clickToOther);
    }
    
    @Override
	protected void onRestart() {
		Log.i(TAG, "onRestart");
		super.onRestart();
	}
    
    @Override
	protected void onStart() {
    	Log.i(TAG, "onStart");
		super.onStart();
	}
    
    @Override
	protected void onResume() {
    	Log.i(TAG, "onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i(TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.i(TAG, "onStop");
		super.onStop();
	}
    
	@Override
	protected void onDestroy() {
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}
    
    OnClickListener clickToOther = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClassName("com.android.demo.lifecycle", "com.android.demo.lifecycle.Activity2");
			startActivity(intent);
		}
    	
    };
    
}