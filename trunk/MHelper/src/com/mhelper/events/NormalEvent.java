package com.mhelper.events;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class NormalEvent extends Service {
    private Context context = this;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void onCreate() {
		AudioManager audioMgr=null;
		audioMgr = (AudioManager)context.getSystemService(context.AUDIO_SERVICE);
		int ringerMode = AudioManager.RINGER_MODE_NORMAL;
		audioMgr.setRingerMode(ringerMode);
		Log.i("SlientEvent.onStartCommand()", "start");
		Settings.System.putInt(getContentResolver(),
				Settings.System.AIRPLANE_MODE_ON, 0);
		Intent i = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		//i.putExtra("state", !isEnabled);
		sendBroadcast(i);
		super.onCreate();
	}

}
