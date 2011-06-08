package com.mhelper.ui;

import java.util.Calendar;

import com.mhelper.middle.MHelperStrings;

import android.widget.ExpandableListView.OnGroupClickListener;

import android.R.bool;
import android.R.integer;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TimePicker;
import android.widget.ExpandableListView.OnGroupClickListener;

public class NewCondSettings extends ExpandableListActivity {
	ExpandableListAdapter condAdapter;
	public SharedPreferences prefs;
	
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
	public int condAlramFinishYear;
	public int condAlarmFinishMonth;
	public int condAlarmFinishDay;
	public int condAlarmFinishHour;
	public int condAlramFinishMinute;
	public boolean condAlarmShouldFinish;
	
	public int typeToMessage;
	public boolean sms_slient;
	public boolean sms_vibration;
	public boolean sms_airmode;
	public boolean sms_normal;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Bundle extras = getIntent().getExtras();
		if (extras == null) {
			Log.d("NewCondSettings.onCreate()", "extras == null");
			finish();
		}
		mode = extras.getInt(MODE);
		
		//set data
        if (mode == MODE_NEW) {
        	//initiate alarm params
        	final Calendar c = Calendar.getInstance();
    	    condAlramYear = c.get(Calendar.YEAR);
    	    condAlarmMonth = c.get(Calendar.MONTH);
    	    condAlarmDay = c.get(Calendar.DAY_OF_MONTH);
    	    condAlarmHour = c.get(Calendar.HOUR_OF_DAY);
    	    condAlramMinute = c.get(Calendar.MINUTE);
    	    condAlramFinishYear = c.get(Calendar.YEAR);
    	    condAlarmFinishMonth = c.get(Calendar.MONTH);
    	    condAlarmFinishDay = c.get(Calendar.DAY_OF_MONTH);
    	    condAlarmFinishHour = c.get(Calendar.HOUR_OF_DAY);
    	    condAlramFinishMinute = c.get(Calendar.MINUTE);
    	    condAlarmShouldFinish = false;
    	    Editor editor = prefs.edit();
    	    editor.putBoolean(MHelperStrings.UI_SHOULD_FINISH, false);
    	    //initiate calendar params
    	    
    	    //initiate message params
    	    typeToMessage = 0;
        } else if (mode == MODE_EDIT) {
        	//get ctype
			cType = prefs.getInt(MHelperStrings.UI_COND_TYPE, 0);
			if (cType == 0) {
				//get alarm params
				condAlramYear = prefs.getInt(MHelperStrings.UI_START_YEAR, 2011);
				condAlarmMonth = prefs.getInt(MHelperStrings.UI_START_MONTH, 1);
				condAlarmDay = prefs.getInt(MHelperStrings.UI_START_DAY, 1);
				condAlarmHour = prefs.getInt(MHelperStrings.UI_START_HOUR, 0);
				condAlramMinute = prefs.getInt(MHelperStrings.UI_START_MINUTE, 0);
				condAlarmShouldFinish = prefs.getBoolean(MHelperStrings.UI_SHOULD_FINISH, false);
				if (condAlarmShouldFinish) {
					condAlramFinishYear = prefs.getInt(MHelperStrings.UI_FINISH_YEAR, 2011);
					condAlarmFinishMonth = prefs.getInt(MHelperStrings.UI_FINISH_MONTH, 1);
					condAlarmFinishDay = prefs.getInt(MHelperStrings.UI_FINISH_DAY, 1);
					condAlarmFinishHour = prefs.getInt(MHelperStrings.UI_FINISH_HOUR, 0);
					condAlramFinishMinute = prefs.getInt(MHelperStrings.UI_FINISH_MINUTE, 0);
				}
			}
			else if (cType == 1){
				//get calendar params
			}
			else if (cType == 2) {
				//get message params
				typeToMessage = prefs.getInt(MHelperStrings.UI_MESSAGE_TYPE, 0);
			}
		} else 
			finish();

        // Set up our adapter
        if (condAdapter == null || !(condAdapter instanceof NewCondExpandableListAdapter))
            condAdapter = new NewCondExpandableListAdapter(NewCondSettings.this);
        setListAdapter(condAdapter);
        getExpandableListView().setOnGroupClickListener(
    			new ExpandableListView.OnGroupClickListener() {
					
					@Override
					public boolean onGroupClick(ExpandableListView parent, View v,
							int groupPosition, long id) {
						// TODO Auto-generated method stub
						cType = groupPosition;
						Editor editor = prefs.edit();
						editor.putInt(MHelperStrings.UI_COND_TYPE, cType);
						editor.commit();
						for (int i = 0; i < 3; i++) {
							if (i == groupPosition) {
								if (parent.isGroupExpanded(groupPosition))
									parent.collapseGroup(groupPosition);
								else {
									parent.expandGroup(groupPosition);
								}
							}
							else {
								parent.collapseGroup(i);
							}
						}
						
						return true;
					}
				});
    }
	
	@Override
	public void onResume() {
		super.onResume();
        // set up ui
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
				
			}
			getExpandableListView().expandGroup(cType);
			//set other group cannot collapse
		} else {
			finish();
		}
        setListAdapter(condAdapter);
	}
	
	public void expandOneGroupAndCollapseOthers(int group) {
		ExpandableListView view = getExpandableListView();
    	for (int i = 0; i < 3; i++) {
    		if (i== group)
    			view.expandGroup(i);
    		else {
				view.collapseGroup(i);
			}
    	}
	}
}
