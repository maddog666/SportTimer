package com.hook38.sporttimer.view;
import com.hook38.sporttimer.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ClockView extends Fragment {
	TextView hourText;
	TextView minText;
	TextView secText;
	TextView centisecText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		hourText = (TextView) (TextView)getView().findViewById(R.id.hour_textview);
		minText = (TextView) (TextView)getView().findViewById(R.id.min_textview);
		secText = (TextView) (TextView)getView().findViewById(R.id.sec_textview);
		centisecText = (TextView) (TextView)getView().findViewById(R.id.centisec_textview);
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.clockview_fragment, container, false);			
	}
	
	public void setHourTime(String hourTime) {
		this.hourText.setText(hourTime);
	}
	
	public void setMinTime(String min) {
		this.minText.setText(min);
	}
	
	public void setSecTime(String sec) {
		this.secText.setText(sec);
	}
	
	public void setCentisecTime(String sec) {
		this.centisecText.setText(sec);
	}
	
}
