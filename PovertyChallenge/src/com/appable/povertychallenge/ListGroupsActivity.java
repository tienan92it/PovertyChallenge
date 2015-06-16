package com.appable.povertychallenge;

import java.util.ArrayList;

import com.appable.povertychallenge.dialogs.EmailDialog;
import com.appable.povertychallenge.dialogs.EmailDialog.OnClickButtonDialogListener;
import com.appable.povertychallenge.dialogs.PasswordDialog;
import com.appable.povertychallenge.widgets.AbstractTopBarView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListGroupsActivity extends Activity {

	private Context mContext;
	private Activity mActivity;
	private ArrayAdapter groupAdapter;
	private ArrayList<String> lstGroups;
	private ListView lv_list_groups;
	private AlertDialog dialogIsAdmin;
	private EmailDialog emailDialog;
	private PasswordDialog enterPasswordDialog;
	private boolean isExistPassword;
	private Dialog dialogInput;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_groups);
		mContext = this;
		mActivity = this;

		init();
		initGUI();
		initEvent();
	}

	private void init() {

		lstGroups = new ArrayList<String>();
		groupAdapter = new ArrayAdapter<String>(getBaseContext(),
				android.R.layout.simple_list_item_1, lstGroups);

		isExistPassword = true;

		lv_list_groups = (ListView) findViewById(R.id.lv_list_groups);
		lv_list_groups.setAdapter(groupAdapter);

	}

	private void initGUI() {
		for (int i = 0; i < 10; i++)
			lstGroups.add("Group " + i);
	}

	private void initEvent() {
		lv_list_groups.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {

				dialogIsAdmin = new AlertDialog.Builder(mContext)
						.setMessage(getString(R.string.question_is_admin))
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
										openEmailDialog(position);
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										Intent intent = new Intent(mContext,
												SlidingActivity.class);
										intent.putExtra(
												SlidingActivity.GROUP_NAME,
												lstGroups.get(position));
										startActivity(intent);
										finish();
									}
								}).create();

				dialogIsAdmin.show();
			}
		});
	}

	private void openEmailDialog(final int position) {
		emailDialog = new EmailDialog(mContext, mActivity);
		emailDialog
				.setOnClickButtonDialogListener(new OnClickButtonDialogListener() {

					@Override
					public void onEmailInvalid() {
						dialogInput = emailDialog.initEmailDialog(
								getString(R.string.msg_enter_email),
								lstGroups.get(position),
								getString(R.string.error_invalid_email));
						dialogInput.show();
						dialogInput
								.getWindow()
								.setSoftInputMode(
										WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
					}

					@Override
					public void onEmailEmpty() {
						dialogInput = emailDialog.initEmailDialog(
								getString(R.string.msg_enter_email),
								lstGroups.get(position),
								getString(R.string.error_empty_email));
						dialogInput.show();
						dialogInput
								.getWindow()
								.setSoftInputMode(
										WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
					}

					@Override
					public void onCheckEmailSuccess() {
						dialogInput.dismiss();
						openPasswordDialog(position);
					}

					@Override
					public void onCheckEmailFailure() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onCancel() {
						// TODO Auto-generated method stub

					}
				});
		dialogInput = emailDialog.initEmailDialog(
				getString(R.string.msg_enter_email), lstGroups.get(position));
		dialogInput.show();
		dialogInput.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
	}

	private void openPasswordDialog(final int position) {
		enterPasswordDialog = new PasswordDialog(mContext, mActivity,
				isExistPassword);
		enterPasswordDialog
				.setOnClickButtonDialogListener(new PasswordDialog.OnClickButtonDialogListener() {

					@Override
					public void onPasswordEmpty() {
						dialogInput = enterPasswordDialog.initPasswordDialog(
								getString(R.string.msg_enter_password),
								lstGroups.get(position),
								getString(R.string.error_empty_password), null);
						dialogInput.show();
						dialogInput
								.getWindow()
								.setSoftInputMode(
										WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
					}

					@Override
					public void onConfirmPasswordNotMatch() {
						dialogInput = enterPasswordDialog.initPasswordDialog(
								getString(R.string.msg_enter_password),
								lstGroups.get(position), null,
								getString(R.string.error_not_match_password));
						dialogInput.show();
						dialogInput
								.getWindow()
								.setSoftInputMode(
										WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
					}

					@Override
					public void onCheckPasswordSuccess() {
						Intent intent = new Intent(mContext,
								SlidingActivity.class);
						intent.putExtra(SlidingActivity.GROUP_NAME,
								lstGroups.get(position));
						startActivity(intent);
						finish();
					}

					@Override
					public void onCheckPasswordFailure() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onCancel() {
						// TODO Auto-generated method stub

					}
				});

		dialogInput = enterPasswordDialog
				.initPasswordDialog(getString(R.string.msg_enter_password),
						lstGroups.get(position));
		dialogInput.show();
		dialogInput.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
	}
}
