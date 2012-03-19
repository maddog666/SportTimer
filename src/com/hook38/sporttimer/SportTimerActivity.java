package com.hook38.sporttimer;

import com.hook38.sporttimer.controller.Controller;
import com.hook38.sporttimer.controller.TimerController;
import com.hook38.sporttimer.view.ClockView;
import com.hook38.sporttimer.view.ListView;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SportTimerActivity extends FragmentActivity implements OnClickListener {
	private enum Status {STOPPED, 
						PAUSED, 
						STARTED};
						
	private TimerController controller;
	private Button startButton;
	private Button resetButton;
	private Button pauseButton;
	private Button addButton;
	
	private Status status = Status.STOPPED;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ClockView clockview_fragment = 
        		(ClockView) getSupportFragmentManager().findFragmentById(R.id.clock_fragment);
        ListView listview_fragment = 
        		(ListView) getSupportFragmentManager().findFragmentById(R.id.listview_fragment);
        controller = new TimerController(clockview_fragment, listview_fragment);
        startButton = (Button)findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        pauseButton = (Button)findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(this);
        resetButton = (Button)findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
        addButton = (Button)findViewById(R.id.add_button);
        addButton.setOnClickListener(this);
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
}