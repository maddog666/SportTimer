package com.hook38.sporttimer.view;


import java.util.List;

import com.hook38.sporttimer.R;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class ListView extends ListFragment {
	
	//ListAdapter myListAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
	}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.listview_fragment, container, false);		
	}
	
	public void populateList(List<String> list) {
		ListAdapter myListAdapter = new ArrayAdapter<String> (
				getActivity(), 
				android.R.layout.simple_list_item_1,
				list
		);
		setListAdapter(myListAdapter);
	}
	
	
}
