package com.hook38.sporttimer.controller;

import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.ListView;

public abstract class Controller {
	protected ClockView clockView;
	protected ListView listView;
	private String leftTimeString;
	private String midTimeString;
	private String rightTimeString;
	
	public String getTimeString() {
		return leftTimeString + ":" 
				+ midTimeString + ":" 
				+ rightTimeString;
	}
	
	public Controller(ClockView clockView, ListView listView) {
		this.clockView = clockView;
		this.listView = listView;
	}
	
	private void setTime(String left, String mid, String right) {
		clockView.setLeftTime(left);
		clockView.setMiddleTime(mid);
		clockView.setRightTime(right);
	}
	
	public void setTime(long left, long mid, long right) {
		leftTimeString = String.valueOf(left);
		midTimeString = String.valueOf(mid);
		rightTimeString = String.valueOf(right);
		this.setTime(leftTimeString, midTimeString, rightTimeString);
	}
}
