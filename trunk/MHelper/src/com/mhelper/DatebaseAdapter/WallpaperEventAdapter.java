package com.mhelper.DatebaseAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

public class WallpaperEventAdapter {
	private static MDBHelperAdapter mDbHelper;
	public WallpaperEventAdapter(Context ctx)
	{
		mDbHelper=new MDBHelperAdapter(ctx);
	}
	
	public int insertWallpaperEvent(int ceid,String uri)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(MDBHelperAdapter.KEY_CONDEVENTID, ceid);
		initialValues.put(MDBHelperAdapter.KEY_WALLPAPERURI, uri);
		mDbHelper.open();
		return (int)MDBHelperAdapter.getDBHelper().insert(MDBHelperAdapter.DATABASE_TABLE7, null, initialValues);
	}
	public boolean deleteWallpaperEvent(int ceid) {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().delete(MDBHelperAdapter.DATABASE_TABLE7, MDBHelperAdapter.KEY_CONDEVENTID + "=" + ceid, null) > 0;
	}
	public Cursor getAllWallpaperEvents() {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().query(MDBHelperAdapter.DATABASE_TABLE7, new String[] { MDBHelperAdapter.KEY_CONDEVENTID, MDBHelperAdapter.KEY_WALLPAPERURI
		}, null, null, null, null, null);
	}

	public Cursor getWallpaperEvent(int ceid) throws SQLException {
		mDbHelper.open();
		Cursor mCursor =
			MDBHelperAdapter.getDBHelper().query(true, MDBHelperAdapter.DATABASE_TABLE7, new String[] { MDBHelperAdapter.KEY_CONDEVENTID, MDBHelperAdapter.KEY_WALLPAPERURI},  MDBHelperAdapter.KEY_CONDEVENTID + "=" + ceid, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public boolean updateWallpaperEvent(int ceid, String uri) {
		mDbHelper.open();
		ContentValues args = new ContentValues();
		args.put(MDBHelperAdapter.KEY_CONDEVENTID, ceid);
		args.put(MDBHelperAdapter.KEY_WALLPAPERURI, uri);
		return MDBHelperAdapter.getDBHelper().update(MDBHelperAdapter.DATABASE_TABLE7, args, MDBHelperAdapter.KEY_CONDEVENTID + "=" + ceid, null) > 0;
	}
	public void dropWallpaperEvents(){
		mDbHelper.open();
		mDbHelper.dropTable(MDBHelperAdapter.DATABASE_TABLE7);
	}
	public void recreateWallpaperEvents()
	{
		mDbHelper.open();
		mDbHelper.recreateTable(7);
	}
}
