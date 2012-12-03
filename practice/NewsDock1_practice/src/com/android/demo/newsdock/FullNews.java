package com.android.demo.newsdock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class FullNews extends Activity {
	
	String strTitle;
	String strDate;
	String strDescription;
	String strLink;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full_news);
		
		Intent intent = getIntent();
		strTitle = intent.getStringExtra("title");
		strDate = intent.getStringExtra("date");
		strDescription = intent.getStringExtra("description");
		strLink = intent.getStringExtra("link");
		
		TextView title = (TextView) findViewById(R.id.title);
		TextView date = (TextView) findViewById(R.id.date);
		TextView description = (TextView) findViewById(R.id.desc);
		
		title.setText(strTitle);
		date.setText(strDate);
		description.setText(Html.fromHtml(strDescription));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// enter your code here
		
		return true;
	}
	
	

}
