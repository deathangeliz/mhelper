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
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		boolean isEnabled = Settings.System.getInt(getContentResolver(),
				Settings.System.AIRPLANE_MODE_ON, 0) == 1;
		Settings.System.putInt(getContentResolver(),
				Settings.System.AIRPLANE_MODE_ON, isEnabled ? 0 : 1);
		Intent i = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		i.putExtra("state", !isEnabled);
		sendBroadcast(i);

		return Service.START_NOT_STICKY;
	}

}
