package com.hook38.sporttimer.view;
import com.hook38.sporttimer.R;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ClockView extends Fragment {
	/*
	TextView hourText;
	TextView minText;
	TextView secText;
	TextView centisecText;*/
	
	private ImageView hourView;
	private ImageView minLeftView;
	private ImageView minRightView;
	private ImageView secLeftView;
	private ImageView secRightView;
	private ImageView centisecView;
	
	private Drawable one;
	private Drawable two;
	private Drawable three;
	private Drawable four;
	private Drawable five;
	private Drawable six;
	private Drawable seven;
	private Drawable eight;
	private Drawable nine;
	private Drawable zero;
	
	private static String DIGIT_FILE_NAME = "ic_menu_digit";
	private static String TAG = "ClockView";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
	}
	
	private Drawable getDigit(char i){
		switch(i){
		case '1':			
			if(one == null){
				one = getResources().getDrawable(
					this.getDigitFile(
						this.getFileFromDigit('1')));
			
			}
			return one;
		case '2':			
			if(two == null){
				two = getResources().getDrawable(
					this.getDigitFile(
						this.getFileFromDigit('2')));
			
			}
			return two;
			
		case '3':			
			if(three == null){
				three = getResources().getDrawable(
					this.getDigitFile(
						this.getFileFromDigit('3')));
			
			}
			return three;
		case '4':			
			if(four == null){
				four = getResources().getDrawable(
					this.getDigitFile(
						this.getFileFromDigit('4')));
			
			}
			return four;
		case '5':			
			if(five == null){
				five = getResources().getDrawable(
					this.getDigitFile(
						this.getFileFromDigit('5')));
			
			}
			return five;
		case '6':			
			if(six == null){
				six = getResources().getDrawable(
					this.getDigitFile(
						this.getFileFromDigit('6')));
			
			}
			return six;
		case '7':			
			if(seven == null){
				seven = getResources().getDrawable(
					this.getDigitFile(
						this.getFileFromDigit('7')));
			
			}
			return seven;
		case '8':			
			if(eight == null){
				eight = getResources().getDrawable(
					this.getDigitFile(
						this.getFileFromDigit('8')));
			
			}
			return eight;
		case '9':			
			if(nine == null){
				nine = getResources().getDrawable(
					this.getDigitFile(
						this.getFileFromDigit('9')));
			
			}
			return nine;
		default:			
			if(zero == null){
				zero = getResources().getDrawable(
						this.getDigitFile(
								this.getFileFromDigit('0')));
				
			}
			return zero;
		}		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		hourView = (ImageView)getView().findViewById(R.id.hour_clock_imageview);
		minLeftView = (ImageView)getView().findViewById(R.id.min_left_clock_imageview);
		minRightView = (ImageView)getView().findViewById(R.id.min_right_clock_imageview);
		secLeftView = (ImageView)getView().findViewById(R.id.sec_left_clock_imageview);
		secRightView = (ImageView)getView().findViewById(R.id.sec_right_clock_imageview);
		centisecView = (ImageView)getView().findViewById(R.id.centisec_clock_imageview);
		/*
		hourText = (TextView) (TextView)getView().findViewById(R.id.hour_textview);
		minText = (TextView) (TextView)getView().findViewById(R.id.min_textview);
		secText = (TextView) (TextView)getView().findViewById(R.id.sec_textview);
		centisecText = (TextView) (TextView)getView().findViewById(R.id.centisec_textview);
		*/
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.clockview_fragment, container, false);			
	}
	
	public void setHourTime(String hour) {
		//this.hourText.setText(hourTime);
		hourView.setImageDrawable(
				this.getDigit(hour.charAt(
					hour.length()-1)));
	}
	
	public void setMinTime(String min) {
		//this.minText.setText(min);
		minLeftView.setImageDrawable(
				this.getDigit(min.charAt(0)));		
		
		minRightView.setImageDrawable(
				this.getDigit(min.charAt(1)));
	}
	
	public void setSecTime(String sec) {
		//this.secText.setText(sec);
		secLeftView.setImageDrawable(
				this.getDigit(sec.charAt(0)));		
		
		secRightView.setImageDrawable(
				this.getDigit(sec.charAt(1)));
	}
	
	public void setCentisecTime(String centisec) {
		centisecView.setImageDrawable(
				this.getDigit(centisec.charAt(
					centisec.length()-1)));
	}
	
	private String getFileFromDigit(char i){
		return ClockView.DIGIT_FILE_NAME+i;
	}
	
	private int getDigitFile (String fileName) {
		return getResources().getIdentifier(fileName, "drawable", getActivity().getPackageName());		
	}
	
}
