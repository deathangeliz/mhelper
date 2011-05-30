package com.mhelper.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import com.mhelper.DatebaseAdapter.CondEventAdapter;
import com.mhelper.DatebaseAdapter.ConditionsAdapter;
import com.mhelper.DatebaseAdapter.DetailCondAdapter;
import com.mhelper.DatebaseAdapter.DetailEventAdapter;
import com.mhelper.DatebaseAdapter.EventsAdapter;
import com.mhelper.DatebaseAdapter.MDBHelperAdapter;
import com.mhelper.conditions.TimeCondition;
import com.mhelper.events.ChangeWallpaperEvent;
import com.mhelper.middle.AlarmSetHelper;

import android.R.id;
import android.R.integer;
import android.R.string;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;

import android.database.Cursor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;

import com.mhelper.R;


public class Home extends ExpandableListActivity {
	HomeExpandableListAdapter adapter;
	ArrayList<String> groupContent;
	ArrayList<ArrayList<String>> childrenContent;
	ArrayList<Integer> condEventId;
	ArrayList<Integer> ids;
	
	static final private int NEW_MENU_ITEM = Menu.FIRST;
	static final private int ABOUT_MENU_ITEM = Menu.FIRST + 1;
	
	static final private int REQUEST_NEW_PARAMS = 0;
	static final private int REQUEST_EDIT_PARAMS = 1;
	
	static final public int MODE_NEW = 0;
	static final public int MODE_EDIT = 1;
	static final public String MODE = "mode";
	
	static final public String COND_TYPE = "cond_type";
	static final public int COND_TIME = 0;
	static final public int COND_CALENDAR = 1;
	static final public int COND_MESSAGE = 2;
	static final public String EVENT_TYPE = "event_type";
	static final public int EVENT_SHUTDOWM = 0;
	static final public int EVENT_SLIENT = 1;
	static final public int EVENT_VIBRATION = 2;
	static final public int EVENT_AIRMODE = 3;
	static final public int EVENT_NOTIFICATION = 4;
	static final public int EVENT_CHANGE_WALLPAPER = 5;
	
	SharedPreferences prefs;
	
	private Cursor mCEACursor;
	private CondEventAdapter mCEAHelper; 
	private Cursor mDCACursor;
	private DetailCondAdapter mDCAHelper;
	private ConditionsAdapter mCAHelper;
	private EventsAdapter mEAHelper;
	private DetailEventAdapter mDEAHelper;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Drawable drawable = getResources().getDrawable(R.drawable.background);
        getExpandableListView().setBackgroundDrawable(drawable);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        // Set up our adapter
        /*mCEAHelper = new CondEventAdapter(Home.this);
        mDCAHelper = new DetailCondAdapter(Home.this);
        mCAHelper = new ConditionsAdapter(Home.this);
        mEAHelper = new EventsAdapter(Home.this);
        mDEAHelper = new DetailEventAdapter(this);*/
        
        adapter = new HomeExpandableListAdapter(Home.this);
        getOrRefreshDate();
        setListAdapter(adapter);
    }
	
	@Override
	public void onRestoreInstanceState(Bundle saveInstanceState) {
		super.onRestoreInstanceState(saveInstanceState);
	}
	
	@Override
	public void onRestart() {
		super.onRestart();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		getOrRefreshDate();
		setListAdapter(adapter);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//refresh list content
        getOrRefreshDate();
        setListAdapter(adapter);
	}
	
	@Override
	public void onSaveInstanceState (Bundle saveInstanceState) {
		super.onSaveInstanceState(saveInstanceState);
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onStop() {
		super.onStop();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode) {
		    case (REQUEST_NEW_PARAMS) : {
		    	if (resultCode == Activity.RESULT_OK)
		    		createCondEvent();
		    }
		    break;
		    case (REQUEST_EDIT_PARAMS) : {
		    	if (resultCode == Activity.RESULT_OK)
		    		editCondEvent();
		    }
		    break;
		}
	}
	
	public void createCondEvent() {
		Log.d("HOME.createCondEvent()", 
				"call database method to create cond-event");
		//Here may like this:		
		//prefs.getInt("eType", -1);
		//prefs.getInt("cType", -1);
		//use pref to get params
		Log.d("Home.createCondEvent()", "cType=" + prefs.getInt("cType", -1));
		Log.d("Home.createCondEvent()", "eType=" + prefs.getInt("eType", -1));
		
		int cType = prefs.getInt("cType", -1);
		int eType = prefs.getInt("eType", -1);
	    switch (cType) {
		case 0:
			if (eType > 0 && eType < 4) {
				int ceid = condEventId.size();
				condEventId.add(ceid);
				int id = ids.size();
				ids.add(id);
				Calendar st = getSettingStartTime();
				AlarmSetHelper ash = new AlarmSetHelper(this);
				ash.startToAlarm(st, null, false, eType, id, ceid, false);
				String timeStr = "Alarm";
				String modeStr = "";
				switch (eType) {
				case 1:
					modeStr = "Slient";
					break;
				case 2:
					modeStr = "Vibration";
					break;
				case 3:
					modeStr = "AirMode";
					break;
				default:
					Log.i("Home.creatCondEvent()", "noThisEvent");
					break;
				}
				groupContent.add(timeStr + " - " + modeStr);
				Log.i("Home.createCondEvent", "" + groupContent.size());
				String childrenStr = "StartTime: " + st.getTime().toString()
				                   + "\n" + "Change mode: " + modeStr;
				ArrayList<String> addStrings = new ArrayList<String>();
				addStrings.add(childrenStr);
				childrenContent.add(addStrings);
				getOrRefreshDate();
				setListAdapter(adapter);
			}
			break;

		default:
			break;
		}
		/*int cType = prefs.getInt("cType", -1);
		int eType = prefs.getInt("eType", -1);
		if (cType == 0 && (eType > 0 && eType < 4)) {
				mCAHelper.insertCondition("" + prefs.getInt("condAlarmHour", 0)
						+ " : " + prefs.getInt("condAlramMinute", 0));
				mEAHelper.insertEvent("change mode");
				int id = (int)mCEAHelper.insertCondEvent(1, 2);
				mDCAHelper.insertDetailCondition("mode ", "change to mode" + eType, 
						"start time", "finish time", 1, id);
				mDEAHelper.insertDetailEvent(id, 0);
			   
			TimeCondition tCondition = new TimeCondition(this);
			tCondition.setDescription("time desc");
			Calendar c = Calendar.getInstance();
			int condAlramYear = prefs.getInt("condAlramYear", 2011);
			int condAlarmMonth = prefs.getInt("condAlarmMonth", 9);
			int condAlarmDay = prefs.getInt("condAlarmDay", 1);
			int condAlarmHour = prefs.getInt("condAlarmHour", 0);
			int condAlramMinute = prefs.getInt("condAlramMinute", 0);
			c.set(condAlramYear, condAlarmMonth, condAlarmDay, condAlarmHour, condAlramMinute);
		    tCondition.setStartTime(c);
		    tCondition.setPoint(false);
		    tCondition.setId(id);
		}*/
		getOrRefreshDate();
		setListAdapter(adapter);
	}
	
	public Calendar getSettingStartTime() {
		Calendar c = Calendar.getInstance();
		int condAlramYear = prefs.getInt("condAlramYear", 2011);
		int condAlarmMonth = prefs.getInt("condAlarmMonth", 9) - 1;
		int condAlarmDay = prefs.getInt("condAlarmDay", 1);
		int condAlarmHour = prefs.getInt("condAlarmHour", 0);
		int condAlramMinute = prefs.getInt("condAlramMinute", 0);
		c.set(condAlramYear, condAlarmMonth, condAlarmDay, condAlarmHour, condAlramMinute);
		return c;
	}
	
	public void editCondEvent() {
		Log.d("HOME.editCondEvent()", 
				"call database method to edit cond-event");
		//Here may like this:
		//Database.editCondEvent(extras); or deliver context to it
		getOrRefreshDate();
		setListAdapter(adapter);
	}
	
	public void getOrRefreshDate() {
		groupContent = getGroupContent();
        if (groupContent == null)
        	groupContent = new ArrayList<String>();
        childrenContent = getChildrenContent();
        if (childrenContent == null)
        	childrenContent = new ArrayList<ArrayList<String>>();
        condEventId = getCondEventId();
        if (condEventId == null)
        	condEventId = new ArrayList<Integer>();
        ids = getIds();
        if (ids == null)
        	ids = new ArrayList<Integer>();
        adapter.setGroupContent(groupContent);
        adapter.setChildrenContent(childrenContent);
        adapter.setCondEventID(condEventId);
        if (groupContent.size() == 0)
        	setTitle("MHelper -Click menu to add.");
        else
        	setTitle("MHelper");
	}
	
	public void toDeleteCondEvent(final int groupPosition) {
		//invoke database method to delete
		//    the cond-event which id is condEventId.get(groupPosition)
		getOrRefreshDate();
		setListAdapter(adapter);
	}
	
	public void toEditCondEvent(final int groupPosition) {
		int ecid = condEventId.get(groupPosition);
		Intent intent = new Intent(Home.this, NewCondEvent.class);
		Bundle extras = getCondEventData(ecid);
		extras.putInt(MODE, MODE_EDIT);
		intent.putExtras(extras);
		startActivityForResult(intent, REQUEST_EDIT_PARAMS);
	}
	
	public Bundle getCondEventData(int ceid) {
		//get Bundle data from database
		Bundle data = new Bundle();
		/*mDCACursor = mCEAHelper.getCondEvent(ceid);
		data.putInt("CTYPE", mDCACursor.getShort(0));
		data.putInt("ETYPE",mDCACursor.getShort(1));*/
		return data;
	}
	
	public ArrayList<String> getGroupContent() {
		//invoke database method
		if (groupContent == null)
			groupContent = new ArrayList<String>();
		/*mCEACursor = mCEAHelper.getAllCondEvent();
		mCEACursor.moveToFirst();
		while(mCEACursor.isAfterLast()==false)
		{
			AL.add(mCEACursor.getString(2));
		}*/
		return groupContent;
	}
	
	public ArrayList<ArrayList<String>> getChildrenContent() {
		//invoke database method
		if (childrenContent == null)
			childrenContent = new ArrayList<ArrayList<String>>();
		/*mDCACursor =mDCAHelper.getAllDetailCondition();
		while(mCEACursor.isAfterLast()==false)
		{
			AL.add(mCEACursor.getString(1));
		}*/
		return childrenContent;
	}
	
	public ArrayList<Integer> getCondEventId() {
		//invoke database method
		if (condEventId == null)
			condEventId = new ArrayList<Integer>();
		/*mCEACursor = mCEAHelper.getAllCondEvent();
		mCEACursor.moveToFirst();
		while(mCEACursor.isAfterLast()==false)
		{
			AL.add((int)mCEACursor.getShort(2));
		}*/
		return condEventId;
	}
	
	public ArrayList<Integer> getIds() {
		if (ids == null)
			ids = new ArrayList<Integer>();
		return ids;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		
		MenuItem newItem = menu.add(0, NEW_MENU_ITEM,
				Menu.NONE, "New");
		MenuItem aboutItem = menu.add(0, ABOUT_MENU_ITEM,
				Menu.NONE, "About");
		
		//add icon
		newItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Home.this, NewCondEvent.class);
				intent.putExtra(MODE, MODE_NEW);
				startActivityForResult(intent, REQUEST_NEW_PARAMS);
				return true;
			}
		});
		aboutItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				showAbout();
				return true;
			}
		});
		
		return true;
	}
	
	public void showAbout() {
		String title = "About";
		String message = "I don not know what to say...";
		String buttonString = "OK";
		AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(buttonString, 
				new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
		builder.setCancelable(false);
		builder.show();
		
	}
}
