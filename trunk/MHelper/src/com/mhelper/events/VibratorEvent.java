package com.mhelper.events;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;

public class VibratorEvent extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);   
		long[] pattern = {800, 50, 400, 30}; // OFF/ON/OFF/ON...   
		vibrator.vibrate(pattern, 2);
		return START_NOT_STICKY;
	}
}
