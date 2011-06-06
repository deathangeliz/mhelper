package com.mhelper.DatebaseAdapter;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MDBHelperAdapter {
	public static final String KEY_CTYPE="ctype";
	public static final String KEY_CDESC="cdesc";
	public static final String KEY_ETYPE="etype";
	public static final String KEY_EDESC="edesc";
	public static final String KEY_CEID="ceid";
	public static final String KEY_CONDTYPE="condtype";
	public static final String KEY_EVENTTYPE="eventtype";
	public static final String KEY_TITLE="title";
	public static final String KEY_DESCRIPTION="description";
	public static final String KEY_STARTTIME="starttime";
	public static final String KEY_FINISHTIME="finishtime";
	public static final String KEY_POINT="point";
	public static final String KEY_CONDEVENTID="condeventid";
	public static final String KEY_CDID="cdid"; 
	public static final String KEY_DETAILEVENTSORT="detaileventsort";
	public static final String KEY_NOTIFICATIONTYPE="notificationtype";
	public static final String KEY_NOTIFICATIONMESSAGE="notificationmessage";
	public static final String KEY_WALLPAPERURI="wallpaperuri";
	private static DatabaseHelper mDbHelper;
	private static SQLiteDatabase mDb;
	
	public static final String DATABASE_NAME = "MDB";
	public static final String DATABASE_TABLE1 = "conditions";
	public static final String DATABASE_TABLE2 = "events";
	public static final String DATABASE_TABLE3 = "condevent";
	public static final String DATABASE_TABLE4 = "detailcond";	
	public static final String DATABASE_TABLE5 = "detailevent";	
	public static final String DATABASE_TABLE6 = "notificationevent";	
	public static final String DATABASE_TABLE7 = "wallpaperevent";
	public static final int DATABASE_VERSION = 1;
	private final Context mCtx;
	private static final String DATABASE_CREATE1 = "create table conditions (ctype INTEGER  primary key autoincrement, "
		+ "cdesc text not null);";
	private static final String DATABASE_CREATE2 = "create table events (etype INTEGER primary key autoincrement, "
		+ "edesc text not null);";
	private static final String DATABASE_CREATE3 = "create table condevent (ceid INTEGER primary key autoincrement, "
		+ "condtype integer not null,eventtype integer not null,"
		+"FOREIGN KEY (condtype) REFERENCES conditions(ctype),"
		+"FOREIGN KEY (eventtype) REFERENCES events(etype));";
	private static final String DATABASE_CREATE4 = "create table detailcond (title text not null, "
		+"description text not null,"
		+"starttime text not null,"
		+"finishtime text not null,"
		+"point integer not null,"
		+"condeventid integer primary key,"
		+"FOREIGN KEY (condeventid) REFERENCES condevent(ceid)"
		+ ");";
	private static final String DATABASE_CREATE5 = "create table detailevent (condeventid INTEGER primary key, "
		+ "detaileventsort integer not null,"
		+"FOREIGN KEY (condeventid) REFERENCES condevent(ceid));";
	private static final String DATABASE_CREATE6 = "create table notificationevent (condeventid INTEGER primary key, "
		+ "noficationtype integer not null,"
		+"noficationmessage test not null, "
		+"FOREIGN KEY (condeventid) REFERENCES condevent(ceid));";
	private static final String DATABASE_CREATE7 = "create table wallpaperevent (condeventid INTEGER primary key, "
		+"wallpaperuri test not null, "
		+"FOREIGN KEY (condeventid) REFERENCES condevent(ceid));";
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE1);
			db.execSQL(DATABASE_CREATE2);
			db.execSQL(DATABASE_CREATE3);
			db.execSQL(DATABASE_CREATE4);
			db.execSQL(DATABASE_CREATE5);
			db.execSQL(DATABASE_CREATE6);
			db.execSQL(DATABASE_CREATE7);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS diary");
			onCreate(db);
		}
	}
	public MDBHelperAdapter(Context ctx) {
		this.mCtx = ctx;
	}
	public MDBHelperAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void closeclose() {
		mDbHelper.close();
	}
	public static SQLiteDatabase getDBHelper() throws SQLException {
		return mDb;
	}
	public void dropTable(String table)
	{
		try {
			mDb.execSQL("drop table " + table);
		} catch (SQLException e) {
		}
	}
	public void recreateTable(int i){
		String table = null,database_create = null;
		if(i==1)
		{table="conditions";database_create=DATABASE_CREATE1;}
		else if(i==2)
		{table="events";database_create=DATABASE_CREATE2;}
		else if(i==3)
		{table="condevent";database_create=DATABASE_CREATE3;}
		else if(i==4)
		{table="detailcond";database_create=DATABASE_CREATE4;}
		else if(i==5)
		{table="detailevent";database_create=DATABASE_CREATE5;}
		else if(i==6)
		{table="notificationevent";database_create=DATABASE_CREATE6;}
		else if(i==7)
		{table="wallpaperevent";database_create=DATABASE_CREATE7;}
		try {
			MDBHelperAdapter.getDBHelper().execSQL("DROP TABLE IF EXISTS "+table);
			mDb.execSQL(database_create);
		} catch (SQLException e) {
		}

	}
}
