package com.android.demo.menu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Main extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    private static final int MENU_OPT1 = 1;
    private static final int MENU_OPT2 = 2;
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d("Menu", "onCreateOptionsMenu");
		menu.add(0, MENU_OPT1, 0, R.string.menu_opt1);
		menu.add(0, MENU_OPT2, 1, R.string.menu_opt2);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d("Menu", "onOptionsItemSelected");
		int id = item.getItemId();
		if (id == MENU_OPT1) {
			Toast.makeText(Main.this, R.string.menu_opt1, Toast.LENGTH_LONG).show();
		} else if (id == MENU_OPT2) {
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