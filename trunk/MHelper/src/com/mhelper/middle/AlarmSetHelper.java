package com.mhelper.middle;

import java.util.Calendar;

import com.mhelper.conditions.TimeCondition;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
public class AlarmSetHelper {
	
	public  AlarmSetHelper(Context context){
		setThiscontext(context);
	}
	private Context context;
	
	public void startToAlarm(Calendar startTime, Calendar finishTime, boolean point, 
			int eventid, int id, long condEventId, boolean setFinish) {
		TimeCondition tc = new TimeCondition();
		tc.setStartTime(startTime);
		tc.setFinishTime(finishTime);
		tc.setPoint(point);
		//tc.setEventid(eventid);
		tc.setId(id);
		tc.setCondEventld(condEventId);
		
		addToAlarm(tc, true, setFinish);
	}
	
	public void startTimeCondition(long condEventId, Calendar startTime, Calendar endTime, Boolean shouldEnd)
	{
		TimeCondition tc = new TimeCondition();
		tc.setStartTime(startTime);
		tc.setFinishTime(endTime);
		tc.setPoint(!shouldEnd);
		tc.setCondEventld(condEventId);
		
		addToAlarm(tc, true, shouldEnd);
	}
	
	public void addToAlarm(TimeCondition condition, boolean setStart, boolean setFinish)
	{   
		if(setStart)
		{
			Intent intent =new Intent(MHelperBroadcastReceiver.MHELPER_BROADCAST);
	        intent.putExtra(MHelperStrings.COND_EVENT_ID, (int)(condition.getCondEventld()));
	        intent.putExtra(MHelperStrings.COND_TYPE, 0);
	        intent.putExtra(MHelperStrings.FLAG, false);
	        PendingIntent PI = PendingIntent.getBroadcast(context, (int)(condition.getCondEventld()), intent,PendingIntent.FLAG_UPDATE_CURRENT);
	        AlarmManager alarms = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
	        alarms.set(AlarmManager.RTC, condition.getStartTime().getTimeInMillis(), PI);
	        Log.i("ALarmSetHelper.addToAlarm()", "sendBroadcast");
	    }
		if(setFinish)
		{
			Intent intent =new Intent(MHelperBroadcastReceiver.MHELPER_BROADCAST);
	        intent.putExtra(MHelperStrings.COND_EVENT_ID, (int)(condition.getCondEventld()));
	        intent.putExtra(MHelperStrings.FLAG, true);
	        intent.putExtra(MHelperStrings.COND_TYPE, 0);
	        PendingIntent PI2 = PendingIntent.getBroadcast(context, (int)(condition.getCondEventld()), intent,PendingIntent.FLAG_UPDATE_CURRENT);
	        AlarmManager alarms2 = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
	        alarms2.set(AlarmManager.RTC, condition.getFinishTime().getTimeInMillis(), PI2);
		}
	}
   
	public void cancelAlarm(long condEventId, boolean setStart,
			boolean setFinish) {
		if (setStart) {
			Intent intent3 = new Intent(MHelperBroadcastReceiver.MHELPER_BROADCAST);
			PendingIntent PI = PendingIntent.getBroadcast(context,
					(int)condEventId, intent3,
					PendingIntent.FLAG_UPDATE_CURRENT);
			AlarmManager alarms = (AlarmManager) context
					.getSystemService(context.ALARM_SERVICE);
			alarms.cancel(PI);

		}
		if (setFinish) {
			Intent intent4 = new Intent(MHelperBroadcastReceiver.MHELPER_BROADCAST);
			PendingIntent PI = PendingIntent.getBroadcast(context,
					(int)condEventId, intent4,
					PendingIntent.FLAG_UPDATE_CURRENT);
			AlarmManager alarms = (AlarmManager) context
					.getSystemService(context.ALARM_SERVICE);
			alarms.cancel(PI);
		}
	}
	public void setThiscontext(Context thiscontext) {
		this.context = thiscontext;
	}

	public Context getThiscontext() {
		return context;
	}
	

}