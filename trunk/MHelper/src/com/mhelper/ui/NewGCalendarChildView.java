package com.mhelper.ui;

import com.mhelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class NewGCalendarChildView extends LinearLayout {
	TextView newGCalendarText;
	ToggleButton newGCalendToggle;

	public NewGCalendarChildView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_alarm_child, this, true);
		
		newGCalendarText = (TextView)findViewById(R.id.newGCalendarText);
		newGCalendToggle = (ToggleButton)findViewById(R.id.newGCalendarToggle);	
	}
	
	public void setGCalendarText(String str) {
		newGCalendarText.setText(str);
	}

	public boolean isGCalendarToggleOn() {
		return newGCalendToggle.isChecked();
	}
}
