package com.appable.povertychallenge.fragments;

import java.util.ArrayList;

import com.appable.povertychallenge.ListGroupsActivity;
import com.appable.povertychallenge.SplashScreenActivity;
import com.appable.povertychallenge.R;
import com.appable.povertychallenge.adapters.LeftMenuAdapter;
import com.appable.povertychallenge.models.LeftMenuInfo;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class LeftMenuFragment extends BaseFragment implements
		OnItemClickListener {
	
	private String mGroupName = null;
	private TextView tv_group_name;
	private ListView mListView;
	private LeftMenuAdapter mAdapter;
	private ArrayList<LeftMenuInfo> mAdminInfos;
	private ArrayList<LeftMenuInfo> mInfos;
	private boolean hasAdmin;

	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// mRootView = inflater.inflate(R.layout.frg_leftmenu, null);
	// return mRootView;
	// }
	//
	// @Override
	// public void onActivityCreated(Bundle savedInstanceState) {
	// super.onActivityCreated(savedInstanceState);
	// mContext = (Context) mActivity;
	// init();
	// }
	
	public LeftMenuFragment(String groupName){
		mGroupName = groupName;
	}

	public void init() {

		hasAdmin = true;

		mInfos = new ArrayList<LeftMenuInfo>();
		mAdminInfos = new ArrayList<LeftMenuInfo>();
		getAdminItems();

		// implement ListView
		tv_group_name = (TextView) mRootView.findViewById(R.id.tv_group_name);
		mListView = (ListView) mRootView.findViewById(R.id.listView);
		mListView.setOnItemClickListener(this);
		if (mAdapter == null) {
			mAdapter = new LeftMenuAdapter(getActivity());
		}

		String[] navMenuTitles;
		navMenuTitles = getResources().getStringArray(R.array.items_left_menu);
		TypedArray navMenuIcons = getResources().obtainTypedArray(
				R.array.icons_left_menu);
		TypedArray navMenuIconsHighLight = getResources().obtainTypedArray(
				R.array.icons_left_menu_highlight);
		for (int i = 0; i < navMenuTitles.length; i++) {
			LeftMenuInfo info = new LeftMenuInfo(navMenuTitles[i],
					navMenuIcons.getResourceId(i, -1),
					navMenuIconsHighLight.getResourceId(i, -1));
			if (i == 0) {
				info.setSelected(true);
			}
			mInfos.add(info);
			mAdapter.addEntry(info);
		}

		// Recycle the typed array
		navMenuIcons.recycle();
		navMenuIconsHighLight.recycle();

		// Defined Array values to show in ListView
		mListView.setAdapter(mAdapter);

		if (hasAdmin) {
			addAdminItems();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (position < mAdapter.getCount() - 1) {
			mSlidingActivity.changeMenu(position);
			changeSelected(position);
		} else {
			mSlidingActivity.onBackPressed();
			mSlidingActivity.finish();
//			mSlidingActivity.overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
		}
	}

	public void changeSelected(int selectedPos) {
		for (int i = 0; i < mAdapter.getCount(); i++) {
			LeftMenuInfo info = (LeftMenuInfo) mAdapter.getItem(i);
			if (i != selectedPos) {
				info.setSelected(false);
			} else {
				info.setSelected(true);
			}
		}
		mAdapter.notifyDataSetChanged();
		if (mSlidingActivity.getSlidingMenu().isMenuShowing()) {
			mSlidingActivity.toggle();
		}
	}

	private void getAdminItems() {
		String[] navMenuTitles;
		navMenuTitles = getResources().getStringArray(
				R.array.admin_items_left_menu);
		TypedArray navMenuIcons = getResources().obtainTypedArray(
				R.array.admin_icons_left_menu);
		TypedArray navMenuIconsHighLight = getResources().obtainTypedArray(
				R.array.admin_icons_left_menu_highlight);
		for (int i = 0; i < navMenuTitles.length; i++) {
			LeftMenuInfo info = new LeftMenuInfo(navMenuTitles[i],
					navMenuIcons.getResourceId(i, -1),
					navMenuIconsHighLight.getResourceId(i, -1));
			// if (i == 0) {
			// info.setSelected(true);
			// }
			mAdminInfos.add(info);
		}
		// Recycle the typed array
		navMenuIcons.recycle();
		navMenuIconsHighLight.recycle();
	}

	public void addAdminItems() {
		if (hasAdmin) {
			// mAdapter.removeEntry(mAdapter.getCount() - 1);
			mAdapter.insertEntries(mAdapter.getCount() - 1, mAdminInfos);
			mAdapter.notifyDataSetChanged();
			hasAdmin = true;
		}
	}

	@Override
	public int getLayoutResource() {
		// TODO Auto-generated method stub
		return R.layout.frg_leftmenu;
	}

	@Override
	public void initGUI() {
		tv_group_name.setText(mGroupName);
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub

	}
}
