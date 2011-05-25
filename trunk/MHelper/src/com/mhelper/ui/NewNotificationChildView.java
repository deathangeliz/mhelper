package com.mhelper.ui;

import com.mhelper.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewNotificationChildView extends LinearLayout {
	TextView newShutdownText;
	ImageView newShutdownImage;

	public NewNotificationChildView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_shutdown_child, this, true);
		
		newShutdownText = (TextView)findViewById(R.id.newShutdownText);
		newShutdownImage = (ImageView)findViewById(R.id.newShutdownImage);
	}
	
	public void setGroupText(String str){
		newShutdownText.setText(str);
	}

	public void setGroupImage(Drawable drawable){
		newShutdownImage.setImageDrawable(drawable);
	}
}
