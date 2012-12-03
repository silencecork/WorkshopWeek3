package com.android.demo.newsdock;

import com.android.demo.newsdock.feed.NewsFeed;
import com.android.demo.newsdock.feed.NewsFeed.OnClickNewsListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewSwitcher;

public class Main extends Activity {

	NewsFeed feed;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.viewSwitcher1);
        
        feed = new NewsFeed();
        feed.getNews(this, NewsFeed.COUNTRY_TW, NewsFeed.TOPIC_TECH, switcher, listener);
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		feed.destroy();
	}
    
	OnClickNewsListener listener = new OnClickNewsListener() {

		@Override
		public void onClick(View v, String title, String url, String date, String description) {
			
			// enter your code here
			
		}
    	
    };
    
}