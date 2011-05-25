package com.mhelper.ui;

import com.mhelper.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewVibrationChildView extends LinearLayout {
	TextView newVibrationText;
	ImageView newVibrationImage;

	public NewVibrationChildView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.new_vibrator_child, this, true);
		
		newVibrationText = (TextView)findViewById(R.id.newVibratorText);
		newVibrationImage = (ImageView)findViewById(R.id.newVibratorImage);
	}
	
	public void setGroupText(String str){
		newVibrationText.setText(str);
	}

	public void setGroupImage(Drawable drawable){
		newVibrationImage.setImageDrawable(drawable);
	}
}
