package com.mhelper.ui;

import java.security.spec.EllipticCurve;

import android.app.ExpandableListActivity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils.EllipsizeCallback;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class NewEventSettings extends ExpandableListActivity {
    ExpandableListAdapter eventAdapter;
    public SharedPreferences prefs;
    
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
        	
        	imageUri = null;
        } else if (mode == MODE_EDIT) {
        	eType = prefs.getInt("eType", -1);
        	if (eType == 0) {
        		
        	} else if (eType == 1) {
				
			} else if (eType == 2) {
				
			} else if (eType == 3) {
				
			} else if (eType == 4) {
				//notificationType = extras.getInt("notificationType");
				//notificationContent = new StringBuilder(extras.getString("notificationContent"));
				notificationType = prefs.getInt("notificationType", 0);
				notificationContent = prefs.getString("notificationContent", "no message");
			} else if (eType == 5) {
				//imageUri = extras.getString("imageUri");
				imageUri = prefs.getString("imageUri", null);
			}
        }
        
        // Set up our adapter
        eventAdapter = new NewEventExpandableListAdapter(NewEventSettings.this);
        setListAdapter(eventAdapter);
        
        if (mode == MODE_NEW) {
        	
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
        	} else if (eType == 5) {
        		
        	}
        	getExpandableListView().setOnGroupClickListener(
        			new ExpandableListView.OnGroupClickListener() {
						
						@Override
						public boolean onGroupClick(ExpandableListView parent, View v,
								int groupPosition, long id) {
							// TODO Auto-generated method stub
							if (groupPosition == 0) {
								eType = 0;
							} else if (groupPosition == 1){
								eType = 1;
							} else if (groupPosition == 2){
								eType = 4;
							} else if (groupPosition == 3){
								eType = 5;
							} 
							Editor editor = prefs.edit();
							editor.putInt("eType", eType);
							editor.commit();
							if (parent.isGroupExpanded(groupPosition))
								parent.collapseGroup(groupPosition);
							else {
								parent.expandGroup(groupPosition);
							}
							return true;
						}
					});
        } else {
			finish();
		}
       
    }
}
