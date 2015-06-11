package com.appable.povertychallenge.fragments;

import com.appable.povertychallenge.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class LeftMenuFragment extends BaseFragment implements OnItemClickListener{

	private ListView mListView;
//	private LeftMenuAdapter mAdapter;
//	private ArrayList<LeftMenuInfo> mAdminInfos;
//	private ArrayList<LeftMenuInfo> mInfos;
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
//		init();
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

}
