package com.mhelper.middle;

import com.mhelper.DatebaseAdapter.DetailEventAdapter;
import com.mhelper.DatebaseAdapter.MDBHelperAdapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

/*TODO
 <receiver android:name="EventBroadcastReceiver">
			<intent-filter>
				<action
					android:name="com.mhelper.action.EVENTBROADCASTRECEIVER"/>
			</intent-filter>
		</receiver>*/ 
public class EventBroadcastReceiver extends BroadcastReceiver{
	private Context context;
	private Cursor mDEACursor;
	private DetailEventAdapter mDEAHelper;

	private int sort;
	public void onReceive(Context context,Intent intent)
	{
		this.context = context;
		//TODO intent.putExtra(MDBHelperAdapter.KEY_CONDEVENTID, "3");
		Bundle extras = intent.getExtras();
		
		mDEACursor=mDEAHelper.getDetailEvent(extras.getShort(MDBHelperAdapter.KEY_CONDEVENTID));
		sort=mDEACursor.getShort(1);
		if(sort==0)
		{
			NoneDetail();
		}
	}
	
	
	private void NoneDetail()
	{
		//TODO ¹Ø»ú
		
	}
	
}
