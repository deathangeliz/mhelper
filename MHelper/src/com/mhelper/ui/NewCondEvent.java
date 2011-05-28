package com.mhelper.ui;

import com.mhelper.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class NewCondEvent extends TabActivity {
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		Resources res = getResources(); // Resource object to get Drawables
	    final TabHost tabHost = getTabHost();  // The activity TabHost
	    
	    tabHost.addTab(tabHost.newTabSpec("conditions").setIndicator("Conditions",
                res.getDrawable(R.drawable.new_tab_cond))
                .setContent(new Intent(NewCondEvent.this, NewCondSettings.class)));
        
	    tabHost.addTab(tabHost.newTabSpec("events").setIndicator("Events",
                res.getDrawable(R.drawable.new_tab_event))
                .setContent(new Intent(NewCondEvent.this, NewEventSettings.class)));
	    tabHost.setCurrentTabByTag("events");
	}
}
