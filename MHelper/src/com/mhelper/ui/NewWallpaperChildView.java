package com.mhelper.ui;

import java.io.File;

import com.mhelper.R;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewWallpaperChildView extends LinearLayout {

	private Context context;
	public static final int REQUEST_CODE = 1;
	private TextView textView;
	SharedPreferences prefs = null;
	public NewWallpaperChildView(Context _context) {
		super(_context);
		this.context = _context;
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		//this.tempFile = Uri.Builder();
		initialize();
	}
	
	public void initialize(){
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.new_change_wallpaper_view, this, true);
		textView = (TextView)findViewById(R.id.chooseImageTextView);
		String uriStr = prefs.getString(context.getString(R.string.IMAGE_URI_KEY), "");
		textView.setText(uriStr);
		Button chooseImageButton = (Button)findViewById(R.id.chooseImageButton);
		chooseImageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 Intent intent = new Intent(Intent.ACTION_GET_CONTENT); 
	             intent.setType("image/*");
	             ExpandableListActivity activity = (ExpandableListActivity)context;
	             activity.startActivityForResult(intent, REQUEST_CODE);
			}
		});
	}
	
	public void setString(String str){
		SharedPreferences.Editor editor = prefs.edit();
		editor.remove(context.getString(R.string.IMAGE_URI_KEY));
		editor.putString(context.getString(R.string.IMAGE_URI_KEY), str);
		editor.commit();
		initialize();
	}
}
