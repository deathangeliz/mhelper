package com.mhelper.DatebaseAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;


public class DetailEventAdapter {
	private static MDBHelperAdapter mDbHelper;
	public DetailEventAdapter(Context ctx)
	{
		mDbHelper=new MDBHelperAdapter(ctx);
	}
	
	public long insertDetailEvent(int ceid,int sort)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(MDBHelperAdapter.KEY_CONDEVENTID, ceid);
		initialValues.put(MDBHelperAdapter.KEY_DETAILSORT, sort);
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().insert(MDBHelperAdapter.DATABASE_TABLE5, null, initialValues);
	}
	public boolean deleteDetailEvent(int ceid) {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().delete(MDBHelperAdapter.DATABASE_TABLE5, MDBHelperAdapter.KEY_CONDEVENTID + "=" + ceid, null) > 0;
	}
	public Cursor getAllDetailEvents() {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().query(MDBHelperAdapter.DATABASE_TABLE5, new String[] { MDBHelperAdapter.KEY_CONDEVENTID, MDBHelperAdapter.KEY_DETAILSORT
		}, null, null, null, null, null);
	}

	public Cursor getDetailEvent(int ceid) throws SQLException {
		mDbHelper.open();
		Cursor mCursor =
			MDBHelperAdapter.getDBHelper().query(true, MDBHelperAdapter.DATABASE_TABLE5, new String[] { MDBHelperAdapter.KEY_CONDEVENTID, MDBHelperAdapter.KEY_DETAILSORT},  MDBHelperAdapter.KEY_CONDEVENTID + "=" + ceid, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public boolean updateEvents(int ceid, int sort) {
		mDbHelper.open();
		ContentValues args = new ContentValues();
		args.put(MDBHelperAdapter.KEY_CONDEVENTID, ceid);
		args.put(MDBHelperAdapter.KEY_DETAILSORT, sort);
		return MDBHelperAdapter.getDBHelper().update(MDBHelperAdapter.DATABASE_TABLE5, args, MDBHelperAdapter.KEY_CONDEVENTID + "=" + ceid, null) > 0;
	}
	public void dropEvents(){
		mDbHelper.open();
		mDbHelper.dropTable(MDBHelperAdapter.DATABASE_TABLE5);
	}
	public void recreateEvents()
	{
		mDbHelper.open();
		mDbHelper.recreateTable(5);
	}
}
