package com.mhelper.ui;

import com.mhelper.R;
import com.mhelper.middle.MHelperStrings;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class NewGCStartChildView extends LinearLayout {
	
	private Context context;
	private DatePickerDialog dpd;
	private TimePickerDialog tpd;
	TextView alarmDate;
	TextView alarmTime;
	Button gcChooseButton;
	int position = 0;
	
	public NewGCStartChildView(Context _context, int position) {
		super(_context);
		this.context = _context;
		this.position = position;
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_gc_start_child, this, true);
		
		alarmDate = (TextView)findViewById(R.id.gcDateButton);
		alarmTime = (TextView)findViewById(R.id.gcTimeButton);
		gcChooseButton = (Button)findViewById(R.id.gcChooseButton);
		
		alarmDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				OnDateSetListener listener = new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
						NewCondSettings newCondSettings = (NewCondSettings)context;
						newCondSettings.condAlramYear = arg1;
						newCondSettings.condAlarmMonth = arg2 + 1;
						newCondSettings.condAlarmDay = arg3;
						Editor editor = PreferenceManager.getDefaultSharedPreferences(
								context.getApplicationContext()).edit();
						editor.putInt(MHelperStrings.UI_START_DAY, newCondSettings.condAlarmDay);
						editor.putInt(MHelperStrings.UI_START_MONTH, newCondSettings.condAlarmMonth);
						editor.putInt(MHelperStrings.UI_START_YEAR, newCondSettings.condAlramYear);
						editor.commit();
						String dateStr = arg1 + "/" + (arg2+1) + "/" + arg3;
						setAlarmDate(dateStr);
					}
				};
				NewCondSettings newCondSettings = (NewCondSettings)context;
				dpd = new DatePickerDialog(context, listener, newCondSettings.condAlramYear, newCondSettings.condAlarmMonth, newCondSettings.condAlarmDay);
				dpd.show();
			}
		});
		alarmTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				OnTimeSetListener listener = new OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
						NewCondSettings newCondSettings = (NewCondSettings)context;
						newCondSettings.condAlarmHour = arg1;
						newCondSettings.condAlramMinute = arg2;
						Editor editor = PreferenceManager.getDefaultSharedPreferences(
								context.getApplicationContext()).edit();
						editor.putInt(MHelperStrings.UI_START_HOUR, newCondSettings.condAlarmHour);
						editor.putInt(MHelperStrings.UI_START_MINUTE, newCondSettings.condAlramMinute);
						editor.commit();
						String timeStr = arg1 + ":";
						if(arg2 < 10){
							timeStr = timeStr + "0" + arg2;
						}else
							timeStr = timeStr + arg2;
						setAlarmTime(timeStr);
					}
				};
				NewCondSettings newCondSettings = (NewCondSettings)context;
				tpd = new TimePickerDialog(context, listener, newCondSettings.condAlarmHour, newCondSettings.condAlramMinute, false);
				tpd.show();
			}
		});
		gcChooseButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				NewCondSettings newCondSettings = (NewCondSettings)context;
				Intent intent = new Intent(newCondSettings, NewGCalendarRadioGroup.class);
				newCondSettings.startActivityForResult(intent, 0);
			}
		});
	}
	
	public void setAlarmDate(String str){
		alarmDate.setText(str);
	}
	
	public void setAlarmTime(String str) {
		alarmTime.setText(str);
	}
}
