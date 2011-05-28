package com.mhelper.ui;

import com.mhelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class NewMessageChildView extends LinearLayout {
	Spinner newMessageSpinner;
	TextView newMessageText;
	int messageType = 0;
	Context context;
	
	public NewMessageChildView(Context _context) {
		super(_context);
		context = _context;
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_message_child, this, true);
		
		newMessageSpinner = (Spinner)findViewById(R.id.newMessageSpinner);
		newMessageText = (TextView)findViewById(R.id.newMessageText);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                _context, R.array.message_type, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		newMessageSpinner.setAdapter(adapter);
		newMessageSpinner.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    @Override
					public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        messageType = position;
                        ((NewCondSettings)context).typeToMessage = messageType;
                    }

                    @Override
					public void onNothingSelected(AdapterView<?> parent) {
                        messageType = 0;
                        ((NewCondSettings)context).typeToMessage = messageType;
                    }
                });
	}

	public void setMessageText(String str) {
		newMessageText.setText(str);
	}
	
	public int getSelectedMessageType() {
		return messageType;
	}
	
	public void setSelectedMessageType(int type) {
		messageType = type;
		newMessageSpinner.setSelection(messageType);
	}
}
