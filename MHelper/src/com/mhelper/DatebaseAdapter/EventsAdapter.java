package com.mhelper.DatebaseAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;


public class EventsAdapter {
	private static MDBHelperAdapter mDbHelper;
	public EventsAdapter(Context ctx)
	{
		mDbHelper=new MDBHelperAdapter(ctx);
	}
	
	public long insertEvent(String edesc)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(MDBHelperAdapter.KEY_EDESC, edesc);
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().insert(MDBHelperAdapter.DATABASE_TABLE2, null, initialValues);
	}
	public boolean deleteEvent(int et) {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().delete(MDBHelperAdapter.DATABASE_TABLE2, MDBHelperAdapter.KEY_ETYPE + "=" + et, null) > 0;
	}
	public Cursor getAllEvents() {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().query(MDBHelperAdapter.DATABASE_TABLE2, new String[] { MDBHelperAdapter.KEY_ETYPE, MDBHelperAdapter.KEY_EDESC
		}, null, null, null, null, null);
	}

	public Cursor getEvent(int et) throws SQLException {
		mDbHelper.open();
		Cursor mCursor =
			MDBHelperAdapter.getDBHelper().query(true, MDBHelperAdapter.DATABASE_TABLE2, new String[] { MDBHelperAdapter.KEY_ETYPE, MDBHelperAdapter.KEY_EDESC},  MDBHelperAdapter.KEY_ETYPE + "=" + et, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public boolean updateEvent(int et, String edesc) {
		mDbHelper.open();
		ContentValues args = new ContentValues();
		args.put(MDBHelperAdapter.KEY_ETYPE, et);
		args.put(MDBHelperAdapter.KEY_EDESC, edesc);
		return MDBHelperAdapter.getDBHelper().update(MDBHelperAdapter.DATABASE_TABLE2, args, MDBHelperAdapter.KEY_ETYPE + "=" + et, null) > 0;
	}
	public void dropEvents(){
		mDbHelper.open();
		mDbHelper.dropTable(MDBHelperAdapter.DATABASE_TABLE2);
	}
	public void recreateEvents()
	{
		mDbHelper.open();
		mDbHelper.recreateTable(2);
	}
}
