package com.hook38.sporttimer;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingActivity extends PreferenceActivity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
	}
}
