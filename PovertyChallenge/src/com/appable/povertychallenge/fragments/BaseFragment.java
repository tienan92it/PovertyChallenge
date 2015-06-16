package com.appable.povertychallenge.fragments;

import com.appable.povertychallenge.SlidingActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

	private final String TAG = getClass().getName();

	protected Activity mActivity;
	protected Context mContext;
	protected View view;
	
	protected SlidingActivity mSlidingActivity;
	public Resources mRes;
	public View mRootView = null;

	public FragmentManager mFragmentManager;

	// handle mode of fragment

	public BaseFragment() {

	}

	/*
	 * called to do initial creation of the fragment.
	 * 
	 * @see android.support.v4.app.Fragment#onCreate()
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSlidingActivity = (SlidingActivity) getActivity();
		// set Handle Event For ActionBar
		mRes = mSlidingActivity.getResources();
		mFragmentManager = getFragmentManager();
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// super.onSaveInstanceState(outState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mRootView = inflater.inflate(getLayoutResource(), container, false);

		init();
		initGUI();
		initEvent();
		return mRootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = activity;
		mContext = activity.getBaseContext();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	public abstract int getLayoutResource();

	public abstract void init();

	public abstract void initGUI();

	public abstract void initEvent();

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.gc();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	protected void showDataOnLogCat(String data) {
		if (data != null) {
			Log.d(TAG, data);
		}
	}
}
