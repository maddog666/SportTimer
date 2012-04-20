package com.hook38.sporttimer.view;

import com.hook38.sporttimer.R;
import com.hook38.sporttimer.utils.InputNumberFilterMinMax;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TimeInputView extends Fragment {

	EditText leftInput;
	EditText middleInput;
	EditText rightInput; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		leftInput = (EditText) getActivity().findViewById(R.id.time_input_left);
		leftInput.setFilters(new InputFilter[]{ new InputNumberFilterMinMax("0", "24")});
		middleInput = (EditText) getActivity().findViewById(R.id.time_input_middle);
		middleInput.setFilters(new InputFilter[]{ new InputNumberFilterMinMax("0", "60")});
		rightInput = (EditText) getActivity().findViewById(R.id.time_input_right);
		rightInput.setFilters(new InputFilter[]{ new InputNumberFilterMinMax("0", "60")});
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.time_input_fragment, container, false);			
	}
	
	public String getHour() {
		return leftInput.getText().toString();
	}
	
	public String getMinute() {
		return middleInput.getText().toString();
	}
	
	public String getSecond() {
		return rightInput.getText().toString();
	}
	
	public void setHour(String text){
		leftInput.setText(text);
	}
	
	public void setMinute(String text){
		middleInput.setText(text);
	}
	
	public void setSecond(String text){
		rightInput.setText(text);
	}
	
	public void clear() {
		setHour("0");
		setMinute("0");
		setSecond("0");
	}
}
