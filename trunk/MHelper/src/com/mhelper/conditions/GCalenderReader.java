package com.mhelper.conditions;

import java.util.ArrayList;
import java.util.Calendar;

import com.mhelper.R;

//import android.R;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.widget.Toast;

public class GCalenderReader {
	private Context context;
	public   GCalenderReader(Context context)
	{
		this.context = context;
	}
	
	public ArrayList<TimeCondition> readCalendar(Calendar time){
		ArrayList<TimeCondition> timeCondList = new ArrayList<TimeCondition>();
		String contentProviderFirst = "";
		String []contentProviderLast = new String[]{"calendar", "calendarEx"};
		Calendar firstTime = (Calendar) time.clone();
		firstTime.set(Calendar.HOUR_OF_DAY, 0);
		firstTime.set(Calendar.MINUTE, 0);
		firstTime.set(Calendar.SECOND, 0);

		Calendar lastTime = (Calendar) time.clone();
		lastTime.set(Calendar.HOUR_OF_DAY, 23);
		lastTime.set(Calendar.MINUTE, 59);
		lastTime.set(Calendar.SECOND, 59);
		if (Build.VERSION.SDK_INT >= 8)
			contentProviderFirst = "com.android.";
		else
			contentProviderFirst = "";
		ContentResolver contentResolver = context.getContentResolver();
		Uri.Builder builder = null;
		boolean findContent = false;
		Cursor timeCursor = null;
		String strStore = "";
		for(String providerStr : contentProviderLast){
			builder = Uri.parse(
					"content://"+ contentProviderFirst + providerStr + "/instances/when").buildUpon();
			ContentUris.appendId(builder, firstTime.getTimeInMillis());
			ContentUris.appendId(builder, lastTime.getTimeInMillis());
			timeCursor = contentResolver.query(builder.build(),
					new String[] { "event_id", "begin", "end" }, null, null,
					"begin");
			strStore = providerStr;
			if (timeCursor != null)
			{
				findContent = true;
				break;
			}
		}
		if (!findContent){
			Handler handler = new Handler(Looper.getMainLooper());
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, context.getString(R.string.Read_calendar_unsuccessfully_words), Toast.LENGTH_LONG)
					.show();
				}
			});
	        return null;
		}
		while (timeCursor.moveToNext()) {
			TimeCondition timecondition = new TimeCondition();
			Cursor cursor = contentResolver
					.query(Uri
							.parse("content://" + contentProviderFirst + strStore + "/events"),
							new String[] { "title", "description",
									"transparency" }, "_id" + "="
									+ timeCursor.getString(0), null, null);
			if (cursor.moveToFirst()) {
				timecondition.setTitle(cursor.getString(0));
				timecondition.setDescription(cursor.getString(1));
				
			}
			timecondition.setId(timeCursor.getInt(0));

			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTimeInMillis(timeCursor.getLong(1));
		    timecondition.setStartTime(startCalendar);

			Calendar finishCalendar = Calendar.getInstance();
			finishCalendar.setTimeInMillis(timeCursor.getLong(2));
			timecondition.setFinishTime(finishCalendar);
			timeCondList.add(timecondition);
			//EventsDBAdapter.insertEvent(event);
		}
		return timeCondList;
	}
	

}
