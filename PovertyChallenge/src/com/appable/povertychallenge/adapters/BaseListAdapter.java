package com.appable.povertychallenge.adapters;

import java.util.ArrayList;

import com.appable.povertychallenge.interfaces.IList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class BaseListAdapter<E> extends BaseAdapter implements
		IList<E> {
	public abstract int getItemLayoutRes();

	public abstract void onBindData(int position, View itemView,
			View convertView);

	protected Context mContext;
	protected ArrayList<E> mEntries;
	protected LayoutInflater mLayoutInflater;

	private boolean isBusy = false;

	public BaseListAdapter(Context context) {
		this.mContext = context;
		this.mEntries = new ArrayList<E>();
		this.mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mEntries.size();
	}

	@Override
	public Object getItem(int position) {
		return mEntries.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemView;
		if (convertView == null) {
			itemView = mLayoutInflater.inflate(getItemLayoutRes(), parent,
					false);
		} else {
			itemView = convertView;
		}
		onBindData(position, itemView, convertView);
		return itemView;
	}

	public Context getContext() {
		return mContext;
	}

	public Resources getResources() {
		return mContext.getResources();
	}

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	// ===========================================
	// =============>IList interface<=============
	// ===========================================
	@Override
	public void addEntries(ArrayList<E> entries) {
		if (entries != null) {
			mEntries.addAll(entries);
			onDataChange();
		}
	}

	@Override
	public void addEntry(E entry) {
		if (entry != null) {
			mEntries.add(entry);
			onDataChange();
		}
	}

	@Override
	public void removeEntry(E entry) {
		if (entry != null) {
			mEntries.remove(entry);
			onDataChange();
		}
	}

	@Override
	public void clear() {
		mEntries.clear();
	}

	@Override
	public ArrayList<E> getEntries() {
		return mEntries;
	}

	protected void onDataChange() {

	}

	@Override
	public void removeEntry(int position) {
		mEntries.remove(position);
	}
}
