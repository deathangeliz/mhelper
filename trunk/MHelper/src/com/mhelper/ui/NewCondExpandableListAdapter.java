package com.mhelper.ui;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.mhelper.R;

public class NewCondExpandableListAdapter extends BaseExpandableListAdapter {

	public Context context;
	
	private String[] group = { "Alarm", "Google Calendar", "Message" };
	private final int GROUP_COUNT = 3;
	
	private String[][] children = {
			{ "Start Time", "End Time" },
			{ "Calendar" },
			{ "Message Type" }
	};	
	private final int[] CHILDREN_COUNT = { 2, 1, 1 };
	
	public NewCondExpandableListAdapter(Context ctx){
		context = ctx;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return children[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView != null) 
			convertView = null;

		if (groupPosition == 0) {
			if (childPosition == 0) {
				NewAlarmChildView view = new NewAlarmChildView(context, childPosition);
				String dateStr = "" + ((NewCondSettings)context).condAlramYear + "/" + 
				    ((NewCondSettings)context).condAlarmMonth + "/" +
				    ((NewCondSettings)context).condAlarmDay;
				view.setAlarmDate(dateStr);
				String timeStr = "" + ((NewCondSettings)context).condAlarmHour + ":";
				if(((NewCondSettings)context).condAlramMinute < 10){
					timeStr = timeStr + "0" + ((NewCondSettings)context).condAlramMinute;
				}else
					timeStr = timeStr + ((NewCondSettings)context).condAlramMinute;
				view.setAlarmTime(timeStr);
				return view;
			} else if (childPosition == 1) {
				NewAlarmFinsihChildView view = new NewAlarmFinsihChildView(context, childPosition);
				if (((NewCondSettings)context).condAlarmShouldFinish) {
					view.finishCheckBox.setChecked(true);
				} else {
					view.finishCheckBox.setChecked(false);
				}
				String dateStr = "" + ((NewCondSettings)context).condAlramFinishYear + "/" + 
			        ((NewCondSettings)context).condAlarmFinishMonth + "/" +
			        ((NewCondSettings)context).condAlarmFinishDay;
			    view.setAlarmDate(dateStr);
			    String timeStr = "" + ((NewCondSettings)context).condAlarmFinishHour + ":";
			    if(((NewCondSettings)context).condAlramFinishMinute < 10){
				    timeStr = timeStr + "0" + ((NewCondSettings)context).condAlramFinishMinute;
			    }else
				    timeStr = timeStr + ((NewCondSettings)context).condAlramFinishMinute;
			    view.setAlarmTime(timeStr);
				return view;
			} else {
	        Log.i("NewCondSettings.getChildView()", "groupPosition==0 error");
			    return null;
		    }	
		}
		else if (groupPosition == 1) {
			View view = new NewGCalendarChildView(context);
			return view;
		}
		else if (groupPosition == 2) {
			View view = new NewMessageChildView(context);
			return view;
		}
		
		return null;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return CHILDREN_COUNT[groupPosition];
	}

	@Override
	public String getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return group[groupPosition];
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return GROUP_COUNT;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView != null){
			if (convertView instanceof NewGroupView) {
				((NewGroupView)convertView).setGroupText(group[groupPosition]);
				switch (groupPosition) {
				case 0:
					((NewGroupView)convertView).setGroupImage(context.getResources().getDrawable(R.drawable.alarm));
					break;
				case 1:
					((NewGroupView)convertView).setGroupImage(context.getResources().getDrawable(R.drawable.calendar));
					break;
				case 2:
					((NewGroupView)convertView).setGroupImage(context.getResources().getDrawable(R.drawable.messages));
					break;
				default:
					break;
				}
				return convertView;
			}
			else 
				//In this we should throw exception, but now just return null.
			    return null;
		}
		
		NewGroupView ngv = new NewGroupView(context);
		ngv.setGroupText(group[groupPosition]);
		switch (groupPosition) {
		case 0:
		    ngv.setGroupImage(context.getResources().getDrawable(R.drawable.alarm));
			break;
		case 1:
			ngv.setGroupImage(context.getResources().getDrawable(R.drawable.calendar));
			break;
		case 2:
			ngv.setGroupImage(context.getResources().getDrawable(R.drawable.messages));
			break;
		default:
			break;
		}
		
		return ngv;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
