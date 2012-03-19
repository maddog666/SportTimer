package com.hook38.sporttimer.controller;

import android.os.Handler;

import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.ListView;

public class TimerController extends Controller {
	//time which timer start
	private long startTime;
	//time which timer paused
	private long pauseTime;
	//total summed paused time (paused time - restart time)
	private long pausedTime;
	private Handler handler = new Handler();
	
	public TimerController(ClockView clockView, ListView listview) {
		super(clockView, listview);
		// TODO Auto-generated constructor stub
	}
	
	public void add() {
		String time = getTimeString();
		listView.addItem(time);
	}
	
	public void start() {
		reset();
		startTime = System.currentTimeMillis();
		handler.postDelayed(Timer, 0);
	}
	
	public void restart() {
		pausedTime = System.currentTimeMillis() - pauseTime;
		handler.postDelayed(Timer, 10);
	}
	
	public void pause() {
		pauseTime = System.currentTimeMillis();
		handler.removeCallbacks(Timer);
	}
	
	public void reset() {
		handler.removeCallbacks(Timer);
		pausedTime = 0;
		setTime(0, 0, 0);
		listView.clearItems();
	}
	
	private Runnable Timer = new Runnable() {
		//private Handler handler = new Handler();		
		public void run() {
			long spentTime = System.currentTimeMillis() - startTime - pausedTime;			
			long mins = spentTime/(1000 * 60);
			long secs = (spentTime/1000) % 60;
			long centisecs = (spentTime/10) % 100;
			setTime(mins, secs, centisecs);
			handler.postDelayed(this, 10);
		}
	};

}
