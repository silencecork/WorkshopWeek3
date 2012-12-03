package com.android.demo.newsdock.feed;

import java.net.URL;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class NewsFeed {
	
	public interface OnClickNewsListener {
		public void onClick(View v, String title, String url, String date, String description);
	}
	
	protected static final String TAG = "NewsFeed";
	private NewsFeedHandler newsHandler;
	private Handler handler = new Handler();
	private int index = 0;
	private boolean stop = false;
	private ViewSwitcher newsChanger;
	private OnClickNewsListener newClickListener;
	public static final String COUNTRY_TW = "tw";
	public static final String COUNTRY_US = "us";
	
	public static final String TOPIC_HIGHLIGHT = "h";
	public static final String TOPIC_NATION = "n";
	public static final String TOPIC_TECH = "t";
	public static final String TOPIC_FINANCIAL = "b";
	public static final String TOPIC_SPORT = "s";
	public static final String TOPIC_ENTERTAIN = "e";
	public static final String TOPIC_SOCIETY = "y";
	public static final String TOPIC_HEALTH = "m";
	
	public NewsFeed() {
		
	}
	
	public void getNews(final Context context, final String country, final String newsKind, final ViewSwitcher switcher, final OnClickNewsListener listener) {
		switcher.setOnClickListener(clickListener);
		newsChanger = switcher;
		newClickListener = listener;
		Thread th = new Thread(new Runnable() {
			public void run() {
				try {
					URL url = new URL(generateUrl(country, newsKind));
					newsHandler = new NewsFeedHandler();
					newsHandler.processFeed(url);
					News[] feeds = newsHandler.getNews();
					if (feeds == null || feeds.length < 0) {
						return;
					}
					/*for (int i = 0; i < feeds.length; i++) {
						News news = feeds[i];
						System.out.println("title " + news.title);
						System.out.println("link " + news.link);
						System.out.println("date " + news.publishDate);
						System.out.println("category " + news.category);
						System.out.println("desc " + news.description);
					}
					Log.d(TAG, "start loop");*/
					doLoop(feeds, switcher);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		th.start();
	}
	
	private void doLoop(final News[] news, final ViewSwitcher switcher) {
		handler.post(new Runnable() {
			public void run() {
				if (index > news.length - 1) {
					index = 0;
				}
				
				TextView text = (TextView) switcher.getCurrentView();
				if (text != null) {
					switcher.showNext();
					News n = news[index];
					text.setText(n.title);
					text.setTag(n);
					index++;
				}
				if (!stop) {
					handler.postDelayed(this, 5000);
				}
			}
		});
	}
	
	private String generateUrl(String country, String category) {
		country = (country == null) ? COUNTRY_TW : country;
		category = (category == null) ? TOPIC_HIGHLIGHT : category;
		return "http://news.google.com/news?ned=" + country + "&topic=" + category + "&output=rss";
	}
	
	public void destroy() {
		stop = true;
	}
	
	private OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.d("onClick", "onCLick");
			ViewSwitcher switcher = newsChanger;
			View currentView = switcher.getCurrentView();
			if (currentView != null) {
				if (newClickListener != null) {
					News news = (News) currentView.getTag();
					newClickListener.onClick(currentView, news.title, news.link, news.publishDate, news.description);
				}
			}
		}
    	
    };
}
