package com.hook38.sporttimer.model;

import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hook38.sporttimer.utils.TimeUnits;
import com.hook38.sporttimer.utils.countdowntimer.RoutineList;

public class CountdownTimerModel {
	private ArrayList<TimeUnits> list = new ArrayList<TimeUnits>();
	
	public void add(TimeUnits units) {
		list.add(units);
	}
	
	public TimeUnits get(int posi) {
		return list.get(posi);
	}
	
	public int size() {
		return list.size();
	}
	
	public void clear() {
		list.clear();
	}
	
	public void remove(int i) {
		list.remove(i);
	}
	
	public void set(int posi, TimeUnits units) {
		list.set(posi, units);
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public String toString() {
		String text = "";
		boolean start = true;
		for(TimeUnits units : list) {
			if(start) {
				text = text.concat(",");
				start = false;
			}
			text = text.concat(units.toString());
		}
		return text;
	}
	
	public List<String> toStringList() {
		ArrayList<String> tempList = new ArrayList<String>();
		for(TimeUnits units : this.list) {
			tempList.add(units.toString());
		}
		return tempList;
	}
	
}
