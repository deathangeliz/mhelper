package com.mhelper.ui;

import com.mhelper.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewSlientChildView extends LinearLayout {
	TextView newSlientText;
	ImageView newSlientImage;

	public NewSlientChildView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_slient_child, this, true);
		
		newSlientText = (TextView)findViewById(R.id.newSlientText);
		newSlientImage = (ImageView)findViewById(R.id.newSlientImage);
	}
	
	public void setGroupText(String str){
		newSlientText.setText(str);
	}

	public void setGroupImage(Drawable drawable){
		newSlientImage.setImageDrawable(drawable);
	}
}
