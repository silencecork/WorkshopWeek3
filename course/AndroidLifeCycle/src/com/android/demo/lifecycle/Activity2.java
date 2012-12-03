package com.android.demo.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Activity2 extends Activity {
	
	private static final String TAG = "Activity2";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
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
	
}