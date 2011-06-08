package com.mhelper.ui;

import com.mhelper.R;
import com.mhelper.middle.MHelperStrings;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class NewMessageTypeChildView extends LinearLayout {
	CheckBox slientCheckBox;
	CheckBox vibrationCheckBox;
	CheckBox airmodeCheckBox;
	CheckBox normalCheckBox;
	Context context;
	
	public NewMessageTypeChildView(Context _context) {
		super(_context);
		context = _context;
		
		String infService = Context.LAYOUT_INFLATER_SERVICE;
   		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_message_type_child, this, true);
		
		slientCheckBox = (CheckBox)findViewById(R.id.newSMSSlientCheckBox);
		vibrationCheckBox = (CheckBox)findViewById(R.id.newSMSVibrationCheckBox);
		airmodeCheckBox = (CheckBox)findViewById(R.id.newSMSAirCheckBox);
		normalCheckBox = (CheckBox)findViewById(R.id.newSMSNormalCheckBox);
		
		slientCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					((NewCondSettings)context).sms_slient = true;
					Editor editor = PreferenceManager.getDefaultSharedPreferences(
							context.getApplicationContext()).edit();
					editor.putBoolean(MHelperStrings.SMS_SLIENT, true);
					editor.commit();
				} else {
					((NewCondSettings)context).sms_slient = false;
					Editor editor = PreferenceManager.getDefaultSharedPreferences(
							context.getApplicationContext()).edit();
					editor.putBoolean(MHelperStrings.SMS_SLIENT, false);
					editor.commit();
				}
			}
		});
        vibrationCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					((NewCondSettings)context).sms_vibration = true;
					Editor editor = PreferenceManager.getDefaultSharedPreferences(
							context.getApplicationContext()).edit();
					editor.putBoolean(MHelperStrings.SMS_VIBRATION, true);
					editor.commit();
				} else {
					((NewCondSettings)context).sms_vibration = false;
					Editor editor = PreferenceManager.getDefaultSharedPreferences(
							context.getApplicationContext()).edit();
					editor.putBoolean(MHelperStrings.SMS_VIBRATION, false);
					editor.commit();
				}
			}
		});
        airmodeCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					((NewCondSettings)context).sms_airmode = true;
					Editor editor = PreferenceManager.getDefaultSharedPreferences(
							context.getApplicationContext()).edit();
					editor.putBoolean(MHelperStrings.SMS_AIRMODE, true);
					editor.commit();
				} else {
					((NewCondSettings)context).sms_airmode = false;
					Editor editor = PreferenceManager.getDefaultSharedPreferences(
							context.getApplicationContext()).edit();
					editor.putBoolean(MHelperStrings.SMS_AIRMODE, false);
					editor.commit();
				}
			}
		});
        normalCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					((NewCondSettings)context).sms_normal = true;
					Editor editor = PreferenceManager.getDefaultSharedPreferences(
							context.getApplicationContext()).edit();
					editor.putBoolean(MHelperStrings.SMS_NORMAL, true);
					editor.commit();
				} else {
					((NewCondSettings)context).sms_normal = false;
					Editor editor = PreferenceManager.getDefaultSharedPreferences(
							context.getApplicationContext()).edit();
					editor.putBoolean(MHelperStrings.SMS_NORMAL, false);
					editor.commit();
				}
			}
		});
	}

	public void setWhichEnable(boolean slient, boolean vibration, 
			boolean airmode, boolean normal) {
		slientCheckBox.setChecked(slient);
		vibrationCheckBox.setChecked(vibration);
		airmodeCheckBox.setChecked(airmode);
		normalCheckBox.setChecked(normal);
		}
}
