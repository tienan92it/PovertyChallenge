package com.appable.povertychallenge.dialogs;

import com.appable.povertychallenge.R;
import com.appable.povertychallenge.utils.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class PasswordDialog {

	public interface OnClickButtonDialogListener {
		public void onCancel();

		public void onPasswordEmpty();

		public void onConfirmPasswordNotMatch();

		public void onCheckPasswordFailure();

		public void onCheckPasswordSuccess();
	}

	private OnClickButtonDialogListener listener;

	public void setOnClickButtonDialogListener(OnClickButtonDialogListener ltn) {
		listener = ltn;
	}

	private AlertDialog.Builder builder;
	private Dialog passwordDialog;
	private Context mContext;
	private Activity mActivity;
	private boolean isExistPassword;

	private EditText edtPassword;
	private EditText edtConfirmPassword;
	private TextView txtTitleDialog;
	private TextView txtEnterPassword;

	private String messageDialog;
	private String messageTitleDialog;
	private String errorPassword;
	private String errorConfirmPassword;
	private int maxLength = 100;

	public PasswordDialog(Context context, Activity activity,
			boolean isExistPassword) {
		mContext = context;
		mActivity = activity;
		this.isExistPassword = isExistPassword;
	}

	public Dialog initPasswordDialog() {
		messageDialog = mContext.getString(R.string.msg_enter_password);
		messageTitleDialog = "";
		builder = new AlertDialog.Builder(mContext);
		init();
		initGUI();
		initEvents();
		passwordDialog = builder.create();
		passwordDialog.setCanceledOnTouchOutside(false);
		return passwordDialog;
	}

	public Dialog initPasswordDialog(String messageDialog,
			String messageTitleDialog) {
		this.messageDialog = messageDialog;
		this.messageTitleDialog = messageTitleDialog;
		builder = new AlertDialog.Builder(mContext);
		init();
		initGUI();
		initEvents();
		passwordDialog = builder.create();
		passwordDialog.setCanceledOnTouchOutside(false);
		return passwordDialog;
	}

	public Dialog initPasswordDialog(String messageDialog,
			String messageTitleDialog, String errorPassword,
			String errorConfirmPassword) {
		this.messageDialog = messageDialog;
		this.messageTitleDialog = messageTitleDialog;
		this.errorPassword = errorPassword;
		this.errorConfirmPassword = errorConfirmPassword;
		builder = new AlertDialog.Builder(mContext);
		init();
		initGUI();
		initEvents();
		passwordDialog = builder.create();
		passwordDialog.setCanceledOnTouchOutside(false);
		return passwordDialog;
	}

	private void init() {
		LayoutInflater inflater = mActivity.getLayoutInflater();
		View view = inflater.inflate(R.layout.dialog_enter_password, null);
		edtPassword = (EditText) view.findViewById(R.id.edtPassword);
		edtConfirmPassword = (EditText) view
				.findViewById(R.id.edtConfirmPassword);
		txtEnterPassword = (TextView) view.findViewById(R.id.txtEnterPassword);
		txtTitleDialog = (TextView) view.findViewById(R.id.txtTitleDialog);
		builder.setView(view);
	}

	private void initGUI() {
		edtPassword.setHint(mContext.getString(R.string.str_hint_password));
		edtPassword.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		if (errorPassword != null && errorPassword.length() > 0)
			edtPassword.setError(errorPassword);

		edtConfirmPassword.setHint(mContext
				.getString(R.string.str_hint_confirm_password));
		edtConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		if (errorConfirmPassword != null && errorConfirmPassword.length() > 0)
			edtConfirmPassword.setError(errorConfirmPassword);
		if (!isExistPassword) {
			edtConfirmPassword.setVisibility(View.VISIBLE);
			edtPassword.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		}

		txtTitleDialog.setText(messageTitleDialog);
		txtEnterPassword.setText(messageDialog);
		if (messageTitleDialog != null && messageTitleDialog.length() > 0)
			txtTitleDialog.setVisibility(View.VISIBLE);
	}

	private void initEvents() {
		builder.setPositiveButton(R.string.str_btn_submit,
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (listener != null) {

							String password = edtPassword.getText().toString()
									.trim();
							String confirmPassword = edtConfirmPassword
									.getText().toString().trim();
							if (password.length() > 0) {
								if (isExistPassword)
									listener.onCheckPasswordSuccess();
								else if (password.compareTo(confirmPassword) == 0)
									listener.onCheckPasswordSuccess();
								else
									listener.onConfirmPasswordNotMatch();
							} else {
								listener.onPasswordEmpty();
							}
						}
					}
				});

		builder.setNegativeButton(R.string.str_btn_cancel,
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						if (listener != null)
							listener.onCancel();
					}
				});
	}

}
