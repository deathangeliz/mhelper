package com.mhelper.ui;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.ExpandableListAdapter;
import android.widget.TimePicker;

public class NewCondSettings extends ExpandableListActivity {
	ExpandableListAdapter condAdapter;
	
	public int condAlramYear;
	public int condAlarmMonth;
	public int condAlarmDay;
	public int condAlarmHour;
	public int condAlramMitute;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        final Calendar c = Calendar.getInstance();
	    condAlramYear = c.get(Calendar.YEAR);
	    condAlarmMonth = c.get(Calendar.MONTH);
	    condAlarmDay = c.get(Calendar.DAY_OF_MONTH);
	    condAlarmHour = c.get(Calendar.HOUR_OF_DAY);
	    condAlramMitute = c.get(Calendar.MINUTE);

        // Set up our adapter
        condAdapter = new NewCondExpandableListAdapter(NewCondSettings.this);
        setListAdapter(condAdapter);
    }
	
}
