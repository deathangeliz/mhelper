package com.mhelper.events;
import com.mhelper.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class NotificationEvent{
	private int type = 0;
	private String str = "";
	private Context context;
	private AlertDialog.Builder ad;
	private NotificationManager notificationManager;
	private int condEventId = 0;
	public NotificationEvent(int type, String str, Context context, int condEventId){
		this.type = type;
		this.str = str;
		this.context = context;
		this.condEventId = condEventId;
	}
	
	public void notifyNotification()
	{
		switch(type){
		case 0://toast
			Handler handler = new Handler(Looper.getMainLooper());
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, str, Toast.LENGTH_SHORT);
				}
			});
			break;
		case 1://notification
			notificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
			Notification notification = new Notification(R.drawable.icon, str, System.currentTimeMillis());
			notificationManager.notify(condEventId, notification);
			break;
		case 2:// dialogue
			ad = new AlertDialog.Builder(context);
			ad.setCancelable(true);
			ad.setTitle(context.getString(R.string.DIALOG_TITLE));
			LayoutInflater factory = LayoutInflater.from(context);
			final View v1=factory.inflate(R.layout.dialog_view,null);
			ad.setView(v1);
			TextView textView = (TextView)v1.findViewById(R.id.dialogTextView);
			textView.setText(str);
			ad.show();
			break;
		default:
			break;
		}
	}
}