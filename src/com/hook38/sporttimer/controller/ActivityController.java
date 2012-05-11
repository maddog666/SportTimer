package com.hook38.sporttimer.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;

import com.hook38.sporttimer.utils.TimeUnits;
import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.ListView;

public abstract class ActivityController {
	private Activity activity;
	protected ClockView clockView;
	protected Context context;
	protected ListView listView;
	private String leftTimeString;
	private String midTimeString;
	private String rightTimeString;
	private String centisecTimeString;
	//protected Runnable Timer;
	private ArrayList<String> list = new ArrayList<String>();
	
	
	public String getTimeString() {
		return leftTimeString + ":" 
				+ midTimeString + ":" 
				+ rightTimeString + ":"
				+ centisecTimeString;
	}
	
	//public ActivityController(Context context, ClockView clockView, ListView listView) {
		public ActivityController(Activity activity, ClockView clockView, ListView listView) {
		this.clockView = clockView;
		//this.context = context;
		this.activity = activity;
		this.listView = listView;
	}
	
	private void setTime(String hour, String min, String sec, String centisec) {
		clockView.setHourTime(hour);
		clockView.setMinTime(min);
		clockView.setSecTime(sec);
		clockView.setCentisecTime(centisec);
	}
	
	public void setTime(long hour, long min, long sec, long centisec) {
		NumberFormat df = NumberFormat.getInstance();
		df.setMinimumIntegerDigits(2);
		leftTimeString = String.valueOf(df.format(hour));
		midTimeString = String.valueOf(df.format(min));
		rightTimeString = String.valueOf(df.format(sec));
		df.setMinimumIntegerDigits(1);
		centisecTimeString = String.valueOf(df.format(centisec));
		this.setTime(leftTimeString, midTimeString, rightTimeString, centisecTimeString);
	}
	
	protected void addItem(String item){
		list.add(0, item);
		setList(list);
	}
	
	protected void clearItems() {
		list.clear();
		setList(list);
	}
	
	private void setList(List<String> list) {
		listView.populateList(list);
	}
	
	/**
	 * Add the user defined time into the timer model
	 * @param units
	 */
	public void addTime(TimeUnits units) {
	}
	
	/**
	 * Get the time in timer model given the 
	 * @param posi position of the list
	 * @return TimeUnits at the position
	 */
	public TimeUnits getTime(int posi) {
		return null;
	}
	
	/**
	 * Remove the time at the defined position in the timer model
	 * @param posi
	 */
	public void removeTime(int posi) {
	}
	
	/**
	 * Get the current activity that created this controller.
	 * @return
	 */
	public Activity getActivity(){
		return this.activity;
	}
	public abstract void close();
	public abstract void initiate();
}
