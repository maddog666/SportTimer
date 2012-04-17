package com.hook38.sporttimer;

import com.hook38.sporttimer.controller.ActivityController;
import com.hook38.sporttimer.controller.CountdownTimerController;
import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.ListView;
import com.hook38.sporttimer.view.TimeInputView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * This is the main activity class for the countdown clock,
 * The purpose of this app is to beep at the end of a count down
 * user should be able to program a set of routine prior to 
 * the countdown.
 * @author williamhu
 *
 */
public class CountDownTimerActivity extends FragmentActivity implements OnClickListener {

	CountdownTimerController controller;
	TimeInputView timeinputview_fragment;
	Button startButton;
	Button pauseButton;
	Button restartButton;
	Button addButton;
	EditText hourInput;
	EditText minuteInput;
	EditText secondInput;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdowntimer);
        
        //retrieve building block fragments
        ClockView clockview_fragment = 
        		(ClockView) getSupportFragmentManager().findFragmentById(R.id.clock_fragment);
        ListView listview_fragment = 
        		(ListView) getSupportFragmentManager().findFragmentById(R.id.listview_fragment);
        timeinputview_fragment = 
        		(TimeInputView) getSupportFragmentManager().findFragmentById(R.id.timer_input_fragment);
        
        controller = new CountdownTimerController(getBaseContext(), 
        											clockview_fragment, 
        											listview_fragment, 
        											timeinputview_fragment);
        
        
        startButton = (Button)findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        pauseButton = (Button)findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(this);
        restartButton = (Button)findViewById(R.id.restart_button);
        restartButton.setOnClickListener(this);
        addButton = (Button)findViewById(R.id.add_button);
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
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Time input");
			alertDialog.setMessage(controller.getInputHour()+" " + 
					controller.getInputMinute() + " " +
					controller.getInputSecond());
			alertDialog.show();
			break;
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		controller.destroy();
	}
}
