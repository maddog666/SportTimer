package com.hook38.sporttimer.utils.countdowntimer;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

import android.util.Log;

public class RoutineList {
	Hashtable<String, Routine> list;
	private static final String TAG = "RoutineList";
	public RoutineList() {
		list = new Hashtable<String, Routine>();
	}
	
	public RoutineList(String listString) {
		this();
		try{
			String [] routinesString = listString.split(",");
			for(String routineHash:routinesString) {
				String [] routineString = routineHash.split("=");
				String key = routineString[0];
				Routine routine = new Routine(routineString[1]);
				list.put(key, routine);
			}
		}catch(Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}
	
	public void put(String key, Routine routine) {
		list.put(key, routine);
	}
	
	public void clear() {
		list.clear();
	}
	
	public Routine get(String key) {
		return list.get(key);
	}
	
	public void remove (int index) throws ArrayIndexOutOfBoundsException{
		list.remove(index);
	}
	
	public String toString() {
		String text = "";
		Set<String> keys = list.keySet();
		int i = 0;
		for(String key: keys){
			if(i!=0){
				text+=",";
			}
			text += key + "=" + list.get(i).toString();
			i++;
		}
		return text;
	}
	
	
}
