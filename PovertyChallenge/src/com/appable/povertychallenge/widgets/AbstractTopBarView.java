package com.appable.povertychallenge.widgets;

import com.appable.povertychallenge.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public abstract class AbstractTopBarView extends RelativeLayout {

	protected LayoutInflater mInflater;
	protected Context mContext;

	protected TextView tv_title;
	protected View bt_left;
	protected View bt_right;
	protected View mLayout;

	protected abstract int getLayoutID();

	public AbstractTopBarView(Context context) {
		super(context);
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mLayout = mInflater.inflate(getLayoutID(), this);

		if (!isInEditMode()) {
			init();
		}
	}

	public View getTitle() {
		return tv_title;
	}

	public View getBtnRight() {
		return bt_right;
	}

	public View getBtnLeft() {
		return bt_left;
	}

	public AbstractTopBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mLayout = mInflater.inflate(getLayoutID(), this);

		if (!isInEditMode()) {
			init();
		}
	}

	public AbstractTopBarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mLayout = mInflater.inflate(getLayoutID(), this);

		if (!isInEditMode()) {
			init();
		}
	}

	private void init() {
		tv_title = (TextView) mLayout.findViewById(R.id.tv_title);
		bt_left = mLayout.findViewById(R.id.bt_left);
		bt_right = mLayout.findViewById(R.id.bt_right);

		initEvent();
	}

	private void initEvent() {
		bt_right.addOnLayoutChangeListener(new OnLayoutChangeListener() {

			@Override
			public void onLayoutChange(View v, int left, int top, int right,
					int bottom, int oldLeft, int oldTop, int oldRight,
					int oldBottom) {
				// TODO Auto-generated method stub
				int width = Math.max((right - left), bt_left.getMeasuredWidth());
				tv_title.setPadding(width, tv_title.getPaddingTop(), width,
						tv_title.getPaddingBottom());
			}
		});

		bt_left.addOnLayoutChangeListener(new OnLayoutChangeListener() {

			@Override
			public void onLayoutChange(View v, int left, int top, int right,
					int bottom, int oldLeft, int oldTop, int oldRight,
					int oldBottom) {
				// TODO Auto-generated method stub
				int width = Math.max((right - left),
						bt_right.getMeasuredWidth());

				tv_title.setPadding(width, tv_title.getPaddingTop(), width,
						tv_title.getPaddingBottom());
			}
		});
	}

	public void setTopBarTextSize(float size) {
		tv_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
	}

	public void initialTopbar(CharSequence title, boolean isShowLeftBtn,
			boolean isShowRightBtn) {
		tv_title.setText(title);
		bt_left.setVisibility(isShowLeftBtn ? View.VISIBLE : View.GONE);
		bt_right.setVisibility(isShowRightBtn ? View.VISIBLE : View.GONE);
	}

	// public void initialTopbar(String title, boolean isShowLeftBtn, boolean
	// isShowRightBtn)
	// {
	// tv_title.setText(title);
	// bt_left.setVisibility(isShowLeftBtn ? View.VISIBLE : View.GONE);
	// bt_right.setVisibility(isShowRightBtn ? View.VISIBLE : View.GONE);
	// }

}
