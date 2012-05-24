package com.hook38.sporttimer;

import java.text.NumberFormat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
public class TimeInputActivity extends Activity implements OnClickListener, 
		OnTouchListener, OnGestureListener{
	private int hour, min, sec;
	private ImageView hourImage, minLeftImage, minRightImage, secLeftImage,
		secRightImage;

	/**
	 * The name prefix of the digit graphic files.
	 */
	private static String DIGIT_FILE_NAME = "ic_menu_digit";
	
	/**
	 * The increment speed of timer on long press.
	 */
	private static long INCREMENTAL_SPEED = 100;
	
	/**
	 * Allowed maximum and minimum input time.
	 */
	private static int HOUR_MAX = 9;
	private static int HOUR_MIN = 0;
	private static int MIN_MAX = 59;
	private static int MIN_MIN = 0;
	private static int SEC_MAX = 59;
	private static int SEC_MIN = 0;
	
	private  GestureDetector mGestureDetector; 
	/**
	 * The item that is being clicked, currently only handle up or down 
	 * arrows ImageView
	 */
	private View touchItem;
	/**
	 * A runnable item for handling long press number incremental
	 */
	private Handler mHandler;
	/**
	 * The position of the TimeUnit in the timelist that is being modified.
	 */
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
        
        mGestureDetector =  new  GestureDetector(this);       
        mGestureDetector.setIsLongpressEnabled(true);        
        
        ImageView hourUpImage = (ImageView)findViewById(R.id.time_input_hour_plus_image);
        hourUpImage.setOnClickListener(this);
        hourUpImage.setOnTouchListener(this);
        ImageView hourDownImage = (ImageView)findViewById(R.id.time_input_hour_minus_image);
        hourDownImage.setOnClickListener(this);
        hourDownImage.setOnTouchListener(this);
        ImageView minUpImage = (ImageView)findViewById(R.id.time_input_min_plus_image);
        minUpImage.setOnClickListener(this);
        minUpImage.setOnTouchListener(this);
        ImageView minDownImage = (ImageView)findViewById(R.id.time_input_min_minus_image);
        minDownImage.setOnClickListener(this);
        minDownImage.setOnTouchListener(this);
        ImageView secUpImage = (ImageView)findViewById(R.id.time_input_sec_plus_image);
        secUpImage.setOnClickListener(this);
        secUpImage.setOnTouchListener(this);
        ImageView secDownImage = (ImageView)findViewById(R.id.time_input_sec_minus_image);
        secDownImage.setOnClickListener(this);
        secDownImage.setOnTouchListener(this);
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
	
	private boolean increaseHour() {
		if(hour < HOUR_MAX) {
			hour++;
			this.setHourImage();
			return true;
		}
		return false;
	}
	
	private boolean decreaseHour() {
		if(hour > HOUR_MIN) {
			hour--;		
			this.setHourImage();
			return true;
		}
		return false;
	}
	
	private boolean increaseMin() {
		if(min < MIN_MAX) {
			min++;			
		} else {			
			if(this.increaseHour()){
				min = MIN_MIN;				
			} else {
				return false;
			}
		}
		this.setMinImage();
		return true;
		
	}
	
	private boolean decreaseMin() {
		if(min > MIN_MIN) {
			min--;			
		} else {
			if(this.decreaseHour()) {
				min = MIN_MAX;
			} else{
				return false;
			}
		}
		this.setMinImage();
		return true;
	}
	
	private boolean increaseSec() {
		if(sec < SEC_MAX) {
			sec++;			
		} else {
			if(this.increaseMin()){
				sec = SEC_MIN;				
			}else{
				return false;
			}
		}
		this.setSecImage();
		return true;
		
	}
	
	private boolean decreaseSec() {
		if(sec > SEC_MIN) {
			sec--;			
		} else{
			if(this.decreaseMin()) {
				sec = SEC_MAX;
			}else{
				return false;
			}
		}
		this.setSecImage();
		return true;
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



	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		switch(touchItem.getId()){
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
		return true;
	}


	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}


	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		if(mHandler == null) {
			mHandler = new Handler();
		}
		mHandler.removeCallbacks(mRunnable);
		mHandler.post(mRunnable);
	}


	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}


	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}


	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			this.touchItem = v;
			return mGestureDetector.onTouchEvent(event);
		} else if(event.getAction() == MotionEvent.ACTION_UP){
			this.touchItem = null;
			//stop counting thread
			if(mHandler != null) {
				mHandler.removeCallbacks(mRunnable);
			}
			return true;
		} 
		return true;
	}

	private final Runnable mRunnable = new Runnable() {
		public void run() {
			if(touchItem != null) {
				switch(touchItem.getId()){
				case(R.id.time_input_hour_plus_image):
					increaseHour();
					break;
				case(R.id.time_input_hour_minus_image):
					decreaseHour();
					break;
				case(R.id.time_input_min_plus_image):
					increaseMin();
					break;
				case(R.id.time_input_min_minus_image):
					decreaseMin();
					break;
				case(R.id.time_input_sec_plus_image):
					increaseSec();
					break;
				case(R.id.time_input_sec_minus_image):
					decreaseSec();
					break;
				}
				mHandler.postDelayed(this, INCREMENTAL_SPEED);
			}
		}
	};

}
