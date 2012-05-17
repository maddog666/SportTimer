package com.hook38.sporttimer.utils;

import java.util.List;

import com.hook38.sporttimer.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DoubleTextListAdapter extends BaseAdapter {

	private Context context;
	private List<String> list;
	
	public DoubleTextListAdapter(Context context, List<String> list) {
		this.context = context;
		this.list = list;
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_layout, null);
        }
		
		TextView count = (TextView) convertView.findViewById(R.id.list_view_count);
		count.setText(this.getPositionCount(position));
		
		TextView text = (TextView) convertView.findViewById(R.id.list_view_text);
		text.setText(list.get(position)+".");
		
		return convertView;
	}
	
	public String getPositionCount(int position) {
		return Integer.toString(this.getCount() - position);
	}

}
