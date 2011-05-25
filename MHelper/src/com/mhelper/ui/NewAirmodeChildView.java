package com.mhelper.ui;

import com.mhelper.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewAirmodeChildView extends LinearLayout {
	TextView newAirmodeText;
	ImageView newAirmodeImage;

	public NewAirmodeChildView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_air_child, this, true);
		
		newAirmodeText = (TextView)findViewById(R.id.newAirText);
		newAirmodeImage = (ImageView)findViewById(R.id.newAirImage);
	}
	
	public void setGroupText(String str){
		newAirmodeText.setText(str);
	}

	public void setGroupImage(Drawable drawable){
		newAirmodeImage.setImageDrawable(drawable);
	}
}
