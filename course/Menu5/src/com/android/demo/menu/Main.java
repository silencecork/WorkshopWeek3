package com.android.demo.menu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Main extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d("Menu", "onCreateOptionsMenu");
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d("Menu", "onOptionsItemSelected");
		int id = item.getItemId();
		if (id == R.id.opt1) {
			Toast.makeText(Main.this, R.string.menu_opt1, Toast.LENGTH_LONG).show();
		} else if (id == R.id.opt2) {
			Toast.makeText(Main.this, R.string.menu_opt2, Toast.LENGTH_LONG).show();
		}
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		Log.d("Menu", "onPrepareOptionsMenu");
		return true;
	}
    
}