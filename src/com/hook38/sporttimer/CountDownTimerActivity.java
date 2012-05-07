package com.hook38.sporttimer;

import com.hook38.sporttimer.controller.CountdownTimerController;
import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.InteractiveListView;
import com.hook38.sporttimer.view.ListView;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
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
        Button restartButton = (Button)findViewById(R.id.restart_button);
        restartButton.setOnClickListener(this);
        Button addButton = (Button)findViewById(R.id.add_button);
        addButton.setOnClickListener(this);
        Button saveButton = (Button)findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);
        Button loadButton = (Button)findViewById(R.id.load_button);
        loadButton.setOnClickListener(this);
        
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
		
		case R.id.add_button:
			getController().addTime();
			break;
		case R.id.start_button:
			getController().startButtonClicked();
			break;
		case R.id.pause_button:
			getController().pauseButtonClicked();
			break;
		case R.id.save_button:
			getController().saveRoutineButtonClicked();
			break;
		case R.id.load_button:
			getController().loadRoutineButtonClicked();
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
