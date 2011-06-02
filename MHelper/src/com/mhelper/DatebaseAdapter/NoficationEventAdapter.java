package com.mhelper.DatebaseAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

public class NoficationEventAdapter {
	private static MDBHelperAdapter mDbHelper;
	public NoficationEventAdapter(Context ctx)
	{
		mDbHelper=new MDBHelperAdapter(ctx);
	}
	
	public int insertNoficationEvent(int ceid,int ntfctype,String message)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(MDBHelperAdapter.KEY_CONDEVENTID, ceid);
		initialValues.put(MDBHelperAdapter.KEY_NOTIFICATIONTYPE, ntfctype);
		initialValues.put(MDBHelperAdapter.KEY_NOTIFICATIONMESSAGE, message);
		mDbHelper.open();
		return (int)MDBHelperAdapter.getDBHelper().insert(MDBHelperAdapter.DATABASE_TABLE6, null, initialValues);
	}
	public boolean deleteDetailEvent(int ceid) {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().delete(MDBHelperAdapter.DATABASE_TABLE6, MDBHelperAdapter.KEY_CONDEVENTID + "=" + ceid, null) > 0;
	}
	public Cursor getAllDetailEvents() {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().query(MDBHelperAdapter.DATABASE_TABLE6, new String[] { MDBHelperAdapter.KEY_CONDEVENTID, MDBHelperAdapter.KEY_NOTIFICATIONTYPE,MDBHelperAdapter.KEY_NOTIFICATIONMESSAGE
		}, null, null, null, null, null);
	}

	public Cursor getDetailEvent(int ceid) throws SQLException {
		mDbHelper.open();
		Cursor mCursor =
			MDBHelperAdapter.getDBHelper().query(true, MDBHelperAdapter.DATABASE_TABLE6, new String[] { MDBHelperAdapter.KEY_CONDEVENTID, MDBHelperAdapter.KEY_NOTIFICATIONTYPE,MDBHelperAdapter.KEY_NOTIFICATIONMESSAGE},  MDBHelperAdapter.KEY_CONDEVENTID + "=" + ceid, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public boolean updateEvents(int ceid, int ntfctype,String message) {
		mDbHelper.open();
		ContentValues args = new ContentValues();
		args.put(MDBHelperAdapter.KEY_CONDEVENTID, ceid);
		args.put(MDBHelperAdapter.KEY_NOTIFICATIONTYPE, ntfctype);
		args.put(MDBHelperAdapter.KEY_NOTIFICATIONMESSAGE, message);
		return MDBHelperAdapter.getDBHelper().update(MDBHelperAdapter.DATABASE_TABLE6, args, MDBHelperAdapter.KEY_CONDEVENTID + "=" + ceid, null) > 0;
	}
	public void dropEvents(){
		mDbHelper.open();
		mDbHelper.dropTable(MDBHelperAdapter.DATABASE_TABLE6);
	}
	public void recreateEvents()
	{
		mDbHelper.open();
		mDbHelper.recreateTable(6);
	}
}
