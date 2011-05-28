package com.mhelper.ui;

import com.mhelper.R;
import com.mhelper.ui.Home;
import com.mhelper.ui.HomeExpandableListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeGroupView extends LinearLayout {

	TextView homeGroupText;
	ImageView homeGroupImage;
	Context ctx;
	
	public HomeGroupView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		ctx = context;
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.home_group, this, true);
		
		homeGroupText = (TextView)findViewById(R.id.homeGroupText);
		homeGroupImage = (ImageView)findViewById(R.id.homeGroupImage);
		
		homeGroupImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//delete group
				
			}
		});
	}
	
	public void setHomeText(String str) {
		homeGroupText.setText(str);
	}
	

	public void setDeleteListener(final int groupPosition) {
		homeGroupImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((Home)ctx).deleteData(groupPosition);
			}
		});
	}
}
