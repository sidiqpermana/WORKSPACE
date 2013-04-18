package com.sparks.instabay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sparks.instabay.base.BaseActivity;

public class ForgotPassword extends BaseActivity {
	//private WebView wbTerm;
	private EditText edtEmail;
	private TextView txtTitle;
	private Button btnSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgot_password);

		initViews();
		initProcess();
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		//wbTerm = (WebView) findViewById(R.id.wbTermAndConditions);
		
		txtTitle = (TextView)findViewById(R.id.txtForgotPasswordTitle);
		edtEmail = (EditText)findViewById(R.id.edtForgotPasswordEmail);
		btnSubmit = (Button)findViewById(R.id.btnForgotPasswordSubmit);
		
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		super.initProcess();

		//wbTerm.loadUrl("file:///android_asset/forgot_pass.html");
		
		String teksForgotPassword = "Enter the Email Address<br>associated with your account";
		txtTitle.setText(Html.fromHtml(teksForgotPassword));
		
		
	}

	public static void toForgotPassword(Activity activity) {
		Intent intent = new Intent(activity, ForgotPassword.class);
		activity.startActivity(intent);

		BaseActivity.slideIn(activity);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		BaseActivity.slideOut(ForgotPassword.this);
	}
}