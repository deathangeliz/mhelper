package com.mhelper.ui;

import com.mhelper.R;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.DatePicker;
import android.widget.TabHost;
import android.widget.TimePicker;

public class NewCondEvent extends TabActivity {
	static final public int MODE_NEW = 0;
	static final public int MODE_EDIT = 1;
	static final public String MODE = "mode";
	public int mode = 0;
	static final private int REQUEST_NEW_PARAMS = 0;
	static final private int REQUEST_EDIT_PARAMS = 1;
	
	static final private int SAVE_MENU_ITEM = Menu.FIRST;
	static final private int RETURN_MENU_ITEM = Menu.FIRST + 1;
	
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
                res.getDrawable(R.drawable.settings))
                .setContent(intent));
        
	    intent = new Intent(NewCondEvent.this, NewEventSettings.class);
	    intent.putExtras(extras);
	    tabHost.addTab(tabHost.newTabSpec("events").setIndicator("Events",
                res.getDrawable(R.drawable.camera))
                .setContent(intent));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		
		MenuItem saveItem = menu.add(0, SAVE_MENU_ITEM,
				Menu.NONE, "Save");
		MenuItem returnItem = menu.add(0, RETURN_MENU_ITEM,
				Menu.NONE, "Return");
		
		//add icon
		saveItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				setResult(RESULT_OK);
				finish();
				return true;
			}
		});
		returnItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				setResult(RESULT_CANCELED);
				finish();
				return true;
			}
		});
		
		return true;
	}
	
}
