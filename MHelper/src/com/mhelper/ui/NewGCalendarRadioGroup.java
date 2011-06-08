package com.mhelper.ui;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.mhelper.R;
import com.mhelper.conditions.GCalenderReader;
import com.mhelper.conditions.TimeCondition;
import com.mhelper.middle.MHelperStrings;

public class NewGCalendarRadioGroup extends Activity {
	private RadioGroup mRadioGroup;
	private Button mButton;
	ArrayList<TimeCondition> timeConditions = new ArrayList<TimeCondition>();
	static public final int RESULT_SELECT_GC_ITEMS = 0;
	static public final int RESULT_NO_GC_ITEMS = 1;
	static public final String GC_TIMECONDITION = "gc_time_condition";
	private int checkIndex = 0;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.new_gcalendar_radiogroup);
	     mRadioGroup = (RadioGroup)findViewById(R.id.newGCRadioGroup);
	     mButton = (Button)findViewById(R.id.newGCButton);
	        
	     GCalenderReader gCalenderReader = new GCalenderReader(this);
	     timeConditions = gCalenderReader.readCalendar(Calendar.getInstance());
	     if (timeConditions == null) {
	    	 Toast.makeText(this, "No GCalendar items!!!", Toast.LENGTH_SHORT).show();
	         setResult(RESULT_NO_GC_ITEMS);
	         finish();
	     } else {
	    	 for (int i = 0; i < timeConditions.size(); i++) {
		    	 RadioButton newRadioButton = new RadioButton(this);
		    	 TimeCondition timeCondition = timeConditions.get(i);
		    	 String str = timeCondition.getDescription() + "\n" + 
		             "Start Time: " + timeCondition.getStartTime().getTime().toString() + "\n" + 
		             "Finish Time: " + timeCondition.getFinishTime().getTime().toString();
		         newRadioButton.setText(str);
		         newRadioButton.setTag(i);
		         newRadioButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							checkIndex = (Integer)buttonView.getTag();
						}
					}
				});
		         LinearLayout.LayoutParams layoutParams = new RadioGroup.LayoutParams(
		                 RadioGroup.LayoutParams.WRAP_CONTENT,
		                 RadioGroup.LayoutParams.WRAP_CONTENT);
		         mRadioGroup.addView(newRadioButton, i, layoutParams);
			}
		    if (timeConditions.size() > 0)
		    	mRadioGroup.check(0);
		    mButton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						TimeCondition timeCondition = timeConditions.get(checkIndex);
						SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
						Editor editor = prefs.edit();
						editor.putInt(MHelperStrings.UI_GC_START_YEAR, timeCondition.getStartTime().get(Calendar.YEAR));
						editor.putInt(MHelperStrings.UI_GC_START_MONTH, timeCondition.getStartTime().get(Calendar.MONTH));
						editor.putInt(MHelperStrings.UI_GC_START_DAY, timeCondition.getStartTime().get(Calendar.DATE));
						editor.putInt(MHelperStrings.UI_GC_START_HOUR, timeCondition.getStartTime().get(Calendar.HOUR));
						editor.putInt(MHelperStrings.UI_GC_START_MINUTE, timeCondition.getStartTime().get(Calendar.MINUTE));
						editor.putInt(MHelperStrings.UI_GC_FINISH_YEAR, timeCondition.getFinishTime().get(Calendar.YEAR));
						editor.putInt(MHelperStrings.UI_GC_FINISH_MONTH, timeCondition.getFinishTime().get(Calendar.MONTH));
						editor.putInt(MHelperStrings.UI_GC_FINISH_DAY, timeCondition.getFinishTime().get(Calendar.DATE));
						editor.putInt(MHelperStrings.UI_GC_FINISH_HOUR, timeCondition.getFinishTime().get(Calendar.HOUR));
						editor.putInt(MHelperStrings.UI_GC_FINISH_MINUTE, timeCondition.getFinishTime().get(Calendar.MINUTE));
						editor.commit();
						setResult(RESULT_SELECT_GC_ITEMS);
						finish();
					}
				});
	     }	        
	     
	 }
	 
	 @Override
	 public void onResume() {
		 super.onResume();
		 if (timeConditions == null || timeConditions.size() == 0) {
	    	 Toast.makeText(this, "No GCalendar items!!!", Toast.LENGTH_SHORT).show();
	         setResult(RESULT_NO_GC_ITEMS);
	         finish();
	     }
		 
	 }
	 
}
