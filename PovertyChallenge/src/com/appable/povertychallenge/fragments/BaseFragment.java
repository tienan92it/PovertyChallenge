package com.appable.povertychallenge.fragments;

import com.appable.povertychallenge.MainActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

public abstract class BaseFragment extends Fragment {

	protected MainActivity mActivity;
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
		mActivity = (MainActivity) getActivity();
		// set Handle Event For ActionBar
		mRes = mActivity.getResources();
		mFragmentManager = getFragmentManager();
	}
}
