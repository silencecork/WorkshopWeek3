package com.android.demo.preferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Main.this);
		boolean checkBoxValue = pref.getBoolean(getString(R.string.checkbox_pref_key), false);
		String editTextValue = pref.getString(getString(R.string.edittext_pref_key), null);
		String listValue = pref.getString(getString(R.string.list_pref_key), null);
		
		TextView checkBoxResult = (TextView) findViewById(R.id.checkbox_val);
		checkBoxResult.setText("" + checkBoxValue);
		TextView editTextResult = (TextView) findViewById(R.id.edittext_val);
		editTextResult.setText(editTextValue);
		TextView listResult =  (TextView) findViewById(R.id.list_val);
		listResult.setText(listValue);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem item = menu.add("Settings");
		item.setIcon(android.R.drawable.ic_menu_preferences);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		intent.setClassName("com.android.demo.preferences", "com.android.demo.preferences.PreferenceDemo");
		startActivity(intent);
		return true;
	}
	
}
