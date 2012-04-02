package com.hook38.sporttimer.utils;

import java.util.ArrayList;
/**
 * This class holds the time units. The default time units should contain
 * three units, which looks like hours:minutes:seconds. But additional 
 * units can be added for future modification. 
 * @author William
 *
 */
public class TimeUnits {
	ArrayList<Float> units;
	
	public TimeUnits () {	
		units = new ArrayList<Float>();
	}
	
	public TimeUnits(String string) {
		this();
		String [] unitsString = string.split(":");
		for(int i=0; i<unitsString.length; i++) {
			try{
				units.add(Float.valueOf(unitsString[i]));
			}catch(NumberFormatException e) {
				units.add(0f);
			}
		}
	}
	
	public void add(float unit) {
		units.add(unit);
	}
	
	public void set(int posi, float unit) 
			throws ArrayIndexOutOfBoundsException {		
		units.set(posi, unit);		
	}
	
	public void get(int posi) 
			throws ArrayIndexOutOfBoundsException {		
		units.get(posi);		
	}
	
	public void remove(int posi) 
			throws ArrayIndexOutOfBoundsException {		
		units.remove(posi);		
	}
	
	public void clear() {
		units.clear();
	}
	
	public String toString() {
		//time unit in format num:num:num
		String string = "";
		for(int i=0; i<units.size(); i++) {			
			if(i!=0){
				string +=":";
			}
			string+=units.get(i);
		}
		return string;
	}
	
	
}
