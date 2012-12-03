package com.android.demo.quickmail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.menu_send) {
			sendEmail();
		} else if (id == R.id.menu_quit) {
			finish();
		}
		return true;
	}
    
    private void sendEmail() {
    	EditText inputReceiver = (EditText) findViewById(R.id.receiver);
    	EditText inputCC = (EditText) findViewById(R.id.cc);
    	EditText inputSubject = (EditText) findViewById(R.id.subject);
    	EditText inputContent = (EditText) findViewById(R.id.content);
    	
    	String strReceivers = inputReceiver.getText().toString();
    	String[] receivers = separateEmail(strReceivers);
    	
    	String strCC = inputCC.getText().toString();
    	String[] CCs = separateEmail(strCC);
    	
    	String subject = inputSubject.getText().toString();
    	String content = inputContent.getText().toString();
    	
    	Intent intent = new Intent();
    	intent.setAction(Intent.ACTION_SEND);
    	intent.putExtra(Intent.EXTRA_EMAIL, receivers); 
    	intent.putExtra(Intent.EXTRA_CC, CCs);
    	intent.putExtra(Intent.EXTRA_SUBJECT, subject);
    	intent.putExtra(Intent.EXTRA_TEXT, content);
    	intent.setType("message/rfc822");
    	
    	try {
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(Main.this, R.string.warning, Toast.LENGTH_LONG).show();
		}
    }
    
    private String[] separateEmail(String str) {
    	return (str != null) ? str.split(";") : null;
    }
	
}