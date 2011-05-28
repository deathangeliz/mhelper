package com.mhelper.ui;

import com.mhelper.R;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TabHost;
import android.widget.TimePicker;

public class NewCondEvent extends TabActivity {
	static final public int MODE_NEW = 0;
	static final public int MODE_EDIT = 1;
	static final public String MODE = "mode";
	public int mode = 0;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			mode = extras.getInt(MODE);
		}
		
		Resources res = getResources(); // Resource object to get Drawables
	    final TabHost tabHost = getTabHost();  // The activity TabHost
	    Intent intent;
	    
	    intent = new Intent(NewCondEvent.this, NewCondSettings.class);
	    intent.putExtras(extras);
	    tabHost.addTab(tabHost.newTabSpec("conditions").setIndicator("Conditions",
                res.getDrawable(R.drawable.new_tab_cond))
                .setContent(intent));
        
	    intent = new Intent(NewCondEvent.this, NewEventSettings.class);
	    intent.putExtras(extras);
	    tabHost.addTab(tabHost.newTabSpec("events").setIndicator("Events",
                res.getDrawable(R.drawable.new_tab_event))
                .setContent(intent));
	    tabHost.setCurrentTabByTag("events");
	}
	
}
