package com.hook38.sporttimer.utils.countdowntimer;

import java.util.ArrayList;

import android.util.Log;

import com.hook38.sporttimer.utils.TimeUnits;

public class Routine {

	ArrayList<TimeUnits> routine;
	private final static String TAG = "Routine";
	public Routine() {
		routine = new ArrayList<TimeUnits>();
	}
	
	public Routine(String text) {
		this();
		try{
			String [] routineString = text.split("-");
			for(String unitsString:routineString){
				TimeUnits units = new TimeUnits(unitsString);
				routine.add(units);
			}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}
	
	public void add(TimeUnits units) {
		routine.add(units);
	}
	
	public void set(int index, TimeUnits units) throws ArrayIndexOutOfBoundsException {
		routine.set(index, units);
	}
	
	public TimeUnits get(int index) throws ArrayIndexOutOfBoundsException {
		return routine.get(index);
	}
	
	public TimeUnits remove(int index) throws ArrayIndexOutOfBoundsException {
		return routine.remove(index);
	}
	
	public void clear() {
		routine.clear();
	}
	
	public String toString() {
		String text = "";
		for(int i=0; i<routine.size(); i++) {
			if(i!=0){
				text += "-";
			}
			text += routine.get(i).toString();
		}
		return text;		
	}
	
}
