package com.appable.povertychallenge;

import com.appable.povertychallenge.fragments.AdminFragment;
import com.appable.povertychallenge.fragments.HomeFragment;
import com.appable.povertychallenge.fragments.LeftMenuFragment;
import com.appable.povertychallenge.widgets.AbstractTopBarView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SlidingActivity extends FragmentActivity {
	private final String TAG = getClass().getName();
	
	public static final String GROUP_NAME = "group name";
	
	private String mGroupName = null;
	
	protected Activity mActivity;
	protected Context mContext;
	protected SlidingMenu slidingMenu;
	private int mTitleRes;
	protected Fragment mFrag;
	public LeftMenuFragment leftMenuFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mActivity = this;
		mContext = this;
		mGroupName = getIntent().getStringExtra(GROUP_NAME);
		
		mFrag = new HomeFragment();
		setContentView(R.layout.content_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mFrag).commit();

//		setBehindContentView(R.layout.menu_frame);

//		if (savedInstanceState == null) {
//			leftMenuFragment = new LeftMenuFragment();
//			getSupportFragmentManager().beginTransaction()
//					.replace(R.id.menu_frame, leftMenuFragment).commit();
//		} else {
//			leftMenuFragment = (LeftMenuFragment) getSupportFragmentManager()
//					.findFragmentById(R.id.menu_frame);
//		}

		initSliding();
		init();

	}

	protected void showDataOnLogCat(String data) {
		if (data != null) {
			Log.d(TAG, data);
		}
	}

	private void initSliding() {
		// customize the SlidingMenu
		slidingMenu = new SlidingMenu(this);
//		slidingMenu = getSlidingMenu();
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setBehindOffsetRes(R.dimen.sliding_offset);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.menu_frame);
		leftMenuFragment = new LeftMenuFragment(mGroupName);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, leftMenuFragment).commit();
	}

	private void init() {

	}

	public void changeMenu(int position) {
		Fragment frg = null;
		Intent intent = null;
		switch (position) {
		case 0:
			Toast.makeText(getBaseContext(), "Home", Toast.LENGTH_SHORT).show();
			intent = new Intent(mContext, SplashScreenActivity.class);
			frg = new HomeFragment();
			break;
		case 1:
			Toast.makeText(getBaseContext(), "Notifications",
					Toast.LENGTH_SHORT).show();
			break;
		case 2:
			Toast.makeText(getBaseContext(), "Social Media", Toast.LENGTH_SHORT)
					.show();
			break;
		case 3:
			Toast.makeText(getBaseContext(), "Group Contacts",
					Toast.LENGTH_SHORT).show();
			break;
		case 4:
			Toast.makeText(getBaseContext(), "Fundraising resources",
					Toast.LENGTH_SHORT).show();
			break;
		case 5:
			Toast.makeText(getBaseContext(), "Admin", Toast.LENGTH_SHORT)
					.show();
			intent = new Intent(mContext, ListGroupsActivity.class);
			frg = new AdminFragment();
			break;
		default:
			break;
		}

		if (frg != null) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, frg).commit();
			// pushFragments(frg, false, true);
			if (leftMenuFragment != null) {
				leftMenuFragment.changeSelected(position);
			}
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onBackPressed() {
		if (slidingMenu.isMenuShowing()) {
			slidingMenu.showContent();
		} else {
			super.onBackPressed();
		}
	}
	
	public void showMenu(){
		slidingMenu.showMenu();
	}
	
	public void showContent(){
		slidingMenu.showContent();
	}
	
	public SlidingMenu getSlidingMenu(){
		return slidingMenu;
	}
	
	public void toggle(){
		slidingMenu.toggle();
	}
}
