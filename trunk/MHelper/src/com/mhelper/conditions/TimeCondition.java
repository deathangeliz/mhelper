package com.mhelper.conditions;

import java.util.Calendar;

import com.mhelper.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TimeCondition {
	private String title;
	private String description;
	private Calendar startTime;
	private Calendar finishTime;
	private boolean point;
	private int eventid;
	private int id;
	private long condEventld=0;
	
	
	public TimeCondition(Context context){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		int lastConEventId = pref.getInt(context.getString(R.string.COND_EVENT_ID_KEY), 1);
		lastConEventId ++;
		this.condEventld = lastConEventId;
		editor.putInt(context.getString(R.string.COND_EVENT_ID_KEY), lastConEventId);
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}
	public Calendar getStartTime() {
		return startTime;
	}
	public void setFinishTime(Calendar finishTime) {
		this.finishTime = finishTime;
	}
	public Calendar getFinishTime() {
		return finishTime;
	}
	public void setPoint(boolean point) {
		this.point = point;
	}
	public boolean isPoint() {
		return point;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setCondEventld(long condEventld) {
	
	}
	public long getCondEventld() {
		return condEventld;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}

	public void setEventid(int eventid) {
		this.eventid = eventid;
	}

	public int getEventid() {
		return eventid;
	}
	
	

}