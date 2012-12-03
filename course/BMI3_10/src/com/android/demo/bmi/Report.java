package com.android.demo.bmi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Report extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
		
		Intent intent = getIntent();
		float weight = intent.getFloatExtra("input_weight", 0);
		float height = intent.getFloatExtra("input_height", 0);
		
		float BMI = weight / (height * height);
		TextView result = (TextView) findViewById(R.id.result);
		String strResult = "Your BMI is " + BMI + "\n";
		if (BMI > 24) {
			strResult += getString(R.string.advice_heavy);
		} else if (BMI < 18.5) {
			strResult += getString(R.string.advice_light);
		} else {
			strResult += getString(R.string.advice_average);
		}
		result.setText(strResult);
		
		Button btn = (Button) findViewById(R.id.btn_back);
		btn.setOnClickListener(backClick);
	}
	
	OnClickListener backClick = new OnClickListener() {

		public void onClick(View v) {
			finish();
		}
		
	};
}

