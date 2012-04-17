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
import com.hook38.sporttimer.view.TimeInputView;

public class CountdownTimerController extends ActivityController {
	private static final String TAG = "CountdownTimer";
	private static final String ROUTINE_LIST = "RoutineList";
	private Handler handler = new Handler(); 
	private List<Float> list = new ArrayList<Float>();
	private TimeInputView timeinputview;
	
	public CountdownTimerController(Context context, ClockView clockView, ListView listView, 
			TimeInputView timeinput) {
		super(context, clockView, listView);
		// TODO Auto-generated constructor stub
		this.timeinputview = timeinput;
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
	
	/**
	 * Retrieve hour from the input field in timeinputview
	 * @return hour inputed
	 */
	public String getInputHour() {
		return this.timeinputview.getHour();
	}
	
	/**
	 * Retrieve minute from the input field in timeinputview
	 * @return minute inputed
	 */
	public String getInputMinute() {
		return this.timeinputview.getMinute();
	}
	
	/**
	 * Retrieve second from the input field in timeinputview
	 * @return second inputed
	 */
	public String getInputSecond() {
		return this.timeinputview.getSecond();
	}
}
