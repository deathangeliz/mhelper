package com.mhelper.events;

import java.io.InputStream;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ChangeWallpaperEvent extends Service
{
	private static int index = 0;
	private int[] resIds = new int[]
	{

	};
	

	@Override
	public void onStart(Intent intent, int startId)
	{

		if(index == 5)
			index = 0;
		InputStream inputStream = getResources().openRawResource(resIds[index++]);
		try
		{
			setWallpaper(inputStream);
		}
		catch (Exception e)
		{

		}
		super.onStart(intent, startId);
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
