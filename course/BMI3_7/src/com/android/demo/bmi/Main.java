package com.android.demo.bmi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button = (Button) findViewById(R.id.btn_ok);
        button.setOnClickListener(calcBMI);
    }
    
    private OnClickListener calcBMI = new OnClickListener() {

		public void onClick(View v) {
			Log.d("onClick", "clicked!!");
			EditText inputHeight = (EditText) findViewById(R.id.input_height);
			EditText inputWeight = (EditText) findViewById(R.id.input_weight);
			String strHeight = inputHeight.getText().toString();
			String strWeight = inputWeight.getText().toString();
			
			if (TextUtils.isEmpty(strWeight) || TextUtils.isEmpty(strHeight)) {
				AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
				builder.setTitle(R.string.warning_title);
				builder.setMessage(R.string.warning);
				builder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				builder.show();
				return;
			}
			
			float height = Float.parseFloat(strHeight) / 100;
			float weight = Float.parseFloat(strWeight);
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
		}
		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.menu_related) {
			showDialog();
		} else if (id == R.id.menu_exit) {
			finish();
		}
		return true;
	}
	
	private void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
		builder.setTitle(R.string.menu_info);
		builder.setMessage(R.string.bmi_formula);
		builder.setPositiveButton(R.string.btn_health, new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		builder.setNegativeButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		builder.show();
	}
}