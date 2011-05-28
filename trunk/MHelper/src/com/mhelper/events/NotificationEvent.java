package com.mhelper.events;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.Gravity;
import android.widget.Toast;

public class NotificationEvent extends Activity{
	
	
/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent =new Intent();
		Bundle bundle= intent.getExtras();

		Context context= getApplicationContext();
		String msg;
		int duration=Toast.LENGTH_LONG;	
		Toast toast=Toast.makeText(context,msg, duration);
		int offsetX=0;
		int offsetY=0;
		toast.setGravity(Gravity.BOTTOM, offsetX, offsetY);
		toast.show();
		
		
		Dialog d=new Dialog(this);
		

		
		String svcName=Context.NOTIFICATION_SERVICE;
		NotificationManager notificationManager=(NotificationManager)getSystemService(svcName);
		String tickerText;
		long when=System.currentTimeMillis();
		Notification notification=new Notification(0, tickerText,when);
		notification.number++;
		
		
		
	}
}