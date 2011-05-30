package com.mhelper.events;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;

public class SilentEvent extends Service{

	private Context context = this;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		AudioManager audioMgr=null;
		audioMgr = (AudioManager)context.getSystemService(context.AUDIO_SERVICE);
		int ringerMode = AudioManager.RINGER_MODE_SILENT;
		audioMgr.setRingerMode(ringerMode);
		Log.i("SlientEvent.onStartCommand()", "start");
		super.onCreate();
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	/*@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		/*AudioManager audioMgr=null;
		audioMgr = (AudioManager)context.getSystemService(context.AUDIO_SERVICE);
		int ringerMode = AudioManager.RINGER_MODE_SILENT;
		audioMgr.setRingerMode(ringerMode);
		Log.i("SlientEvent.onStartCommand()", "start");
		return START_NOT_STICKY;
	}    */ 

	

	
}
