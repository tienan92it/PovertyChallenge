package com.appable.povertychallenge;

import com.appable.povertychallenge.fragments.LeftMenuFragment;
import com.appable.povertychallenge.widgets.AbstractTopBarView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
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

public class MainActivity extends SlidingFragmentActivity {
	private final String TAG = getClass().getName();

	private SlidingMenu slidingMenu;
	private int mTitleRes;
	protected Fragment mFrag;
	public LeftMenuFragment leftMenuFragment;
	private AbstractTopBarView headerView;
	private ImageView btn_menu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.content_frame);
		setBehindContentView(R.layout.menu_frame);
		leftMenuFragment = new LeftMenuFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, leftMenuFragment).commit();

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
		slidingMenu = getSlidingMenu();
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setBehindOffsetRes(R.dimen.sliding_offset);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	}

	private void init() {

		headerView = (AbstractTopBarView) findViewById(R.id.headerView);
		headerView.initialTopbar("Home", true, false);
		btn_menu = (ImageView) headerView.getBtnLeft();
		
		btn_menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				slidingMenu.showMenu();
			}
		});
	}

	public void changeMenu(int position) {
		Fragment frg = null;
		switch (position) {
		case 0:
			Toast.makeText(getBaseContext(), "Home", Toast.LENGTH_SHORT).show();
			headerView.initialTopbar("Home", true, false);
			break;
		case 1:
			Toast.makeText(getBaseContext(), "Notifications",
					Toast.LENGTH_SHORT).show();
			headerView.initialTopbar("Notifications", true, false);
			break;
		case 2:
			Toast.makeText(getBaseContext(), "Social Media", Toast.LENGTH_SHORT)
					.show();
			headerView.initialTopbar("Social Media", true, false);
			break;
		case 3:
			Toast.makeText(getBaseContext(), "Group Contacts",
					Toast.LENGTH_SHORT).show();
			headerView.initialTopbar("Group Contacts", true, false);
			break;
		case 4:
			Toast.makeText(getBaseContext(), "Fundraising resources",
					Toast.LENGTH_SHORT).show();
			headerView.initialTopbar("Fundraising resources", true, false);
			break;
		case 5:
			Toast.makeText(getBaseContext(), "Admin", Toast.LENGTH_SHORT)
					.show();
			headerView.initialTopbar("Admin", true, false);
			break;
		default:
			break;
		}
		// pushFragments(frg, false, true);
		if (leftMenuFragment != null) {
			leftMenuFragment.changeSelected(position);
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
}
