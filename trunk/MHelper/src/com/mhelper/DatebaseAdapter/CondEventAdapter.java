package com.mhelper.DatebaseAdapter;

import java.util.ArrayList;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

public class CondEventAdapter {
	private static MDBHelperAdapter mDbHelper;
	private DetailCondAdapter DCHelper;
	public CondEventAdapter(Context ctx)
	{
		mDbHelper=new MDBHelperAdapter(ctx);
		DCHelper=new DetailCondAdapter(ctx);
	}
	
	public int insertCondEvent(int condtype,int eventtype)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(MDBHelperAdapter.KEY_CONDTYPE, condtype);
		initialValues.put(MDBHelperAdapter.KEY_EVENTTYPE, eventtype);
		mDbHelper.open();
		return (int)MDBHelperAdapter.getDBHelper().insert(MDBHelperAdapter.DATABASE_TABLE3, null, initialValues);
	}
	public boolean deleteCondEvent(int ceid) {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().delete(MDBHelperAdapter.DATABASE_TABLE3, MDBHelperAdapter.KEY_CEID + "=" + ceid, null) > 0;
	}
	public Cursor getAllCondEvent() {
		mDbHelper.open();
		return MDBHelperAdapter.getDBHelper().query(MDBHelperAdapter.DATABASE_TABLE3, new String[] { MDBHelperAdapter.KEY_CEID,MDBHelperAdapter.KEY_CONDTYPE, MDBHelperAdapter.KEY_EVENTTYPE
		}, null, null, null, null, null);
	}

	public Cursor getCondEvent(int ceid) throws SQLException {
		mDbHelper.open();
		Cursor mCursor =
			MDBHelperAdapter.getDBHelper().query(true, MDBHelperAdapter.DATABASE_TABLE3, new String[] {  MDBHelperAdapter.KEY_CEID,MDBHelperAdapter.KEY_CONDTYPE, MDBHelperAdapter.KEY_EVENTTYPE},  MDBHelperAdapter.KEY_CEID + "=" + ceid, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public boolean updateCondEvent(int ceid,int condtype,int eventtype) {
		mDbHelper.open();
		ContentValues args = new ContentValues();
		args.put(MDBHelperAdapter.KEY_CONDTYPE, condtype);
		args.put(MDBHelperAdapter.KEY_EVENTTYPE, eventtype);
		return MDBHelperAdapter.getDBHelper().update(MDBHelperAdapter.DATABASE_TABLE3, args, MDBHelperAdapter.KEY_CEID + "=" + ceid, null) > 0;
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
	
	public ArrayList<String> getGroupData()
	{
		ArrayList<String> lst=new ArrayList();
		Cursor CECursor;
		Cursor DCCursor;
		String groupData;
		String EventName=new String();
		
		CECursor= getAllCondEvent();
		
		while(CECursor.moveToLast()==true)
		{
			DCCursor=DCHelper.getDetailCondition(Integer.valueOf(CECursor.getString(0)));
			
			switch(Integer.valueOf(CECursor.getString(2))){
			case 0:
				EventName="shutdown";
				break;
			case 1:
				EventName="Silent mode";
				break;
			//TODO 其他类型的ETYPE
			default:
				break;
			}
			groupData=DCCursor.getString(0)+EventName+CECursor.getString(0);//条件名＋事件名＋编号
			
			lst.add(groupData);
			CECursor.moveToNext();
		}
		return lst;
	}
	
	public ArrayList<ArrayList <String>> getChildrenData()
	{
		ArrayList<String> lstc=new ArrayList();
		ArrayList<ArrayList <String>> lst = new ArrayList<ArrayList <String>>();
		Cursor DCCursor;
		Cursor CECursor;
		String EventName=new String();
		CECursor= getAllCondEvent();
		
		while(CECursor.moveToLast()==true)
		{
			DCCursor=DCHelper.getDetailCondition(Integer.valueOf(CECursor.getString(0)));
			
			switch(Integer.valueOf(CECursor.getString(2))){
			case 0:
				EventName="shutdown";
				break;
			case 1:
				EventName="Silent mode";
				break;
			//TODO 其他类型的ETYPE
			default:
				break;
			}
			lstc.clear();
			lstc.add(DCCursor.getString(2)+DCCursor.getString(3)+EventName);//开始时间＋结束时间＋事件名称
			
			lst.add(lstc);
			
			CECursor.moveToNext();
		}
		return lst;
	}
	
	public ArrayList<Integer> getCondEventId(){
		ArrayList<Integer> lst=new ArrayList<Integer>();
		Cursor CECursor;
		
		CECursor= getAllCondEvent();
		while(CECursor.moveToLast()==true)
		{
			lst.add(Integer.valueOf(CECursor.getString(0)));
			CECursor.moveToNext();
		}
		return lst;
	}
}
