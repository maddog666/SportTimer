package com.hook38.sporttimer.controller;

import android.app.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Toast;

import com.hook38.sporttimer.R;
import com.hook38.sporttimer.TimeInputActivity;
import com.hook38.sporttimer.model.CountdownTimerModel;
import com.hook38.sporttimer.utils.TimeUnits;
import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.ListView;

public class CountdownTimerController extends ActivityController {
	private static final String TAG = "CountdownTimer";
	private static final String ROUTINE_LIST = "RoutineList";
	private static final int ADD_CODE = 1;
	private static final int EDIT_CODE = 2;
	
	private MediaPlayer mp;
	
	//time which timer start
	private long startTime;
	//time which timer paused
	private long pauseTime;
	//total summed paused time (paused time - restart time)
	private long pausedTime;
	private long stopTime;
	
	private Handler handler = new Handler(); 
	private CountdownTimerModel timerModel;
	
	public CountdownTimerController(Activity activity, ClockView clockView, ListView listView) {
		super(/*context*/activity, clockView, listView);
		// TODO Auto-generated constructor stub
		this.timerModel = new CountdownTimerModel();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public void handleTimeInput(int requestCode, Intent data) {
		TimeUnits units = new TimeUnits();
		units.add(data.getStringExtra("hour"));
		units.add(data.getStringExtra("minute"));
		units.add(data.getStringExtra("second"));
		switch(requestCode){
		case(CountdownTimerController.ADD_CODE):
			//handle add time
			this.addTime(units);
			break;
		case(CountdownTimerController.EDIT_CODE):			
			//handle edit time
			this.editTime(data.getExtras().getInt("posi"), units);
			break;
		}
			
	}

	public void startTimer() {		
		startTimerThread(0);
	}
	
	private void startTimerThread(int posi){
		final int currentPosi = posi;
		try{
			TimeUnits time = timerModel.get(posi);
			long millis = time.getMillisFromHour(0);
			
			new CountDownTimer(millis, 100) {
			     public void onTick(long millis) {
			    	 long hours = millis/(1000 * 60 * 60);
						long mins = millis/(1000 * 60);
						long secs = (millis/1000) % 60;
						//long centisecs = (millis/10) % 100;
						setTime(hours, mins, secs);
			     }
	
			     public void onFinish() {
			    	 setTime(0, 0, 0);
			    	 int resid = R.raw.beep;
			    	 if(mp!=null) {
			    		 mp.release();
			    	 }
			    	 mp = MediaPlayer.create(getActivity(), resid);
			    	 mp.start();
			    	 startTimerThread(currentPosi + 1);
			     }
			}.start();
		}catch(IndexOutOfBoundsException e) {
			
		}
	}

	
	
	/**
	 * Update the listView, that reflect the new routines. 
	 */
	private void updateView() {
		this.listView.populateList(timerModel.toStringList());
	}
	
	/**
	 * This initiate the activity, which allow user to add an time.
	 */
	public void addTime() {
		Intent i = new Intent(getActivity(), TimeInputActivity.class);
		getActivity().startActivityForResult(i, CountdownTimerController.ADD_CODE);
	}
	
	/**
	 * Return the size of the current timers in the timer model
	 * @return size of the current timer
	 */
	public int timerSize() {
		return timerModel.size();
	}
	
	/**
	 * Add the user defined time into the timer model
	 * @param units
	 */
	public void addTime(TimeUnits units) {
		if(!units.hasNoTime()){
			timerModel.add(units);
			this.updateView();
		}else{
			Toast.makeText(this.getActivity().getApplicationContext(), 
					this.getActivity().getString(R.string.no_time_warning), 
					Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * Get the time in timer model given the 
	 * @param posi position of the list
	 * @return TimeUnits at the position
	 */
	public TimeUnits getTime(int posi) {
		return timerModel.get(posi);
	}
	
	/**
	 * Remove the time at the defined position in the timer model
	 * @param posi
	 */
	public void removeTime(int posi) {
		timerModel.remove(posi);
		this.updateView();
	}

	/**
	 * This is the method which start up the activity that allow user to make modification
	 * of a prestored time.
	 * @param posi position of the time that want to be modify
	 */
	public void editTime(int posi) {
		Intent i = new Intent(getActivity(), TimeInputActivity.class);
		TimeUnits units = this.getTime(posi);
		i.putExtra("hour", Integer.toString(
								units.get(0)));
		i.putExtra("minute", Integer.toString(
								units.get(1)));
		i.putExtra("second", Integer.toString(
								units.get(2)));
		i.putExtra("posi", posi);
		getActivity().startActivityForResult(i, CountdownTimerController.EDIT_CODE);
	}
	
	/**
	 * This is how the controller handle edit time internally
	 * @param posi
	 * @param units
	 */
	private void editTime(int posi, TimeUnits units) {
		if(!units.hasNoTime()) {
			timerModel.set(posi, units);
			this.updateView();
		}else{
			Toast.makeText(this.getActivity().getApplicationContext(), 
				this.getActivity().getString(R.string.no_time_warning), 
				Toast.LENGTH_SHORT).show();
		}
	}
	
}
