package com.hook38.sporttimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
public class TimeInputActivity extends Activity implements OnClickListener {
	EditText hourInput;
	EditText minuteInput;
	EditText secondInput;
	private int posi = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_input);
        Intent sender=getIntent();
        
        
        hourInput = (EditText)findViewById(R.id.time_input_left);
        minuteInput = (EditText)findViewById(R.id.time_input_middle);
        secondInput = (EditText)findViewById(R.id.time_input_right);
        try{
        	//set the time input fields for edit
        	hourInput.setText(sender.getExtras().getString("hour"));
        	minuteInput.setText(sender.getExtras().getString("minute"));
        	secondInput.setText(sender.getExtras().getString("second"));
        	posi = sender.getExtras().getInt("posi");
        }catch(NullPointerException e){
        	
        }
        
        Button button = (Button)findViewById(R.id.time_input_button);
        button.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case(R.id.time_input_button):
			Intent intent=new Intent();
	    	intent.putExtra("hour", hourInput.getText().toString());
	    	intent.putExtra("minute", minuteInput.getText().toString());
	    	intent.putExtra("second", secondInput.getText().toString());
	    	intent.putExtra("posi", posi);
	    	setResult(RESULT_OK, intent);
	    	finish();
			
		}
		
	}
}
