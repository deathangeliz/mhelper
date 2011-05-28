package com.mhelper.ui;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.ExpandableListAdapter;
import android.widget.TimePicker;

public class NewCondSettings extends ExpandableListActivity {
	ExpandableListAdapter condAdapter;
	
	static final public int MODE_NEW = 0;
	static final public int MODE_EDIT = 1;
	static final public String MODE = "mode";
	public int mode = 0;
	static final public String CTYPE = "cType";
	public int cType;
	
	public int condAlramYear;
	public int condAlarmMonth;
	public int condAlarmDay;
	public int condAlarmHour;
	public int condAlramMinute;
	
	public int typeToMessage;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
		if (extras == null) {
			Log.d("NewCondSettings.onCreate()", "extras == null");
			finish();
		}
		mode = extras.getInt(MODE);
		
		//set data
        if (mode == MODE_NEW) {
        	final Calendar c = Calendar.getInstance();
    	    condAlramYear = c.get(Calendar.YEAR);
    	    condAlarmMonth = c.get(Calendar.MONTH);
    	    condAlarmDay = c.get(Calendar.DAY_OF_MONTH);
    	    condAlarmHour = c.get(Calendar.HOUR_OF_DAY);
    	    condAlramMinute = c.get(Calendar.MINUTE);
    	    
    	    typeToMessage = 0;
        } else if (mode == MODE_EDIT) {
			cType = extras.getInt(CTYPE);
			if (cType == 0) {
				condAlramYear = extras.getInt("year");
				condAlarmMonth = extras.getInt("month");
				condAlarmDay = extras.getInt("dat");
				condAlarmHour = extras.getInt("hour");
				condAlramMinute = extras.getInt("minute");
			}
			else if (cType == 1){
				
			}
			else if (cType == 2) {
				typeToMessage = extras.getInt("typeToMessage");
			}
		} else 
			finish();

        // Set up our adapter
        condAdapter = new NewCondExpandableListAdapter(NewCondSettings.this);
        if (mode == MODE_NEW) {
        	NewAlarmChildView alramView = (NewAlarmChildView)condAdapter.getChildView(0, 0, 
        			false, null, null);
        	if (alramView == null)
        		Log.i("NewCondSetting", "alramView == null");
        	else {
        		String dateStr = condAlramYear + "/" + condAlarmMonth + "/" + condAlarmDay;
            	alramView.setAlarmDate(dateStr);
            	String timeStr = condAlarmHour + ":";
    			if(condAlramMinute < 10){
    				timeStr = timeStr + "0" + condAlramMinute;
    			}else
    				timeStr = timeStr + condAlramMinute;
    			alramView.setAlarmTime(timeStr);
        	}    
        	
        	NewMessageChildView messageView = (NewMessageChildView)condAdapter.getChildView(2, 0, 
        			false, null, null);
        	if (messageView == null)
        		Log.i("NewCondSetting", "messageView == null");
        	else {
        		messageView.setSelectedMessageType(typeToMessage);
        	}
        } else if (mode == MODE_EDIT){
			if (cType == 0) {
				NewAlarmChildView alramView = (NewAlarmChildView)condAdapter.getChildView(0, 0, 
	        			false, null, null);
	        	if (alramView == null)
	        		Log.i("NewCondSetting", "alramView == null");
	        	else {
	        		String dateStr = condAlramYear + "/" + condAlarmMonth + "/" + condAlarmDay;
	            	alramView.setAlarmDate(dateStr);
	            	String timeStr = condAlarmHour + ":";
	    			if(condAlramMinute < 10){
	    				timeStr = timeStr + "0" + condAlramMinute;
	    			}else
	    				timeStr = timeStr + condAlramMinute;
	    			alramView.setAlarmTime(timeStr);
	        	}    
			} else if (cType == 1){
				
			} else if (cType == 2) {
				NewMessageChildView messageView = (NewMessageChildView)condAdapter.getChildView(2, 0, 
	        			false, null, null);
	        	if (messageView == null)
	        		Log.i("NewCondSetting", "messageView == null");
	        	else {
	        		messageView.setSelectedMessageType(typeToMessage);
	        	}
			}
			//set other group cannot collapse
		} else {
			finish();
		}
        setListAdapter(condAdapter);
    }
	
}
