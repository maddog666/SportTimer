package com.hook38.sporttimer.view;

import com.hook38.sporttimer.CountDownTimerActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

public class InteractiveListView extends ListView {

	@Override
	public void onStart() {
		super.onStart();
		getListView().setOnItemLongClickListener(new OnItemLongClickListener(){

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
			
		});
	}
}
