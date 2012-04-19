package com.hook38.sporttimer.view;
import com.hook38.sporttimer.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ClockView extends Fragment {
	TextView leftText;
	TextView middleText;
	TextView rightText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		leftText = (TextView) (TextView)getView().findViewById(R.id.left_textview);
		middleText = (TextView) (TextView)getView().findViewById(R.id.middle_textview);
		rightText = (TextView) (TextView)getView().findViewById(R.id.right_textview);
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.clockview_fragment, container, false);			
	}
	
	public void setLeftTime(String leftTime) {
		this.leftText.setText(leftTime);
	}
	
	public void setMiddleTime(String midTime) {
		this.middleText.setText(midTime);
	}
	
	public void setRightTime(String rightTime) {
		this.rightText.setText(rightTime);
	}
	
	
}
