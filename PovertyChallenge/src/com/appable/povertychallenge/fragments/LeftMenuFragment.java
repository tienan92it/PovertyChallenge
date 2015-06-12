package com.appable.povertychallenge.fragments;

import java.util.ArrayList;

import com.appable.povertychallenge.R;
import com.appable.povertychallenge.adapters.LeftMenuAdapter;
import com.appable.povertychallenge.models.LeftMenuInfo;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class LeftMenuFragment extends BaseFragment implements OnItemClickListener{

	private ListView mListView;
	private LeftMenuAdapter mAdapter;
	private ArrayList<LeftMenuInfo> mAdminInfos;
	private ArrayList<LeftMenuInfo> mInfos;
	private boolean hasAdmin;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.frg_leftmenu, null);
		return mRootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
	}
	
	private void init() {

		mInfos = new ArrayList<LeftMenuInfo>();
		mAdminInfos = new ArrayList<LeftMenuInfo>();
		
		// implement ListView
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

	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mActivity.changeMenu(position);
		changeSelected(position);
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
		if (mActivity.getSlidingMenu().isMenuShowing()) {
			mActivity.toggle();
		}
	}
}
