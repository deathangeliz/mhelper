package com.mhelper.middle;

import com.mhelper.DatebaseAdapter.DetailEventAdapter;
import com.mhelper.DatebaseAdapter.MDBHelperAdapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

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
		Bundle extras = intent.getExtras();
		if (extras == null) {
			Log.i("BroadcastReceiver.onReceive()", "extras == null");
			return;
		}		
			
		/*mDEACursor=mDEAHelper.getDetailEvent(extras.getShort(MDBHelperAdapter.KEY_CONDEVENTID));
		sort=mDEACursor.getShort(1);
		if(sort==0)
		{
			//关机事件
		}
		else if(sort==1)
		{
			if(extras.getShort("flag")==1)
			{
				//开启静音
			}
			if(extras.getShort("flag")==0)
			{
				//关闭静音
			}
		}
		else if(sort==2)
		{
			if(extras.getShort("flag")==1)
			{
				//开启飞行模式
			}
			if(extras.getShort("flag")==0)
			{
				//关闭静音模式
			}
		}
		else if(sort==3)
		{
			if(extras.getShort("flag")==1)
			{
				//开启震动
			}
			if(extras.getShort("flag")==0)
			{
				//关闭震动
			}
		}*/
		
		
	}
	
}
