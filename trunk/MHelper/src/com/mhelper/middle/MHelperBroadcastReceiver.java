package com.mhelper.middle;

import com.mhelper.DatebaseAdapter.CondEventAdapter;
import com.mhelper.DatebaseAdapter.NotificationEventAdapter;
import com.mhelper.DatebaseAdapter.WallpaperEventAdapter;
import com.mhelper.events.ChangeWallpaperEvent;
import com.mhelper.events.NormalEvent;
import com.mhelper.events.NotificationEvent;
import com.mhelper.events.PlaneEvent;
import com.mhelper.events.SilentEvent;
import com.mhelper.events.VibratorEvent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MHelperBroadcastReceiver extends BroadcastReceiver {

	private Context context;
	static final public String MHELPER_BROADCAST = "com.mhelper.action.MHELPER_BROADCAST"; 
	
	@Override
	public void onReceive(Context _context, Intent intent) {
		// TODO Auto-generated method stub
		this.context = _context;
		Bundle extras = intent.getExtras();
		if (extras == null) {
			Log.i("BroadcastReceiver.onReceive()", "extras == null");
			return;
		}
		
		int cond_event_id = extras.getInt(MHelperStrings.COND_EVENT_ID);
		Log.i("BroadcastReceiver.onReceive()", "cond_event_id=" + cond_event_id);
		boolean flag = extras.getBoolean(MHelperStrings.FLAG);
		Log.i("BroadcastReceiver.onReceive()", "flag=" + flag);
		CondEventAdapter condEventAdapter = new CondEventAdapter(context);
		Cursor condEventCursor = condEventAdapter.getCondEvent(cond_event_id);
		if (condEventCursor == null) {
			Log.i("MHelperBroadcastReceiver.onReceive()", "condEventCursor == null");
			return;
		}
		int cond_type = extras.getInt(MHelperStrings.COND_TYPE);
		Log.i("BroadcastReceiver.onReceive()", "cond_type=" + cond_type);
		if (cond_type != condEventCursor.getInt(1))
			Log.i("BroadcastReceiver.onReceive()", "cond_type != condEventCursor.getInt(1)");
		int event_type = condEventCursor.getInt(2);
		Log.i("BroadcastReceiver.onReceive()", "event_type=" + event_type);
		
		if (cond_type == 0) {
			if (event_type >= 0 && event_type < 6) {
				switch (event_type) {
				case 0: {
					if (!flag) {
						
					} else {
						
					}
					break;
				}			
				case 1: {
					if (!flag) {
						context.startService(new Intent(this.context, SilentEvent.class));
						Log.i("BroadcastReceiver.onReceive()", "SilentEvent");
					} else {
						context.startService(new Intent(this.context, NormalEvent.class));
					}
					break;
				}				
				case 2: {
					if (!flag) {
						context.startService(new Intent(this.context, VibratorEvent.class));
						Log.i("BroadcastReceiver.onReceive()", "VibratorEvent");
					} else {
						context.startService(new Intent(this.context, NormalEvent.class));
					}
					break;
				}
				case 3: {
					if (!flag) {
						context.startService(new Intent(this.context, PlaneEvent.class));
					} else {
						context.startService(new Intent(this.context, NormalEvent.class));
					}
					break;
				}
				case 4: {
					if (!flag) {
						NotificationEventAdapter NEHelper = new NotificationEventAdapter(context);
						Cursor NECursor = NEHelper.getNotificationEvent(cond_event_id);
						int notificationType = Integer.valueOf(NECursor.getString(1));
						String notificationMessage = NECursor.getString(2);
						NotificationEvent ne = new NotificationEvent(notificationType, 
								notificationMessage, context, cond_event_id);
						ne.notifyNotification();
					} else {
						
					}
					break;
				}
				case 5: {
					if (!flag) {
						WallpaperEventAdapter WEHelper = new WallpaperEventAdapter(context);
						Cursor WECursor = WEHelper.getWallpaperEvent(cond_event_id);
						Uri wallpaperUri = Uri.parse(WECursor.getString(1));
						ChangeWallpaperEvent cwe = new ChangeWallpaperEvent(context, wallpaperUri);
						cwe.changeWallpaper();
					} else {
						
					}
					break;
				}
				default:
					break;
				}
			}
		}
		/*---------------temporary solution----------------
		
		int cond_event_id = extras.getInt("COND_EVENT_ID");
		Log.i("BroadcastReceiver.onReceive()", "cond_event_id=" + cond_event_id);
		int cond_type = extras.getInt("COND_TYPE");
		Log.i("BroadcastReceiver.onReceive()", "cond_type=" + cond_type);
		int event_type = extras.getInt("EVENT_TYPE");
		Log.i("BroadcastReceiver.onReceive()", "event_type=" + event_type);
		
		if (cond_type == 0) {
			int time_cond_type = extras.getInt("TIME_COND_TYPE");
			Log.i("BroadcastReceiver.onReceive()", "time_cond_type=" + time_cond_type);
			int time_cond_id = extras.getInt("TIME_COND_ID");
			Log.i("BroadcastReceiver.onReceive()", "time_cond_id=" + time_cond_id);
			
			if (time_cond_type == 1) {
				switch (event_type) {
				case 1:
					context.startService(new Intent(this.context, SilentEvent.class));
					Log.i("BroadcastReceiver.onReceive()", "SilentEvent");
					break;
				case 2:
					context.startService(new Intent(this.context, VibratorEvent.class));
					Log.i("BroadcastReceiver.onReceive()", "VibratorEvent");
					break;
				case 3:
					context.startService(new Intent(this.context, VibratorEvent.class));
					Log.i("BroadcastReceiver.onReceive()", "VibratorEvent");
					break;
				default:
					break;
				}
			} else if (time_cond_type == 2) {
				Log.i("BroadcastReceiver.onReceive()", "time_cond_type == 2,unhandle");
			}
		} else if (cond_type == 2) {
			Log.i("BroadcastReceiver.onReceive()", "SMS_COND");
			switch (event_type) {
			case 1:
				context.startService(new Intent(this.context, SilentEvent.class));
				Log.i("BroadcastReceiver.onReceive()", "SilentEvent");
				break;
			case 2:
				context.startService(new Intent(this.context, VibratorEvent.class));
				Log.i("BroadcastReceiver.onReceive()", "VibratorEvent");
				break;
			case 3:
				context.startService(new Intent(this.context, VibratorEvent.class));
				Log.i("BroadcastReceiver.onReceive()", "VibratorEvent");
				break;
			default:
				break;
			}
		}
		//---------------temporary solution----------------*/
	}

}
