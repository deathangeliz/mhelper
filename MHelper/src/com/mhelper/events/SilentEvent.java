package com.mhelper.events;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;

public class SilentEvent extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		
		super.onCreate();
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		AudioManager audioMgr=null;
		int ringerMode = AudioManager.RINGER_MODE_SILENT;
		audioMgr.setRingerMode(ringerMode);
		return START_NOT_STICKY;
	}     

	

	
}
