package com.hook38.sporttimer.controller;
import java.util.List;

import android.app.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.hook38.sporttimer.R;
import com.hook38.sporttimer.TextInputActivity;
import com.hook38.sporttimer.TimeInputActivity;
import com.hook38.sporttimer.model.CountdownTimerModel;
import com.hook38.sporttimer.utils.TimeUnits;
import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.InteractiveListView;
import com.hook38.sporttimer.view.ListView;

public class CountdownTimerController extends ActivityController{
	private static final String TAG = "CountdownTimer";
	private static final String ROUTINE_LIST = "RoutineList";
	private static final int TIME_ADD_CODE = 1;
	private static final int TIME_EDIT_CODE = 2;
	private static final int TEXT_ADD_CODE = 4;
	private static final int TEXT_EDIT_CODE = 5;
	private static int posi = 0;
	private static CountdownTimerStoreController storeController;
	
	private enum Status {STOPPED, 
		PAUSED, 
		STARTED};
		
	private Status status = Status.STOPPED;
	
	private MediaPlayer mp;
	
	//time which timer start
	private long startTime;
	//time which timer paused
	private long pauseTime;
	//total summed paused time (paused time - restart time)
	private long pausedTime = 0;
	private long stopTime;
	//time left on the countdown clock
	private long countdownTime;
	private Handler handler = new Handler(); 
	private CountdownTimerModel timerModel;
	
	public CountdownTimerController(Activity activity, ClockView clockView, InteractiveListView listView) {
		super(/*context*/activity, clockView, listView);
		// TODO Auto-generated constructor stub
		this.timerModel = new CountdownTimerModel();
		storeController  = new CountdownTimerStoreController(activity);
	}
	
	@Override
	public void initiate() {
		this.loadRoutineSpinner();
	}
	
	private void loadRoutineSpinner() {
		Log.d("CountdownTimerController", "loadRoutineSpinner");
		List<String> list = storeController.getRoutines();
		((InteractiveListView)listView).populateSpinner(list);
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		storeController.close();
	}
	
	private void addRoutine(String name) {
		storeController.storeRoutine(name);
	}
	
	public void removeRoutine(String name) {
		storeController.deleteRoutine(name);
	}
	
	public void saveRoutine(String routineName) {
		Log.d("CountdownTimerController", "saveRoutine: "+routineName);
		storeController.storeTimerModel(timerModel, routineName);
	}
	
	private void editRoutine(String oldName, String newName) {
		storeController.editRoutine(oldName, newName);
	}
	
	public void saveRoutineButtonClicked() {
		this.saveRoutine(((InteractiveListView)listView).getSelectedRoutine());
		
	}
	
	public void loadRoutineButtonClicked() {
		this.loadRoutine(((InteractiveListView)listView).getSelectedRoutine());
	}
	
	public void loadRoutine(String routineName) {
		Log.d("CountdownTimerController", "loadRoutine: "+routineName);
		timerModel = storeController.retrieveTimerModel(routineName);
		listView.populateList(timerModel.toStringList());
	}
	
	public void handleInput(int requestCode, Intent data) {
		if(requestCode == CountdownTimerController.TIME_ADD_CODE ||
				requestCode == CountdownTimerController.TIME_EDIT_CODE) {
			//handle time input
			TimeUnits units = new TimeUnits();
			units.add(data.getStringExtra("hour"));
			units.add(data.getStringExtra("minute"));
			units.add(data.getStringExtra("second"));
			switch(requestCode){
			case(CountdownTimerController.TIME_ADD_CODE):
				//handle add time
				this.addTime(units);
				break;
			case(CountdownTimerController.TIME_EDIT_CODE):			
				//handle edit time
				this.editTime(data.getExtras().getInt("posi"), units);
				break;
			}
		}else{
			//handle text input
			String text = data.getStringExtra("text");
			if(text == null || text.length() < 1) {
				Toast.makeText(this.getActivity().getApplicationContext(), 
						this.getActivity().getString(R.string.no_text_warning), 
						Toast.LENGTH_SHORT).show();
			}
			switch(requestCode) {
			case(CountdownTimerController.TEXT_ADD_CODE):
				this.addRoutine(text);
				//focus to the added routine
				this.loadListView(text);
				break;
			case(CountdownTimerController.TEXT_EDIT_CODE):
				String originalText = data.getStringExtra("originaltext");
				this.editRoutine(originalText, text);
				this.loadListView(text);
				break;
			}
		}
			
	}
	
	/**
	 * Given the name of the intentional select routine item, reload the routine spinner, 
	 * select the routine item, and load the routine time units from the listview.
	 * @param text name of the intentional selection routine.
	 */
	private void loadListView(String text) {
		this.loadRoutineSpinner();
		((InteractiveListView)listView).selectSpinner(text);
		this.loadRoutine();
	}
	
	
	public void addRoutineButtonClicked() {
		Intent i = new Intent(getActivity(), TextInputActivity.class);
		getActivity().startActivityForResult(i, CountdownTimerController.TEXT_ADD_CODE);
	}
	
	public void editRoutineButtonClicked(String originalName) {
		Intent i = new Intent(getActivity(), TextInputActivity.class);
		i.putExtra("text", originalName);
		getActivity().startActivityForResult(i, CountdownTimerController.TEXT_EDIT_CODE);
	}
	
	public void removeRoutineButtonClicked(String name) {
		this.removeRoutine(name);
		this.loadRoutine();
	}

	public void startButtonClicked() {	
		switch(status) {
		case STOPPED:			
			this.posi = 0;
			startTimer(posi);
		}
	}
	
	private void startTimer(int posi) {
		try{		
			startTime = System.currentTimeMillis();
			TimeUnits time = timerModel.get(posi);
			countdownTime = time.getMillisFromHour(0);
			Countdown countdown = new Countdown();
			status = Status.STARTED;
			handler.post(countdown);
		} catch (IndexOutOfBoundsException e) {
			
		}
	}
	
	public void pauseButtonClicked() {
		
	}
	

	
	private class Countdown implements Runnable {
		//private Handler handler = new Handler();		
		public void run() {
			long millis = countdownTime - (System.currentTimeMillis() - startTime) + pausedTime;
			if(millis <=0) {
				onFinish();
				return;
			}
			long hours = millis/(1000 * 60 * 60);
			long mins = millis/(1000 * 60);
			long secs = (millis/1000) % 60;
			//long centisecs = (countdownTime/10) % 100;
			setTime(hours, mins, secs);
			handler.postDelayed(this, 100);
		}
		
		public void onFinish(){
			setTime(0, 0, 0);
			beepSound();
			status = Status.STOPPED;
			handler.removeCallbacks(this);
			posi++;
			startTimer(posi);
		}
	};

	private void beepSound(){
		int resid = R.raw.beep;
   	 	if(mp!=null) {
   	 		mp.release();
   	 	}
   	 	mp = MediaPlayer.create(getActivity(), resid);
   	 	mp.start();  	 
	}
	
	/**
	 * Update the listView, that reflect the new routines. 
	 */
	private void updateView() {
		this.listView.populateList(timerModel.toStringList());
	}
	
	/**
	 * This initiate the input time activity, which allow user to input
	 *  a time.
	 */
	public void addTime() {
		Intent i = new Intent(getActivity(), TimeInputActivity.class);
		getActivity().startActivityForResult(i, CountdownTimerController.TIME_ADD_CODE);
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
		getActivity().startActivityForResult(i, CountdownTimerController.TIME_EDIT_CODE);
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
	
	/**
	 * Determine the currently selected item from the routine spinner, and update
	 * the routine list, given the routine selected.
	 */
	private void loadRoutine() {
		this.loadRoutine(((InteractiveListView)listView).getSelectedRoutine());
	}
	
}
