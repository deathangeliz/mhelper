package com.mhelper;

import com.mhelper.DatebaseAdapter.*;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.widget.TextView;

public class Main extends Activity {

	private ConditionsAdapter CA;
	private DetailCondAdapter DCA;
	private EventsAdapter EA;
	private DetailEventAdapter DEA;
	private CondEventAdapter CEA;
	
	private Cursor TextCursor;
	 
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.main);   
       
       CA=new ConditionsAdapter(this);
       DCA=new DetailCondAdapter(this);
       EA=new EventsAdapter(this);
       DEA=new DetailEventAdapter(this);
       CEA=new CondEventAdapter(this);
       
       DCA.recreateCondictions();
       CA.recreateConditions();
       EA.recreateEvents();
       DEA.recreateEvents();
       CEA.recreateCondEvent();
       
       CA.insertCondition("在三点五十分");
       EA.insertEvent("关机事件");
       CEA.insertCondEvent(1, 1);
       DCA.insertDetailCondition("关机", "关机描述", "开始时间", "结束时间", 1,1);
       DEA.insertDetailEvent(1, 0);
       TextCursor=DEA.getDetailEvent(1);
       TextView tv =(TextView)findViewById(R.id.CAtextview);
       tv.setText(TextCursor.getString(1));
       
      
   }
}
	
   
