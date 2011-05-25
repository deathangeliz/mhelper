package com.mhelper.ui;

import com.mhelper.R;

import android.R.drawable;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewAlarmChildView extends LinearLayout {
	TextView newAlarmText;
	EditText newAlarmEdit;
	ImageView newAlarmImage;
	
	public NewAlarmChildView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_alarm_child, this, true);
		
		newAlarmText = (TextView)findViewById(R.id.newAlarmText);
		newAlarmEdit = (EditText)findViewById(R.id.newAlarmEdit);
		newAlarmImage = (ImageView)findViewById(R.id.newAlarmImage);
	}

	public void setAlarmText(String str){
		newAlarmText.setText(str);
	}
	
	public void setAlarmEdit(String str) {
		newAlarmEdit.setText(str);
	}
	
	public void setAlarm(Drawable drawable) {
		newAlarmImage.setImageDrawable(drawable);
	}
}
