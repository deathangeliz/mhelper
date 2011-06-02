package com.mhelper.DatebaseAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;


public class ConditionsAdapter {
	private static MDBHelperAdapter mDbHelper;
	public ConditionsAdapter(Context ctx)
	{
		mDbHelper=new MDBHelperAdapter(ctx);
	}
	
	public int insertCondition(String cdesc)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(MDBHelperAdapter.KEY_CDESC, cdesc);
		mDbHelper.open();
		return (int)MDBHelperAdapter.getDBHelper().insert(MDBHelperAdapter.DATABASE_TABLE1, null, initialValues);
	}
	public boolean deleteCondition(int ct) {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().delete(MDBHelperAdapter.DATABASE_TABLE1, MDBHelperAdapter.KEY_CTYPE + "=" + ct, null) > 0;
	}
	public Cursor getAllConditions() {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().query(MDBHelperAdapter.DATABASE_TABLE1, new String[] { MDBHelperAdapter.KEY_CTYPE, MDBHelperAdapter.KEY_CDESC
		}, null, null, null, null, null);
	}

	public Cursor getCondition(int ct) throws SQLException {
		mDbHelper.open();
		Cursor mCursor =
			MDBHelperAdapter.getDBHelper().query(true, MDBHelperAdapter.DATABASE_TABLE1, new String[] { MDBHelperAdapter.KEY_CTYPE, MDBHelperAdapter.KEY_CDESC},  MDBHelperAdapter.KEY_CTYPE + "=" + ct, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public boolean updateCondition(int ct, String desc) {
		mDbHelper.open();
		ContentValues args = new ContentValues();
		args.put(MDBHelperAdapter.KEY_CTYPE, ct);
		args.put(MDBHelperAdapter.KEY_CDESC, desc);
		return MDBHelperAdapter.getDBHelper().update(MDBHelperAdapter.DATABASE_TABLE1, args, MDBHelperAdapter.KEY_CTYPE + "=" + ct, null) > 0;
	}
	public void dropConditions(){
		mDbHelper.open();
		mDbHelper.dropTable(MDBHelperAdapter.DATABASE_TABLE1);
	}
	public void recreateConditions()
	{
		mDbHelper.open();
		mDbHelper.recreateTable(1);
	}
}
