package com.hook38.sporttimer.utils;

import java.text.NumberFormat;
import java.util.ArrayList;
/**
 * This class holds the time units. The default time units should contain
 * three units, which looks like hours:minutes:seconds. But additional 
 * units can be added for future modification. 
 * @author William
 *
 */
public class TimeUnits {
	ArrayList<Integer> units;
	
	public TimeUnits () {	
		units = new ArrayList<Integer>();
	}
	
	public TimeUnits(String string) {
		this();
		this.setUnit(string);
	}
	
	protected void setUnit(String string) {
		String [] unitsString = string.split(":");
		for(int i=0; i<unitsString.length; i++) {
			try{
				units.add(Integer.valueOf(unitsString[i]));
			}catch(NumberFormatException e) {
				units.add(0);
			}
		}
	}
	
	public void add(String unit) throws NumberFormatException{
		this.add(Integer.parseInt(unit));
	}
	
	public void add(int unit) {
		units.add(unit);

	}
	
	public void set(int posi, int unit) 
			throws IndexOutOfBoundsException {		
		units.set(posi, unit);		
	}
	
	public Integer get(int posi) 
			throws IndexOutOfBoundsException {		
		
		return units.get(posi);		
	}
	
	public void remove(int posi) 
			throws IndexOutOfBoundsException {		
		units.remove(posi);		
	}
	
	public void clear() {
		units.clear();
	}
	
	public String toString() {
		//time unit in format num:num:num
		String string = "";
		NumberFormat df = NumberFormat.getInstance();
		df.setMinimumIntegerDigits(2);
		for(int i=0; i<units.size(); i++) {			
			if(i!=0){
				string +=":";
			}			
			String unitString = String.valueOf(df.format(units.get(i)));
			string+=unitString;
		}
		return string;
	}
	
	public int size(){
		return units.size();
	}
	
	private boolean isEmpty(){
		return units.isEmpty();
	}
	
	
	/**
	 * Determine if this timeunits has no time, e.g. 0 hour, 0 min, 0 sec.
	 * @return whether this timeunit has no time.
	 */
	public boolean hasNoTime(){
		if(this.isEmpty()) {
			return true;
		} else{
			for(Integer time : units) {
				if(time!=0){
					return false;
				}
			}
			return true;
		}
	}
	
	/**
	 * Calculate MilliSecond from the position containing the hour
	 * @param posi
	 * @return
	 */
	public long getMillisFromHour(int posi) {
		long min = 0;
		try{
			min = this.get(posi) * 60;		
		} catch(IndexOutOfBoundsException e) {
			
		}
		return getMillisFromMinute(posi+1, min); 
	}
	
	/**
	 * Calculate the time in milliseconds, given the position of the minute time, 
	 * and the previous time in minutes.
	 * @param posi
	 * @param min
	 * @return
	 */
	public long getMillisFromMinute(int posi, long min) {
		try{
			min += this.get(posi);
		} catch(IndexOutOfBoundsException e) {
			
		}
		long sec = min * 60;
		return getMillisFromSecond(posi+1, sec);
	}
	
	/**
	 * Calculate the time in milliseconds, given the position of the second, and the
	 * previous time in seconds.
	 * @param posi
	 * @param sec
	 * @return
	 */
	public long getMillisFromSecond(int posi, long sec) {
		try{
			sec += this.get(posi);
		} catch(IndexOutOfBoundsException e) {
			
		}
		long centisecs = sec * 10;
		return getMillisFromCentisecond(posi+1, centisecs);
	}
	
	/**
	 * calculate the time in milliseconds, given the position of the centiseconds, 
	 * and the previous time in centicseconds. 
	 * @param posi
	 * @param centisec
	 * @return
	 */
	private long getMillisFromCentisecond(int posi, long centisec) {
		try{
			centisec += this.get(posi);
		} catch(IndexOutOfBoundsException e) {
			
		}
		return centisec * 100;
	}
	
	
}
