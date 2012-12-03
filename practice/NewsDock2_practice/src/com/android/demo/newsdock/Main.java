package com.android.demo.newsdock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.android.demo.newsdock.feed.NewsFeed;
import com.android.demo.newsdock.feed.NewsFeed.OnClickNewsListener;

public class Main extends Activity {

	NewsFeed feed;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
	
	@Override
	protected void onResume() {
		super.onResume();
		// Change value of country and topic
		// it should get from SharedPreferences
		String country = "";
		String topic = "";
		ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.viewSwitcher1);
		if (feed != null) {
			feed.destroy();
		}
		feed = new NewsFeed();
		feed.getNews(this, country, topic, switcher, listener);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		feed.destroy();
	}
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.menu_setting) {
			
		}
		return true;
	}



	OnClickNewsListener listener = new OnClickNewsListener() {

		@Override
		public void onClick(View v, String title, String url, String date, String description) {
			Intent intent = new Intent("android.demo.intent.action.READ");
			intent.putExtra("title", title);
			intent.putExtra("date", date);
			intent.putExtra("description", description);
			intent.putExtra("link", url);
			
			try {
				startActivity(intent);
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(Main.this, R.string.warning, Toast.LENGTH_LONG).show();
			}
		}
    	
    };
    
}