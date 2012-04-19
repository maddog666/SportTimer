package com.hook38.sporttimer.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
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
				    	Toast.makeText(getActivity().getApplicationContext(), items[item]+" "+selected, Toast.LENGTH_SHORT).show();
				    }
				});
				AlertDialog alert = builder.create();
				alert.show();
				return false;
			}
			
		});
	}
}
