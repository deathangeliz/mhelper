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
			Intent intent =new Intent("MHelperBroadcast");
	        intent.putExtra("COND_EVENT_ID", condition.getCondEventld());
	        intent.putExtra("COND_TYPE", 1);
	        intent.putExtra("TIME_COND_ID", condition.getId());
	        PendingIntent PI = PendingIntent.getBroadcast(thiscontext, condition.getId(), intent,PendingIntent.FLAG_UPDATE_CURRENT);
	        AlarmManager alarms = (AlarmManager)thiscontext.getSystemService(thiscontext.ALARM_SERVICE);
	        alarms.set(AlarmManager.RTC, condition.getStartTime().getTimeInMillis(), PI);
	    }
		if(setFinish)
		{
			Intent intent =new Intent("MHelperBroadcast");
	        intent.putExtra("COND_EVENT_ID", condition.getCondEventld());
	        intent.putExtra("COND_TYPE", 2);
	        intent.putExtra("TIME_COND_ID", condition.getId());
	        PendingIntent PI2 = PendingIntent.getBroadcast(thiscontext, condition.getId(), intent,PendingIntent.FLAG_UPDATE_CURRENT);
	        AlarmManager alarms2 = (AlarmManager)thiscontext.getSystemService(thiscontext.ALARM_SERVICE);
	        alarms2.set(AlarmManager.RTC, condition.getFinishTime().getTimeInMillis(), PI2);
		}
	}
   
	public void cancelAlarm(TimeCondition condition2, boolean setStart,
			boolean setFinish) {
		if (setStart) {
			Intent intent3 = new Intent("MHelperBroadcast");
			PendingIntent PI = PendingIntent.getBroadcast(thiscontext,
					condition2.getId(), intent3,
					PendingIntent.FLAG_UPDATE_CURRENT);
			AlarmManager alarms = (AlarmManager) thiscontext
					.getSystemService(thiscontext.ALARM_SERVICE);
			alarms.cancel(PI);

		}
		if (setFinish) {
			Intent intent4 = new Intent("MHelperBroadcast");
			PendingIntent PI = PendingIntent.getBroadcast(thiscontext,
					condition2.getId(), intent4,
					PendingIntent.FLAG_UPDATE_CURRENT);
			AlarmManager alarms = (AlarmManager) thiscontext
					.getSystemService(thiscontext.ALARM_SERVICE);
			alarms.cancel(PI);
		}
	}
	public void setThiscontext(Context thiscontext) {
		this.thiscontext = thiscontext;
	}

	public Context getThiscontext() {
		return thiscontext;
	}
	

}