package com.mhelper.ui;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class HomeExpandableListAdapter extends BaseExpandableListAdapter {
	Context context;
	ArrayList<String> groupContent = new ArrayList<String>();
	ArrayList<ArrayList<String>> childContent = new	ArrayList<ArrayList<String>>();
	
	public  HomeExpandableListAdapter(Context ctx) {
		context = ctx;
	}
	
	public ArrayList<String> getGroupContent() {
		return groupContent;
	}
	
	public boolean setGroupContent(ArrayList<String> gc) {
		groupContent = gc;
		return true;
	}
	
	public ArrayList<ArrayList<String>> getChildContent() {
		return childContent;
	}
	
	public boolean setChildContent(ArrayList<ArrayList<String>> cc) {
		childContent = cc;
		return true;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childContent.get(groupPosition).get(childPosition);
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
		return null;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return childContent.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groupContent.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return groupContent.size();
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
		if (convertView != null) {
			
		}
		return null;
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
