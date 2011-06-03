package com.mhelper.ui;

import com.mhelper.R;
import com.mhelper.middle.MHelperStrings;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class NewNotificationMessageChildView extends LinearLayout {
	EditText newNotificationEdit;
	Button newNotificationButton;
	Context context;
	
	public NewNotificationMessageChildView(Context _context) {
		super(_context);
		context = _context;
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_notification_message, this, true);
		
		newNotificationEdit = (EditText)findViewById(R.id.newNotificationEdit);
		newNotificationButton = (Button)findViewById(R.id.newNotificationButton);
		newNotificationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((NewEventSettings)context).notificationContent = getEditText();
				Editor editor = PreferenceManager.getDefaultSharedPreferences(
						context.getApplicationContext()).edit();
				editor.putString(MHelperStrings.UI_NOTIFICATION_CONTENT, getEditText());
				editor.commit();
			}
		});
	}

	public String getEditText() {
		return newNotificationEdit.getText().toString();
	}
	
	public void setEditText(String str) {
		newNotificationEdit.setText(str);
	}
}
