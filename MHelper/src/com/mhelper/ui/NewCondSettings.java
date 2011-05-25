package com.mhelper.ui;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;

public class NewCondSettings extends ExpandableListActivity {
	ExpandableListAdapter condAdapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up our adapter
        condAdapter = new NewCondExpandableListAdapter(NewCondSettings.this);
        setListAdapter(condAdapter);
    }
}
