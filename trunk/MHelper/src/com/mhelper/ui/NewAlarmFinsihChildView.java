package com.mhelper.ui;

import com.mhelper.R;
import com.mhelper.middle.MHelperStrings;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class NewAlarmFinsihChildView extends LinearLayout {
	private Context context;
	private DatePickerDialog dpd;
	private TimePickerDialog tpd;
	TextView alarmDate;
	TextView alarmTime;
	CheckBox finishCheckBox;
	int position = 0;
	
	public NewAlarmFinsihChildView(Context _context, int position) {
		super(_context);
		this.context = _context;
		this.position = position;
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_alarm_finish_child, this, true);
		
		alarmDate = (TextView)findViewById(R.id.finishdateButton);
		alarmTime = (TextView)findViewById(R.id.finishTimeButton);
		finishCheckBox = (CheckBox)findViewById(R.id.finishCheckBox);
		alarmDate.setEnabled(false);
		alarmTime.setEnabled(false);
		finishCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					alarmDate.setEnabled(true);
					alarmTime.setEnabled(true);
					((NewCondSettings)context).condAlarmShouldFinish = true;
					Editor editor = PreferenceManager.getDefaultSharedPreferences(
							context.getApplicationContext()).edit();
					editor.putBoolean(MHelperStrings.UI_SHOULD_FINISH, true);
					editor.commit();
				} else {
					alarmDate.setEnabled(false);
					alarmTime.setEnabled(false);
					((NewCondSettings)context).condAlarmShouldFinish = false;
					Editor editor = PreferenceManager.getDefaultSharedPreferences(
							context.getApplicationContext()).edit();
					editor.putBoolean(MHelperStrings.UI_SHOULD_FINISH, false);
					editor.commit();
				}
			}
			
		});
		alarmDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				OnDateSetListener listener = new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
						NewCondSettings newCondSettings = (NewCondSettings)context;
						newCondSettings.condAlramFinishYear = arg1;
						newCondSettings.condAlarmFinishMonth = arg2 + 1;
						newCondSettings.condAlarmFinishDay = arg3;
						Editor editor = PreferenceManager.getDefaultSharedPreferences(
								context.getApplicationContext()).edit();
						editor.putInt(MHelperStrings.UI_FINISH_DAY, newCondSettings.condAlarmFinishDay);
						editor.putInt(MHelperStrings.UI_FINISH_MONTH, newCondSettings.condAlarmFinishMonth);
						editor.putInt(MHelperStrings.UI_FINISH_YEAR, newCondSettings.condAlramFinishYear);
						editor.commit();
						String dateStr = arg1 + "/" + (arg2+1) + "/" + arg3;
						setAlarmDate(dateStr);
					}
				};
				NewCondSettings newCondSettings = (NewCondSettings)context;
				dpd = new DatePickerDialog(context, listener, newCondSettings.condAlramFinishYear, 
						newCondSettings.condAlarmFinishMonth, newCondSettings.condAlarmFinishDay);
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
						newCondSettings.condAlarmFinishHour = arg1;
						newCondSettings.condAlramFinishMinute = arg2;
						Editor editor = PreferenceManager.getDefaultSharedPreferences(
								context.getApplicationContext()).edit();
						editor.putInt(MHelperStrings.UI_FINISH_HOUR, newCondSettings.condAlarmFinishHour);
						editor.putInt(MHelperStrings.UI_FINISH_MINUTE, newCondSettings.condAlramFinishMinute);
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
