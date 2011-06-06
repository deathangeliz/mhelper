package com.mhelper.ui;

import java.util.ArrayList;

import com.mhelper.R.id;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class HomeExpandableListAdapter extends BaseExpandableListAdapter {
	Context context;
	ArrayList<String> groupContent = new ArrayList<String>();
	ArrayList<ArrayList<String>> childrenContent = new	ArrayList<ArrayList<String>>();
	ArrayList<Integer> condEventId = new ArrayList<Integer>();
	//Object groupLock = new Object();
	//Object childrenLock = new Object();
	//Object idLock = new Object();
	
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
	
	public ArrayList<ArrayList<String>> getChildrenContent() {
		return childrenContent;	
	}
	
	public boolean setChildrenContent(ArrayList<ArrayList<String>> cc) {
		childrenContent = cc;
	    return true;
	}
	
	public ArrayList<Integer> getCondEventId() {
			return condEventId;
	}
	
	public boolean setCondEventID(ArrayList<Integer> ceid) {
	    condEventId = ceid;
	    return true;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
			return childrenContent.get(groupPosition).get(childPosition);
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
			if (convertView instanceof HomeChildView) {
				((HomeChildView)convertView).setHomeText((String)getChild(groupPosition, childPosition));
				((HomeChildView)convertView).setEditListenner(groupPosition);
				return convertView;
			}
			else {
				////In this we should throw exception, but now just return null.
				return null;
			}
		}
		HomeChildView view = new HomeChildView(context);
		view.setHomeText((String)getChild(groupPosition, childPosition));
		view.setEditListenner(groupPosition);
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return childrenContent.get(groupPosition).size();
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
			if (convertView instanceof HomeGroupView) {
				((HomeGroupView)convertView).setHomeText((String)getGroup(groupPosition));
				((HomeGroupView)convertView).setDeleteListener(groupPosition);
				return convertView;
			}
			else {
				////In this we should throw exception, but now just return null.
				return null;
			}
		}
		HomeGroupView view = new HomeGroupView(context);
		view.setHomeText((String)getGroup(groupPosition));
		view.setDeleteListener(groupPosition);
		return view;
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
