package com.sparks.instabay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.sparks.instabay.base.BaseActivity;

public class TermAndConditions extends BaseActivity{
	
	private WebView wbTerm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.term_and_conditions);
		
		initViews();
		initProcess();
	}
	
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		wbTerm = (WebView)findViewById(R.id.wbTermAndConditions);
	}
	
	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		super.initProcess();
		
		wbTerm.loadUrl("file:///android_asset/term_and_conditions.html");
	}
	
	public static void toTermAndConditions(Activity activity){
		Intent intent = new Intent(activity, TermAndConditions.class);
		activity.startActivity(intent);
		
		BaseActivity.slideIn(activity);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		BaseActivity.slideOut(TermAndConditions.this);
	}
}
