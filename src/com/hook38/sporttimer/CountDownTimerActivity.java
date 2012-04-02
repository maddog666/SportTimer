package com.hook38.sporttimer;

import com.hook38.sporttimer.controller.Controller;
import com.hook38.sporttimer.controller.CountdownTimerController;
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

public class CountDownTimerActivity extends FragmentActivity implements OnClickListener {

	Controller controller;
	Button startButton;
	Button pauseButton;
	Button restartButton;
	Button addButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdowntimer);
        
        ClockView clockview_fragment = 
        		(ClockView) getSupportFragmentManager().findFragmentById(R.id.clock_fragment);
        ListView listview_fragment = 
        		(ListView) getSupportFragmentManager().findFragmentById(R.id.listview_fragment);
        controller = new CountdownTimerController(getBaseContext(), clockview_fragment, listview_fragment);
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
    		return true;
    	case R.id.timer:    		
    		break;
    	case R.id.exit:
    		finish();
    		return true;
    	}
    	return false;
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		controller.destroy();
	}
}
