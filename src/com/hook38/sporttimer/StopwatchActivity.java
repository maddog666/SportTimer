package com.hook38.sporttimer;

import com.hook38.sporttimer.controller.StopwatchController;
import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.ListView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StopwatchActivity extends FragmentActivity implements OnClickListener {
	private enum Status {STOPPED, 
						PAUSED, 
						STARTED};
						
	private StopwatchController controller;
	private Button startButton;
	private Button resetButton;
	private Button pauseButton;
	private Button addButton;
	private Menu menu;
	private Status status = Status.STOPPED;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch);
        ClockView clockview_fragment = 
        		(ClockView) getSupportFragmentManager().findFragmentById(R.id.clock_fragment);
        ListView listview_fragment = 
        		(ListView) getSupportFragmentManager().findFragmentById(R.id.listview_fragment);
        controller = new StopwatchController(clockview_fragment, listview_fragment);
        startButton = (Button)findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        pauseButton = (Button)findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(this);
        resetButton = (Button)findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
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
    		break;
    	case R.id.timer:
    		startActivity(new Intent(this, TimerActivity.class));
    		return true;    		
    	case R.id.exit:
    		finish();
    		return true;
    	}
    	return false;
    }
    
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.start_button:
			switch(status){
			case STOPPED:
				controller.start();								
				break;
			case PAUSED:				
				controller.restart();				
				break;
			case STARTED:				
				break;
			
			}
			status = Status.STARTED;
			break;
		case R.id.pause_button:
			switch(status){
			case STOPPED:
				break;
			case PAUSED:
				break;
			case STARTED:				
				controller.pause();				
				break;
			}
			status = Status.PAUSED;
			break;
		case R.id.add_button:
			switch(status){
			case STOPPED:
				break;
			case PAUSED:
				break;
			case STARTED:	
				controller.add();
				break;
			}
			break;
		case R.id.reset_button:			
			controller.reset();
			status = Status.STOPPED;
			break;
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		controller.destroy();
	}
}