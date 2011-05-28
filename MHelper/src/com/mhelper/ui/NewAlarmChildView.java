package com.mhelper.ui;

import com.mhelper.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class NewAlarmChildView extends LinearLayout {
	TextView newAlarmDate;
	EditText newAlarmTime;
	ImageView newAlarmImage;
	
	public NewAlarmChildView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_alarm_child, this, true);
		
		newAlarmDate = (EditText)findViewById(R.id.newAlarmDate);
		newAlarmTime = (EditText)findViewById(R.id.newAlarmTime);
		newAlarmImage = (ImageView)findViewById(R.id.newAlarmImage);
	}

	public void setAlarmDate(String str){
		newAlarmDate.setText(str);
	}
	
	public void setAlarmTime(String str) {
		newAlarmTime.setText(str);
	}
	
	public void setAlarm(Drawable drawable) {
		newAlarmImage.setImageDrawable(drawable);
	}
}
