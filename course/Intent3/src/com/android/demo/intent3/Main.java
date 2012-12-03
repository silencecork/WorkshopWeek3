package com.android.demo.intent3;

import com.android.demo.intent3.R;

import android.app.Activity;
import android.content.Intent;
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
        
        Button browserBtn = (Button) findViewById(R.id.browser);
        browserBtn.setOnClickListener(clickOpenBrowser);
        
    }
    
    private OnClickListener clickOpenBrowser = new OnClickListener() {

		@Override
		public void onClick(View v) {
			openBrowser();
		}
    	
    };  
     
    private void openBrowser() {
    	Intent intent = new Intent();
    	intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
		try {
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(Main.this, R.string.warning, Toast.LENGTH_LONG).show();
		}
    }
    
}

