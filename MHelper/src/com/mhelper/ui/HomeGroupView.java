package com.mhelper.ui;

import com.mhelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeGroupView extends LinearLayout {

	TextView homeGroupText;
	ImageView homeeGroupImage;
	
	public HomeGroupView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.home_group, this, true);
		
		homeGroupText = (TextView)findViewById(R.id.homeGroupText);
		homeeGroupImage = (ImageView)findViewById(R.id.homeGroupImage);
		
		homeeGroupImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void setHomeText(String str) {
		homeGroupText.setText(str);
	}

}
