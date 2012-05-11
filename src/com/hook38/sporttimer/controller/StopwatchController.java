package com.hook38.sporttimer.controller;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.ListView;

public class StopwatchController extends ActivityController {
	
	private enum Status {STOPPED, 
		PAUSED, 
		STARTED};	
		
	private Status status = Status.STOPPED;
	//time which timer start
	private long startTime;
	//time which timer paused
	private long pauseTime;
	//total summed paused time (paused time - restart time)
	private long pausedTime;
	private Handler handler = new Handler();

	@Override
	public void initiate() {
		
	}
	
	@Override
	public void close() {
		if(handler != null) {
			handler.removeCallbacks(Timer);
		}
	}
	
	public StopwatchController(/*Context context*/Activity activity, ClockView clockView, ListView listview) {
		super(/*context*/activity, clockView, listview);
		// TODO Auto-generated constructor stub
	}
	
	private void add() {
		String time = getTimeString();
		this.addItem(time);
	}
	
	private void start() {
		reset();
		startTime = System.currentTimeMillis();
		handler.postDelayed(Timer, 0);
	}
	
	private void restart() {
		pausedTime = System.currentTimeMillis() - pauseTime;
		handler.postDelayed(Timer, 10);
	}
	
	private void pause() {
		pauseTime = System.currentTimeMillis();
		handler.removeCallbacks(Timer);
	}
	
	private void reset() {
		handler.removeCallbacks(Timer);
		pausedTime = 0;
		setTime(0, 0, 0, 0);
		this.clearItems();
	}
	
	public void startButtonClicked() {
		switch(status){
		case STOPPED:
			this.start();								
			break;
		case PAUSED:				
			this.restart();				
			break;
		case STARTED:				
			break;
		
		}
		status = Status.STARTED;
	}
	
	public void pauseButtonClicked() {
		switch(status){
		case STOPPED:
			break;
		case PAUSED:
			break;
		case STARTED:				
			this.pause();				
			break;
		}
		status = Status.PAUSED;
	}
	
	public void addButtonClicked() {
		switch(status){
		case STOPPED:
			break;
		case PAUSED:
			break;
		case STARTED:	
			this.add();
			break;
		}
	}
	
	public void resetButtonClicked() {
		this.reset();
		status = Status.STOPPED;
	}
	
	
	private Runnable Timer = new Runnable() {
		//private Handler handler = new Handler();		
		public void run() {
			long spentTime = System.currentTimeMillis() - startTime - pausedTime;		
			long hours = spentTime/(1000 * 60 * 60);
			long mins = spentTime/(1000 * 60) % 60;
			long secs = (spentTime/1000) % 60;
			long centisecs = (spentTime/10) % 10;
			setTime(hours, mins, secs, centisecs);
			handler.postDelayed(this, 10);
		}
	};

}
