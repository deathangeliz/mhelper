package com.mhelper.events;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.net.Uri;

public class ChangeWallpaperEvent
{
	Uri uri = null;
	Context context = null;
	public ChangeWallpaperEvent(Context context, Uri uri){
		this.context = context;
		this.uri = uri;
	}
	
	public void changeWallpaper(){
		InputStream inputStream = null;
		try {
			inputStream = context.getContentResolver().openInputStream(uri);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			context.setWallpaper(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
