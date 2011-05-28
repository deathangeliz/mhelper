package com.mhelper.ui;

import com.mhelper.R;

import android.content.Context;
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

public class NewModeChildView extends LinearLayout {
	Spinner newModeSpinner;
	TextView newModeText;
	int modeType = 0;
	Context context;

	public NewModeChildView(Context _context) {
		super(_context);
		context = _context;
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_mode_child, this, true);
		
		newModeSpinner = (Spinner)findViewById(R.id.newModeSpinner);
		newModeText = (TextView)findViewById(R.id.newMessageText);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            context, R.array.mode_type, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		newModeSpinner.setAdapter(adapter);
		newModeSpinner.setOnItemSelectedListener(
	            new OnItemSelectedListener() {
	                @Override
					public void onItemSelected(
	                        AdapterView<?> parent, View view, int position, long id) {
	                	modeType = position;
	                	Editor editor = PreferenceManager.getDefaultSharedPreferences(
								context.getApplicationContext()).edit();
	                	editor.putInt("eType", modeType + 1);
	                	editor.commit();
	                }

	                @Override
					public void onNothingSelected(AdapterView<?> parent) {
	                	modeType = 0;
	                	Editor editor = PreferenceManager.getDefaultSharedPreferences(
								context.getApplicationContext()).edit();
	                	editor.putInt("eType", modeType + 1);
	                	editor.commit();
	                }
	            });
	}
	
	public void setModeText(String str) {
		newModeText.setText(str);
	}

	public int getSelectedModeType() {
		return modeType;
	}
}
