package com.sparks.instabay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.sparks.instabay.base.BaseActivity;

public class ConfirmRegistration extends BaseActivity{
	
	private Button btnLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_registration);
		
		initViews();
		initActions();
	}
	
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		
		btnLogin = (Button)findViewById(R.id.btnConfirmRegisLogin);
	}
	
	@Override
	public void initActions() {
		// TODO Auto-generated method stub
		super.initActions();
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Login.toLogin(ConfirmRegistration.this);
			}
		});
	}
	
	public static void toConfirmRegistration(Activity activity){
		Intent intent = new Intent(activity, ConfirmRegistration.class);
		activity.startActivity(intent);
	}
}
