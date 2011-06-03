package com.mhelper.ui;

import com.mhelper.R;
import com.mhelper.middle.MHelperStrings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class NewNotificationSelectChildView extends LinearLayout {
	TextView newNotificationText;
	Spinner newNotificationSpinner;
	int selectedType = 0;
	Context context;

	public NewNotificationSelectChildView(Context _context) {
		super(_context);
		context = _context;
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_notification_selected_child, this, true);
		
		newNotificationText = (TextView)findViewById(R.id.newNotificationText);
		newNotificationSpinner = (Spinner)findViewById(R.id.newNotificationSpinner);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context, R.array.message_type, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		newNotificationSpinner.setAdapter(adapter);
		newNotificationSpinner.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    @Override
					public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                    	selectedType = position;
                    	((NewEventSettings)context).notificationType = selectedType;
                    	Editor editor = PreferenceManager.getDefaultSharedPreferences(
                    			context.getApplicationContext()).edit();
                    	//editor.putInt("notificationType", selectedType);
                    	editor.putInt(MHelperStrings.UI_NOTIFICATION_TYPE, selectedType);
                    	editor.commit();
                    }

                    @Override
					public void onNothingSelected(AdapterView<?> parent) {
                    	selectedType = 0;
                    	((NewEventSettings)context).notificationType = selectedType;
                    	Editor editor = PreferenceManager.getDefaultSharedPreferences(
                    			context.getApplicationContext()).edit();
                    	//ditor.putInt("notificationType", selectedType);
                    	editor.putInt(MHelperStrings.UI_NOTIFICATION_TYPE, selectedType);
                    	editor.commit();
                    }
                });
	}
	
	public void setNotificationSelectText(String str){
		newNotificationText.setText(str);
	}

	public int getSelectedType(){
		return selectedType;
	}
	
	public void setSelectedType(int type) {
		selectedType = type;
		newNotificationSpinner.setSelection(selectedType);
	}
}
