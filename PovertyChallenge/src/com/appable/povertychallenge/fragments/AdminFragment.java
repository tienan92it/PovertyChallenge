package com.appable.povertychallenge.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.appable.povertychallenge.R;
import com.appable.povertychallenge.widgets.AbstractTopBarView;

public class AdminFragment extends BaseFragment {

	private AbstractTopBarView headerView;
	private ImageView btn_menu;
	
	public void init(){
		headerView = (AbstractTopBarView) mRootView.findViewById(R.id.headerView);
		headerView.initialTopbar("Admin", true, false);
		btn_menu = (ImageView) headerView.getBtnLeft();

		btn_menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mSlidingActivity.showMenu();
			}
		});
	}

	@Override
	public int getLayoutResource() {
		// TODO Auto-generated method stub
		return R.layout.frag_admin;
	}

	@Override
	public void initGUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		
	}
	
	
}
