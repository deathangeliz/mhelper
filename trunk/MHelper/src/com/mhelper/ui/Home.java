package com.mhelper.ui;

import java.util.ArrayList;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;

public class Home extends ExpandableListActivity {
	HomeExpandableListAdapter adapter;
	ArrayList<String> groupContent;
	ArrayList<ArrayList<String>> childrenContent;
	ArrayList<Integer> condEventId;
	
	static final private int NEW_MENU_ITEM = Menu.FIRST;
	static final private int ABOUT_MENU_ITEM = Menu.FIRST + 1;
	static final private int REQUEST_NEW = 0;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up our adapter
        adapter = new HomeExpandableListAdapter(Home.this);
        getOrRefreshDate();
        setListAdapter(adapter);
    }
	
	@Override
	public void onRestoreInstanceState(Bundle saveInstanceState) {
		super.onRestoreInstanceState(saveInstanceState);
	}
	
	@Override
	public void onRestart() {
		super.onRestart();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		getOrRefreshDate();
		setListAdapter(adapter);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//refresh list content
        getOrRefreshDate();
        setListAdapter(adapter);
	}
	
	@Override
	public void onSaveInstanceState (Bundle saveInstanceState) {
		super.onSaveInstanceState(saveInstanceState);
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onStop() {
		super.onStop();
	}
	
	public void getOrRefreshDate() {
		groupContent = getGroupContent();
        if (groupContent == null)
        	groupContent = new ArrayList<String>();
        childrenContent = getChildrenContent();
        if (childrenContent == null)
        	childrenContent = new ArrayList<ArrayList<String>>();
        condEventId = getCondEventId();
        if (condEventId == null)
        	condEventId = new ArrayList<Integer>();
        adapter.setGroupContent(groupContent);
        adapter.setChildrenContent(childrenContent);
        adapter.setCondEventID(condEventId);
	}
	
	public void deleteData(final int groupId) {
		//invoke database method to delete
		//    the cond-event which id is condEventId.get(groupId)
		getOrRefreshDate();
		setListAdapter(adapter);
	}
	
	public ArrayList<String> getGroupContent() {
		//invoke database method
		return new ArrayList<String>();
	}
	
	public ArrayList<ArrayList<String>> getChildrenContent() {
		//invoke database method
		return new ArrayList<ArrayList<String>>();
	}
	
	public ArrayList<Integer> getCondEventId() {
		//invoke database method
		return new ArrayList<Integer>();
	}
	
	public boolean onCreateOptionMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		
		MenuItem newItem = menu.add(0, NEW_MENU_ITEM,
				Menu.NONE, "New");
		MenuItem aboutItem = menu.add(0, ABOUT_MENU_ITEM,
				Menu.NONE, "About");
		
		//add icon
		newItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Home.this, NewCondEvent.class);
				startActivityForResult(intent, REQUEST_NEW);
				return true;
			}
		});
		aboutItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				showAbout();
				return true;
			}
		});
		
		return true;
	}
	
	public void showAbout() {
		String title = "About";
		String message = "I don not know what to say...";
		String buttonString = "OK";
		AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(buttonString, 
				new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
		builder.setCancelable(false);
		builder.show();
		
	}
}
