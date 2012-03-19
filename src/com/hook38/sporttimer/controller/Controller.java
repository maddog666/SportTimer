package com.hook38.sporttimer.controller;

import com.hook38.sporttimer.view.ClockView;

public abstract class Controller {
	private ClockView clockView;
	
	public Controller(ClockView clockView) {
		this.clockView = clockView;
	}
	
	private void setTime(String left, String mid, String right) {
		clockView.setLeftTime(left);
		clockView.setMiddleTime(mid);
		clockView.setRightTime(right);
	}
	
	public void setTime(long left, long mid, long right) {
		String leftTimeString = String.valueOf(left);
		String midTimeString = String.valueOf(mid);
		String rightTimeString = String.valueOf(right);
		this.setTime(leftTimeString, midTimeString, rightTimeString);
	}
}
