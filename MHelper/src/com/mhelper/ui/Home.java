package com.mhelper.ui;

import java.util.ArrayList;
import java.util.Calendar;

import com.mhelper.DatebaseAdapter.CondEventAdapter;
import com.mhelper.DatebaseAdapter.ConditionsAdapter;
import com.mhelper.DatebaseAdapter.DetailCondAdapter;
import com.mhelper.DatebaseAdapter.DetailEventAdapter;
import com.mhelper.DatebaseAdapter.EventsAdapter;
import com.mhelper.DatebaseAdapter.MDBHelperAdapter;
import com.mhelper.DatebaseAdapter.NotificationEventAdapter;
import com.mhelper.DatebaseAdapter.TimeDetailCondAdapter;
import com.mhelper.DatebaseAdapter.WallpaperEventAdapter;
import com.mhelper.conditions.TimeCondition;
import com.mhelper.events.ChangeWallpaperEvent;
import com.mhelper.middle.AlarmSetHelper;
import com.mhelper.middle.MHelperStrings;

import android.R.id;
import android.R.integer;
import android.R.string;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;

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
import android.widget.ExpandableListView.OnGroupClickListener;

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
	
	/*private Cursor mCEACursor;
	private CondEventAdapter mCEAHelper; 
	private Cursor mDCACursor;
	private DetailCondAdapter mDCAHelper;
	private ConditionsAdapter mCAHelper;
	private EventsAdapter mEAHelper;
	private DetailEventAdapter mDEAHelper;*/
	
	public int selectedGroup;
	
	private CondEventAdapter condEventAdapter;
	//-------------debug-------------------
	private ConditionsAdapter conditionsAdapter;
	private DetailCondAdapter detailCondAdapter;
	private EventsAdapter eventsAdapter;
	private DetailEventAdapter detailEventAdapter;
	private NotificationEventAdapter notificationEventAdapter;
	private WallpaperEventAdapter wallpaperEventAdapter;
	//--------------------------------
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Drawable drawable = getResources().getDrawable(R.drawable.background);
        getExpandableListView().setBackgroundDrawable(drawable);
        //initiate database adapter
        condEventAdapter = new CondEventAdapter(getApplicationContext());
        //-----------------debug----------------
        conditionsAdapter=new ConditionsAdapter(this);
        detailCondAdapter=new DetailCondAdapter(this);
        eventsAdapter=new EventsAdapter(this);
        detailEventAdapter=new DetailEventAdapter(this);
        notificationEventAdapter=new NotificationEventAdapter(this);
        wallpaperEventAdapter=new WallpaperEventAdapter(this);
        
        detailCondAdapter.recreateCondictions();
        conditionsAdapter.recreateConditions();
        eventsAdapter.recreateEvents();
        detailEventAdapter.recreateDetailEvent();
        condEventAdapter.recreateCondEvent();
        notificationEventAdapter.recreateNotificationEvents();
        wallpaperEventAdapter.recreateWallpaperEvents();
        //---------------------------------------
        
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        // Set up our adapter
        /*mCEAHelper = new CondEventAdapter(Home.this);
        mDCAHelper = new DetailCondAdapter(Home.this);
        mCAHelper = new ConditionsAdapter(Home.this);
        mEAHelper = new EventsAdapter(Home.this);
        mDEAHelper = new DetailEventAdapter(this);*/
        
        adapter = new HomeExpandableListAdapter(Home.this);
        groupContent = new ArrayList<String>();
        childrenContent = new ArrayList<ArrayList<String>>();
        condEventId = new ArrayList<Integer>();
        setListAdapter(adapter);
        getExpandableListView().setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// TODO Auto-generated method stub
				int groupCount = adapter.getGroupCount();
				selectedGroup = groupPosition;
				for (int i = 0; i < groupCount; i++) {
					if (i == groupPosition) {
						if (parent.isGroupExpanded(groupPosition))
							parent.collapseGroup(groupPosition);
						else {
							parent.expandGroup(groupPosition);
						}
					} else {
						parent.collapseGroup(groupPosition);
					}
				}
				return true;
			} 
		});
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
	}
	
	@Override
	public void onResume() {
		super.onResume();
		getOrRefreshData();
		setListAdapter(adapter);
		adapter.notifyDataSetChanged();
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
		Log.d("Home.createCondEvent()", "cType=" + prefs.getInt(MHelperStrings.UI_COND_TYPE, -1));
		Log.d("Home.createCondEvent()", "eType=" + prefs.getInt(MHelperStrings.UI_EVENT_TYPE, -1));
		
		int cType = prefs.getInt(MHelperStrings.UI_COND_TYPE, -1);
		int eType = prefs.getInt(MHelperStrings.UI_EVENT_TYPE, -1);
	    switch (cType) {
		case 0: {
			int ceid;
			Bundle params = new Bundle();
			if (eType == 0) {
				
			} else if (eType > 0 && eType < 4) {

			} else if (eType == 4) {
				int nt = prefs.getInt(MHelperStrings.UI_NOTIFICATION_TYPE, -1);
				if (nt == -1)
					Log.i("Home.createCondEvent()", "NOTIFICATION_TYPE=-1");
				params.putInt(MDBHelperAdapter.KEY_NOTIFICATIONTYPE, 
						nt);
				params.putString(MDBHelperAdapter.KEY_NOTIFICATIONMESSAGE, 
						prefs.getString(MHelperStrings.UI_NOTIFICATION_CONTENT, ""));
			} else if (eType == 5) {
				String str = prefs.getString(MHelperStrings.UI_WALLPAPER_URI, "");
				if (str == null) {
					Log.i("Home.createCondEvent()", "eType == 5,str == null");
					return;
				}
				params.putString(MDBHelperAdapter.KEY_WALLPAPERURI, 
						MHelperStrings.UI_WALLPAPER_URI);
			}
			ceid = condEventAdapter.createCondEvent(cType, eType, params);	
			//deliver ceid and time params to time cond and start
			Calendar startTime = getSettingStartTime();
			boolean shouldFinish = prefs.getBoolean(MHelperStrings.UI_SHOULD_FINISH, false);
			Calendar finishTime = Calendar.getInstance();
			if (shouldFinish) {
				finishTime = getSettingFinishTime();
				detailCondAdapter.insertDetailCondition("Alarm", 
						"StartTime: " + startTime.getTime().toString() + "\n" + "FinishTime: " + finishTime.getTime().toString(), 
						startTime.getTime().toString(), 
						finishTime.getTime().toString(), 1, ceid);
			} else {
				detailCondAdapter.insertDetailCondition("Alarm", 
						"StartTime: " + startTime.getTime().toString(),
						startTime.getTime().toString(), 
						"FinishTime", 0, ceid);
			}		
			if (eType < 4) {
				String modeStr = "";
				switch (eType) {
				case 0:
					modeStr = "Shutdown";
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
				detailEventAdapter.insertDetailEvent(ceid, eType);
			} else if (eType == 4) {
				
			} else if (eType == 5) {
				
			}
			TimeCondition tc = setTimeCondition(ceid, startTime, finishTime, shouldFinish);
			AlarmSetHelper ash = new AlarmSetHelper(this);
			ash.addToAlarm(tc, true, shouldFinish);
		}
			break;
		case 1: {
			int ceid;
			Bundle params = new Bundle();
			if (eType == 0) {
				
			} else if (eType > 0 && eType < 4) {

			} else if (eType == 4) {
				int nt = prefs.getInt(MHelperStrings.UI_NOTIFICATION_TYPE, -1);
				if (nt == -1)
					Log.i("Home.createCondEvent()", "NOTIFICATION_TYPE=-1");
				params.putInt(MDBHelperAdapter.KEY_NOTIFICATIONTYPE, 
						nt);
				params.putString(MDBHelperAdapter.KEY_NOTIFICATIONMESSAGE, 
						prefs.getString(MHelperStrings.UI_NOTIFICATION_CONTENT, ""));
			} else if (eType == 5) {
				String str = prefs.getString(MHelperStrings.UI_WALLPAPER_URI, "");
				if (str == null) {
					Log.i("Home.createCondEvent()", "eType == 5,str == null");
					return;
				}
				params.putString(MDBHelperAdapter.KEY_WALLPAPERURI, 
						MHelperStrings.UI_WALLPAPER_URI);
			}
			ceid = condEventAdapter.createCondEvent(cType, eType, params);	
			//deliver ceid and time params to time cond and start
			Calendar startTime = getSettingStartTime();
			boolean shouldFinish = prefs.getBoolean(MHelperStrings.UI_SHOULD_FINISH, false);
			Calendar finishTime = Calendar.getInstance();
			if (shouldFinish) {
				finishTime = getSettingFinishTime();
				detailCondAdapter.insertDetailCondition("Calendar", 
						"StartTime: " + startTime.getTime().toString() + "\n" + "FinishTime: " + finishTime.getTime().toString(), 
						startTime.getTime().toString(), 
						finishTime.getTime().toString(), 1, ceid);
			} else {
				detailCondAdapter.insertDetailCondition("Calendar", 
						"StartTime: " + startTime.getTime().toString(),
						startTime.getTime().toString(), 
						"FinishTime", 0, ceid);
			}		
			if (eType < 4) {
				String modeStr = "";
				switch (eType) {
				case 0:
					modeStr = "Shutdown";
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
				detailEventAdapter.insertDetailEvent(ceid, eType);
			} else if (eType == 4) {
				
			} else if (eType == 5) {
				
			}
			TimeCondition tc = setTimeCondition(ceid, startTime, finishTime, shouldFinish);
			AlarmSetHelper ash = new AlarmSetHelper(this);
			ash.addToAlarm(tc, true, shouldFinish);
		}
			break;
		case 2: {
			int ceid = condEventAdapter.createCondEvent(cType, -1, null);
			break;
		}	
		default:
			break;
		}
		getOrRefreshData();
		setListAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	
	public Calendar getSettingStartTime() {
		Calendar c = Calendar.getInstance();
		int condAlramYear = prefs.getInt(MHelperStrings.UI_START_YEAR, 2011);
		int condAlarmMonth = prefs.getInt(MHelperStrings.UI_START_MONTH, 9) - 1;
		int condAlarmDay = prefs.getInt(MHelperStrings.UI_START_DAY, 1);
		int condAlarmHour = prefs.getInt(MHelperStrings.UI_START_HOUR, 0);
		int condAlramMinute = prefs.getInt(MHelperStrings.UI_START_MINUTE, 0);
		c.set(condAlramYear, condAlarmMonth, condAlarmDay, condAlarmHour, condAlramMinute, 0);
		return c;
	}
	
	public Calendar getSettingFinishTime() {
		Calendar c = Calendar.getInstance();
		int condAlramYear = prefs.getInt(MHelperStrings.UI_FINISH_YEAR, 2011);
		int condAlarmMonth = prefs.getInt(MHelperStrings.UI_FINISH_MONTH, 9) - 1;
		int condAlarmDay = prefs.getInt(MHelperStrings.UI_FINISH_DAY, 1);
		int condAlarmHour = prefs.getInt(MHelperStrings.UI_FINISH_HOUR, 0);
		int condAlramMinute = prefs.getInt(MHelperStrings.UI_FINISH_MINUTE, 0);
		c.set(condAlramYear, condAlarmMonth, condAlarmDay, condAlarmHour, condAlramMinute, 0);
		return c;
	}
	
	public void editCondEvent() {
		Log.d("HOME.editCondEvent()", 
				"call database method to edit cond-event");
		//Here may like this:
		//Database.editCondEvent(extras); or deliver context to it
		getOrRefreshData();
		setListAdapter(adapter);
	}
	
	public void getOrRefreshData() {
		groupContent = getGroupContent();
        childrenContent = getChildrenContent();
        condEventId = getCondEventId();
        /*ids = getIds();
        if (ids == null)
        	ids = new ArrayList<Integer>();*/
        adapter.setGroupContent(groupContent);
        adapter.setChildrenContent(childrenContent);
        adapter.setCondEventID(condEventId);
        if (groupContent.size() == 0)
        	setTitle("MHelper -Click menu to add.");
        else
        	setTitle("MHelper");
	}
	
	public void toDeleteCondEvent(final int groupPosition) {
		int ceid = condEventId.get(groupPosition);
		Cursor CECursor = condEventAdapter.getCondEvent(ceid);
		int ctype = Integer.valueOf(CECursor.getString(1));
		int etype = Integer.valueOf(CECursor.getString(2));
		if (ctype == 0) {
			if (etype < 4) {
				detailEventAdapter.deleteDetailEvent(ceid);
			} else if (etype == 4) {
				notificationEventAdapter.deleteNotificationEvent(ceid);			
			} else if (etype == 5) {
				wallpaperEventAdapter.deleteWallpaperEvent(ceid);
			}
			Cursor DCCursor = detailCondAdapter.getDetailCondition(ceid);
			int point = Integer.valueOf(DCCursor.getString(5));
			boolean setFinish = (point == 1 ? false : true);
			detailCondAdapter.deleteCondition(ceid);
			AlarmSetHelper ash = new AlarmSetHelper(this);
			ash.cancelAlarm(ceid, true, setFinish);
			condEventAdapter.deleteCondEvent(ceid);
		} else if (ctype == 1) {
			if (etype < 4) {
				detailEventAdapter.deleteDetailEvent(ceid);
			} else if (etype == 4) {
				notificationEventAdapter.deleteNotificationEvent(ceid);			
			} else if (etype == 5) {
				wallpaperEventAdapter.deleteWallpaperEvent(ceid);
			}
			Cursor DCCursor = detailCondAdapter.getDetailCondition(ceid);
			int point = Integer.valueOf(DCCursor.getString(5));
			boolean setFinish = (point == 1 ? false : true);
			detailCondAdapter.deleteCondition(ceid);
			AlarmSetHelper ash = new AlarmSetHelper(this);
			ash.cancelAlarm(ceid, true, setFinish);
			condEventAdapter.deleteCondEvent(ceid);
		} else if (ctype == 2) {
			condEventAdapter.deleteCondEvent(ceid);
			Editor editor = prefs.edit();
			editor.putBoolean(MHelperStrings.SMS_SLIENT, false);
			editor.putBoolean(MHelperStrings.SMS_VIBRATION, false);
			editor.putBoolean(MHelperStrings.SMS_AIRMODE, false);
			editor.putBoolean(MHelperStrings.SMS_NORMAL, false);
			editor.commit();
		}
		/*condEventAdapter.deleteCondEvent(ceid);
		AlarmSetHelper ash = new AlarmSetHelper(this);
		ash.cancelAlarm(ceid, true, false);*/
		getOrRefreshData();
		setListAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	
	public void toEditCondEvent(final int groupPosition) {
		int ceid = condEventId.get(groupPosition);
		Intent intent = new Intent(Home.this, NewCondEvent.class);
		Bundle extras = getCondEventData(ceid);
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
		return condEventAdapter.getGroupData();
	}
	
	public ArrayList<ArrayList<String>> getChildrenContent() {
		//invoke database method
		return condEventAdapter.getChildrenData();
	}
	
	public ArrayList<Integer> getCondEventId() {
		//invoke database method
		return condEventAdapter.getCondEventId();
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
	
	public TimeCondition setTimeCondition(long condEventId, Calendar startTime, 
			Calendar endTime, Boolean shouldEnd) {
		TimeCondition tc = new TimeCondition();
		tc.setStartTime(startTime);
		tc.setFinishTime(endTime);
		tc.setPoint(!shouldEnd);
		tc.setCondEventld(condEventId);
		return tc;
	}
}
