package com.appable.povertychallenge.adapters;

import com.appable.povertychallenge.R;
import com.appable.povertychallenge.models.LeftMenuInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LeftMenuAdapter extends BaseListAdapter<LeftMenuInfo> {

	public LeftMenuAdapter(Context context) {
		super(context);

	}

	@Override
	public int getItemLayoutRes() {

		return R.layout.item_leftmenu;
	}

	private class ViewHolder {
		public TextView tvTitle;
		public ImageView icon;
		public View adminGroup;
		public View line;
		public View ivRed;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemView;
		itemView = mLayoutInflater.inflate(getItemLayoutRes(), parent, false);
		onBindData(position, itemView, convertView);
		return itemView;
	}

	@Override
	public void onBindData(int position, View itemView, View convertView) {
		ViewHolder vh = null;
		vh = new ViewHolder();
		vh.tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
		vh.icon = (ImageView) itemView.findViewById(R.id.ivIcon);
		vh.adminGroup = itemView.findViewById(R.id.adminGroup);
		vh.line = itemView.findViewById(R.id.lineGreen);
//		vh.ivRed = itemView.findViewById(R.id.ivRed);
		
		if (getItem(position) instanceof LeftMenuInfo) {
			LeftMenuInfo info = (LeftMenuInfo) getItem(position);
			vh.tvTitle.setText(info.getTitle());
			if (info.isSelected()) {
				vh.tvTitle.setTextColor(getResources().getColor(
						R.color.wf_green));
				vh.icon.setImageResource(info.getHightLightIcon());
			} else {
				vh.tvTitle.setTextColor(getResources().getColor(
						R.color.menu_title_selector));
				vh.icon.setImageResource(info.getIcon());
			}
			
//			if(info.isShowRedBubble()){
//				vh.ivRed.setVisibility(View.VISIBLE);
//			}else{
//				vh.ivRed.setVisibility(View.INVISIBLE);
//			}
		}

		// check admin
		if (getCount() > 11 && position == 10) {
			vh.adminGroup.setVisibility(View.VISIBLE);
			vh.line.setVisibility(View.GONE);
		}
	}

}
