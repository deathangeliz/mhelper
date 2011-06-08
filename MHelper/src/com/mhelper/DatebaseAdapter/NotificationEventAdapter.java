package com.mhelper.DatebaseAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

public class NotificationEventAdapter {
	private static MDBHelperAdapter mDbHelper;
	public NotificationEventAdapter(Context ctx)
	{
		mDbHelper=new MDBHelperAdapter(ctx);
	}
	
	public int insertNotificationEvent(int ceid,int ntfctype,String message)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(MDBHelperAdapter.KEY_CONDEVENTID, ceid);
		initialValues.put(MDBHelperAdapter.KEY_NOTIFICATIONTYPE, ntfctype);
		initialValues.put(MDBHelperAdapter.KEY_NOTIFICATIONMESSAGE, message);
		mDbHelper.open();
		int i = (int)MDBHelperAdapter.getDBHelper().insert(MDBHelperAdapter.DATABASE_TABLE6, null, initialValues);
		return i;
	}
	public boolean deleteNotificationEvent(int ceid) {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().delete(MDBHelperAdapter.DATABASE_TABLE6, MDBHelperAdapter.KEY_CONDEVENTID + "=" + ceid, null) > 0;
	}
	public Cursor getAllNotificationEvents() {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().query(MDBHelperAdapter.DATABASE_TABLE6, new String[] { MDBHelperAdapter.KEY_CONDEVENTID, MDBHelperAdapter.KEY_NOTIFICATIONTYPE,MDBHelperAdapter.KEY_NOTIFICATIONMESSAGE
		}, null, null, null, null, null);
	}

	public Cursor getNotificationEvent(int ceid) throws SQLException {
		mDbHelper.open();
		Cursor mCursor =
			MDBHelperAdapter.getDBHelper().query(true, MDBHelperAdapter.DATABASE_TABLE6, new String[] { MDBHelperAdapter.KEY_CONDEVENTID, MDBHelperAdapter.KEY_NOTIFICATIONTYPE,MDBHelperAdapter.KEY_NOTIFICATIONMESSAGE},  MDBHelperAdapter.KEY_CONDEVENTID + "=" + ceid, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		mDbHelper.closeclose();
		return mCursor;
	}

	public boolean updateNotificationEvent(int ceid, int ntfctype,String message) {
		mDbHelper.open();
		ContentValues args = new ContentValues();
		args.put(MDBHelperAdapter.KEY_CONDEVENTID, ceid);
		args.put(MDBHelperAdapter.KEY_NOTIFICATIONTYPE, ntfctype);
		args.put(MDBHelperAdapter.KEY_NOTIFICATIONMESSAGE, message);
		return MDBHelperAdapter.getDBHelper().update(MDBHelperAdapter.DATABASE_TABLE6, args, MDBHelperAdapter.KEY_CONDEVENTID + "=" + ceid, null) > 0;
	}
	public void dropNotificationEvents(){
		mDbHelper.open();
		mDbHelper.dropTable(MDBHelperAdapter.DATABASE_TABLE6);
	}
	public void recreateNotificationEvents()
	{
		mDbHelper.open();
		mDbHelper.recreateTable(6);
	}
}
