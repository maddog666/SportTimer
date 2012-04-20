package com.hook38.sporttimer;

import com.hook38.sporttimer.controller.ActivityController;

import android.support.v4.app.FragmentActivity;

public class SportTimerActivity extends FragmentActivity {
	ActivityController controller;
	
	public ActivityController getController(){
		return controller;
	}
}
