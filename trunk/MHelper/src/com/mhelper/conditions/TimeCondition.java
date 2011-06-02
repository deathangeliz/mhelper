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
	private int id = 0;
	private long condEventld=0;
	
	
	public TimeCondition(){
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
	    this.condEventld = condEventld;
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
	
	

}