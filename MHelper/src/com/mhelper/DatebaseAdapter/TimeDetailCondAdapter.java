package com.mhelper.DatebaseAdapter;

import java.util.ArrayList;
import java.util.Calendar;

import com.mhelper.conditions.TimeCondition;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class TimeDetailCondAdapter {
	private Context context;
	private int i = 0;
	private static final String KEY_ID = "_id";
	private static final int KEY_ID_COLUMN = 0;
	private static final String EVENT_ID = "event_id";//专门为 Google Calendar 准备的 ID
	private static final int EVENT_ID_COLUMN = 1;
	private static final String TITLE = "title";
	private static final int TITLE_COLUMN = 2;
	private static final String DESCRIPTION = "description";
	private static final int DESCRIPTION_COLUMN = 3;
	private static final String START_TIME = "start_time";
	private static final int START_TIME_COLUMN = 4;
	private static final String END_TIME = "end_time";
	private static final int END_TIME_COLUMN = 5;
	private static final String POINT = "point";
	private static final int POINT_COLUMN = 6;
	private static final String COND_EVENT_ID = "cond_event_id";
	private static final int COND_EVENT_ID_COLUMN = 7;
	private static final String DATABASE_TABLE = "timeCondDetailTable";
	private static final String DATABASE_NAME = "condDatabase.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CERATE = "create table " + DATABASE_TABLE + 
	                                               " (" + KEY_ID + " integer primary key autoincrement, "
	                                               + EVENT_ID + " integer, " +
	                                               TITLE + " text, " + 
	                                               DESCRIPTION + " text, " +
	                                               START_TIME + " long not null, " + 
	                                               END_TIME + " long, " + 
	                                               POINT + " bit not null, " + 
	                                               COND_EVENT_ID + " long not null)";
	private SQLiteDatabase db = null;
	private myDBHelper dbHelper = null;
	
	private TimeDetailCondAdapter(Context context){
		this.context = context;
		dbHelper = new myDBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public  void open(Context context) {
		if (db == null)
		    db = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		db.close();
		dbHelper.close();
	}
	
	public void clearDB()
	{
		db.delete(DATABASE_TABLE, null, null);
	}
	
	public long insertTimeCond(TimeCondition condition){
		ContentValues eventValue = new ContentValues();
		eventValue.put(EVENT_ID, condition.getId());
		eventValue.put(TITLE, condition.getTitle());
		eventValue.put(DESCRIPTION, condition.getDescription());
		eventValue.put(START_TIME, condition.getStartTime().getTimeInMillis());
		eventValue.put(END_TIME, condition.getFinishTime().getTimeInMillis());
		eventValue.put(POINT, (condition.isPoint()? 1 : 0));
		eventValue.put(COND_EVENT_ID, condition.getCondEventld());
		return db.insert(DATABASE_TABLE, null, eventValue);
	}
	
	public boolean removeTimeCond(int condEventId){
		return db.delete(DATABASE_TABLE, EVENT_ID + "=" + condEventId, null) > 0;
	}
	
	public boolean updateTimeCond(TimeCondition condition){
		ContentValues eventValue = new ContentValues();
		eventValue.put(EVENT_ID, condition.getId());
		eventValue.put(TITLE, condition.getTitle());
		eventValue.put(DESCRIPTION, condition.getDescription());
		eventValue.put(START_TIME, condition.getStartTime().getTimeInMillis());
		eventValue.put(END_TIME, condition.getFinishTime().getTimeInMillis());
		eventValue.put(POINT, (condition.isPoint()? 1 : 0));
		eventValue.put(COND_EVENT_ID, condition.getCondEventld());
		return db.update(DATABASE_TABLE, eventValue, COND_EVENT_ID + "=" + condition.getCondEventld(), null) > 0;
	} 
	
	public ArrayList<String> getStrs(long [] condEventIds){
		String str = "";
		ArrayList<String> strList = new ArrayList<String>();
		for(long condEventId : condEventIds){
			str = "";
			Cursor cursor = db.query(DATABASE_TABLE, new String[] { TITLE,
					DESCRIPTION, START_TIME, END_TIME, POINT }, COND_EVENT_ID
					+ "=" + condEventId, null, null, null, null);
			if (cursor.moveToFirst()) {
				String title = cursor.getString(TITLE_COLUMN);
				String description = cursor.getString(DESCRIPTION_COLUMN);
				Calendar startTime = Calendar.getInstance();
				startTime.setTimeInMillis(cursor.getLong(START_TIME_COLUMN));
				Calendar endTime = Calendar.getInstance();
				endTime.setTimeInMillis(cursor.getLong(END_TIME_COLUMN));
				boolean isPoint = (cursor.getInt(POINT_COLUMN) == 1 ? true
						: false);
				str = str + "StartTime: " + startTime.getTime().toString();
				if (!isPoint) {
					str = str + "\n" + "EndTime: "
							+ endTime.getTime().toString();
				}
			}
			strList.add(str);
		}
		return strList;
	}
	
    private static class myDBHelper extends SQLiteOpenHelper{

		public myDBHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CERATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w("EventsDBAdapter","Upgrading from version" + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
    }

}
