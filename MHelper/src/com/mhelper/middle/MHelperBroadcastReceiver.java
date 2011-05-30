package com.mhelper.middle;

import com.mhelper.events.PlaneEvent;
import com.mhelper.events.SilentEvent;
import com.mhelper.events.VibratorEvent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MHelperBroadcastReceiver extends BroadcastReceiver {

	private Context context;
	static final public String MHELPER_BROADCAST = "com.mhelper.action.MHELPER_BROADCAST"; 
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		this.context = context;
		Bundle extras = intent.getExtras();
		if (extras == null) {
			Log.i("BroadcastReceiver.onReceive()", "extras == null");
			return;
		}
		//---------------temporary solution----------------
		
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
			Log.i("BroadcastReceiver.onReceive()", "time_cond_type=" + time_cond_type);
			
			if (time_cond_type == 1) {
				switch (cond_type) {
				case 1:
					context.startService(new Intent(this.context, SilentEvent.class));
					break;
				case 2:
					context.startService(new Intent(this.context, VibratorEvent.class));
					break;
				case 3:
					context.startService(new Intent(this.context, PlaneEvent.class));
					break;
				default:
					break;
				}
			} else if (time_cond_type == 2) {
				Log.i("BroadcastReceiver.onReceive()", "time_cond_type == 2,unhandle");
			}
		}
		//---------------temporary solution----------------
	}

}
