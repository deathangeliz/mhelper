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
	private int met;//��Ӧ�ڼ��������¼���
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCEAdapter = new CondEventAdapter(this);
		//TODO setContentView();//�����ļ�
		met=0;
		Bundle extras = getIntent().getExtras();
		/*TODO ���������������������¼�����
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
			//TODO ����metֵ����Cond��Event�Ĳ���ֵ��������ý����ֵ
			
		}
		
		/*TODO �ر����ý��棬���������¼���
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
