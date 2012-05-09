package com.hook38.sporttimer;

import com.hook38.sporttimer.controller.CountdownTimerController;
import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.InteractiveListView;
import com.hook38.sporttimer.view.ListView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * This is the main activity class for the countdown clock,
 * The purpose of this app is to beep at the end of a count down
 * user should be able to program a set of routine prior to 
 * the countdown.
 * @author williamhu
 *
 */
public class CountDownTimerActivity extends SportTimerActivity implements OnClickListener {
	private String TAG = "CountDownTimerActivity";
	public static final String PREFS_NAME = "MyPrefsFile";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdowntimer);
        
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        //retrieve building block fragments
        ClockView clockview_fragment = 
        		(ClockView) getSupportFragmentManager().findFragmentById(R.id.clock_fragment);
        InteractiveListView listview_fragment = 
        		(InteractiveListView) getSupportFragmentManager().findFragmentById(R.id.listview_fragment);

        controller = new CountdownTimerController(this, 
        											clockview_fragment, 
        											listview_fragment);
        
        controller.initiate();
        
        
        Button startButton = (Button)findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button pauseButton = (Button)findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(this);
        Button stopButton = (Button)findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);

        
        this.initiate(savedInstanceState);
	}
	
	private void initiate(Bundle savedInstanceState) {
		if(savedInstanceState != null) {
			this.getController().selectRoutine(savedInstanceState.getString("selectedRoutine"));
		}
	}
	
	@Override
	public void onResume(){
		super.onResume();
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		String selectedRoutine =
				settings.getString(getString(R.string.selected_routine_key), null);
		if(selectedRoutine != null) {
			this.getController().selectRoutine(selectedRoutine);
		}
	}
	
	@Override
	public void onPause(){
		super.onPause();
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(getString(R.string.selected_routine_key), 
				this.getController().getSelectedRoutine());
		editor.commit();
	}
	
	
	@Override
    public void onActivityResult(int requestCode,int resultCode,Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			getController().handleInput(requestCode, data);
		}
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();        
		inflater.inflate(R.menu.primary_menu, menu);		
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	super.onOptionsItemSelected(item);
    	switch(item.getItemId()) {
    	case R.id.stopwatch:
    		startActivity(new Intent(this, StopwatchActivity.class));
    		finish();
    		return true;
    	case R.id.timer:    		
    		break;
    	case R.id.setting:
    		startActivity(new Intent(this, SettingActivity.class));
    		return true;  
    	case R.id.exit:
    		finish();
    		return true;
    	}
    	return false;
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()) {
		case R.id.start_button:
			getController().startButtonClicked();
			break;
		case R.id.pause_button:
			getController().pauseButtonClicked();
			break;
		case R.id.stop_button:
			getController().stopButtonClicked();
			break;
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		controller.close();
	}
	
	public CountdownTimerController getController() {
		return (CountdownTimerController)this.controller;
	}
	

}
