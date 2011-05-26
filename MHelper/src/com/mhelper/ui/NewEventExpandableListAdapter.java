package com.mhelper.ui;

import android.R.integer;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class NewEventExpandableListAdapter extends BaseExpandableListAdapter {

	private Context context;
	
	private String[] group = { "Shut Down", "Slient Mode", "Vibrator Mode", "Air Mode", "Notification", "Change Wallpaper", };
	private final int GROUP_COUNT = 6;
	
	private String[][] children = {
			{ "Auto shut down your phone" },
			{ "Auto Slient" },
			{ "Auto Vibration" },
			{ "Auto Air Mode" },
			{ "Message Type", "Message Content" },
			{ "Image Directory" }
	};
	private final int[] CHILDREN_COUNT = { 1, 1, 1, 1, 2, 1};
	
	public NewEventExpandableListAdapter(Context ctx){
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
		if (convertView != null) {
			if (groupPosition == 0) {
				if (convertView instanceof NewShutdownChildView)
					return convertView;
				else 
					//In this we should throw exception, but now just return null.
					return null;
			}
			else if (groupPosition == 1) {
				if (convertView instanceof NewSlientChildView)
					return convertView;
				else 
					//In this we should throw exception, but now just return null.
					return null;
			}
			else if (groupPosition == 2) {
				if (convertView instanceof NewVibrationChildView)
					return convertView;
				else 
					//In this we should throw exception, but now just return null.
					return null;
			}
			else if (groupPosition == 3) {
				if (convertView instanceof NewAirmodeChildView)
					return convertView;
				else 
					//In this we should throw exception, but now just return null.
					return null;
			}
			else if (groupPosition == 4) {
				if (convertView instanceof NewNotificationSelectChildView)
					return convertView;
				else 
					//In this we should throw exception, but now just return null.
					return null;
			}
			else if (groupPosition == 5) {
				if (convertView instanceof NewWallpaperChildView)
					return convertView;
				else 
					//In this we should throw exception, but now just return null.
					return null;
			}
		}	
		if (groupPosition == 0) {
			View view = new NewShutdownChildView(context);
			return view;
		}
		else if (groupPosition == 1) {
			View view = new NewSlientChildView(context);
			return view;
		}
		else if (groupPosition == 2) {
			View view = new NewVibrationChildView(context);
			return view;
		}
		else if (groupPosition == 3) {
			View view = new NewAirmodeChildView(context);
			return view;
		}
		else if (groupPosition == 4) {
			View view = new NewNotificationSelectChildView(context);
			return view;
		}
		else if (groupPosition == 5) {
			View view = new NewWallpaperChildView(context);
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
	public Object getGroup(int groupPosition) {
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
			if (convertView instanceof NewGroupView)
				return convertView;
			else 
				//In this we should throw exception, but now just return null.
			    return null;
		}
		
		NewGroupView ngv = new NewGroupView(context);
		ngv.setGroupText(group[groupPosition]);
		/*set the newGroupImage src 
		switch (groupPosition) */
		
		return ngv;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
