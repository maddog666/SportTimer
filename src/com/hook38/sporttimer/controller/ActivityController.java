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
	//protected Runnable Timer;
	private ArrayList<String> list = new ArrayList<String>();
	
	public String getTimeString() {
		return leftTimeString + ":" 
				+ midTimeString + ":" 
				+ rightTimeString;
	}
	
	//public ActivityController(Context context, ClockView clockView, ListView listView) {
		public ActivityController(Activity activity, ClockView clockView, ListView listView) {
		this.clockView = clockView;
		//this.context = context;
		this.activity = activity;
		this.listView = listView;
	}
	
	private void setTime(String left, String mid, String right) {
		clockView.setLeftTime(left);
		clockView.setMiddleTime(mid);
		clockView.setRightTime(right);
	}
	
	public void setTime(long left, long mid, long right) {
		NumberFormat df = NumberFormat.getInstance();
		df.setMinimumIntegerDigits(2);
		leftTimeString = String.valueOf(df.format(left));
		midTimeString = String.valueOf(df.format(mid));
		rightTimeString = String.valueOf(df.format(right));
		this.setTime(leftTimeString, midTimeString, rightTimeString);
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
	public abstract void destroy();
}
