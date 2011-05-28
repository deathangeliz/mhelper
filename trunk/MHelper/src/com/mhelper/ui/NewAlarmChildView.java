package com.mhelper.ui;

import com.mhelper.R;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class NewAlarmChildView extends LinearLayout {
	Context ctx;
	
	TextView newAlarmDate;
	EditText newAlarmTime;
	ImageView newAlarmImage;
	
    static final int TIME_DIALOG_ID = 0;
    static final int DATE_DIALOG_ID = 1;
	
	public NewAlarmChildView(Context context) {
		super(context);
		ctx = context;
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_alarm_child, this, true);
		
		newAlarmDate = (EditText)findViewById(R.id.newAlarmDate);
		newAlarmTime = (EditText)findViewById(R.id.newAlarmTime);
		newAlarmImage = (ImageView)findViewById(R.id.newAlarmImage);
		
		newAlarmDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		newAlarmTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
	}

	public void setAlarmDate(String str){
		newAlarmDate.setText(str);
	}
	
	public void setAlarmTime(String str) {
		newAlarmTime.setText(str);
	}
	
	public void setAlarmImage(Drawable drawable) {
		newAlarmImage.setImageDrawable(drawable);
	}
	
}
