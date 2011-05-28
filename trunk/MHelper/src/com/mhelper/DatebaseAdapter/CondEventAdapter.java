package com.mhelper.DatebaseAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

public class CondEventAdapter {
	private static MDBHelperAdapter mDbHelper;
	public CondEventAdapter(Context ctx)
	{
		mDbHelper=new MDBHelperAdapter(ctx);
	}
	
	public long insertCondEvent(int condtype,int eventtype)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(MDBHelperAdapter.KEY_CONDTYPE, condtype);
		initialValues.put(MDBHelperAdapter.KEY_EVENTTYPE, eventtype);
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().insert(MDBHelperAdapter.DATABASE_TABLE3, null, initialValues);
	}
	public boolean deleteCondEvent(int et) {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().delete(MDBHelperAdapter.DATABASE_TABLE3, MDBHelperAdapter.KEY_CEID + "=" + et, null) > 0;
	}
	public Cursor getAllCondEvent() {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().query(MDBHelperAdapter.DATABASE_TABLE3, new String[] { MDBHelperAdapter.KEY_CONDTYPE, MDBHelperAdapter.KEY_EVENTTYPE
		}, null, null, null, null, null);
	}

	public Cursor getCondEvent(int eventtype) throws SQLException {
		mDbHelper.open();
		Cursor mCursor =
			MDBHelperAdapter.getDBHelper().query(true, MDBHelperAdapter.DATABASE_TABLE3, new String[] { MDBHelperAdapter.KEY_CONDTYPE, MDBHelperAdapter.KEY_EVENTTYPE},  MDBHelperAdapter.KEY_CEID + "=" + eventtype, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public boolean updateCondEvent(int et,int condtype,int eventtype) {
		mDbHelper.open();
		ContentValues args = new ContentValues();
		args.put(MDBHelperAdapter.KEY_CONDTYPE, condtype);
		args.put(MDBHelperAdapter.KEY_EVENTTYPE, eventtype);
		return MDBHelperAdapter.getDBHelper().update(MDBHelperAdapter.DATABASE_TABLE3, args, MDBHelperAdapter.KEY_CEID + "=" + et, null) > 0;
	}
	public void dropCondEvent(){
		mDbHelper.open();
		mDbHelper.dropTable(MDBHelperAdapter.DATABASE_TABLE3);
	}
	public void recreateCondEvent()
	{
		mDbHelper.open();
		mDbHelper.recreateTable(3);
	}
}
