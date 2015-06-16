package com.appable.povertychallenge.dialogs;

import com.appable.povertychallenge.R;
import com.appable.povertychallenge.utils.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EmailDialog {

	public interface OnClickButtonDialogListener {
		public void onCancel();

		public void onEmailEmpty();

		public void onEmailInvalid();

		public void onCheckEmailFailure();

		public void onCheckEmailSuccess();
	}

	private OnClickButtonDialogListener listener;

	public void setOnClickButtonDialogListener(OnClickButtonDialogListener ltn) {
		listener = ltn;
	}

	private AlertDialog.Builder builder;
	private Dialog emailDialog;
	private Context mContext;
	private Activity mActivity;

	private EditText edtEmail;
	private TextView txtTitleDialog;
	private TextView txtEnterEmail;

	private String messageDialog;
	private String messageTitleDialog;
	private String error;
	private int maxLength = 100;

	public EmailDialog(Context context, Activity activity) {
		mContext = context;
		mActivity = activity;
	}

	public Dialog initEmailDialog() {
		messageDialog = mContext.getString(R.string.msg_enter_email);
		messageTitleDialog = "";
		builder = new AlertDialog.Builder(mContext);
		init();
		initGUI();
		initEvents();
		emailDialog = builder.create();
		emailDialog.setCanceledOnTouchOutside(false);
		return emailDialog;
	}

	public Dialog initEmailDialog(String messageDialog,
			String messageTitleDialog) {
		this.messageDialog = messageDialog;
		this.messageTitleDialog = messageTitleDialog;
		builder = new AlertDialog.Builder(mContext);
		init();
		initGUI();
		initEvents();
		emailDialog = builder.create();
		emailDialog.setCanceledOnTouchOutside(false);
		return emailDialog;
	}

	public Dialog initEmailDialog(String messageDialog,
			String messageTitleDialog, String error) {
		this.messageDialog = messageDialog;
		this.messageTitleDialog = messageTitleDialog;
		this.error = error;
		builder = new AlertDialog.Builder(mContext);
		init();
		initGUI();
		initEvents();
		emailDialog = builder.create();
		emailDialog.setCanceledOnTouchOutside(false);
		return emailDialog;
	}

	private void init() {
		LayoutInflater inflater = mActivity.getLayoutInflater();
		View view = inflater.inflate(R.layout.dialog_enter_email, null);
		edtEmail = (EditText) view.findViewById(R.id.edtEmail);
		txtEnterEmail = (TextView) view.findViewById(R.id.txtEnterEmail);
		txtTitleDialog = (TextView) view.findViewById(R.id.txtTitleDialog);
		builder.setView(view);
	}

	private void initGUI() {
		edtEmail.setHint(mContext.getString(R.string.str_hint_email));
		edtEmail.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		if (error != null && error.length() > 0)
			edtEmail.setError(error);
		txtTitleDialog.setText(messageTitleDialog);
		txtEnterEmail.setText(messageDialog);
		if (messageTitleDialog != null && messageTitleDialog.length() > 0)
			txtTitleDialog.setVisibility(View.VISIBLE);
	}

	private void initEvents() {
		builder.setPositiveButton(R.string.str_btn_submit,
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (listener != null) {

							String email = edtEmail.getText().toString().trim();
							if (email.length() > 0) {
								if (Utils.isValidEmail(email))
									listener.onCheckEmailSuccess();
								else
									listener.onEmailInvalid();
							} else {
								listener.onEmailEmpty();
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
