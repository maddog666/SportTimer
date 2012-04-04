package com.hook38.sporttimer.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.ListView;

public abstract class ActivityController {
	protected ClockView clockView;
	protected Context context;
	protected ListView listView;
	private String leftTimeString;
	private String midTimeString;
	private String rightTimeString;
	protected Runnable Timer;
	private ArrayList<String> list = new ArrayList<String>();
	
	public String getTimeString() {
		return leftTimeString + ":" 
				+ midTimeString + ":" 
				+ rightTimeString;
	}
	
	public ActivityController(Context context, ClockView clockView, ListView listView) {
		this.clockView = clockView;
		this.context = context;
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
	
	public abstract void destroy();
}
