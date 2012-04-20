package com.hook38.sporttimer.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.hook38.sporttimer.model.CountdownTimerModel;
import com.hook38.sporttimer.utils.TimeUnits;
import com.hook38.sporttimer.utils.countdowntimer.RoutineList;
import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.ListView;
import com.hook38.sporttimer.view.InteractiveListView;
import com.hook38.sporttimer.view.TimeInputView;

public class CountdownTimerController extends ActivityController {
	private static final String TAG = "CountdownTimer";
	private static final String ROUTINE_LIST = "RoutineList";
	private Handler handler = new Handler(); 
	private TimeInputView timeinputview;
	private CountdownTimerModel timerModel;
	
	public CountdownTimerController(Context context, ClockView clockView, ListView listView, 
			TimeInputView timeinput) {
		super(context, clockView, listView);
		// TODO Auto-generated constructor stub
		this.timeinputview = timeinput;
		this.timerModel = new CountdownTimerModel();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
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
	
	private void updateView() {
		this.listView.populateList(timerModel.toStringList());
	}
	
	public void addTime() {
		TimeUnits units = new TimeUnits();
		units.add(Float.parseFloat(getInputHour()));
		units.add(Float.parseFloat(getInputMinute()));
		units.add(Float.parseFloat(getInputSecond()));
		this.addTime(units);
	}
	
	/**
	 * Return the size of the current timers in the timer model
	 * @return size of the current timer
	 */
	public int timerSize() {
		return timerModel.size();
	}
	
	/**
	 * Add the user defined time into the timer model
	 * @param units
	 */
	public void addTime(TimeUnits units) {
		timerModel.add(units);
		this.updateView();
	}
	
	/**
	 * Get the time in timer model given the 
	 * @param posi position of the list
	 * @return TimeUnits at the position
	 */
	public TimeUnits getTime(int posi) {
		return timerModel.get(posi);
	}
	
	/**
	 * Remove the time at the defined position in the timer model
	 * @param posi
	 */
	public void removeTime(int posi) {
		timerModel.remove(posi);
		this.updateView();
	}
	
}
