package com.mhelper.middle;

import java.util.Calendar;

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
	
	public void startToAlarm(Calendar startTime, Calendar finishTime, boolean point, 
			int eventid, int id, long condEventId, boolean setFinish) {
		TimeCondition tc = new TimeCondition(thiscontext);
		tc.setStartTime(startTime);
		tc.setFinishTime(finishTime);
		tc.setPoint(point);
		tc.setEventid(eventid);
		tc.setId(id);
		tc.setCondEventld(condEventId);
		
		addToAlarm(tc, true, setFinish);
	}
	
	public void addToAlarm(TimeCondition condition, boolean setStart, boolean setFinish)
	{   
		if(setStart)
		{
			Intent intent =new Intent(MHelperBroadcastReceiver.MHELPER_BROADCAST);
	        intent.putExtra("COND_EVENT_ID", condition.getCondEventld());
	        intent.putExtra("TIME_COND_TYPE", 1);
	        intent.putExtra("TIME_COND_ID", condition.getId());
	        //temporary solution
	        intent.putExtra("COND_TYPE", 0);
	        intent.putExtra("EVENT_TYPE", condition.getEventid());
	        PendingIntent PI = PendingIntent.getBroadcast(thiscontext, condition.getId(), intent,PendingIntent.FLAG_UPDATE_CURRENT);
	        AlarmManager alarms = (AlarmManager)thiscontext.getSystemService(thiscontext.ALARM_SERVICE);
	        alarms.set(AlarmManager.RTC, condition.getStartTime().getTimeInMillis(), PI);
	    }
		if(setFinish)
		{
			Intent intent =new Intent(MHelperBroadcastReceiver.MHELPER_BROADCAST);
	        intent.putExtra("COND_EVENT_ID", condition.getCondEventld());
	        intent.putExtra("TIME_COND_TYPE", 2);
	        intent.putExtra("TIME_COND_ID", condition.getId());
	        PendingIntent PI2 = PendingIntent.getBroadcast(thiscontext, condition.getId(), intent,PendingIntent.FLAG_UPDATE_CURRENT);
	        AlarmManager alarms2 = (AlarmManager)thiscontext.getSystemService(thiscontext.ALARM_SERVICE);
	        alarms2.set(AlarmManager.RTC, condition.getFinishTime().getTimeInMillis(), PI2);
		}
	}
   
	public void cancelAlarm(TimeCondition condition2, boolean setStart,
			boolean setFinish) {
		if (setStart) {
			Intent intent3 = new Intent(MHelperBroadcastReceiver.MHELPER_BROADCAST);
			PendingIntent PI = PendingIntent.getBroadcast(thiscontext,
					condition2.getId(), intent3,
					PendingIntent.FLAG_UPDATE_CURRENT);
			AlarmManager alarms = (AlarmManager) thiscontext
					.getSystemService(thiscontext.ALARM_SERVICE);
			alarms.cancel(PI);

		}
		if (setFinish) {
			Intent intent4 = new Intent(MHelperBroadcastReceiver.MHELPER_BROADCAST);
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