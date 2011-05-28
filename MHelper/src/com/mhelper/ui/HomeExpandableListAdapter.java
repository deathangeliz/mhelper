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
	Object groupLock = new Object();
	Object childrenLock = new Object();
	Object idLock = new Object();
	
	public  HomeExpandableListAdapter(Context ctx) {
		context = ctx;
	}
	
	public ArrayList<String> getGroupContent() {
		synchronized (groupLock) {
			return groupContent;
		}
	}
	
	public boolean setGroupContent(ArrayList<String> gc) {
		synchronized (groupLock) {
		    groupContent = gc;
		    return true;
		}
	}
	
	public ArrayList<ArrayList<String>> getChildrenContent() {
		synchronized (childrenLock) {
			return childrenContent;
		}		
	}
	
	public boolean setChildrenContent(ArrayList<ArrayList<String>> cc) {
		synchronized (childrenLock) {
		    childrenContent = cc;
		    return true;
		}
	}
	
	public ArrayList<Integer> getCondEventId() {
		synchronized (idLock) {
			return condEventId;
		}
	}
	
	public boolean setCondEventID(ArrayList<Integer> ceid) {
		synchronized (idLock) {
	        condEventId = ceid;
	        return true;
		}
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		synchronized (childrenLock) {
			return childrenContent.get(groupPosition).get(childPosition);
		}		
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
			if (convertView instanceof HomeChildView)
				return convertView;
			else {
				////In this we should throw exception, but now just return null.
				return null;
			}
		}
		synchronized (childrenLock) {
			HomeChildView view = new HomeChildView(context);
			view.setHomeText((String)getChild(groupPosition, childPosition));
			return view;
		}	
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		synchronized (childrenLock) {
			return childrenContent.get(groupPosition).size();
		}	
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		synchronized (groupLock) {
			return groupContent.get(groupPosition);
		}		
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		synchronized (groupLock) {
			return groupContent.size();
		}	
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		synchronized (groupLock) {
			return groupPosition;
		}	
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView != null) {
			if (convertView instanceof HomeGroupView)
				return convertView;
			else {
				////In this we should throw exception, but now just return null.
				return null;
			}
		}
		synchronized (groupLock) {
			HomeGroupView view = new HomeGroupView(context);
			view.setHomeText((String)getGroup(groupPosition));
			view.setDeleteListener(groupPosition);
			return view;
		}	
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
