package com.hook38.sporttimer;

import com.google.ads.AdView;
import com.hook38.sporttimer.controller.StopwatchController;
import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.ListView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * This is the main class for a stopwatch, just like any sport watch, 
 * it can start and pause, and can also record a set of current time.
 * @author williamhu
 *
 */
public class StopwatchActivity extends SportTimerActivity implements OnClickListener {						
	private static String TAG = "StopwatchActivity";
	private Menu menu;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch);
        ClockView clockview_fragment = 
        		(ClockView) getSupportFragmentManager().findFragmentById(R.id.clock_fragment);
        ListView listview_fragment = 
        		(ListView) getSupportFragmentManager().findFragmentById(R.id.listview_fragment);
        
        controller = new StopwatchController(/*getBaseContext()*/this, clockview_fragment, listview_fragment);
        this.configAdView();
        Button startButton = (Button)findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button pauseButton = (Button)findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(this);
        Button resetButton = (Button)findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
        Button addButton = (Button)findViewById(R.id.add_button);
        addButton.setOnClickListener(this);
        
        
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
    		break;
    	case R.id.timer:
    		startActivity(new Intent(this, CountDownTimerActivity.class));
    		finish();
    		return true;
    	/*
    	case R.id.setting:
    		startActivity(new Intent(this, SettingActivity.class));
    		return true;
    		*/
    	case R.id.help:
    		startActivity(new Intent(this, StopwatchHelpActivity.class));
    		return true;
    	case R.id.exit:
    		finish();
    		return true;
    	}
    	return false;
    }
    
	public void onClick(View view) {
		// TODO Auto-generated method stub
		this.getVibrator().vibrate(getResources().getInteger(R.integer.vibrate_length));
		switch (view.getId()) {
		case R.id.start_button:
			getController().startButtonClicked();
			break;
		case R.id.pause_button:
			getController().pauseButtonClicked();
			break;
		case R.id.add_button:
			getController().addButtonClicked();
			break;
		case R.id.reset_button:			
			getController().resetButtonClicked();
			break;
		}
	}
	
	
	@Override
	public void onStop() {		
		controller.close();
		super.onStop();
	}
	public StopwatchController getController(){
		return (StopwatchController)controller;
	}
	
	protected AdView getAdView() {
		return (AdView)this.findViewById(R.id.stopwatch_adview);
	}
	
}