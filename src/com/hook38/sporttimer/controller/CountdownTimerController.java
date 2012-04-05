package com.hook38.sporttimer.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.hook38.sporttimer.utils.countdowntimer.RoutineList;
import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.ListView;

public class CountdownTimerController extends ActivityController {
	private static final String TAG = "CountdownTimer";
	private static final String ROUTINE_LIST = "RoutineList";
	private Handler handler = new Handler(); 
	private List<Float> list = new ArrayList<Float>();
	
	public CountdownTimerController(Context context, ClockView clockView, ListView listView) {
		super(context, clockView, listView);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	private SharedPreferences loadPreference () {
		SharedPreferences settings = context.getSharedPreferences(TAG, 0);
		return settings;
	}
	
	private void savePreferences (String name, SharedPreferences preferences, RoutineList list) {
		SharedPreferences settings = context.getSharedPreferences(TAG, 0);
		settings.edit().putString(
				ROUTINE_LIST, 
				list.toString()
		).commit();	
	}

	private Runnable Timer = new Runnable() {
		//private Handler handler = new Handler();		
		public void run() {
			
			handler.postDelayed(this, 10);
		}
	};
}
