package com.sparks.instabay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.sparks.instabay.base.BaseActivity;

public class Register extends BaseActivity{
	
	private TextView txtTerm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		initViews();
		initProcess();
		initActions();
	}
	
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		txtTerm = (TextView)findViewById(R.id.txtRegisterTerm);
	}
	
	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		super.initProcess();
		
		String teks = "<b><u>Terms and Policy</u></b>";
		
		txtTerm.setText(Html.fromHtml(teks));
	}
	
	@Override
	public void initActions() {
		// TODO Auto-generated method stub
		super.initActions();
		
		txtTerm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				TermAndConditions.toTermAndConditions(Register.this);
			}
		});
	}
	
	public static void toRegister(Activity activity){
		Intent intent = new Intent(activity, Register.class);
		activity.startActivity(intent);
		
		BaseActivity.slideIn(activity);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		BaseActivity.slideOut(Register.this);
	}
}
