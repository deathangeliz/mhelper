package com.mhelper.middle;

import com.mhelper.events.NormalEvent;
import com.mhelper.events.PlaneEvent;
import com.mhelper.events.SilentEvent;
import com.mhelper.events.VibratorEvent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {
	
	private  static final String queryString = "@mhelper-";
	private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	public void onReceive(Context _context,Intent _intent){
		if(_intent.getAction().equals(SMS_RECEIVED)){
			SmsManager sms =SmsManager.getDefault();
			Bundle bundle = _intent.getExtras();
			if(bundle!=null){
				Object[] pdus = (Object[])bundle.get("pdus");
				SmsMessage[] messages= new SmsMessage[pdus.length];
				for(int i=0;i<pdus.length;i++)
					messages[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
				for(SmsMessage  message : messages) {
					String msg=message.getMessageBody();
					 String to = message.getOriginatingAddress();
					 if(msg.toLowerCase().startsWith(queryString))
					 {
						 String cmd = msg.substring(queryString.length());
						 if(cmd.equalsIgnoreCase("shutdown")){
							 /*Intent intent = new Intent(MHelperBroadcastReceiver.MHELPER_BROADCAST);
							 intent.putExtra(MHelperStrings.COND_TYPE, 2);
							 intent.putExtra("enventName","SMS");
							 intent.putExtra(MHelperStrings.EVENT_TYPE, 0);
							 _context.sendBroadcast(intent);*/
						 }
						 else if(cmd.equalsIgnoreCase("slient")){
							 SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(_context.getApplicationContext());
							 boolean b = prefs.getBoolean(MHelperStrings.SMS_SLIENT, false);
							 if (b)
							     _context.startService(new Intent(_context, SilentEvent.class));
							 /*Intent intent = new Intent(MHelperBroadcastReceiver.MHELPER_BROADCAST);
							 intent.putExtra(MHelperStrings.COND_TYPE, 2);
							 intent.putExtra(MHelperStrings.EVENT_TYPE, 1);
							 intent.putExtra("enventName","SMS");
							 _context.sendBroadcast(intent);*/
						 }
						 else if(cmd.equalsIgnoreCase("airmode")){
							 SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(_context.getApplicationContext());
							 boolean b = prefs.getBoolean(MHelperStrings.SMS_AIRMODE, false);
							 if (b)
							     _context.startService(new Intent(_context, PlaneEvent.class));
							 /*Intent intent = new Intent(MHelperBroadcastReceiver.MHELPER_BROADCAST);
							 intent.putExtra(MHelperStrings.COND_TYPE, 2);
							 intent.putExtra(MHelperStrings.EVENT_TYPE, 3);
							 intent.putExtra("enventName","SMS");
							 _context.sendBroadcast(intent);*/
						 }
						 else if(cmd.equalsIgnoreCase("vibration")){
							 SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(_context.getApplicationContext());
							 boolean b = prefs.getBoolean(MHelperStrings.SMS_VIBRATION, false);
							 if (b)
							     _context.startService(new Intent(_context, VibratorEvent.class));
							 /*Intent intent = new Intent(MHelperBroadcastReceiver.MHELPER_BROADCAST);
							 intent.putExtra(MHelperStrings.COND_TYPE, 2);
							 intent.putExtra(MHelperStrings.EVENT_TYPE, 2);
							 intent.putExtra("enventName","SMS");
							 _context.sendBroadcast(intent);*/
						 }
						 else if(cmd.equalsIgnoreCase("normal")){
							 SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(_context.getApplicationContext());
							 boolean b = prefs.getBoolean(MHelperStrings.SMS_NORMAL, false);
							 if (b)
							     _context.startService(new Intent(_context, NormalEvent.class));
						 }
					 }
				}
			}
		}
	}
}
				 
					
				
				
				
			
			
	
	


