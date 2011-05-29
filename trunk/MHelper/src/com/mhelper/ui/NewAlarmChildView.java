package com.mhelper.ui;

import com.mhelper.R;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class NewAlarmChildView extends LinearLayout {
	private Context context;
	private DatePickerDialog dpd;
	private TimePickerDialog tpd;
	TextView alarmDate;
	TextView alarmTime;
	int position = 0;
	
	public NewAlarmChildView(Context _context, int position){
		super(_context);
		this.context = _context;
		this.position = position;
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_alarm_child, this, true);
		
		alarmDate = (TextView)findViewById(R.id.dateButton);
		alarmTime = (TextView)findViewById(R.id.timeButton);
		
		alarmDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				OnDateSetListener listener = new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
						NewCondSettings newCondSettings = (NewCondSettings)context;
						newCondSettings.condAlarmDay = arg1;
						newCondSettings.condAlarmMonth = arg2 + 1;
						newCondSettings.condAlarmDay = arg3;
						Editor editor = PreferenceManager.getDefaultSharedPreferences(
								context.getApplicationContext()).edit();
						editor.putInt("condAlarmDay", newCondSettings.condAlarmDay);
						editor.putInt("condAlarmMonth", newCondSettings.condAlarmMonth);
						editor.putInt("condAlarmDay", newCondSettings.condAlarmDay);
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
						editor.putInt("condAlarmHour", newCondSettings.condAlarmHour);
						editor.putInt("condAlramMinute", newCondSettings.condAlramMinute);
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
	}

	public void setAlarmDate(String str){
		alarmDate.setText(str);
	}
	
	public void setAlarmTime(String str) {
		alarmTime.setText(str);
	}
	
	
}
