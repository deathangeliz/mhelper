package com.mhelper.events;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;

public class PlaneEvent extends Service {

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	@Override
	public void onCreate() {
		Settings.System.putInt(getContentResolver(),
				Settings.System.AIRPLANE_MODE_ON, 1);
		Intent i = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		//i.putExtra("state", !isEnabled);
		sendBroadcast(i);

		super.onCreate();
	}

}
