package com.hook38.sporttimer.view;

import java.util.List;

import com.hook38.sporttimer.CountDownTimerActivity;
import com.hook38.sporttimer.R;
import com.hook38.sporttimer.utils.DoubleTextListAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemLongClickListener;

public class InteractiveListView extends ListView {
	private String TAG = "InteractiveListView";
	ArrayAdapter<String> adapter;
	Spinner routine_spinner;
	Vibrator vibrator;
	
	private Vibrator getVibrator() {
		if(this.vibrator == null) {
			this.vibrator = (Vibrator)getActivity().getSystemService(Context.VIBRATOR_SERVICE);
		}
		return this.vibrator;
	}
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.interactive_listview_fragment, container, false);		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		routine_spinner = (Spinner)getActivity().findViewById(R.id.routine_spinner);
		routine_spinner.setOnItemSelectedListener(new SpinnerOnItemSelectedListener());
		
		Button addTimeButton = (Button)getActivity().findViewById(R.id.add_time_button);
		addTimeButton.setOnClickListener(new ButtonOnClickListener());
		Button addButton = (Button)getActivity().findViewById(R.id.add_routine_button);
		addButton.setOnClickListener(new ButtonOnClickListener());
		Button editButton = (Button)getActivity().findViewById(R.id.edit_routine_button);
		editButton.setOnClickListener(new ButtonOnClickListener());
		Button removeButton = (Button)getActivity().findViewById(R.id.remove_routine_button);
		removeButton.setOnClickListener(new ButtonOnClickListener());
		getListView().setOnItemLongClickListener(new ListOnItemLongClickListener());
	}
	
	private class ButtonOnClickListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			getVibrator().vibrate(
					getResources().getInteger(R.integer.vibrate_length));
			switch(v.getId()){
			case R.id.add_time_button:
				((CountDownTimerActivity)getActivity())
    			.getController()
    			.addTime();
				break;
			case R.id.add_routine_button:
				((CountDownTimerActivity)getActivity())
    			.getController()
    			.addRoutineButtonClicked();
				break;
			case R.id.edit_routine_button:
				((CountDownTimerActivity)getActivity())
    			.getController()
    			.editRoutineButtonClicked(getSelectedRoutine());
				break;
			case R.id.remove_routine_button:
				if(adapter.getCount() > 1) {	
					//remove routine from database
					String selected = getSelectedRoutine();
					adapter.remove(selected);
					((CountDownTimerActivity)getActivity())
	    			.getController()
	    			.removeRoutineButtonClicked(selected);
					
				}
				break;
			}
		}
		
	}
	
	/**
	 * Manually select an spinner, given the item name.
	 * @param name name of the item.
	 */
	public void selectSpinner(String name) {
		int i = adapter.getPosition(name);
		routine_spinner.setSelection(i);
	}
	
	
	/**
	 * Return the selected spinner
	 * @return Name of the selected spinner
	 */
	public String getSelectedRoutine() {
		try {
			return routine_spinner.getSelectedItem().toString();
		} catch (IndexOutOfBoundsException e) {
			
			return routine_spinner.getItemAtPosition(0).toString();
		}
	}
	
	private class ListOnItemLongClickListener implements OnItemLongClickListener {
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			final CharSequence[] items = {"Edit", "Delete"};
			final int selected = arg2;

			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("Choose an action");
			builder.setItems(items, new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int item) {
			    	switch(item){
			    	case 0: //edit
			    		((CountDownTimerActivity)getActivity())
		    			.getController()
		    			.editTime(selected);
			    		break;	
			    	case 1: //delete
			    		((CountDownTimerActivity)getActivity())
			    			.getController()
			    			.removeTime(selected);
			    		break;
			    	default:
			    		break;
			    	}
			    }
			});
			AlertDialog alert = builder.create();
			alert.show();
			return false;
		}
	}
	
	
	private class SpinnerOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			((CountDownTimerActivity)getActivity())
			.getController()
			.loadRoutine(getSelectedRoutine());			
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void populateSpinner(List<String> list) {
		adapter = new ArrayAdapter<String>(getActivity(), 
						android.R.layout.simple_spinner_item, 
						list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		routine_spinner = (Spinner)getActivity().findViewById(R.id.routine_spinner);
		routine_spinner.setAdapter(adapter);
		
	}
	
	@Override
	public void populateList(List<String> list) {		
		DoubleTextListAdapter myListAdapter = 
				new DoubleTextListAdapter(getActivity(), list) {
			@Override
			public String getPositionCount(int position) {
				return Integer.toString(position + 1);
			}
		};
				
		setListAdapter(myListAdapter);
	}
	
}
