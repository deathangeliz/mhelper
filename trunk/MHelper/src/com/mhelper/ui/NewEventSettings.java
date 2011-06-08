package com.mhelper.ui;

import com.mhelper.middle.MHelperStrings;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class NewEventSettings extends ExpandableListActivity {
    ExpandableListAdapter eventAdapter;
    SharedPreferences prefs;
    
    static final public int MODE_NEW = 0;
	static final public int MODE_EDIT = 1;
	static final public String MODE = "mode";
	public int mode = 0;
	static final public String ETYPE = "eType";
	public int eType;
	
	public int notificationType;
	public String notificationContent;
	
	public String imageUri;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
			Log.d("NewEventSettings.onCreate()", "extras == null");
			finish();
        }
        mode = extras.getInt(MODE);
        
        //set data
        if (mode == MODE_NEW) {
        	notificationType = 0;
        	notificationContent = "";
        	
        	imageUri = "";
        } else if (mode == MODE_EDIT) {
        	eType = extras.getInt(ETYPE);
        	if (eType == 0) {
        		
        	} else if (eType == 1) {
				
			} else if (eType == 2) {
				
			} else if (eType == 3) {
				
			} else if (eType == 4) {
				notificationType = extras.getInt(MHelperStrings.UI_NOTIFICATION_TYPE, 0);
				notificationContent = extras.getString(MHelperStrings.UI_NOTIFICATION_CONTENT);
			} else if (eType == 5) {
				imageUri = extras.getString(MHelperStrings.UI_WALLPAPER_URI);
			}
        }
        
        // Set up our adapter
        eventAdapter = new NewEventExpandableListAdapter(NewEventSettings.this);
        setListAdapter(eventAdapter);
        getExpandableListView().setOnGroupClickListener(
        		new ExpandableListView.OnGroupClickListener() {
					
					@Override
					public boolean onGroupClick(ExpandableListView parent, View v,
							int groupPosition, long id) {
						// TODO Auto-generated method stub
						eType = groupPosition;
						if (eType == 2)
							eType = 4;
						else if (eType == 3)
							eType = 5;
						Editor editor = prefs.edit();
						editor.putInt(MHelperStrings.UI_EVENT_TYPE, eType);
						editor.commit();
						for (int i = 0; i < 4; i++) {
							if (i == groupPosition) {
								if (parent.isGroupExpanded(groupPosition))
									parent.collapseGroup(groupPosition);
								else 
									parent.expandGroup(groupPosition);
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
		if (mode == MODE_NEW) {
        	NewNotificationSelectChildView notificationSelectChildView = 
        		(NewNotificationSelectChildView)eventAdapter.getChildView(2, 0, false, null, null);
        	if (notificationSelectChildView == null) {
        		Log.i("NewEventSettings", "notificationSelectChildView == null");
        	} else {
        		notificationSelectChildView.setSelectedType(notificationType);
			}	
        	NewNotificationMessageChildView notificationMessageChildView = 
        		(NewNotificationMessageChildView)eventAdapter.getChildView(2, 1, true, null, null);
        	if (notificationMessageChildView == null) {
        		Log.i("NewEventSettings", "notificationMessageChildView == null");
        	} else {
        		notificationMessageChildView.setEditText(notificationContent);
			}	
        	
        	NewWallpaperChildView wallpaperChildView = 
        		(NewWallpaperChildView)eventAdapter.getChildView(3, 0, true, null, null);
        	if (wallpaperChildView == null) 
        		Log.i("NewEventSettings", "wallpaperChildView == null");
        	else {
        		wallpaperChildView.setString(imageUri);
			}        	
        } else if (mode == MODE_EDIT){
        	if (eType == 4) {
        		NewNotificationSelectChildView nnsView = 
        			(NewNotificationSelectChildView)eventAdapter.getChildView(2, 0, 
        					false, null, null);
        		if (nnsView == null) {
        			Log.i("NewEventSettings", "nnsView == null");
        		} else {
					nnsView.setSelectedType(notificationType);
				}
        		NewNotificationMessageChildView nnmView = 
        			(NewNotificationMessageChildView)eventAdapter.getChildView(2,
        					1, true, null, null);
        		if (nnmView == null) {
        			Log.i("NewEventSettings", "nnmView == null");
        		} else {
					nnmView.setEditText(notificationContent.toString());
				}
        		getExpandableListView().expandGroup(2);
        	} else if (eType == 5) {
        		NewWallpaperChildView wallpaperChildView = 
            		(NewWallpaperChildView)eventAdapter.getChildView(3, 0, true, null, null);
            	if (wallpaperChildView == null) 
            		Log.i("NewEventSettings", "wallpaperChildView == null");
            	else {
            		wallpaperChildView.setString(imageUri);
    			}        	
            	getExpandableListView().expandGroup(3);
        	}
        }
        setListAdapter(eventAdapter);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if (resultCode == RESULT_OK){
			if (requestCode == NewWallpaperChildView.REQUEST_CODE){
				Uri uri = data.getData();
				View view = eventAdapter.getChildView(3, 0, true, null, null);
				if (view != null && view instanceof NewWallpaperChildView){
					((NewWallpaperChildView)view).setString(uri.toString());
					Editor editor = prefs.edit();
					editor.putString(MHelperStrings.UI_WALLPAPER_URI, uri.toString());
					editor.commit();
					//此处需要把 uri 存起来
				}
			}
		}
	}
}
