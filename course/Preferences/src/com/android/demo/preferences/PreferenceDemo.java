package com.android.demo.preferences;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferenceDemo extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}

}


