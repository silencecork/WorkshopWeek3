package com.android.demo.nasa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Details extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		
		TextView text = (TextView) findViewById(R.id.description);
		// Enter your code here to receive description from intent
		
		Button backBtn = (Button) findViewById(R.id.btn_back);
		backBtn.setOnClickListener(backClick);
	}
	
	OnClickListener backClick = new OnClickListener() {

		public void onClick(View v) {
			finish();
		}
		
	};

}
