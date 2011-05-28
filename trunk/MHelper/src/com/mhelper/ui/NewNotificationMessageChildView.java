package com.mhelper.ui;

import com.mhelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

public class NewNotificationMessageChildView extends LinearLayout {
	EditText newNotificationEdit;
	
	public NewNotificationMessageChildView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_notification_message, this, true);
		
		newNotificationEdit = (EditText)findViewById(R.id.newNotificationEdit);
	}

	public String getEditText() {
		return newNotificationEdit.getText().toString();
	}
	
	public void setEditText(String str) {
		newNotificationEdit.setText(str);
	}
}
