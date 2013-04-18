package com.sparks.instabay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.sparks.instabay.base.BaseActivity;

public class Login extends BaseActivity{
	
	private Button btnLogin, btnRegister;
	private TextView txtForgetPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		initViews();
		initActions();
	}
	
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		
		btnLogin = (Button)findViewById(R.id.btnLoginSubmit);
		btnRegister = (Button)findViewById(R.id.btnLoginRegister);
		
		txtForgetPassword = (TextView)findViewById(R.id.txtLoginForgotPassword);
	}
	
	@Override
	public void initActions() {
		// TODO Auto-generated method stub
		super.initActions();
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Home.toHome(Login.this);
				finish();
			}
		});
		
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Register.toRegister(Login.this);
			}
		});
		
		txtForgetPassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ForgotPassword.toForgotPassword(Login.this);
			}
		});
	}
	
	public static void toLogin(Activity activity){
		Intent intent = new Intent(activity, Login.class);
		activity.startActivity(intent);
		
		BaseActivity.slideIn(activity);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		BaseActivity.slideOut(Login.this);
	}
}
