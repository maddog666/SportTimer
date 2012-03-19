package com.hook38.sporttimer.view;


import java.util.ArrayList;
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
	ArrayList<String> list = new ArrayList<String>();
	ListAdapter myListAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setList(list);		
	}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.listview_fragment, container, false);		
	}
	
	public void addItem(String item){
		list.add(item);
		setList(list);
	}
	
	public void clearItems() {
		list.clear();
		setList(list);
	}
	
	private void setList(List list) {
		myListAdapter = new ArrayAdapter<String> (
				getActivity(), 
				android.R.layout.simple_list_item_1,
				list
		);
		setListAdapter(myListAdapter);
	}
}
