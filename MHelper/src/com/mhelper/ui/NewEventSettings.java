package com.mhelper.ui;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;

public class NewEventSettings extends ExpandableListActivity {
    ExpandableListAdapter eventAdapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up our adapter
        eventAdapter = new NewCondExpandableListAdapter(NewEventSettings.this);
        setListAdapter(eventAdapter);
    }
}
