package com.mhelper.ui;

import com.mhelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeChildView extends LinearLayout {
	TextView homeChildText;
	ImageView homeChildImage;
	
	public HomeChildView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.home_child, this, true);
		
		homeChildText = (TextView)findViewById(R.id.homeChildText);
		homeChildImage = (ImageView)findViewById(R.id.homeChildImage);
		
		homeChildImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void setHomeText(String str) {
		homeChildText.setText(str);
	}

}
