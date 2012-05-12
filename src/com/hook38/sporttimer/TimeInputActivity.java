package com.hook38.sporttimer;

import java.text.NumberFormat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
public class TimeInputActivity extends Activity implements OnClickListener {
	private EditText hourInput, minuteInput, secondInput;	
	private int hour, min, sec;
	private ImageView hourImage, minLeftImage, minRightImage, secLeftImage,
		secRightImage;

	private static String DIGIT_FILE_NAME = "digit";
	
	private static int HOUR_MAX = 9;
	private static int HOUR_MIN = 0;
	private static int MIN_MAX = 59;
	private static int MIN_MIN = 0;
	private static int SEC_MAX = 59;
	private static int SEC_MIN = 0;
	
	private int posi = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_input);
        Intent sender=getIntent();
        
        hourImage = (ImageView)findViewById(R.id.time_input_hour_image);
        minLeftImage= (ImageView)findViewById(R.id.time_input_left_min_image);
        minRightImage= (ImageView)findViewById(R.id.time_input_right_min_image);
        secLeftImage= (ImageView)findViewById(R.id.time_input_left_sec_image);
        secRightImage= (ImageView)findViewById(R.id.time_input_right_sec_image);
        
        ImageView hourUpImage = (ImageView)findViewById(R.id.time_input_hour_plus_image);
        hourUpImage.setOnClickListener(this);
        ImageView hourDownImage = (ImageView)findViewById(R.id.time_input_hour_minus_image);
        hourDownImage.setOnClickListener(this);
        ImageView minUpImage = (ImageView)findViewById(R.id.time_input_min_plus_image);
        minUpImage.setOnClickListener(this);
        ImageView minDownImage = (ImageView)findViewById(R.id.time_input_min_minus_image);
        minDownImage.setOnClickListener(this);
        ImageView secUpImage = (ImageView)findViewById(R.id.time_input_sec_plus_image);
        secUpImage.setOnClickListener(this);
        ImageView secDownImage = (ImageView)findViewById(R.id.time_input_sec_minus_image);
        secDownImage.setOnClickListener(this);
        //hourInput = (EditText)findViewById(R.id.time_input_left);
        //minuteInput = (EditText)findViewById(R.id.time_input_middle);
        //secondInput = (EditText)findViewById(R.id.time_input_right);
        try{
        	//set the time input fields for edit
        	hour = Integer.parseInt(sender.getExtras().getString("hour"));
        	min = Integer.parseInt(sender.getExtras().getString("minute"));
        	sec = Integer.parseInt(sender.getExtras().getString("second"));
        	posi = sender.getExtras().getInt("posi");
        	
        	this.setHourImage();
        	this.setMinImage();
        	this.setSecImage();
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
	    	intent.putExtra("hour", Integer.toString(hour));
	    	intent.putExtra("minute", Integer.toString(min));
	    	intent.putExtra("second", Integer.toString(sec));
	    	intent.putExtra("posi", posi);
	    	setResult(RESULT_OK, intent);
	    	finish();
		case(R.id.time_input_hour_plus_image):
			this.increaseHour();
			break;
		case(R.id.time_input_hour_minus_image):
			this.decreaseHour();
			break;
		case(R.id.time_input_min_plus_image):
			this.increaseMin();
			break;
		case(R.id.time_input_min_minus_image):
			this.decreaseMin();
			break;
		case(R.id.time_input_sec_plus_image):
			this.increaseSec();
			break;
		case(R.id.time_input_sec_minus_image):
			this.decreaseSec();
			break;
		}
		
	}
	
	private void increaseHour() {
		if(hour < HOUR_MAX) {
			hour++;
			this.setHourImage();
		}
	}
	
	private void decreaseHour() {
		if(hour > HOUR_MIN) {
			hour--;		
			this.setHourImage();
		}
	}
	
	private void increaseMin() {
		if(min < MIN_MAX) {
			min++;
		} else {
			min = MIN_MIN;
			this.increaseHour();
		}
		this.setMinImage();
	}
	
	private void decreaseMin() {
		if(min > MIN_MIN) {
			min--;
			this.setMinImage();
		}
	}
	
	private void increaseSec() {
		if(sec < SEC_MAX) {
			sec++;
		} else {
			this.increaseMin();
			sec = SEC_MIN;
		}
		this.setSecImage();
	}
	
	private void decreaseSec() {
		if(sec > SEC_MIN) {
			sec--;
			this.setSecImage();
		}
	}
	
	
	private void setHourImage() {
		String hourString = Integer.toString(hour);
		hourImage.setImageDrawable(
				this.getDigitDrawable(hourString.charAt(0)));
	}
	
	private void setMinImage() {
		NumberFormat df = NumberFormat.getInstance();
		df.setMinimumIntegerDigits(2);
		String minString = String.valueOf(df.format(min));

		minLeftImage.setImageDrawable(
			this.getDigitDrawable(minString.charAt(0)));
	
		minRightImage.setImageDrawable(
			this.getDigitDrawable(minString.charAt(1)));

	}
	
	private void setSecImage() {
		NumberFormat df = NumberFormat.getInstance();
		df.setMinimumIntegerDigits(2);
		String secString = String.valueOf(df.format(sec));

		secLeftImage.setImageDrawable(
				this.getDigitDrawable(secString.charAt(0)));
	
		secRightImage.setImageDrawable(
			this.getDigitDrawable(secString.charAt(1)));

	}
	
	private String getFileFromDigit(char i){
		return DIGIT_FILE_NAME+i;
	}
	
	private int getDigitFile (String fileName) {
		return getResources().getIdentifier(fileName, "drawable", getPackageName());		
	}
	
	private Drawable getDigitDrawable(char i) {
		int id = this.getDigitFile(this.getFileFromDigit(i));
		return getResources().getDrawable(id);
	}
}
