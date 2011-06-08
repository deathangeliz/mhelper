package com.mhelper.DatebaseAdapter;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;


public class DetailCondAdapter {
	private static MDBHelperAdapter mDbHelper;
	public DetailCondAdapter(Context ctx)
	{
		mDbHelper=new MDBHelperAdapter(ctx);
	}
	
	public int insertDetailCondition(String title, String description, String startTime, String finishTime,int point,int condEventid)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(MDBHelperAdapter.KEY_TITLE, title);
		initialValues.put(MDBHelperAdapter.KEY_DESCRIPTION, description);
		initialValues.put(MDBHelperAdapter.KEY_STARTTIME, startTime);
		initialValues.put(MDBHelperAdapter.KEY_FINISHTIME, finishTime);
		initialValues.put(MDBHelperAdapter.KEY_POINT, point);
		initialValues.put(MDBHelperAdapter.KEY_CONDEVENTID, condEventid);
		mDbHelper.open();
		int i = (int)MDBHelperAdapter.getDBHelper().insert(MDBHelperAdapter.DATABASE_TABLE4, null, initialValues);
		mDbHelper.closeclose();
		return i;
	}
	
	public boolean deleteCondition(int ceID) {
		mDbHelper.open();
		boolean b = MDBHelperAdapter.getDBHelper().delete(MDBHelperAdapter.DATABASE_TABLE4, MDBHelperAdapter.KEY_CONDEVENTID + "=" + ceID, null) > 0;
		mDbHelper.closeclose();
		return b;
	}

	public Cursor getDetailCondition(int ceID) {
		mDbHelper.open();
		Cursor mCursor =
			MDBHelperAdapter.getDBHelper().query(true, MDBHelperAdapter.DATABASE_TABLE4, new String[] { MDBHelperAdapter.KEY_CONDEVENTID,MDBHelperAdapter.KEY_TITLE,MDBHelperAdapter.KEY_DESCRIPTION,MDBHelperAdapter.KEY_STARTTIME,MDBHelperAdapter.KEY_FINISHTIME,MDBHelperAdapter.KEY_POINT}, 
				MDBHelperAdapter.KEY_CONDEVENTID + "=" + ceID, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		mDbHelper.closeclose();
		return mCursor;
	}

	public Cursor getAllDetailCondition() {
		mDbHelper.open();
		Cursor cursor = MDBHelperAdapter.getDBHelper().query(MDBHelperAdapter.DATABASE_TABLE4, new String[] { MDBHelperAdapter.KEY_CONDEVENTID,MDBHelperAdapter.KEY_TITLE,MDBHelperAdapter.KEY_DESCRIPTION,MDBHelperAdapter.KEY_STARTTIME,MDBHelperAdapter.KEY_FINISHTIME,MDBHelperAdapter.KEY_POINT}, 
				null, null, null, null, null);
		mDbHelper.closeclose();
		return cursor;
	}
	
	public boolean updateDetailCondition(String title, String description, String startTime, String finishTime,int point,int condEventid) {
		mDbHelper.open();
		ContentValues args = new ContentValues();
		args.put(MDBHelperAdapter.KEY_TITLE, title);
		args.put(MDBHelperAdapter.KEY_DESCRIPTION, description);
		args.put(MDBHelperAdapter.KEY_STARTTIME, startTime);
		args.put(MDBHelperAdapter.KEY_FINISHTIME, finishTime);
		args.put(MDBHelperAdapter.KEY_POINT, point);
		args.put(MDBHelperAdapter.KEY_CONDEVENTID, condEventid);
		boolean b = MDBHelperAdapter.getDBHelper().update(MDBHelperAdapter.DATABASE_TABLE4, args, MDBHelperAdapter.KEY_CONDEVENTID + "=" + condEventid, null) > 0;
		mDbHelper.closeclose();
		return b;
	}
	public void dropConditions(){
		mDbHelper.open();
		mDbHelper.dropTable(MDBHelperAdapter.DATABASE_TABLE4);
		mDbHelper.closeclose();
	}
	public void recreateCondictions()
	{
		mDbHelper.open();
		mDbHelper.recreateTable(4);
		mDbHelper.closeclose();
	}
}
