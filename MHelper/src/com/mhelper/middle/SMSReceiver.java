package com.mhelper.middle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {
	
	private  static final String queryString = "#@control";
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
				for(SmsMessage  message : messages)
				{String msg=message.getMessageBody();
				 String to = message.getOriginatingAddress();
				 if(msg.toLowerCase().startsWith(queryString))
				 {
					 if(sms.equals("#@control关机")){
						 Intent intent = new Intent("MHelperBroadcast");
						 intent.putExtra("enventId", 1);
						 _context.sendBroadcast(intent);
					 }
					 if(sms.equals("#@control静音")){
						 Intent intent = new Intent("MHelperBroadcast");
						 intent.putExtra("enventId", 2);
						 _context.sendBroadcast(intent);
					 }
					 if(sms.equals("#@control飞行模式")){
						 Intent intent = new Intent("MHelperBroadcast");
						 intent.putExtra("enventId", 3);
						 _context.sendBroadcast(intent);
					 }
					 if(sms.equals("#@control震动")){
						 Intent intent = new Intent("MHelperBroadcast");
						 intent.putExtra("enventId", 4);
						 _context.sendBroadcast(intent);
					 }
					 if(sms.equals("#@control更换壁纸")){
						 Intent intent = new Intent("MHelperBroadcast");
						 intent.putExtra("enventId", 1);
						 _context.sendBroadcast(intent);
					 }
					 
				 }
				}
			}
		}
	}
}
				 
					
				
				
				
			
			
	
	


