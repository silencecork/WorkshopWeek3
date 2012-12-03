package com.android.demo.intent1;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button mapBtn = (Button) findViewById(R.id.map);
        mapBtn.setOnClickListener(mapClick);
        
        Button dialBtn = (Button) findViewById(R.id.dial);
        dialBtn.setOnClickListener(dialClick);
        
        Button searchBtn = (Button) findViewById(R.id.google);
        searchBtn.setOnClickListener(searchClick);
        
        Button smsBtn = (Button) findViewById(R.id.sms);
        smsBtn.setOnClickListener(smsClick);
        
        Button mailBtn = (Button) findViewById(R.id.email);
        mailBtn.setOnClickListener(mailClick);
    }
    
    private OnClickListener mapClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startMap();
		}
    	
    };  
    private OnClickListener dialClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			dial();
		}
    	
    };   
    private OnClickListener searchClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			searchGoogle();
		}
    	
    }; 
    private OnClickListener smsClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			sendSMS();
		}
    	
    };
    private OnClickListener mailClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			sendEmail();
		}
    	
    };   
    private void startMap() {
    	Intent intent = new Intent(Intent.ACTION_VIEW);
		Uri data = Uri.parse("geo:25.047192,121.516981");
		intent.setData(data);
		startActivity(intent);
    }
    private void dial() {
    	Intent intent = new Intent(Intent.ACTION_DIAL);
		Uri data = Uri.parse("tel:12345678");
		intent.setData(data);
		startActivity(intent);
    }
    private void searchGoogle() {
    	Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
    	intent.putExtra(SearchManager.QUERY, "Android");
		startActivity(intent);
	}
    private void sendSMS() {
    	Intent intent = new Intent(Intent.ACTION_SENDTO);
    	Uri data = Uri.parse("smsto:12345678");
    	intent.setData(data);
    	intent.putExtra("sms_body", "Android is Good!");
    	startActivity(intent);
    }
    private void sendEmail() {
    	Intent intent = new Intent();
    	intent.setAction(Intent.ACTION_SEND);
    	intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"chih.ting.liu@gmail.com"}); 
    	intent.putExtra(Intent.EXTRA_CC, new String[] {"silencecork@silencecork.com"});
    	intent.putExtra(Intent.EXTRA_SUBJECT, "Android is Good");
    	intent.putExtra(Intent.EXTRA_TEXT, "Android is a perfect mobile platform.");
    	intent.setType("message/rfc822");   
    	try {
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(Main.this, R.string.warning, Toast.LENGTH_LONG).show();
		}
    }
    
}