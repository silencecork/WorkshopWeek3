package com.android.practice.guess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		Button startBtn = (Button) findViewById(R.id.btn_start);
		startBtn.setOnClickListener(startClick);
	}
	
	OnClickListener startClick = new OnClickListener() {

		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClassName("com.android.practice.guess", "com.android.practice.guess.Main");
			startActivity(intent);
		}
		
	};

}
