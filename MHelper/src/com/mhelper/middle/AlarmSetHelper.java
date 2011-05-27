package com.mhelper.middle;

import com.mhelper.conditions.TimeCondition;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
public class AlarmSetHelper {
	
	public  AlarmSetHelper(Context context){
		setThiscontext(context);
	}
	private Context thiscontext;
	
	public void addToAlarm(TimeCondition condition, boolean setStart, boolean setFinish)
	{   
		if(setStart)
		{
			Intent intent =new Intent("condition");
	        intent.putExtra("id", condition.getId());
	        intent.putExtra("title", condition.getTitle());
	        PendingIntent PI = PendingIntent.getBroadcast(thiscontext, condition.getId(), intent,PendingIntent.FLAG_UPDATE_CURRENT);
	        AlarmManager alarms = (AlarmManager)thiscontext.getSystemService(thiscontext.ALARM_SERVICE);
	        alarms.set(AlarmManager.RTC, condition.getStartTime().getTimeInMillis(), PI);
	    }
		if(setFinish)
		{
			Intent intent2 =new Intent("condition1");
	        intent2.putExtra("id", condition.getId());
	        intent2.putExtra("title", condition.getTitle());
	        PendingIntent PI2 = PendingIntent.getBroadcast(thiscontext, condition.getId(), intent2,PendingIntent.FLAG_UPDATE_CURRENT);
	        AlarmManager alarms2 = (AlarmManager)thiscontext.getSystemService(thiscontext.ALARM_SERVICE);
	        alarms2.set(AlarmManager.RTC, condition.getFinishTime().getTimeInMillis(), PI2);
		}
	}

	public void setThiscontext(Context thiscontext) {
		this.thiscontext = thiscontext;
	}

	public Context getThiscontext() {
		return thiscontext;
	}
	

}