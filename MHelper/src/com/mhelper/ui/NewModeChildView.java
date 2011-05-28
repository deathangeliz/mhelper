package com.mhelper.ui;

import com.mhelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class NewModeChildView extends LinearLayout {
	Spinner newModeSpinner;
	TextView newModeText;
	int modeType = 0;

	public NewModeChildView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_mode_child, this, true);
		
		newModeSpinner = (Spinner)findViewById(R.id.newModeSpinner);
		newModeText = (TextView)findViewById(R.id.newMessageText);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            context, R.array.mode_type, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		newModeSpinner.setAdapter(adapter);
		newModeSpinner.setOnItemSelectedListener(
	            new OnItemSelectedListener() {
	                @Override
					public void onItemSelected(
	                        AdapterView<?> parent, View view, int position, long id) {
	                	modeType = position;
	                }

	                @Override
					public void onNothingSelected(AdapterView<?> parent) {
	                	modeType = 0;
	                }
	            });
	}
	
	public void setModeText(String str) {
		newModeText.setText(str);
	}

	public int getSelectedModeType() {
		return modeType;
	}
}
