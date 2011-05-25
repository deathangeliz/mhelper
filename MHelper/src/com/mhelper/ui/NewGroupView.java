package com.mhelper.ui;

import com.mhelper.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewGroupView extends LinearLayout {
	TextView newGroupText;
	ImageView newGroupImage;

	public NewGroupView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_group, this, true);
		
		newGroupText = (TextView)findViewById(R.id.newGroupText);
		newGroupImage = (ImageView)findViewById(R.id.newGroupImage);
	}
	
	public void setGroupText(String str){
		newGroupText.setText(str);
	}

	public void setGroupImage(Drawable drawable){
		newGroupImage.setImageDrawable(drawable);
	}
}
