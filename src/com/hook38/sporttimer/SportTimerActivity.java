package com.hook38.sporttimer;

import com.hook38.sporttimer.controller.ActivityController;

import android.content.Context;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;

public class SportTimerActivity extends FragmentActivity {
	ActivityController controller;
	private Vibrator vibrator;
	
	public ActivityController getController(){
		return controller;
	}
	
	protected Vibrator getVibrator(){
		if(this.vibrator == null) {
			this.vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		}
		return this.vibrator;
	}
}
