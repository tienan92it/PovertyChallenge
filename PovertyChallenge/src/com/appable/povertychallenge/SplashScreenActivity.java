package com.appable.povertychallenge;

import com.appable.povertychallenge.widgets.AbstractTopBarView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends Activity{
	
	private TextView tv_view_groups;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		init();
		initEvent();
	}
	
	private void init(){
		tv_view_groups = (TextView) findViewById(R.id.tv_view_group);
	}
	
	private void initEvent(){
		tv_view_groups.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), ListGroupsActivity.class);
				startActivity(intent);
//				overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
			}
		});
	}
}
