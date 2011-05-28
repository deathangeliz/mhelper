package com.mhelper.middle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import com.mhelper.DatebaseAdapter.CondEventAdapter;
import com.mhelper.DatebaseAdapter.MDBHelperAdapter;

public class CondEventEdit extends Activity{
	
	private CondEventAdapter mCEAdapter;
	private int met;//对应第几个条件事件对
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCEAdapter = new CondEventAdapter(this);
		//TODO setContentView();//布局文件
		met=0;
		Bundle extras = getIntent().getExtras();
		/*TODO 从主界面跳进设置条件事件界面
		 * protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Cursor c = mCondEventCursor;
		c.moveToPosition(position);
		Intent i = new Intent(this, CondEventEdit.class);
		i.putExtra(MDBHelperAdapter.KEY_CEID, id);
		startActivityForResult(i, ACTIVITY_EDIT);//ACTIVITY_EDIT=1
	}*/ 
		if (extras != null) {
			met=extras.getInt(MDBHelperAdapter.KEY_CEID);
			//TODO 根据met值调用Cond和Event的参数值，填充设置界面的值
			
		}
		
		/*TODO 关闭设置界面，更新条件事件对
		 * Button confirmButton = (Button) findViewById(R.id.confirm);
		 * confirmButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				String title = mTitleText.getText().toString();
				String body = mBodyText.getText().toString();
				if (mRowId != 0) {
					mDbHelper.updateDiary(mRowId, title, body);
				} else
					mDbHelper.createDiary(title, body);
				Intent mIntent = new Intent();
				setResult(RESULT_OK, mIntent);
				finish();
			}*/
		
		
	}
}
