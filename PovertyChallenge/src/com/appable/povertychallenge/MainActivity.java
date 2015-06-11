package com.appable.povertychallenge;

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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends SlidingFragmentActivity {
	private final String TAG = getClass().getName();

	private SlidingMenu slidingMenu;
	private int mTitleRes;
	protected Fragment mFrag;

	public MainActivity(int titleRes) {
		mTitleRes = titleRes;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setTitle(mTitleRes);
		setContentView(R.layout.content_frame);
		setBehindContentView(R.layout.menu_frame);

		initSliding();

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
}
